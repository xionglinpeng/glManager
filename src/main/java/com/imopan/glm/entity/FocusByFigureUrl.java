package com.imopan.glm.entity;

import org.mongodb.morphia.annotations.Entity;

/**
 * <p>焦点轮播图的URL地址。</p>
 * @author xlp
 *
 */
@Entity(noClassnameStored=true)
public class FocusByFigureUrl extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String focusByFigureUrl;

	public String getFocusByFigureUrl() {
		return focusByFigureUrl;
	}

	public void setFocusByFigureUrl(String focusByFigureUrl) {
		this.focusByFigureUrl = focusByFigureUrl;
	}

	@Override
	public String toString() {
		return "FocusByFigureUrl [focusByFigureUrl=" + focusByFigureUrl + "]";
	}
	
	
}
