package com.imopan.glm.vo;

import java.util.List;

/**
 * The Class LimitBean.
 */
public class LimitInfoBean<T> extends PersistentObject{

	/**
	 * serialVersionUID
	 *
	 * @since 1.0.0
	 */
	private static final long serialVersionUID = -5928691504654745699L;

	/**
	 * 是否有更多数据
	 */
	private Boolean moreData;
	
	/**
	 * 查询总条数
	 */
	private Integer totalCount;
	
	/**
	 * 查询条数
	 */
	private Integer rowCount;
	
	/**
	 * 下次查询开始位置
	 * 0开始
	 */
	private Integer nextStart;
	
	/**
	 * 查询结果集
	 */
	private List<T> items;

	/**
	 * Gets the 是否有更多数据.
	 *
	 * @return the 是否有更多数据
	 */
	public Boolean getMoreData() {
		return moreData;
	}

	/**
	 * Sets the 是否有更多数据.
	 *
	 * @param moreData the new 是否有更多数据
	 */
	public void setMoreData(Boolean moreData) {
		this.moreData = moreData;
	}

	/**
	 * Gets the 查询总条数.
	 *
	 * @return the 查询总条数
	 */
	public Integer getTotalCount() {
		return totalCount;
	}

	/**
	 * Sets the 查询总条数.
	 *
	 * @param totalCount the new 查询总条数
	 */
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	/**
	 * Gets the 查询条数.
	 *
	 * @return the 查询条数
	 */
	public Integer getRowCount() {
		return rowCount;
	}

	/**
	 * Sets the 查询条数.
	 *
	 * @param rowCount the new 查询条数
	 */
	public void setRowCount(Integer rowCount) {
		this.rowCount = rowCount;
	}

	/**
	 * Gets the 下次查询开始位置 0开始.
	 *
	 * @return the 下次查询开始位置 0开始
	 */
	public Integer getNextStart() {
		return nextStart;
	}

	/**
	 * Sets the 下次查询开始位置 0开始.
	 *
	 * @param nextStart the new 下次查询开始位置 0开始
	 */
	public void setNextStart(Integer nextStart) {
		this.nextStart = nextStart;
	}

	public List<T> getItems() {
		return items;
	}

	public void setItems(List<T> items) {
		this.items = items;
	}
	
	
}
