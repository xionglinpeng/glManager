package com.imopan.glm.bean;

public class ResultBean {
	
	private Integer code;
	private Object data;
	private Exception e;
	
	
	public ResultBean(Object data){
		this.code = 1;
		this.data = data;
	}
	
	public ResultBean(Exception e){
		this.e = e;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Exception getE() {
		return e;
	}

	public void setE(Exception e) {
		this.e = e;
	}
	
	
}
