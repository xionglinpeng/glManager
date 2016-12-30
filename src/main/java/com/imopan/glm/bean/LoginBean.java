package com.imopan.glm.bean;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;

public class LoginBean {

	@NotNull(message="\u90AE\u7BB1\u4E0D\u80FD\u4E3A\u7A7A\u3002")
	@Email(message="\u8BF7\u8F93\u5165\u5408\u6CD5\u7684\u90AE\u7BB1\u3002")
	private String email;
	@NotNull(message="\u5BC6\u7801\u4E0D\u80FD\u4E3A\u7A7A")
	private String password;
	private String validCode;
	private Boolean autoLogin;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getValidCode() {
		return validCode;
	}
	public void setValidCode(String validCode) {
		this.validCode = validCode;
	}
	public Boolean getAutoLogin() {
		return autoLogin;
	}
	public void setAutoLogin(Boolean autoLogin) {
		this.autoLogin = autoLogin;
	}
	@Override
	public String toString() {
		return "LoginBean [email=" + email + ", password=" + password + ", validCode=" + validCode + "]";
	}
	
	
}
