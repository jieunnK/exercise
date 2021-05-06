package egovframework.web.board;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import egovframework.common.controller.CommonController;
import egovframework.common.util.PagingUtil;
import egovframework.model.board.BoardVO;
import egovframework.model.user.UserVO;
import egovframework.service.board.BoardService;
import egovframework.service.user.UserService;

@Controller
public class BoardController extends CommonController{
	private static final Logger LOGGER = LogManager.getLogger(BoardController.class.getName());
	
	@Resource(name="boardService")
	private BoardService boardService;
	
	@Resource(name="userService")
	private UserService userService;
	
	//공지사항 리스트 
	@RequestMapping(value = "/{boardType}/list.do")
	public String noticeList(HttpServletRequest request, HttpServletResponse response,@PathVariable String boardType, ModelMap model,BoardVO pageVO) throws Exception {		
		String returnUrl = "";
		
		System.out.println(pageVO.getSearchType());
		System.out.println(pageVO.getKeyword());
		if("".equals(boardType) || boardType == null){
			model.addAttribute("type","3");//back
			model.addAttribute("message","잘못된 접근입니다.");
			return "/exercise/common/result";
		}
		
		UserVO userVO = null;
		HttpSession session  = request.getSession(true);
		if (session != null && session.getAttribute("userVO") != null) {
			userVO = (UserVO) session.getAttribute("userVO");
			model.addAttribute("userVO",userVO);
		}
		
		
		long totalCount = 0;
		if(boardType.equals("notice")) {
			pageVO.setBoardType("1");
			List<BoardVO> list = boardService.list(pageVO);
			totalCount = boardService.totalCount(pageVO);
			
			pageVO.setTotalCount(totalCount);
			
			PagingUtil paging = new PagingUtil(totalCount,pageVO.getRowCount(),pageVO.getPage(),10,"/"+boardType+"/list.do",pageVO);
				
			model.addAttribute("list", list);
			model.addAttribute("pageVO", pageVO);
			model.addAttribute("paging", paging.getPaging());
			return "exercise/board/noticeList";
		}
		
		return returnUrl;
	}
	
	//공지사항 리스트 
	@RequestMapping(value = "/{boardType}/write.do")
	public String noticewrite(HttpServletRequest request, HttpServletResponse response,@PathVariable String boardType, ModelMap model,BoardVO boardVO) throws Exception {		
		String returnUrl = "";
		
		if("".equals(boardType) || boardType == null){
			model.addAttribute("type","3");//back
			model.addAttribute("message","잘못된 접근입니다.");
			return "/exercise/common/result";
		}
		
		UserVO userVO = null;
		HttpSession session  = request.getSession(true);
		if (session != null && session.getAttribute("userVO") != null) {
			userVO = (UserVO) session.getAttribute("userVO");
		}
		
		if(userVO == null) {
			model.addAttribute("type","3");//back
			model.addAttribute("message","잘못된 접근입니다.");
			return "/exercise/common/result";
		}

		if(Integer.parseInt(userVO.getUserLevel()) <=3) {
			model.addAttribute("type","3");//back
			model.addAttribute("message","잘못된 접근입니다.");
			return "/exercise/common/result";
		}
		
		
		model.addAttribute("mode", "write");
		model.addAttribute("dataVO", boardVO);
		model.addAttribute("pageVO", boardVO);
		
		if(boardType.equals("notice")) {
			
			return "exercise/board/noticewrite";
		}
	
		return returnUrl;
	}
	
	@RequestMapping(value = "/{boardType}/{mode}/action.do", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	public String action(HttpServletRequest request,@Valid ModelMap model,@PathVariable String boardType,@PathVariable String mode,BoardVO boardVO) throws Exception {		
		if((!"write".equals(mode) && !"update".equals(mode) && !"delete".equals(mode)) ||("".equals(boardType) || boardType == null)){
			failedJson(model);	
			return getJsonView();
		}
		
		
		//입력데이터 체크
		if("write".equals(mode) || "update".equals(mode)){

			Map<String,String> resultMap = getFormValidation(mode,boardType,boardVO);
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
			
			if("write".equals(mode)) {
				if("notice".equals(boardType)) {
					boardVO.setBoardUid(UUID.randomUUID().toString());
					boardVO.setBoardType("1"); //공지사항
					
					UserVO userVO = null;
					HttpSession session  = request.getSession(true);
					if (session != null && session.getAttribute("userVO") != null) {
						userVO = (UserVO) session.getAttribute("userVO");
					}else {
						failedMsgUrlJson(model, "로그인 후 이용 가능합니다.","/login.do");
						return getJsonView();
					}
					
					boardVO.setRegisterId(userVO.getUserID());
					boardVO.setRegisterDt(new Date());
					if(boardVO.getBeginDateStr() != null && !boardVO.getBeginDateStr().equals("") && boardVO.getEndDateStr() != null && !boardVO.getEndDateStr().equals("")) {
						try {
					        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					        boardVO.setBeginDate(format.parse(boardVO.getBeginDateStr()+" 00:00:00"));
					        boardVO.setEndDate(format.parse(boardVO.getEndDateStr()+" 23:59:59"));
					        
					    } catch (Exception e) {
					    	e.printStackTrace();
					    }
					}
					
					boolean successFlag = false;
					
					try {
						boardService.insert(boardVO);
						successFlag = true;
					}catch(Exception ex) {
						LOGGER.error("===========resell action write error");
						ex.printStackTrace();
					}
					
					if(successFlag){
						successMsgUrlJson(model,"등록처리 되었습니다.","/"+boardType+"/list.do");
					}else {
						failedMsgJson(model, "등록처리에 실패하였습니다.\r\n다시 확인해주시기 바랍니다.");
					}
					
				}
			}
		}
		
		return getJsonView();
	}
	
	//공지사항 입력데이트 체크
	public Map<String,String> getFormValidation(String mode,String boardType ,BoardVO boardVO){
		Map<String,String> returnMap = new HashMap<String,String>();
		String result = "Y";
		String name = "";
		String message = "";
				
		if("write".equals(mode) || "update".equals(mode)) {
			if(boardVO==null) {
				result = "N";	name = "";	message = "입력 정보가 없습니다.";
				returnMap.put("result", result);
				returnMap.put("name", name);
				returnMap.put("message", message);				
				return returnMap;
			}
			
			if(boardType.equals("notice")) {
				if(boardVO.getDataTitle() == null || boardVO.getDataTitle().equals("")) {
					result = "N";	name = "dataTitle";	message = "제목을 입력해주시기 바랍니다.";	
				}else if(boardVO.getBoardDivision() == null || boardVO.getBoardDivision().equals("")) {
					result = "N";	name = "boardDivision";	message = "공지글 설정여부를 체크해주시기 바랍니다.";	
				}else if(boardVO.getBoardDivision() != null && !boardVO.getBoardDivision().equals("") && boardVO.getBoardDivision().equals("1")) {
					if(boardVO.getBeginDateStr() == null || boardVO.getBeginDateStr().equals("")) {
						result = "N";	name = "beginDateStr";	message = "시작날짜를 입력해주시기 바랍니다.";	
					}else if(boardVO.getEndDateStr() == null || boardVO.getEndDateStr().equals("")) {
						result = "N";	name = "endDateStr";	message = "종료날짜를 입력해주시기 바랍니다.";	
					}
					
				}else if(boardVO.getDataContent() == null || boardVO.getDataContent().equals("")) {
					result = "N";	name = "dataContent";	message = "내용을 입력해주시기 바랍니다.";
				}
				
				if(boardVO.getBeginDateStr() != null && !boardVO.getBeginDateStr().equals("") && boardVO.getEndDateStr() != null && !boardVO.getEndDateStr().equals("")) {						 
			        try {
			        	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			        	Date beginDate = null;
			        	Date endDate = null;
			        	if(boardVO.getBeginDateStr() != null && !boardVO.getBeginDateStr().equals("")) {
			        		beginDate = format.parse(boardVO.getBeginDateStr());
			        	}
			        	if(boardVO.getEndDateStr() != null && !boardVO.getEndDateStr().equals("")) {
			        		endDate = format.parse(boardVO.getEndDateStr());
			        	}
			        	
			        	if(beginDate != null && endDate != null) {
			        		int compare = beginDate.compareTo(endDate);
					            
					        if(compare > 0) {
					        	result = "N";	name = "beginDateStr";	message = "시작일과 종료일 선택이 잘못되었습니다.";	
					        }
			        	}
			           
			        } catch (Exception e) {
			            e.printStackTrace();
			        }
				}
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
