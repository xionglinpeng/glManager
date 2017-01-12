package com.imopan.glm.entity;

public class GameType extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 类型ID
	 */
	private String gtid;

	/**
	 * 类型名称
	 */
	private String gtName;

	/**
	 * 类型名称
	 */
	private String gtDesc;

	public String getGtid() {
		return gtid;
	}

	public void setGtid(String gtid) {
		this.gtid = gtid;
	}

	public String getGtName() {
		return gtName;
	}

	public void setGtName(String gtName) {
		this.gtName = gtName;
	}

	public String getGtDesc() {
		return gtDesc;
	}

	public void setGtDesc(String gtDesc) {
		this.gtDesc = gtDesc;
	}
	
	

}
