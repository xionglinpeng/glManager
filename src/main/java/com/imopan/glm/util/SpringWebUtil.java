/** 
 * Project Name:adv-business-service 
 * File Name:SessionUtil.java 
 * Package Name:com.imopan.adv.platform.util 
 * Date:2016年12月2日下午4:31:37 
 * Copyright (c) 2016, zhangjiakun@imopan.com All Rights Reserved. 
 * 
*/ 

package com.imopan.glm.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/** 
 * ClassName:SessionUtil <br/> 
 * Function: TODO ADD FUNCTION. <br/>  
 * Date:     2016年12月2日 下午4:31:37 <br/> 
 * @author   zhangjiakun 
 * @version   
 * @since    JDK 1.7       
 */
public class SpringWebUtil {

	public static HttpServletRequest getRequest(){
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
		return requestAttributes==null? null : requestAttributes.getRequest();
	}
	
	public static HttpSession getSession(){
		return getRequest().getSession(false);
	}
	
	public static String getRealRootPath(){
		return getRequest().getServletContext().getRealPath("/");
	}
	
	public static String getIp() {
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		if(servletRequestAttributes!=null){
			HttpServletRequest request = servletRequestAttributes.getRequest();
			return request.getRemoteAddr();
		}
		return null;
	}
	
	public static Object getSessionAttribute(String name){
		HttpServletRequest request = getRequest();
		return request == null?null:request.getSession().getAttribute(name);
	}
	
	public static void setSessionAttribute(String name,Object value){
		HttpServletRequest request = getRequest();
		if(request!=null){
			request.getSession().setAttribute(name, value);	
		}
	}
	
	public static Object getRequestAttribute(String name){
		HttpServletRequest request = getRequest();
		return request == null?null:request.getAttribute(name);
	}
	public static void setRequestAttribute(String name,Object value){
		HttpServletRequest request = getRequest();
		if(request!=null){
			request.setAttribute(name, value);	
		}
	}

	public static String getContextPath() {
		return getRequest().getContextPath();
	}

	public static void removeSessionAttribute(String name) {
		getRequest().getSession().removeAttribute(name);
	}
	
}