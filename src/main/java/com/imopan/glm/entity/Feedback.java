package com.imopan.glm.entity;

import java.util.Date;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Property;

/**
 * 反馈
 * 
 *
 * Feedback
 *
 * @author yangzhenyu
 *
 * @since 2016年12月28日 下午5:35:52
 *
 * @version 1.0.0
 */
@Entity(noClassnameStored = true)
public class Feedback extends BaseEntity {

	/**
	 * serialVersionUID
	 *
	 * @since 1.0.0
	 */
	private static final long serialVersionUID = 5168755908395362338L;
	
	private String feedbackId;

	/**
	 * 用户id
	 */
	private String uid;

	/**
	 * 用户名
	 */
	private String nickname;

	/**
	 * 内容
	 */
	private String content;

	@Property(value="insertDate")
	private Date insertTime;
	
	private GlUser glUser;
	
	
	
	
	public String getFeedbackId() {
		return feedbackId;
	}

	public void setFeedbackId(String feedbackId) {
		this.feedbackId = feedbackId;
	}

	/**
	 * 处理状态
	 */
	private String status;

	/**
	 * Gets the 用户id.
	 *
	 * @return the 用户id
	 */
	public String getUid() {
		return uid;
	}

	/**
	 * Sets the 用户id.
	 *
	 * @param uid
	 *            the new 用户id
	 */
	public void setUid(String uid) {
		this.uid = uid;
	}

	/**
	 * Gets the 内容.
	 *
	 * @return the 内容
	 */
	public String getContent() {
		return content;
	}

	/**
	 * Sets the 内容.
	 *
	 * @param content
	 *            the new 内容
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * Gets the insert time.
	 *
	 * @return the insert time
	 */
	public Date getInsertTime() {
		return insertTime;
	}

	/**
	 * Sets the insert time.
	 *
	 * @param insertTime
	 *            the new insert time
	 */
	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}

	/**
	 * Gets the 用户名.
	 *
	 * @return the 用户名
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * Sets the 用户名.
	 *
	 * @param nickname
	 *            the new 用户名
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public GlUser getGlUser() {
		return glUser;
	}

	public void setGlUser(GlUser glUser) {
		this.glUser = glUser;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
