package com.imopan.glm.vo.broadcast;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.imopan.glm.vo.PersistentObject;

/**
 * 分享
 * 
 *
 * ShareBean
 *
 * @author yangzhenyu
 *
 * @since 2016年12月21日 下午4:38:51
 *
 * @version 1.0.0
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShareBean extends PersistentObject{

	/**
	 * serialVersionUID
	 *
	 * @since 1.0.0
	 */
	private static final long serialVersionUID = -1726217478537522416L;
	
	/**
	 * 分享用户id
	 */
	private String uid;
	
	/**
	 * 用户名
	 */
	private String userName;
	
	/**
	 * 分享动态id
	 */
	private String bid;
	
	/**
	 * 分享发布内容
	 */
	private String content;
	
//	/**
//	 * 分享时间
//	 */
//	private Long publishDate;

	/**
	 * Gets the 分享用户id.
	 *
	 * @return the 分享用户id
	 */
	public String getUid() {
		return uid;
	}

	/**
	 * Sets the 分享用户id.
	 *
	 * @param uid the new 分享用户id
	 */
	public void setUid(String uid) {
		this.uid = uid;
	}

	/**
	 * Gets the 分享动态id.
	 *
	 * @return the 分享动态id
	 */
	public String getBid() {
		return bid;
	}

	/**
	 * Sets the 分享动态id.
	 *
	 * @param bid the new 分享动态id
	 */
	public void setBid(String bid) {
		this.bid = bid;
	}

	/**
	 * Gets the 分享发布内容.
	 *
	 * @return the 分享发布内容
	 */
	public String getContent() {
		return content;
	}

	/**
	 * Sets the 分享发布内容.
	 *
	 * @param content the new 分享发布内容
	 */
	public void setContent(String content) {
		this.content = content;
	}



	/**
	 * Gets the 用户名.
	 *
	 * @return the 用户名
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Sets the 用户名.
	 *
	 * @param userName the new 用户名
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
