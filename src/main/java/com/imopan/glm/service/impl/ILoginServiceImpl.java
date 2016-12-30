package com.imopan.glm.service.impl;

import com.imopan.glm.bean.LoginBean;
import com.imopan.glm.service.ILoginService;

public class ILoginServiceImpl implements ILoginService {

	@Override
	public void loginService(LoginBean loginBean) {
		//用户登录验证
		
		
		if(loginBean.getAutoLogin()){
			
		}

	}

}
