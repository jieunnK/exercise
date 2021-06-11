package egovframework.web.login;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import egovframework.common.controller.CommonController;
import egovframework.model.user.UserVO;
import egovframework.service.user.UserService;

@Controller
public class LoginController extends CommonController{
	private static final Logger LOGGER = LogManager.getLogger(LoginController.class.getName());
	
	@Resource(name="userService")
	private UserService userService;
	
	//로그인페이지 이동
	@RequestMapping(value = "/login.do")
	public String login(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		HttpSession session  = request.getSession(true);
		UserVO userVO = null;
		if (session != null && session.getAttribute("userVO") != null) {
			userVO = (UserVO) session.getAttribute("userVO");
		}
		
		if(userVO!=null){//로그인 중이면 메인으로
			return "exercise/index/main";
		}
		
		String returnUrl = request.getParameter("returnUrl") != null ? request.getParameter("returnUrl") : "";
		if(!returnUrl.equals("")) {
			model.addAttribute("returnUrl", returnUrl);
		}
		model.addAttribute("title", "login");
		model.addAttribute("mode", "login");
		
		return "exercise/login/login";
	}
	
	@RequestMapping(value = "/login/{mode}/action.do")
	public String action(HttpServletRequest request, HttpServletResponse response, ModelMap model,@PathVariable String mode,UserVO userVO) throws Exception {
		
		String loginId = userVO.getUserID();
		String password = userVO.getUserPassword();
		String returnUrl = request.getParameter("returnUrl") != null && !request.getParameter("returnUrl").equals("") ? request.getParameter("returnUrl") : "";
		
		String viewPath = "exercise/common/result";//액션 결과 처리페이지
		model.addAttribute("type","2");
		
		if(!"login".equals(mode) && !"logout".equals(mode)){
			model.addAttribute("type","3");//back
			model.addAttribute("message","잘못된 접근입니다.");	
			return viewPath;
		}	
		
		
		
		if("login".equals(mode)) {
			if((loginId == null || loginId.equals("")) || (password == null || password.equals(""))) {
				model.addAttribute("type","2");
				model.addAttribute("message","필수값이 없습니다.");
				model.addAttribute("url","/login.do");
				return viewPath;
			}
			
			String userUid = userService.login(userVO);
					
			if(userUid != null) {
				model.addAttribute("type", "6");
				
				if(!returnUrl.equals("")) {
					model.addAttribute("url", returnUrl);
				}else {
					model.addAttribute("url", "/main.do");
				}
				
						
				LOGGER.info("User LOGIN ============================ "+userVO.getUserID());
				userVO.setUserUid(userUid);
			
				UserVO user = userService.oneData(userVO);
				HttpSession session  = request.getSession(true);
				session.setAttribute("userVO", user);
				model.addAttribute("userVO", user);
				
				return viewPath;
			}else {
				model.addAttribute("type","2");
				model.addAttribute("message","아이디와 비밀번호가 잘못입력되었습니다.\\r\\n다시 확인해주시기 바랍니다.");
				model.addAttribute("url","/login.do");
				return viewPath;
			}
		}else if("logout".equals(mode)) {
			//로그인 세션 삭제
			HttpSession session  = request.getSession(true);
			UserVO user = new UserVO();
			user.setUserName("session remove");
			if (session != null && session.getAttribute("userVO") != null) {
				user = (UserVO) session.getAttribute("userVO");
			}
			
			session.removeAttribute("userVO");
			session.invalidate();
			LOGGER.info("User LOGOUT ============================ ");
			
			model.addAttribute("type","6");//메시지 없이 바로 이동
			model.addAttribute("url","/main.do");		
			
			return viewPath;
		}
		return viewPath;
	}

	
}
