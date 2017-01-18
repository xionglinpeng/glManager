package com.imopan.glm.vo.broadcast;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.imopan.glm.vo.GeometryBean;
import com.imopan.glm.vo.LimitInfoBean;
import com.imopan.glm.vo.PersistentObject;
import com.imopan.glm.vo.common.AttachementBean;
import com.imopan.glm.vo.user.UserBean;

/**
 * 动态
 * 
 *
 * BroadcastBean
 *
 * @author yangzhenyu
 *
 * @since 2016年12月21日 下午3:14:15
 *
 * @version 1.0.0
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BroadcastBean extends PersistentObject {

	/**
	 * serialVersionUID
	 *
	 * @since 1.0.0
	 */
	private static final long serialVersionUID = -1040090655995307694L;

	/**
	 * 动态ID
	 */
	private String bid;

	/**
	 * The user info.
	 */
	private UserBean userInfo;

	/**
	 * 是否被举报
	 */
	private Boolean isReport;
	
	/**
	 * 帖子状态：1open,2close
	 */
	private Integer status;
	
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
	private Long publishDate;
	
	/**
	 * 发表时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	private Date insertDate;

	/**
	 * 点赞用户id
	 */
	private Set<String> favourUids;
	
	/**
	 * 请求用户是否点赞
	 */
	private Boolean isFavour;

	/**
	 * 是否分享
	 */
	private Boolean isShare;

	/**
	 * 好友关系
	 * 
	 * 1好友,2同关注游戏,3同游戏类型
	 */
	private Integer relationType;

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
	private LimitInfoBean<CommentBean> comments;

	/**
	 * 地理位置
	 */
	private GeometryBean geometry;

	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Boolean getIsReport() {
		return isReport;
	}

	public void setIsReport(Boolean isReport) {
		this.isReport = isReport;
	}

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
	 * Gets the 动态ID.
	 *
	 * @return the 动态ID
	 */
	public String getBid() {
		return bid;
	}

	/**
	 * Sets the 动态ID.
	 *
	 * @param bid
	 *            the new 动态ID
	 */
	public void setBid(String bid) {
		this.bid = bid;
	}

	/**
	 * Gets the 评论.
	 *
	 * @return the 评论
	 */
	public LimitInfoBean<CommentBean> getComments() {
		return comments;
	}

	/**
	 * Sets the 评论.
	 *
	 * @param comments
	 *            the new 评论
	 */
	public void setComments(LimitInfoBean<CommentBean> comments) {
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
	 * @param publishDate
	 *            the new 发表时间
	 */
	public void setPublishDate(Long publishDate) {
		this.publishDate = publishDate;
	}

	/**
	 * Gets the 好友关系 1好友,2同关注游戏,3同游戏类型.
	 *
	 * @return the 好友关系 1好友,2同关注游戏,3同游戏类型
	 */
	public Integer getRelationType() {
		return relationType;
	}

	/**
	 * Sets the 好友关系 1好友,2同关注游戏,3同游戏类型.
	 *
	 * @param relationType
	 *            the new 好友关系 1好友,2同关注游戏,3同游戏类型
	 */
	public void setRelationType(Integer relationType) {
		this.relationType = relationType;
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
	 * Gets the 请求用户是否点赞.
	 *
	 * @return the 请求用户是否点赞
	 */
	public Boolean getIsFavour() {
		return isFavour;
	}

	/**
	 * Sets the 请求用户是否点赞.
	 *
	 * @param isFavour the new 请求用户是否点赞
	 */
	public void setIsFavour(Boolean isFavour) {
		this.isFavour = isFavour;
	}

	public UserBean getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserBean userInfo) {
		this.userInfo = userInfo;
	}

	public Date getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}

	public Set<String> getFavourUids() {
		return favourUids;
	}

	public void setFavourUids(Set<String> favourUids) {
		this.favourUids = favourUids;
	}

}
