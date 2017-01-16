package com.imopan.glm.entity.game;

/**
 * 游戏截图, 游戏 GameCentre中的一个属性
 * @author 
 *
 */
public class GamePicture {
	
	/**
	 * 图片ID
	 */
	private String id;
	
	/**
	 * 图片地址
	 */
	private String url;
	
	/**
	 * 图片排列序号
	 */
	private int sort;

	

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
