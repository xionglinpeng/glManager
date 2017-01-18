package com.imopan.glm.entity;

import org.mongodb.morphia.annotations.Entity;

/**
 * <p>焦点图（或者说游戏banner）。</p>
 * @author xlp
 *
 */
@Entity(noClassnameStored=true)
public class FocusByFigure extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * <p>当前关键字词组的ID，对应ObjectId</p>
	 */
	private String focusByFigureId;
	/**
	 * <p>当前关键字词组的排序位置</p>
	 */
	private int sort;
	/**
	 * <p>当前关键字词组：第一个关键词</p>
	 */
	private String title;
	/**
	 * <p>当前关键字词组：第二个关键词</p>
	 */
	private String link;
	/**
	 * <p>当前关键字词组：第三个关键词</p>
	 */
	private String imageUrl;
	/**
	 * <p>当前关键字词组是否已经保存</p>
	 */
	private boolean save;
	/**
	 * <p>当前关键字词组是否已经发布</p>
	 */
	private boolean publish;
	
	
	
	public String getFocusByFigureId() {
		return focusByFigureId;
	}
	public void setFocusByFigureId(String focusByFigureId) {
		this.focusByFigureId = focusByFigureId;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public boolean isSave() {
		return save;
	}
	public void setSave(boolean save) {
		this.save = save;
	}
	public boolean isPublish() {
		return publish;
	}
	public void setPublish(boolean publish) {
		this.publish = publish;
	}
	@Override
	public String toString() {
		return "FocusByFigure [focusByFigureId=" + focusByFigureId + ", sort=" + sort + ", title=" + title + ", link="
				+ link + ", imageUrl=" + imageUrl + ", save=" + save + ", publish=" + publish + "]";
	}
	
	
}
