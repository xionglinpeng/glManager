package com.imopan.glm.service;

import java.util.Map;

import javax.servlet.http.HttpSession;

import com.imopan.glm.bean.LoginBean;
import com.imopan.glm.bean.ResultBean;

/**
 * <p>这个接口是用于对登录业务的操作。</p>
 * @author xlp
 *
 */
public interface ILoginService {
	
	/**
	 * <p>登录业务处理，验证用户是否登录成功。如果用户选择了自动登录，则发送cookie。</p>
	 * @param loginBean 登录相关的参数。
	 * @param session 用户操作自动的登陆的事件。
	 * @param result 存储返回至客户端的登陆结果的信息。
	 * @return
	 */
	public Map<String,String> loginService(LoginBean loginBean,HttpSession session,Map<String,String> result);
}
