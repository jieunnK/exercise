package egovframework.web.user;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.common.controller.CommonController;

import egovframework.model.user.UserVO;
import egovframework.service.user.UserService;


@Controller
public class UserController extends CommonController{
	private static final Logger LOGGER = LogManager.getLogger(UserController.class.getName());
	
	@Resource(name="userService")
	private UserService userService;
	
	//회원가입 작성
	@RequestMapping(value = "/signUP/write.do")
	public String write(HttpServletRequest request, HttpServletResponse response, ModelMap model, UserVO userVO) throws Exception {
		model.addAttribute("mode", "write");
		model.addAttribute("userVO", userVO);		
		
		return "exercise/user/signUp";
	}
	
	//회원가입 작성
	@RequestMapping(value = "/signUP/{mode}/idCheck.do", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	public String loginCheck(@Valid ModelMap model,@PathVariable String mode, UserVO userVO) throws Exception {
		if(!"write".equals(mode)){
			failedJson(model);	
			return getJsonView();
		}
		
		if(userVO.getUserID() == null || userVO.getUserID().equals("")) {
			failedFocusJson(model,"아이디를 입력해주세요.","userID");
			return getJsonView();
		}
		int check = userService.idCheck(userVO);
		
		if(check > 0) {
			failedMsgJson(model, "이미 사용중인 아이디 입니다.");

		}else{
			successMsgJson(model,"사용가능한 아이디 입니다.");

		}
		
		return getJsonView();
	}
	
	//회원가입 작성
	@RequestMapping(value = "/signUP/{mode}/action.do", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	public String action(@Valid ModelMap model,@PathVariable String mode, UserVO userVO) throws Exception {
		
		if(!"write".equals(mode) && !"update".equals(mode) && !"delete".equals(mode)){
			failedJson(model);	
			return getJsonView();
		}
		
		//입력데이터 체크
		if("write".equals(mode) || "update".equals(mode)){

			Map<String,String> resultMap = getFormValidation(mode,userVO);
			if(resultMap!=null) {
				String result = resultMap.get("result")!=null?String.valueOf(resultMap.get("result")):"";
				if(!"Y".equals(result)) {
					failedFocusJson(model,resultMap.get("message")!=null?String.valueOf(resultMap.get("message")):"",resultMap.get("name")!=null?String.valueOf(resultMap.get("name")):"");
					return getJsonView();
				}
			}else{
				failedMsgJson(model,"정보의 체크가 잘못되었습니다.\r\n시스템 관리자에게 문의해주세요.");
				return getJsonView();
			}
		}
		
		if("write".equals(mode)) {
			userVO.setUserUid(UUID.randomUUID().toString());
			userVO.setUserEmail(userVO.getEmail()+"@"+userVO.getEmail2());
			userVO.setUserCheck("2");
			userVO.setUserLevel("1");
			userVO.setRegister_dt(new Date());
			userVO.setRegister_ID(userVO.getUserID());
			boolean successFlag = false;
			
			try {
				userService.insert(userVO);
				successFlag = true;
			}catch(Exception ex) {
				LOGGER.error("===========user action write error");
				ex.printStackTrace();
			}
			
			if(successFlag){
				successMsgUrlJson(model,"등록처리 되었습니다.","/main.do");
			}else {
				failedMsgJson(model, "등록처리에 실패하였습니다.\r\n다시 확인해주시기 바랍니다.");
			}
			
		
		}
		
		return getJsonView();
	}
	
	//입력데이터 체크
	public Map<String,String> getFormValidation(String mode,UserVO userVO){
		Map<String,String> returnMap = new HashMap<String,String>();
		String result = "Y";
		String name = "";
		String message = "";
				
		if("write".equals(mode) || "update".equals(mode)) {
			if(userVO==null) {
				result = "N";	name = "";	message = "입력 정보가 없습니다.";
				returnMap.put("result", result);
				returnMap.put("name", name);
				returnMap.put("message", message);				
				return returnMap;
			}
			
			if(userVO.getUserID() == null || userVO.getUserID().equals("")) {
				result = "N";	name = "userID";	message = "아이디를 입력해주시기 바랍니다.";	
			}else if(userVO.getCheck() == null || userVO.getCheck().equals("")) {
				result = "N";	name = "userID";	message = "아이디 중복체크를 해주세요.";	
			}else if(userVO.getCheck() != null && !userVO.getCheck().equals("")){
				if(userVO.getCheck().equals("false")) {
					result = "N";	name = "userID";	message = "이미 사용중인 아이디 입니다 새로운 아이디를 적어주세요.";	
				}	
			}else if(userVO.getUserPassword() == null || userVO.getUserPassword().equals("")) {
				result = "N";	name = "userPassword";	message = "패스워드를 입력해주시기 바랍니다.";	
			}else if(userVO.getUserPassword2() == null || userVO.getUserPassword2().equals("")) {
				result = "N";	name = "userPassword2";	message = "패스워드확인을 입력해주시기 바랍니다.";	
			}else if(userVO.getUserPassword() != null && !userVO.getUserPassword().equals("") && userVO.getUserPassword2() != null && !userVO.getUserPassword2().equals("")) {
				if(!userVO.getUserPassword().equals(userVO.getUserPassword2())) {
					result = "N";	name = "userPassword2";	message = "패스워드가 맞지 않습니다.";	
				}
			}else if(userVO.getZipCode() == null || userVO.getZipCode().equals("")) {
				result = "N";	name = "zipCode";	message = "우편번호를 검색해주시기 바랍니다.";	
			}else if(userVO.getUserAddr() == null || userVO.getUserAddr().equals("")) {
				result = "N";	name = "userAddr";	message = "주소를 입력해주시기 바랍니다.";	
			}else if(userVO.getUserAddr2() == null || userVO.getUserAddr2().equals("")) {
				result = "N";	name = "userAddr2";	message = "상세주소를 입력해주시기 바랍니다.";	
			}else if(userVO.getUserName() == null || userVO.getUserName().equals("")) {
				result = "N";	name = "userName";	message = "이름을 입력해주시기 바랍니다.";	
			}else if(userVO.getUserPhone() == null || userVO.getUserPhone().equals("")) {
				result = "N";	name = "userPhone";	message = "휴대폰 번호를 입력해주시기 바랍니다.";	
			}else if(userVO.getEmail() == null || userVO.getEmail().equals("")) {
				result = "N";	name = "email";	message = "이메일을 입력해주시기 바랍니다.";	
			}else if(userVO.getEmail2() == null || userVO.getEmail2().equals("")) {
				result = "N";	name = "email2";	message = "이메일을 입력해주시기 바랍니다.";	
			}
			
			
		}else {
			result = "N";	name = "";	message = "잘못된 접근입니다.";
		}
				
		returnMap.put("result", result);
		returnMap.put("name", name);
		returnMap.put("message", message);
				
		return returnMap;
	}
}

