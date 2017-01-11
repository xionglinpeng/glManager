package com.imopan.glm.vo.common;

import com.imopan.glm.vo.PersistentObject;

/**
 * 版本信息
 * 
 *
 * VersionBean
 *
 * @author yangzhenyu
 *
 * @since 2016年12月28日 下午4:08:47
 *
 * @version 1.0.0
 */
public class VersionBean extends PersistentObject{

	/**
	 * serialVersionUID
	 *
	 * @since 1.0.0
	 */
	private static final long serialVersionUID = 2951178092454863235L;

	/**
	 * 版本名
	 */
	private String verName;
	
	/**
	 * 版本code码
	 */
	private String verCode;
	
	/**
	 * 下载信息
	 */
	private String downUrl;
	
	/**
	 * 内容
	 */
	private String content;
	
	/**
	 * 发布时间
	 */
	private String publishDate;

	/**
	 * Gets the 版本名.
	 *
	 * @return the 版本名
	 */
	public String getVerName() {
		return verName;
	}

	/**
	 * Sets the 版本名.
	 *
	 * @param verName the new 版本名
	 */
	public void setVerName(String verName) {
		this.verName = verName;
	}

	/**
	 * Gets the 版本code码.
	 *
	 * @return the 版本code码
	 */
	public String getVerCode() {
		return verCode;
	}

	/**
	 * Sets the 版本code码.
	 *
	 * @param verCode the new 版本code码
	 */
	public void setVerCode(String verCode) {
		this.verCode = verCode;
	}

	/**
	 * Gets the 下载信息.
	 *
	 * @return the 下载信息
	 */
	public String getDownUrl() {
		return downUrl;
	}

	/**
	 * Sets the 下载信息.
	 *
	 * @param downUrl the new 下载信息
	 */
	public void setDownUrl(String downUrl) {
		this.downUrl = downUrl;
	}

	/**
	 * Gets the 内容.
	 *
	 * @return the 内容
	 */
	public String getContent() {
		return content;
	}

	/**
	 * Sets the 内容.
	 *
	 * @param content the new 内容
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * Gets the 发布时间.
	 *
	 * @return the 发布时间
	 */
	public String getPublishDate() {
		return publishDate;
	}

	/**
	 * Sets the 发布时间.
	 *
	 * @param publishDate the new 发布时间
	 */
	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}
}
