package com.imopan.glm.entity;

/**
 * <p>
 * 游戏字段
 * </p>
 */
public class Game {
	/**
	 * <p>
	 * 游戏Id
	 * </p>
	 */
	private String gid;

	/**
	 * <p>
	 * 游戏名字
	 * </p>
	 */
	private String gname;
	/**
	 * <p>
	 * 游戏图片
	 * </p>
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
