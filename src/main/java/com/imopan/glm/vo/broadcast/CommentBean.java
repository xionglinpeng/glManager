package com.imopan.glm.vo.broadcast;


import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.imopan.glm.vo.PersistentObject;

/**
 * 评论
 * 
 *
 * CommentBean
 *
 * @author yangzhenyu
 *
 * @since 2016年12月22日 上午11:11:48
 *
 * @version 1.0.0
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentBean extends PersistentObject{

	/**
	 * serialVersionUID
	 *
	 * @since 1.0.0
	 */
	private static final long serialVersionUID = 7675257175917314655L;

		
//	private BroadcastInfo broadcast = new BroadcastInfo();
	
	/**
	 * 动态id
	 */
	private String bid;
	
	/**
	 * 评论id
	 */
	private String cid;
	
	/**
	 * 评论用户id
	 */
	private String uid;
	
	/**
	 * 用户名
	 */
	private String userName;
	
	/**
	 * 评论内容
	 */
	private String content;
		
	/**
	 * 发表时间
	 */
	private Long publishDate;
	
	/**
	 * The insert date.
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	private Date insertDate;
	
	/**
	 * 回复用户对象
	 */
	private ReplyToUserBean replyToUser;

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
	 * Gets the 评论用户id.
	 *
	 * @return the 评论用户id
	 */
	public String getUid() {
		return uid;
	}

	/**
	 * Sets the 评论用户id.
	 *
	 * @param uid the new 评论用户id
	 */
	public void setUid(String uid) {
		this.uid = uid;
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

	/**
	 * Gets the 评论内容.
	 *
	 * @return the 评论内容
	 */
	public String getContent() {
		return content;
	}

	/**
	 * Sets the 评论内容.
	 *
	 * @param content the new 评论内容
	 */
	public void setContent(String content) {
		this.content = content;
	}



	/**
	 * Gets the 回复用户对象.
	 *
	 * @return the 回复用户对象
	 */
	public ReplyToUserBean getReplyToUser() {
		return replyToUser;
	}

	/**
	 * Sets the 回复用户对象.
	 *
	 * @param replyToUser the new 回复用户对象
	 */
	public void setReplyToUser(ReplyToUserBean replyToUser) {
		this.replyToUser = replyToUser;
	}

	/**
	 * Gets the 发表时间.
	 *
	 * @return the 发表时间
	 */
	public Long getPublishDate() {
		return publishDate;
	}

	/**
	 * Sets the 发表时间.
	 *
	 * @param publishDate the new 发表时间
	 */
	public void setPublishDate(Long publishDate) {
		this.publishDate = publishDate;
	}

	/**
	 * Gets the 动态id.
	 *
	 * @return the 动态id
	 */
	public String getBid() {
		return bid;
	}

	/**
	 * Sets the 动态id.
	 *
	 * @param bid the new 动态id
	 */
	public void setBid(String bid) {
		this.bid = bid;
	}

	public Date getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}

	
}
