package com.imopan.glm.action;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexAction {
	
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public String indexPage(HttpSession session){
		String indexPage = "index";
		//获取用户已经成功登录的标记
		Object loginSunccessMark = session.getAttribute("loginSunccessMark");
		//没有取到标记，或者标记不正确，重定向至登录页
		if(loginSunccessMark==null||!loginSunccessMark.toString().equals("already login success")){
			indexPage = "login_module/login";
		}
		return indexPage;
	}
}
