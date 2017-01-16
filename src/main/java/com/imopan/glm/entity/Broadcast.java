package com.imopan.glm.entity;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.mongodb.morphia.annotations.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.imopan.glm.vo.GeometryBean;
import com.imopan.glm.vo.broadcast.ShareBean;
import com.imopan.glm.vo.common.AttachementBean;
import com.imopan.glm.vo.user.UserBean;

/**
 * 动态
 * 
 *
 * Broadcast
 *
 * @author yangzhenyu
 *
 * @since 2016年12月28日 上午10:42:56
 *
 * @version 1.0.0
 */
@Entity(noClassnameStored = true)
public class Broadcast extends BaseEntity {

	/**
	 * serialVersionUID
	 *
	 * @since 1.0.0
	 */
	private static final long serialVersionUID = 5898896091693565679L;

	/**
	 * The user info.
	 */
	private UserBean userInfo;

	/**
	 * 动态内容
	 */
	private String content;

	/**
	 * 1纯文字,2图文混排,3多图九宫格
	 */
	private Integer type;

	/**
	 * 发表时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	private Date publishDate = new Date();

	/**
	 * 点赞用户id
	 */
	private Set<String> favourUids;

	/**
	 * 是否分享
	 */
	private Boolean isShare = false;

	/**
	 * 分享信息
	 */
	private ShareBean share;

	/**
	 * 附件.图片等.
	 */
	private List<AttachementBean> attachements;

	/**
	 * 评论
	 */
	private List<String> comments;

	/**
	 * 地理位置
	 */
	private GeometryBean geometry;

	/**
	 * Gets the 动态内容.
	 *
	 * @return the 动态内容
	 */
	public String getContent() {
		return content;
	}

	/**
	 * Sets the 动态内容.
	 *
	 * @param content
	 *            the new 动态内容
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * Gets the 1纯文字,2图文混排,3多图九宫格.
	 *
	 * @return the 1纯文字,2图文混排,3多图九宫格
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * Sets the 1纯文字,2图文混排,3多图九宫格.
	 *
	 * @param type
	 *            the new 1纯文字,2图文混排,3多图九宫格
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * Gets the 发表时间.
	 *
	 * @return the 发表时间
	 */
	public Date getPublishDate() {
		return publishDate;
	}

	/**
	 * Sets the 发表时间.
	 *
	 * @param publishDate
	 *            the new 发表时间
	 */
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	

	/**
	 * Gets the 是否分享.
	 *
	 * @return the 是否分享
	 */
	public Boolean getIsShare() {
		return isShare;
	}

	/**
	 * Sets the 是否分享.
	 *
	 * @param isShare
	 *            the new 是否分享
	 */
	public void setIsShare(Boolean isShare) {
		this.isShare = isShare;
	}

	/**
	 * Gets the 分享信息.
	 *
	 * @return the 分享信息
	 */
	public ShareBean getShare() {
		return share;
	}

	/**
	 * Sets the 分享信息.
	 *
	 * @param share
	 *            the new 分享信息
	 */
	public void setShare(ShareBean share) {
		this.share = share;
	}

	/**
	 * Gets the 评论.
	 *
	 * @return the 评论
	 */
	public List<String> getComments() {
		return comments;
	}

	/**
	 * Sets the 评论.
	 *
	 * @param comments
	 *            the new 评论
	 */
	public void setComments(List<String> comments) {
		this.comments = comments;
	}

	/**
	 * Gets the 地理位置.
	 *
	 * @return the 地理位置
	 */
	public GeometryBean getGeometry() {
		return geometry;
	}

	/**
	 * Sets the 地理位置.
	 *
	 * @param geometry
	 *            the new 地理位置
	 */
	public void setGeometry(GeometryBean geometry) {
		this.geometry = geometry;
	}

	/**
	 * Gets the 附件.图片等.
	 *
	 * @return the 附件
	 */
	public List<AttachementBean> getAttachements() {
		return attachements;
	}

	/**
	 * Sets the 附件.图片等.
	 *
	 * @param attachements
	 *            the new 附件
	 */
	public void setAttachements(List<AttachementBean> attachements) {
		this.attachements = attachements;
	}

	/**
	 * Gets the user info.
	 *
	 * @return the user info
	 */
	public UserBean getUserInfo() {
		return userInfo;
	}

	/**
	 * Sets the user info.
	 *
	 * @param userInfo
	 *            the user info
	 */
	public void setUserInfo(UserBean userInfo) {
		this.userInfo = userInfo;
	}

	/**
	 * Gets the 点赞用户id.
	 *
	 * @return the 点赞用户id
	 */
	public Set<String> getFavourUids() {
		return favourUids;
	}

	/**
	 * Sets the 点赞用户id.
	 *
	 * @param favourUids the new 点赞用户id
	 */
	public void setFavourUids(Set<String> favourUids) {
		this.favourUids = favourUids;
	}
}
