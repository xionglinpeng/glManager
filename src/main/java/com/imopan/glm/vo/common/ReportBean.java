package com.imopan.glm.vo.common;

import com.imopan.glm.vo.PersistentObject;
import com.imopan.glm.vo.user.UserBean;

/**
 * The Class ReportBean.
 */
public class ReportBean extends PersistentObject {

	/**
	 * serialVersionUID
	 *
	 * @since 1.0.0
	 */
	private static final long serialVersionUID = 1719355922644264270L;

	/**
	 * 举报动态
	 */
	private BroadcastInfo broadcast;

	/**
	 * 举报用户
	 */
	private UserBean userInfo;
		
	/**
	 * Gets the 举报动态.
	 *
	 * @return the 举报动态
	 */
	public BroadcastInfo getBroadcast() {
		return broadcast;
	}



	/**
	 * Sets the 举报动态.
	 *
	 * @param broadcast the new 举报动态
	 */
	public void setBroadcast(BroadcastInfo broadcast) {
		this.broadcast = broadcast;
	}
	
	/**
	 * The Class BroadcastInfo.
	 */
	public class BroadcastInfo extends PersistentObject {

		/**
		 * serialVersionUID
		 *
		 * @since 1.0.0
		 */
		private static final long serialVersionUID = 2715088519171202240L;

		/**
		 * The bid.
		 */
		private String bid;

		/**
		 * The uid.
		 */
		private String uid;

		/**
		 * The user name.
		 */
		private String userName;

		/**
		 * Gets the bid.
		 *
		 * @return the bid
		 */
		public String getBid() {
			return bid;
		}

		/**
		 * Sets the bid.
		 *
		 * @param bid the new bid
		 */
		public void setBid(String bid) {
			this.bid = bid;
		}

		/**
		 * Gets the uid.
		 *
		 * @return the uid
		 */
		public String getUid() {
			return uid;
		}

		/**
		 * Sets the uid.
		 *
		 * @param uid the new uid
		 */
		public void setUid(String uid) {
			this.uid = uid;
		}

		/**
		 * Gets the user name.
		 *
		 * @return the user name
		 */
		public String getUserName() {
			return userName;
		}

		/**
		 * Sets the user name.
		 *
		 * @param userName the new user name
		 */
		public void setUserName(String userName) {
			this.userName = userName;
		}
	}

}
