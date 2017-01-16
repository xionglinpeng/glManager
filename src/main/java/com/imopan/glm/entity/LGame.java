package com.imopan.glm.entity;


/**
 * 游戏字段
 * 
 *
 * Game
 *
 * @author crystal
 *
 * @since 2016年12月29日 下午5:35:09
 *
 * @version 1.0.0
 */
//@Entity(noClassnameStored = true)
public class LGame {

	/**
	 * 游戏Id
	 */
	private String gid;

	/**
	 * 游戏名字
	 */
	private String gname;
	
	
	/**
	 * 游戏图片
	 */
	private String iconUrl;


	public String getGid() {
		return gid;
	}


	public void setGid(String gid) {
		this.gid = gid;
	}


	public String getGname() {
		return gname;
	}


	public void setGname(String gname) {
		this.gname = gname;
	}


	public String getIconUrl() {
		return iconUrl;
	}


	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}
	
}
