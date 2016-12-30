package com.imopan.glm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.imopan.glm.bean.ResultBean;

@ControllerAdvice
public class GLMExceptionHandler {
	
	@ExceptionHandler(value=Exception.class)
	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ResultBean ExceptionHandler(Exception e){
		StringBuffer errorMessage = new StringBuffer();
		errorMessage.append(e+" "+e.getMessage()+"\n");
		for(StackTraceElement t : e.getStackTrace()){
			errorMessage.append("\t at "+t+"\n");
		}
		return new ResultBean(errorMessage.toString());
	}
}
