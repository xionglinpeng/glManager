package com.imopan.glm.entity;

import java.util.Date;

import org.mongodb.morphia.annotations.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 游戏推广活动
 *
 * GameAdv
 *
 * @author lvqi
 *
 * @since 2016年12月28日 下午5:50:04
 *
 * @version 1.0.0
 */

@Entity(noClassnameStored=false)
public class GameAdv extends BaseEntity {

 
	private static final long serialVersionUID = 3668719900298703194L;

	//专题名称
	private String advName;
	//专题图片
	private String advImg;
	//专题url
	private String advUrl;
	//关联的游戏
	private LGame game;
	//插入时间
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	private Date insertTime;
	//上架时间
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	private Date activeTime;
	//失效时间
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	private Date expireTime;
	
	/**
	 * 专题状态：1上架，2下架
	 */
	private Integer status;
	
	/**
	 * 专题创建人
	 */
	private String creater;
	

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public String getAdvName() {
		return advName;
	}

	public void setAdvName(String advName) {
		this.advName = advName;
	}

	public String getAdvImg() {
		return advImg;
	}

	public void setAdvImg(String advImg) {
		this.advImg = advImg;
	}

	public String getAdvUrl() {
		return advUrl;
	}

	public void setAdvUrl(String advUrl) {
		this.advUrl = advUrl;
	}

	public LGame getGame() {
		return game;
	}

	public void setGame(LGame game) {
		this.game = game;
	}

	public Date getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}

	public Date getActiveTime() {
		return activeTime;
	}

	public void setActiveTime(Date activeTime) {
		this.activeTime = activeTime;
	}

	public Date getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}

	
	
}
