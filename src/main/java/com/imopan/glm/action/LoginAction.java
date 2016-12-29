package com.imopan.glm.action;

import java.security.Key;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.imopan.glm.bean.ResultBean;
import com.util.utils.crypt.RSACrypt;

@Controller
@RequestMapping("/login")
public class LoginAction {
	
	
	/**
	 * <p>登录密码加密的公钥。</p>
	 * @param session 用于存储解密秘钥。
	 */
	@RequestMapping(value="/publicKey",method=RequestMethod.GET)
	@ResponseBody
	public ResultBean publicKey(HttpSession session){
		try {
			Map<String,Key> map = RSACrypt.getSecretKey();
			//将私钥存储至session中
			session.setAttribute(RSACrypt.PRIVATE_KEY, RSACrypt.getPrivateKey(map));
			//返回公钥
			return new ResultBean(RSACrypt.getPublicKeyStr(map));
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultBean(e);
		}
	}
	
	/**
	 * <p>登录。</p>
	 */
	public void login(){
		
	}
	
	/**
	 * <p>创建新账户。</p>
	 */
	public void register(){
		
	}
	
	/**
	 * <p>修改密码。</p>
	 */
	public void updatePassWord(){
		
	}
	
	/**
	 * <p>忘记密码。</p>
	 */
	public void forgetPassword(){
		
	}
}
