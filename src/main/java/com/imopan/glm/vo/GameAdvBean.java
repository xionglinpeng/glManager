package com.imopan.glm.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.imopan.glm.entity.LGame;

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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GameAdvBean extends PersistentObject {

 
	private static final long serialVersionUID = 3668719900298703194L;

	//id
	private String id;
	//专题名称
	private String advName;
	//专题图片
	private String advImg;
	//专题url
	private String advUrl;
	//关联的游戏
	private LGame game;
	//插入时间
	private Date insertTime;
	//上架时间
	private Date activeTime;
	//失效时间
	private Date expireTime;
	
	//专题状态：1上架，2下架
	private Integer status;
	
	//专题创建人
	private String creater;
	
	// 上传图片base64
	private String imageSrc; 
	
	//上传图片的名称
	private String fileName;
	
	//游戏id
	private String gid;
	
	

	public String getGid() {
		return gid;
	}

	public void setGid(String gid) {
		this.gid = gid;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getImageSrc() {
		return imageSrc;
	}

	public void setImageSrc(String imageSrc) {
		this.imageSrc = imageSrc;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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
