package com.imopan.glm.entity;

import org.mongodb.morphia.annotations.Entity;

@Entity(noClassnameStored=true)
public class Keywords extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * <p>当前关键字词组的ID，对应ObjectId</p>
	 */
	private String keywordId;
	/**
	 * <p>当前关键字词组的排序位置</p>
	 */
	private int sort;
	/**
	 * <p>当前关键字词组：第一个关键词</p>
	 */
	private String keyword1;
	/**
	 * <p>当前关键字词组：第二个关键词</p>
	 */
	private String keyword2;
	/**
	 * <p>当前关键字词组：第三个关键词</p>
	 */
	private String keyword3;
	/**
	 * <p>当前关键字词组是否已经保存</p>
	 */
	private boolean save;
	/**
	 * <p>当前关键字词组是否已经发布</p>
	 */
	private boolean publish;
	
	
	
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public String getKeywordId() {
		return keywordId;
	}
	public void setKeywordId(String keywordId) {
		this.keywordId = keywordId;
	}
	public String getKeyword1() {
		return keyword1;
	}
	public void setKeyword1(String keyword1) {
		this.keyword1 = keyword1;
	}
	public String getKeyword2() {
		return keyword2;
	}
	public void setKeyword2(String keyword2) {
		this.keyword2 = keyword2;
	}
	public String getKeyword3() {
		return keyword3;
	}
	public void setKeyword3(String keyword3) {
		this.keyword3 = keyword3;
	}
	public boolean getSave() {
		return save;
	}
	public void setSave(boolean save) {
		this.save = save;
	}
	public boolean getPublish() {
		return publish;
	}
	public void setPublish(boolean publish) {
		this.publish = publish;
	}
	@Override
	public String toString() {
		return "Keywords [keywordId=" + keywordId + ", sort=" + sort + ", keyword1=" + keyword1 + ", keyword2="
				+ keyword2 + ", keyword3=" + keyword3 + ", save=" + save + ", publish=" + publish + "]";
	}
	
	
	
}
