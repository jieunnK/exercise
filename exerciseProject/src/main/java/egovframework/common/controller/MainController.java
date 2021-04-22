package egovframework.common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	
	//메인
	@RequestMapping(value = "/main.do")
	public String main(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		
		return "exercise/common/main";
	}
	
	//로그인페이지 이동
	@RequestMapping(value = "/login.do")
	public String login(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		
		return "exercise/common/login";
	}
	
	
}
