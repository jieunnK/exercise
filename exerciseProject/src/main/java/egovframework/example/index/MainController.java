package egovframework.example.index;

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
		
		return "exercise/index/main";
	}
	
	
	
	
}
