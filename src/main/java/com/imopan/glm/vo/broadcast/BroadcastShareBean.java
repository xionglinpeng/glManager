package com.imopan.glm.vo.broadcast;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.imopan.glm.vo.PersistentObject;
import com.imopan.glm.vo.user.UserBean;

/**
 * 动态分享
 * 
 *
 * BroadcastShareBean
 *
 * @author yangzhenyu
 *
 * @since 2016年12月23日 下午6:17:02
 *
 * @version 1.0.0
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BroadcastShareBean extends PersistentObject {

	/**
	 * serialVersionUID
	 *
	 * @since 1.0.0
	 */
	private static final long serialVersionUID = -8172911086435698889L;

	/**
	 * 用户信息
	 */
	private UserBean userInfo;

	/**
	 * 分享说明内容
	 */
	private String content;

	/**
	 * Gets the 分享说明内容.
	 *
	 * @return the 分享说明内容
	 */
	public String getContent() {
		return content;
	}

	/**
	 * Sets the 分享说明内容.
	 *
	 * @param content
	 *            the new 分享说明内容
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * Gets the 用户信息.
	 *
	 * @return the 用户信息
	 */
	public UserBean getUserInfo() {
		return userInfo;
	}

	/**
	 * Sets the 用户信息.
	 *
	 * @param userInfo
	 *            the new 用户信息
	 */
	public void setUserInfo(UserBean userInfo) {
		this.userInfo = userInfo;
	}

}
