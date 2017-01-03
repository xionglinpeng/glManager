package com.imopan.glm.service;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.imopan.glm.bean.LoginBean;

/**
 * <p>这个接口是用于对登录业务的操作。</p>
 * @author xlp
 *
 */
public interface ILoginService {
	
	/**
	 * <p>登录业务处理，验证用户是否登录成功。如果用户选择了自动登录，则发送cookie。</p>
	 * @param loginBean 登录相关的参数。
	 * @param result 存储返回至客户端的登陆结果的信息。
	 * @param session 用户操作自动的登陆的事件。
	 * @param response 自动登录发送cookie。
	 * @return
	 * @throws Exception
	 */
	public Map<String,String> loginService(LoginBean loginBean,Map<String,String> result,
			HttpSession session,HttpServletResponse response) throws Exception;
}
