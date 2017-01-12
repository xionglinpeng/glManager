package com.imopan.glm.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 附件
 * 图片等
 * 
 *
 * Attachement
 *
 * @author crystal
 *
 * @since 2016年12月30日 下午3:40:26
 *
 * @version 1.0.0
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Attachement extends BaseEntity{

	/**
	 * serialVersionUID
	 *
	 * @since 1.0.0
	 */
	private static final long serialVersionUID = 1387659183346003654L;

	/**
	 * 缩略图地址
	 */
	private String thumbURI;
	
	/**
	 * 资源地址
	 */
	private String resURI;
	
	/**
	 * 图片宽
	 */
	private String w;
	
	/**
	 * 图片高
	 */
	private String h;

	/**
	 * Gets the 缩略图地址.
	 *
	 * @return the 缩略图地址
	 */
	public String getThumbURI() {
		return thumbURI;
	}

	/**
	 * Sets the 缩略图地址.
	 *
	 * @param thumbURI the new 缩略图地址
	 */
	public void setThumbURI(String thumbURI) {
		this.thumbURI = thumbURI;
	}

	/**
	 * Gets the 资源地址.
	 *
	 * @return the 资源地址
	 */
	public String getResURI() {
		return resURI;
	}

	/**
	 * Sets the 资源地址.
	 *
	 * @param resURI the new 资源地址
	 */
	public void setResURI(String resURI) {
		this.resURI = resURI;
	}

	/**
	 * Gets the 图片宽.
	 *
	 * @return the 图片宽
	 */
	public String getW() {
		return w;
	}

	/**
	 * Sets the 图片宽.
	 *
	 * @param w the new 图片宽
	 */
	public void setW(String w) {
		this.w = w;
	}

	/**
	 * Gets the 图片高.
	 *
	 * @return the 图片高
	 */
	public String getH() {
		return h;
	}

	/**
	 * Sets the 图片高.
	 *
	 * @param h the new 图片高
	 */
	public void setH(String h) {
		this.h = h;
	}

			
	
}
