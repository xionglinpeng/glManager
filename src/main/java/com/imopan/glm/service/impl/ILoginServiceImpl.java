package com.imopan.glm.service.impl;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imopan.glm.bean.LoginBean;
import com.imopan.glm.constant.Login;
import com.imopan.glm.entity.User;
import com.imopan.glm.service.ILoginService;
import com.util.utils.crypt.AESCrypt;

@Service
public class ILoginServiceImpl implements ILoginService {
	
	
	@Autowired
	private Datastore datastore;
	@Override
	public Map<String, String> loginService(LoginBean loginBean, Map<String, String> result,
			HttpSession session,HttpServletResponse response) throws Exception {
		//查询登录用户的数据
		Query<User> quser = datastore.createQuery(User.class).field("email").equal(loginBean.getEmail());
		User user = quser.get();
		//比较密码
		if(DigestUtils.md5Hex(loginBean.getPassword()+user.getSalt())
				.equals(user.getPassword())){
			//标记用户登录状态
			session.setAttribute(Login.LOGIN_SUNCCESS_MARK_KEY, Login.LOGIN_SUNCCESS_MARK_VALUE);
			//处理自动登录
			if(loginBean.getAutoLogin()){
				Cookie cookie = new Cookie(Login.AUTO_LOGIN_COOKIE_KEY, AESCrypt.encrypt(loginBean.getPassword(), Login.AUTO_LOGIN_SECRE_KEY));
				cookie.setMaxAge(60*60*24*5);
				cookie.setDomain("localhost");
				cookie.setPath("/login/publicKey");
				cookie.setHttpOnly(true);
				response.addCookie(cookie);
			}
			result.put("situation", "login success!");
		}else{
			result.put("failMsg", "password error!");
		}
		return result;
	}



}
