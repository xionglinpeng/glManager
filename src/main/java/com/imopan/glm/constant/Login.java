package com.imopan.glm.constant;

/**
 * <p>这个枚举用户标记登录过程中所用到的一些公共的数据，例如session的键值和值等。</p>
 * @author xlp
 *
 */
public class Login {
	
	/**
	 * <p>管理后台用户登录成功标记的session键：{@value}。</p>
	 */
	public static final String LOGIN_SUNCCESS_MARK_KEY = "loginSunccessMark";
	
	/**
	 * <p>管理后台用户登录成功标记的session值：{@value}。</p>
	 */
	public static final String LOGIN_SUNCCESS_MARK_VALUE = "already login success";
	
	/**
	 * <p>自动登录AES加密key：{@value}</p>
	 */
	public static final String AUTO_LOGIN_SECRE_KEY = "ASDASDASDasdwsvsdv^%2342134#245vsdfv243t~1`";
	/**
	 * <p>自动登录cookie键：{@value}</p>
	 */
	public static final String AUTO_LOGIN_COOKIE_KEY = "autoPassword";
	
}
