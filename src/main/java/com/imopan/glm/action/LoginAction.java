package com.imopan.glm.action;

import java.security.Key;
import java.security.PrivateKey;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.code.kaptcha.Constants;
import com.imopan.glm.bean.LoginBean;
import com.imopan.glm.bean.ResultBean;
import com.imopan.glm.constant.Login;
import com.imopan.glm.service.ILoginService;
import com.util.utils.crypt.AESCrypt;
import com.util.utils.crypt.RSACrypt;

@Controller
@RequestMapping("/login")
public class LoginAction {
	
	private static final Logger logger = LogManager.getLogger(LoginAction.class);
	
	@Autowired
	private ILoginService iLoginService;
	
	/**
	 * <p>登录密码加密的公钥。并且自动登录数据获取。</p>
	 * @param autoPassword 存储cookie中的自动登录密码。
	 * @param autoEmail 存储cookie中的自动登录邮箱。
	 * @param session 用于存储解密秘钥。
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/publicKey",method=RequestMethod.GET)
	@ResponseBody
	public ResultBean publicKey(
			@CookieValue(value="autop",required=false)String autoPassword,
			@CookieValue(value="autou",required=false)String autoEmail,
			HttpSession session) throws Exception{
		Map<String,Key> map = RSACrypt.getSecretKey();
		//将私钥存储至session中
		session.setAttribute(RSACrypt.PRIVATE_KEY, RSACrypt.getPrivateKey(map));
		//创建返回到客户端的map集合
		Map<String,Object> result = new HashMap<>();
		//存储公钥
		result.put("publicKey", RSACrypt.getPublicKeyStr(map));
		//自动登录密码存储
		if(StringUtils.isNotBlank(autoPassword)&&StringUtils.isNotBlank(autoEmail)){
			//使用公钥加密密码并存储
			String password = AESCrypt.decrypt(autoPassword, Login.AUTO_LOGIN_SECRE_KEY);
			Map<String,String> loginMap = new HashMap<>();
			loginMap.put("email", autoEmail);
			loginMap.put("password", RSACrypt.encrypt(RSACrypt.getPublicKey(map), password));
			result.put("login", loginMap);
		}
		return new ResultBean(result);
	}
	
	
	/**
	 * <p>登录。</p>
	 * @throws Exception 
	 */
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ResponseBody
	public ResultBean login(@Validated @RequestBody LoginBean loginBean,BindingResult bindingResult,
			HttpSession session,HttpServletResponse response) throws Exception{
		//用于存储返回到客户端的结果集
		Map<String,String> result = new HashMap<>();
		result.put("situation", "login fail!");//默认是登录失败，只有到最后一步才是登录成功。
		//验证请求数据是否正常
		if(bindingResult.getErrorCount()>0){
			for(FieldError fieldError : bindingResult.getFieldErrors()){
				logger.info(LogFormart.logFormat("login")+"validated faild: Field<"+fieldError.getField()+"> "+fieldError.getDefaultMessage());
			}
			result.put("failMsg", "validated faild!");
			return new ResultBean(result);
		}
		//验证验证码是否匹配
		//获取session中的验证码
		/*Object kaptcha = session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
		if(kaptcha==null||!kaptcha.toString().equals(loginBean.getKaptcha())){
			logger.info(LogFormart.logFormat("login")+"kaptcha validated faild: request kaptcha="+kaptcha+"; bean kaptcha="+loginBean.getKaptcha());
			result.put("failMsg", "kaptcha validated faild!");
			return new ResultBean(result);
		}*/
		//解密密码
		PrivateKey privateKey = (PrivateKey) session.getAttribute(RSACrypt.PRIVATE_KEY);
		String password = RSACrypt.decrypt(privateKey, loginBean.getPassword());
		loginBean.setPassword(password);
		
		//登录数据处理
		result = iLoginService.loginService(loginBean, result,session,response );
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
