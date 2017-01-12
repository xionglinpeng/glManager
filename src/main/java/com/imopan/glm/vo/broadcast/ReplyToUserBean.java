package com.imopan.glm.vo.broadcast;

import com.imopan.glm.vo.PersistentObject;

/**
 * The Class ReplyUserBean.
 */
public class ReplyToUserBean extends PersistentObject{

	/**
	 * serialVersionUID
	 *
	 * @since 1.0.0
	 */
	private static final long serialVersionUID = 9196450967730563444L;

	/**
	 * 被回复用户id
	 */
	private String uid;
	
	/**
	 * 被回复用户名
	 */
	private String userName;
	
	/**
	 * 评论id
	 */
	private String cid;

	/**
	 * Gets the 被回复用户id.
	 *
	 * @return the 被回复用户id
	 */
	public String getUid() {
		return uid;
	}

	/**
	 * Sets the 被回复用户id.
	 *
	 * @param uid the new 被回复用户id
	 */
	public void setUid(String uid) {
		this.uid = uid;
	}


	/**
	 * Gets the 评论id.
	 *
	 * @return the 评论id
	 */
	public String getCid() {
		return cid;
	}

	/**
	 * Sets the 评论id.
	 *
	 * @param cid the new 评论id
	 */
	public void setCid(String cid) {
		this.cid = cid;
	}

	/**
	 * Gets the 被回复用户名.
	 *
	 * @return the 被回复用户名
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Sets the 被回复用户名.
	 *
	 * @param userName the new 被回复用户名
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
