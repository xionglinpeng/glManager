package com.imopan.glm.service.impl;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.imopan.glm.bean.LoginBean;
import com.imopan.glm.bean.ResultBean;
import com.imopan.glm.service.ILoginService;

@Service
public class ILoginServiceImpl implements ILoginService {

	@Override
	public Map<String, String> loginService(LoginBean loginBean, HttpSession session, Map<String, String> result) {
		// TODO Auto-generated method stub
		return null;
	}



}
