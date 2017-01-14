package com.imopan.glm.entity;

import org.mongodb.morphia.annotations.Entity;

/**
 * 轮播图实体类
 * 
 * @author quchuanyuan
 * xlp修改
 * 
 */
@Entity(noClassnameStored = true)
public class GameBanner extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3255264632304772193L;
	/**
	 * <p>当前轮播图ID，对应ObjectId</p>
	 */
	private String gameBannerId;
	/**
	 * <p>游戏id</p>
	 */
	private String gid;
	/**
	 * <p>轮播图标题</p>
	 */
	private String gname;
	/**
	 * <p>轮播图图片链接</p>
	 */
	private String adsUrl;
	/**
	 * <p>当前轮播图的排序位置</p>
	 */
	private String index;
	/**
	 * <p>当前关键字词组是否已经保存</p>
	 */
	private boolean save;
	/**
	 * <p>当前关键字词组是否已经发布</p>
	 */
	private boolean publish;
	
	
	
	public String getGameBannerId() {
		return gameBannerId;
	}
	public void setGameBannerId(String gameBannerId) {
		this.gameBannerId = gameBannerId;
	}
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
	public String getAdsUrl() {
		return adsUrl;
	}
	public void setAdsUrl(String adsUrl) {
		this.adsUrl = adsUrl;
	}
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public boolean isSave() {
		return save;
	}
	public void setSave(boolean save) {
		this.save = save;
	}
	public boolean isPublish() {
		return publish;
	}
	public void setPublish(boolean publish) {
		this.publish = publish;
	}
	@Override
	public String toString() {
		return "GameBanner [gameBannerId=" + gameBannerId + ", gid=" + gid + ", gname=" + gname + ", adsUrl=" + adsUrl
				+ ", index=" + index + ", save=" + save + ", publish=" + publish + "]";
	}

	

}
