package com.imopan.glm.entity;

import java.util.Date;

import org.mongodb.morphia.annotations.Entity;
/**
 * 游戏礼包任务实体
 * @author quchuanyuan
 *
 */
@Entity(noClassnameStored = true)
public class GameGiftTask extends BaseEntity{

	private static final long serialVersionUID = 1500793913979182775L;
	//用户id
	private String uid;
	//游戏id
	private String gid;
	//礼包id
	private String ggid;
	//礼包名称
	private String ggname;
	//礼包介绍
	private String dsc;
	//游戏名称
	private String gname;
	//礼包码
	private String pkcode;
    //礼包封面
	private String imgUrl;
	//当前状态
	private Integer status;
	//申请时间
	private Date applyTime;

	
	
	public String getGgname() {
		return ggname;
	}

	public void setGgname(String ggname) {
		this.ggname = ggname;
	}

	public String getDsc() {
		return dsc;
	}

	public void setDsc(String dsc) {
		this.dsc = dsc;
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

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}


	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getGgid() {
		return ggid;
	}

	public void setGgid(String ggid) {
		this.ggid = ggid;
	}

	public String getPkcode() {
		return pkcode;
	}

	public void setPkcode(String pkcode) {
		this.pkcode = pkcode;
	}


}
