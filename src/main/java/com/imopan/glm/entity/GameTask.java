package com.imopan.glm.entity;

import java.util.Date;

import org.mongodb.morphia.annotations.Entity;

/**
 * 
 * 尖兵任务实体
 * 
 * @author xlp
 *
 */
@Entity(noClassnameStored = true)
public class GameTask extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * <p>用户id</p>
	 */
	private String uid;
	/**
	 * <p>游戏id</p>
	 */
	private String gid;
	/**
	 * <p>游戏名称</p>
	 */
	private String gname;
	/**
	 * <p>封面</p>
	 */
	private String imgUrl;
	/**
	 * <p>状态</p>
	 */
	private String status;
	/**
	 * <p>申领时间</p>
	 */
	private Date applyTime;
	/**
	 * <p>完成时间</p>
	 */
	private Date finishTime;
	
	
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}
	public Date getFinishTime() {
		return finishTime;
	}
	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}
	@Override
	public String toString() {
		return "GameTask [uid=" + uid + ", gid=" + gid + ", gname=" + gname + ", imgUrl=" + imgUrl + ", status="
				+ status + ", applyTime=" + applyTime + ", finishTime=" + finishTime + "]";
	}
	
	
	
}
