package com.imopan.glm.action;

import java.security.Key;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.imopan.glm.bean.LoginBean;
import com.imopan.glm.bean.ResultBean;
import com.util.utils.crypt.RSACrypt;

@Controller
@RequestMapping("/login")
public class LoginAction {
	
	private static final Logger logger = LogManager.getLogger(LoginAction.class);
	
	private String logFormat(String methodName){
		return "method:<"+methodName+">->";
	}
	
	/**
	 * <p>登录密码加密的公钥。</p>
	 * @param session 用于存储解密秘钥。
	 * @throws Exception 
	 */
	@RequestMapping(value="/publicKey",method=RequestMethod.GET)
	@ResponseBody
	public ResultBean publicKey(HttpSession session) throws Exception{
		Map<String,Key> map = RSACrypt.getSecretKey();
		//将私钥存储至session中
		session.setAttribute(RSACrypt.PRIVATE_KEY, RSACrypt.getPrivateKey(map));
		//返回公钥
		return new ResultBean(RSACrypt.getPublicKeyStr(map));
	}
	
	/**
	 * <p>登录。</p>
	 */
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ResponseBody
	public ResultBean login(@Validated @RequestBody LoginBean loginBean,BindingResult bindingResult){
		if(bindingResult.getErrorCount()>0){
			for(FieldError fieldError : bindingResult.getFieldErrors()){
				logger.info(logFormat("login")+"validated faild: Field<"+fieldError.getField()+"> "+fieldError.getDefaultMessage());
			}
			return new ResultBean("validated faild!");
		}
		
		
		
		return new ResultBean("OK");
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
