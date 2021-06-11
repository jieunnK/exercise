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
	
	//리스트 
	@RequestMapping(value = "/{boardType}/list.do")
	public String list(HttpServletRequest request, HttpServletResponse response,@PathVariable String boardType, ModelMap model,BoardVO pageVO) throws Exception {		
		
		if("".equals(boardType) || boardType == null){
			model.addAttribute("type","3");//back
			model.addAttribute("message","잘못된 접근입니다.");
			return "exercise/common/result";
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
			model.addAttribute("boardType", boardType);
			model.addAttribute("title", "공지사항");
		}else if(boardType.equals("normal")) {
			pageVO.setBoardType("2");
			model.addAttribute("boardType", boardType);
			model.addAttribute("title", "자유게시판");			
		}else if(boardType.equals("question")) {
			pageVO.setBoardType("3");
			model.addAttribute("boardType", boardType);
			model.addAttribute("title", "문의사항");		
		}else if(boardType.equals("qna")) {
			pageVO.setBoardType("4");
			model.addAttribute("boardType", boardType);
			model.addAttribute("title", "자주묻는질문");	
		}
		
		List<BoardVO> list = boardService.list(pageVO);
		totalCount = boardService.totalCount(pageVO);
		
		if(boardType.equals("question")) {
			for(int i =0; i<list.size(); i++) {
				BoardVO boardVO = list.get(i);
				boardVO.setBoardType(list.get(i).getBoardType());
				boardVO.setDataDep(list.get(i).getIdxTmp()+"");
					
				BoardVO replyData = boardService.replyData(boardVO);
				
				if(replyData != null) {
					list.get(i).setTmpField1("답변완료");
				}else {
					list.get(i).setTmpField1("답변접수");
				}
				
			}
		}
		
		pageVO.setTotalCount(totalCount);
		
		PagingUtil paging = new PagingUtil(totalCount,pageVO.getRowCount(),pageVO.getPage(),10,"/"+boardType+"/list.do",pageVO);
			
		model.addAttribute("list", list);
		model.addAttribute("pageVO", pageVO);
		model.addAttribute("paging", paging.getPaging());
		
		return "exercise/board/list";
	}
	
	//글쓰기 
	@RequestMapping(value = "/{boardType}/write.do")
	public String write(HttpServletRequest request, HttpServletResponse response,@PathVariable String boardType, ModelMap model,BoardVO boardVO) throws Exception {		
		
		if("".equals(boardType) || boardType == null){
			model.addAttribute("type","3");//back
			model.addAttribute("message","잘못된 접근입니다.");
			return "exercise/common/result";
		}
		
		UserVO userVO = null;
		HttpSession session  = request.getSession(true);
		if (session != null && session.getAttribute("userVO") != null) {
			userVO = (UserVO) session.getAttribute("userVO");
			boardVO.setUserNicName(userVO.getUserName());
			model.addAttribute("userVO",userVO);
		}
		
		if(userVO == null && (boardType.equals("notice") || boardType.equals("qna"))) {
			model.addAttribute("type","2");
			model.addAttribute("message","로그인 후 이용가능합니다.");
			model.addAttribute("url","/login.do?returnUrl=/"+boardType+"/write.do");
			return "exercise/common/result";
		}
		
		if(userVO != null) {
			if(Integer.parseInt(userVO.getUserLevel()) <=3 && (boardType.equals("notice") || boardType.equals("qna"))) {
				model.addAttribute("type","3");//back
				model.addAttribute("message","잘못된 접근입니다.");
				return "exercise/common/result";
			}
		}
		
		model.addAttribute("mode", "write");
		model.addAttribute("dataVO", boardVO);
		model.addAttribute("pageVO", boardVO);
		
		if(boardType.equals("notice")) {
			model.addAttribute("title", "공지사항");
			model.addAttribute("boardType", boardType);
		}else if(boardType.equals("normal")) {
			model.addAttribute("title", "자유게시판");
			model.addAttribute("boardType", boardType);

		}else if(boardType.equals("question")) {
			model.addAttribute("title", "문의사항");
			model.addAttribute("boardType", boardType);
		}else if(boardType.equals("qna")) {
			model.addAttribute("title","자주묻는질문");
			model.addAttribute("boardType",boardType);
		}
	
		return "exercise/board/write";
	}
	
	//수정
	@RequestMapping(value="/{boardType}/{boardUid}/update.do")
	public String update(HttpServletRequest request,HttpServletResponse response,ModelMap model,@PathVariable String boardType,@PathVariable String boardUid,BoardVO pageVO) throws Exception {		
		if("".equals(boardType) || boardType == null){
			model.addAttribute("type","3");//back
			model.addAttribute("message","잘못된 접근입니다.");
			return "exercise/common/result";
		}
		
		String viewUid = request.getParameter("viewUid") != null ? request.getParameter("viewUid") : "";
		int division = request.getParameter("division") != null ? Integer.parseInt(request.getParameter("division")) : 0 ;
		
		
		UserVO userVO = null;
		HttpSession session  = request.getSession(true);
		if (session != null && session.getAttribute("userVO") != null) {
			userVO = (UserVO) session.getAttribute("userVO");
		}
		
		if(userVO == null && boardType.equals("notice")) {
			model.addAttribute("type","2");
			model.addAttribute("message","로그인 후 이용가능합니다.");
			model.addAttribute("url","/login.do?returnUrl=/"+boardType+"/"+boardUid+"/update.do");
			return "exercise/common/result";
		}
		
		if(userVO != null) {
			if(Integer.parseInt(userVO.getUserLevel()) <=3 && boardType.equals("notice")) {
				model.addAttribute("type","3");//back
				model.addAttribute("message","잘못된 접근입니다.");
				return "exercise/common/result";
			}
		}
		
		BoardVO boardVO = null;
		
		if(boardType.equals("notice")) {
			pageVO.setBoardType("1");	
		}else if(boardType.equals("normal")) {
			pageVO.setBoardType("2");
		}else if(boardType.equals("question")) {
			pageVO.setBoardType("3");
		}else if(boardType.equals("qna")) {
			pageVO.setBoardType("4");
		}

		int check = 0;
		try {
			
			check = boardService.passCount(pageVO);
			
			if(check > 0) {
				boardVO = boardService.getData(pageVO);
				
				if(boardVO.getBeginDate() != null && boardVO.getEndDate() != null) {
					boardVO.setBeginDateStr(boardVO.getSimpleBeginDt());
					boardVO.setEndDateStr(boardVO.getSimpleEndDt());
				}
				
			}else {
				model.addAttribute("type","2");//back
				model.addAttribute("message","비밀번호가 틀렸습니다.");
				model.addAttribute("url","/"+boardType+"/"+boardUid+"/view.do");
				return "exercise/common/result";
			}
		}catch(Exception e){
			LOGGER.error("===========board update error");
			e.printStackTrace();
			
			model.addAttribute("type","3");//back
			model.addAttribute("message","에러 발생.");
			return "exercise/common/result";
		}
		
		if(boardVO != null) {
			if(userVO != null) {
				if((Integer.parseInt(userVO.getUserLevel()) <=3) && (!userVO.getUserID().equals(boardVO.getRegisterId()))) {
					model.addAttribute("type","2");//back
					model.addAttribute("message","본인이 작성한 글만 수정이 가능합니다.");
					model.addAttribute("url","/"+boardType+"/"+boardUid+"/view.do");
					return "exercise/common/result";
				}
			}
		}else {
			model.addAttribute("type","2");//back
			model.addAttribute("message","잘못된 접근입니다.");
			model.addAttribute("url","/"+boardType+"/"+boardUid+"/view.do");
			return "exercise/common/result";
		}
		
		model.addAttribute("mode", "update");
		model.addAttribute("pageVO", pageVO);
		model.addAttribute("dataVO",boardVO);
		model.addAttribute("division", division);
		model.addAttribute("viewUid", viewUid);
		
		return "exercise/board/write";
	}
	
	//답변
	@RequestMapping(value="/{boardType}/{boardUid}/reply.do")
	public String reply(HttpServletRequest request,HttpServletResponse response,ModelMap model,@PathVariable String boardType,@PathVariable String boardUid,BoardVO pageVO) throws Exception {
		if(("".equals(boardType) || boardType == null) || ("".equals(boardUid) || boardUid == null)){
			model.addAttribute("type","3");//back
			model.addAttribute("message","잘못된 접근입니다.");
			return "exercise/common/result";
		}
		
		UserVO userVO = null;
		HttpSession session  = request.getSession(true);
		if (session != null && session.getAttribute("userVO") != null) {
			userVO = (UserVO) session.getAttribute("userVO");
			model.addAttribute("userVO",userVO);
		}
		
		if(userVO == null) {
			model.addAttribute("type","2");
			model.addAttribute("message","로그인 후 이용가능합니다.");
			model.addAttribute("url","/login.do?returnUrl=/"+boardType+"/"+boardUid+"/view.do");
			return "exercise/common/result";
		}
		
		if(userVO != null) {
			if(Integer.parseInt(userVO.getUserLevel()) <=3) {
				model.addAttribute("type","3");//back
				model.addAttribute("message","잘못된 접근입니다.");
				return "exercise/common/result";
			}
		}
		
		if(boardType.equals("question")) {
			pageVO.setBoardType("3");
			model.addAttribute("title", "문의사항 답변");	
		}
		BoardVO boardVO = boardService.getData(pageVO);
		BoardVO dataVO = new BoardVO();
		
		if(boardVO != null) {
			dataVO.setDataTitle(boardVO.getDataTitle());
			dataVO.setDataDep(boardVO.getIdxTmp()+"");
			dataVO.setUserNicName(userVO.getUserName());
		}
		
		
		model.addAttribute("mode", "reply");
		model.addAttribute("pageVO", pageVO);
		model.addAttribute("dataVO",dataVO);
		model.addAttribute("viewUid",boardVO.getBoardUid());
		model.addAttribute("division",2);
		return "exercise/board/write";		
		
	}
	
	//보기
	@RequestMapping("/{boardType}/{boardUid}/view.do")
	public String view(HttpServletRequest request, HttpServletResponse response,@PathVariable String boardType,@PathVariable String boardUid, ModelMap model,BoardVO pageVO) {
		
		if(("".equals(boardType) || boardType == null) || ("".equals(boardUid) || boardUid == null)){
			model.addAttribute("type","3");//back
			model.addAttribute("message","잘못된 접근입니다.");
			return "exercise/common/result";
		}
		
		UserVO userVO = null;
		HttpSession session  = request.getSession(true);
		if (session != null && session.getAttribute("userVO") != null) {
			userVO = (UserVO) session.getAttribute("userVO");
			model.addAttribute("userVO",userVO);
		}
		
		if(boardType.equals("notice")) {
			pageVO.setBoardType("1");					
			model.addAttribute("title", "공지사항");		
		}else if(boardType.equals("normal")){
			pageVO.setBoardType("2");
			model.addAttribute("title", "자유게시판");		
		}else if(boardType.equals("question")) {
			pageVO.setBoardType("3");
			model.addAttribute("title", "문의사항");	
			
		}else if(boardType.equals("qna")) {
			pageVO.setBoardType("4");
			model.addAttribute("titls", "자주묻는질문");
		}
		
		try {
			boardService.updateViewCount(pageVO);
			BoardVO boardVO = boardService.getData(pageVO);
			
			if(boardVO.getDataSecret() != null) {
				if(boardVO.getDataSecret().equals("Y")) {
					if(pageVO.getUserPassword() != null) {
						if(!boardVO.getUserPassword().equals(pageVO.getUserPassword())) {
							model.addAttribute("type","3");//back
							model.addAttribute("message","비밀번호가 맞지 않습니다.");
							return "/exercise/common/result";
						}
					}
				}
			}			
			if(boardVO == null) {
				model.addAttribute("type","3");//back
				model.addAttribute("message","조회된 정보가 없습니다.");
				return "/exercise/common/result";
			}else {
				model.addAttribute("pageVO", pageVO);
				model.addAttribute("boardVO", boardVO);
				model.addAttribute("boardType", boardType);
				
				if(boardType.equals("question")) {
					BoardVO replyData = boardService.replyData(boardVO);
					model.addAttribute("replyData", replyData);				
				}
				return "exercise/board/view";
			}
			
			
		} catch (Exception e) {
			LOGGER.error("===========board view error");
			e.printStackTrace();
			
			model.addAttribute("type","3");//back
			model.addAttribute("message","에러 발생.");
			return "exercise/common/result";
		}
	}
	//처리(등록,수정,삭제)
	@RequestMapping(value = "/{boardType}/{mode}/action.do", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	public String action(HttpServletRequest request,@Valid ModelMap model,@PathVariable String boardType,@PathVariable String mode,BoardVO boardVO) throws Exception {		
		if((!"write".equals(mode) && !"update".equals(mode) && !"delete".equals(mode) && !"reply".equals(mode)) ||("".equals(boardType) || boardType == null)){
			failedJson(model);	
			return getJsonView();
		}
		
		UserVO userVO = null;
		boolean successFlag = false;
		String viewUrl = request.getParameter("viewUid") != null ? request.getParameter("viewUid") : "";
		int division = request.getParameter("division") != null ? Integer.parseInt(request.getParameter("division")) : 0 ;
		
		HttpSession session  = request.getSession(true);
		if (session != null && session.getAttribute("userVO") != null) {
			userVO = (UserVO) session.getAttribute("userVO");	
		}	
		if(userVO == null && (boardType.equals("notice") || boardType.equals("qna"))){
			failedMsgUrlJson(model, "로그인 후 이용 가능합니다.","/login.do");
			return getJsonView();		
		}	
		//입력데이터 체크
		if(("write".equals(mode) && division != 2)|| ("update".equals(mode) && division != 2 )){
			Map<String,String> resultMap = getFormValidation(mode,boardType,boardVO,userVO);
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
		if("write".equals(mode) || "reply".equals(mode)) {		
			if("notice".equals(boardType)) {
				boardVO.setBoardType("1"); //공지사항
			}else if("normal".equals(boardType)) {
				boardVO.setBoardType("2"); //자유게시판	
			}else if("question".equals(boardType)) { //문의사항
				boardVO.setBoardType("3");
			}else if("qna".equals(boardType)) { //자주묻는질문
				boardVO.setBoardType("4");
			}
			
			boardVO.setBoardUid(UUID.randomUUID().toString());
			boardVO.setRegisterId(userVO == null ? boardVO.getUserNicName() : userVO.getUserID());
			boardVO.setRegisterDt(new Date());
			boardVO.setUserNicName(userVO == null ? boardVO.getUserNicName() : userVO.getUserID());
			
			//공지글 설정
			if(boardVO.getBoardDivision() != null) {
				if(boardVO.getBoardDivision().equals("1")) {
					if(boardVO.getBeginDateStr() != null && !boardVO.getBeginDateStr().equals("") && boardVO.getEndDateStr() != null && !boardVO.getEndDateStr().equals("")) {
						try {
					        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					        boardVO.setBeginDate(format.parse(boardVO.getBeginDateStr()+" 00:00:00"));
					        boardVO.setEndDate(format.parse(boardVO.getEndDateStr()+" 23:59:59"));
					        
					    } catch (Exception e) {
					    	e.printStackTrace();
					    }
					}	
				}
			}			
		}else if("update".equals(mode)) {
			if("notice".equals(boardType)) {			
				boardVO.setBoardType("1"); //공지사항				
			}else if("normal".equals(boardType)) {
				boardVO.setBoardType("2"); //자유게시판
			}else if("question".equals(boardType)) { //문의사항
				boardVO.setBoardType("3");
				
			}
			
			if(boardVO.getUserPassword() != null) {
				int check = boardService.passCount(boardVO);
				
				if(check < 1) {
					failedFocusJson(model,"비밀번호가 맞지 않습니다.","userPassword");
					return getJsonView();
				}
				
			}		
			boardVO.setUpdateId(userVO == null ? boardVO.getUserNicName() : userVO.getUserID());
			boardVO.setUpdateDt(new Date());	
			//공지글 설정
			if(boardVO.getBoardDivision() != null) {
				if(boardVO.getBoardDivision().equals("1")) {
					if(boardVO.getBeginDateStr() != null && !boardVO.getBeginDateStr().equals("") && boardVO.getEndDateStr() != null && !boardVO.getEndDateStr().equals("")) {
						try {
					        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					        boardVO.setBeginDate(format.parse(boardVO.getBeginDateStr()+" 00:00:00"));
					        boardVO.setEndDate(format.parse(boardVO.getEndDateStr()+" 23:59:59"));
					        
					    } catch (Exception e) {
					    	e.printStackTrace();
					    }
					}
				}
			}
		}else if("delete".equals(mode)){
				
			if("notice".equals(boardType)) {
				boardVO.setBoardType("1");
			}else if("normal".equals(boardType)) {
				boardVO.setBoardType("2");
			}else if("question".equals(boardType)) {
				boardVO.setBoardType("3");
				
			}
		}
		String msg = "";
		
		//db처리
		try {		
			if("write".equals(mode) || "reply".equals(mode)) {
				boardService.insert(boardVO);
				successFlag = true;
			}else if("update".equals(mode)) {
				int check = boardService.updateData(boardVO);
				successFlag = check > 0 ? true : false;
			}else if("delete".equals(mode)) {
				if(boardVO.getSeacretPassword() != null) {
					boardVO.setUserPassword(boardVO.getSeacretPassword());
				}
				
				int passCount = boardService.passCount(boardVO);
				
				if(boardVO.getSeacretPassword() != null) {
					int check = 0;
					
					if(passCount > 0) {
						check = boardService.deleteData(boardVO);
					}else {
						msg = "비밀번호가 틀렸습니다.";
					}			
					successFlag = check > 0 ? true : false;
				}else {
					boardService.deleteData(boardVO);
					successFlag = true;
				}
				
			}		
		}catch(Exception ex) {
			LOGGER.error("===========board action write error");
			ex.printStackTrace();
		}
		
		//결과값 반환
		if(successFlag){
			String tmp = "";
			String url = "/"+boardType+"/list.do";
			if("write".equals(mode) || "reply".equals(mode)) {
				tmp = "등록완료 되었습니다.";
				
				if(division == 2 && !viewUrl.equals("")) {
					url = "/"+boardType+"/"+viewUrl+"/view.do?"+boardVO.getPagingParam();
				}
			}else if("update".equals(mode)) {
				tmp = "수정완료 되었습니다.";
				
				if(division == 2 && !viewUrl.equals("")) {
					url = "/"+boardType+"/"+viewUrl+"/view.do?"+boardVO.getPagingParam();
				}
			}else if("delete".equals(mode)) {
				tmp = "삭제완료 되었습니다.";
				
				if(division == 2 && !viewUrl.equals("")) {
					url = "/"+boardType+"/"+viewUrl+"/view.do?"+boardVO.getPagingParam();
				}
			}
			successMsgUrlJson(model,tmp,url);
		}else {
			String tmp = "";
			
			if("write".equals(mode) || "reply".equals(mode)) {
				tmp = "등록처리에 실패하였습니다.\r\n다시 확인해주시기 바랍니다.";
			}else if("update".equals(mode)) {
				tmp = "수정처리에 실패하였습니다.\r\n다시 확인해주시기 바랍니다.";
			}else if("delete".equals(mode)) {
				tmp = !msg.equals("") ? msg : "삭제처리에 실패하였습니다.\r\n다시 확인해주시기 바랍니다." ;
			}
			failedMsgJson(model, tmp);
		}	
		return getJsonView();
	}
	
	//공지사항 입력데이트 체크
	public Map<String,String> getFormValidation(String mode,String boardType ,BoardVO boardVO, UserVO userVO){
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
			
			if(boardVO.getDataTitle() == null || boardVO.getDataTitle().equals("")) {
				result = "N";	name = "dataTitle";	message = "제목을 입력해주시기 바랍니다.";	
			}else if(boardVO.getUserNicName() == null || boardVO.getUserNicName().equals("")){
				result = "N";	name = "userNicName";	message = "작성자를 입력해주시기 바랍니다.";	
			}else if(userVO == null && (boardVO.getUserPassword() == null || boardVO.getUserPassword().equals(""))) {
			
				result = "N";	name = "userPassword";	message = "비밀번호를 입력해주시기 바랍니다.";	
			}else if(userVO != null && (boardVO.getBoardDivision() == null || boardVO.getBoardDivision().equals(""))) {
			
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
		}else {
			result = "N";	name = "";	message = "잘못된 접근입니다.";
		}			
		returnMap.put("result", result);
		returnMap.put("name", name);
		returnMap.put("message", message);
				
		return returnMap;
	}
}
