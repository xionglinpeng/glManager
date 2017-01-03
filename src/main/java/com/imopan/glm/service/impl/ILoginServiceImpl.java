package com.imopan.glm.service.impl;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imopan.glm.action.LogFormart;
import com.imopan.glm.bean.LoginBean;
import com.imopan.glm.constant.Login;
import com.imopan.glm.entity.User;
import com.imopan.glm.service.ILoginService;
import com.util.utils.crypt.AESCrypt;

@Service
public class ILoginServiceImpl implements ILoginService {
	
	private static final Logger logger = LogManager.getLogger(ILoginServiceImpl.class);
	
	@Autowired
	private Datastore datastore;
	@Override
	public Map<String, String> loginService(LoginBean loginBean, Map<String, String> result,
			HttpSession session,HttpServletResponse response) throws Exception {
		//查询登录用户的数据
		Query<User> quser = datastore.createQuery(User.class).field("email").equal(loginBean.getEmail());
		User user = quser.get();
		//比较密码
		if(user!=null&&DigestUtils.md5Hex(loginBean.getPassword()+user.getSalt())
				.equals(user.getPassword())){
			//标记用户登录状态
			session.setAttribute(Login.LOGIN_SUNCCESS_MARK_KEY, Login.LOGIN_SUNCCESS_MARK_VALUE);
			//存储email，用于管理后台界面使用
			session.setAttribute(Login.USER_EMAIL_KEY, user.getEmail());
			//处理自动登录
			if(loginBean.getAutoLogin()){
				sendAutoLoginCookie(Login.AUTO_LOGIN_COOKIE_PASSWORD_KEY, 
						AESCrypt.encrypt(loginBean.getPassword(), Login.AUTO_LOGIN_SECRE_KEY),response);
				sendAutoLoginCookie(Login.AUTO_LOGIN_COOKIE_USERNAME_KEY, loginBean.getEmail(),response);
			}
			result.put("situation", "login success!");
		}else{
			logger.info(LogFormart.logFormat("loginService")+"login error : login username error or password error.");
			result.put("failMsg", "login error!");
		}
		return result;
	}
	
	private void sendAutoLoginCookie(String key,String value,HttpServletResponse response){
		Cookie cookie = new Cookie(key,value);
		cookie.setMaxAge(60*60*24*5);
		cookie.setDomain("localhost");
		cookie.setPath("/login/publicKey");
		cookie.setHttpOnly(true);
		response.addCookie(cookie);
	}


}
