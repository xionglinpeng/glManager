package com.imopan.glm.action;

import java.security.Key;
import java.security.PrivateKey;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.code.kaptcha.Constants;
import com.imopan.glm.bean.LoginBean;
import com.imopan.glm.bean.ResultBean;
import com.imopan.glm.service.ILoginService;
import com.util.utils.crypt.RSACrypt;

@Controller
@RequestMapping("/login")
public class LoginAction {
	
	private static final Logger logger = LogManager.getLogger(LoginAction.class);
	
	@Autowired
	private ILoginService iLoginService;
	
	
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
	 * @throws Exception 
	 */
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ResponseBody
	public ResultBean login(@Validated @RequestBody LoginBean loginBean,BindingResult bindingResult,HttpSession session){
		//用于存储返回到客户端的结果集
		Map<String,String> result = new HashMap<>();
		result.put("situation", "login fail!");//默认是登录失败，只有到最后一步才是登录成功。
		//验证请求数据是否正常
		if(bindingResult.getErrorCount()>0){
			for(FieldError fieldError : bindingResult.getFieldErrors()){
				logger.info(logFormat("login")+"validated faild: Field<"+fieldError.getField()+"> "+fieldError.getDefaultMessage());
			}
			result.put("failMsg", "validated faild!");
			return new ResultBean(result);
		}
		//验证验证码是否匹配
		//获取session中的验证码
		Object kaptcha = session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
		if(kaptcha==null||!kaptcha.toString().equals(loginBean.getKaptcha())){
			logger.info(logFormat("login")+"kaptcha validated faild: request kaptcha="+kaptcha+"; bean kaptcha="+loginBean.getKaptcha());
			result.put("failMsg", "kaptcha validated faild!");
			return new ResultBean(result);
		}
		//解密密码
		PrivateKey privateKey = (PrivateKey) session.getAttribute(RSACrypt.PRIVATE_KEY);
		String password = RSACrypt.decrypt(privateKey, loginBean.getPassword());
		loginBean.setPassword(password);
		//登录数据处理
//		return new ResultBean(iLoginService.loginService(loginBean, session, result));
		result.put("situation", "login success!");
		return new ResultBean(result);
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
