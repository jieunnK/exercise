package egovframework.common.controller;

import javax.servlet.http.HttpServlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.ui.ModelMap;

public abstract class CommonController extends HttpServlet{
	private static final Logger LOGGER = LogManager.getLogger(CommonController.class.getName());
	
	//servlet에 설정된 json 전용 뷰
	protected String getJsonView() 
	{
		return "jsonView";
	}
		
	//잘못된 접근처리 해당 URL 이동
	protected void accessErrorUrlResult(ModelMap model,String url) {
		model.addAttribute("type","2");//이동
		model.addAttribute("message","잘못된 접근입니다.");	
		model.addAttribute("url",url);	
	}
		
	//잘못된 접근처리 해당 이전으로 이동
	protected void accessErrorBackResult(ModelMap model) {
		model.addAttribute("type","3");//이전으로
		model.addAttribute("message","잘못된 접근입니다.");
	}	
		
	//알림출려후 해당 URL 이동
	protected void msgUrlResult(ModelMap model,String msg,String url) {
		model.addAttribute("type","2");//이동
		model.addAttribute("message",msg);	
		model.addAttribute("url",url);	
	}
	//알림출려후 해당 이전으로 이동
	protected void msgBackResult(ModelMap model,String msg) {
		model.addAttribute("type","3");//이동
		model.addAttribute("message",msg);
	}
		
		
	//json 잘못된 접근처리 해당 URL 이동
	protected void failedMsgJson(ModelMap model,String msg) {
		model.addAttribute("code",999);//실패 알림 출력후
		model.addAttribute("message",msg);	
	}	
	//json 잘못된 접근처리 해당 이전으로 이동
	protected void failedJson(ModelMap model) {
		model.addAttribute("code",999);//에러
		model.addAttribute("message","잘못된 접근입니다.");
	}	
	//json 입력란에 포커스처리
	protected void failedFocusJson(ModelMap model,String msg,String name) {
		model.addAttribute("code",Integer.valueOf(555));//입력란에 포커스처리
		model.addAttribute("message",msg);
		model.addAttribute("name",name);
	}	
	//json 알림출려후 해당 URL 이동
	protected void successMsgUrlJson(ModelMap model,String msg,String url) {
		model.addAttribute("code",200);//성공 처리후 이동
		model.addAttribute("message",msg);	
		model.addAttribute("url",url);	
	}
	//json 잘못된 접근처리 해당 URL 이동
	protected void failedMsgUrlJson(ModelMap model,String msg,String url) {
		model.addAttribute("code",998);//실패 알림 출력후 이동
		model.addAttribute("message",msg);	
		model.addAttribute("url",url);	
	}
	//json 알림출려후 해당 이전으로 이동
	protected void successMsgJson(ModelMap model,String msg) {
		model.addAttribute("code",200);//성공 알림출력
		model.addAttribute("message",msg);
	}
}
