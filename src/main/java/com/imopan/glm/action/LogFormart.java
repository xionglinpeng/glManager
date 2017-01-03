package com.imopan.glm.action;

/**
 * <p>日志。</p>
 * @author xlp
 *
 */
public class LogFormart {
	
	/**
	 * <p>日志格式。</p>
	 * @param methodName 调用日志的方法名。
	 * @return
	 */
	public static String logFormat(String methodName){
		return "method:<"+methodName+">->";
	}
}
