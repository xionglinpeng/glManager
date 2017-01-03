package com.imopan.glm.action;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.imopan.glm.constant.Login;

@Controller
public class IndexAction {
	
	@RequestMapping(value="/glm",method=RequestMethod.GET)
	public String indexPage(HttpSession session){
		String indexPage = "index";
		//获取用户已经成功登录的标记
		Object loginSunccessMark = session.getAttribute(Login.LOGIN_SUNCCESS_MARK_KEY);
		//没有取到标记，或者标记不正确，重定向至登录页
		if(loginSunccessMark==null||!loginSunccessMark.toString()
				.equals(Login.LOGIN_SUNCCESS_MARK_VALUE)){
			indexPage = "login_module/login";
		}
		return indexPage;
	}
}
