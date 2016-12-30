package com.imopan.glm.service;

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
	 */
	public void loginService(LoginBean loginBean);
}
