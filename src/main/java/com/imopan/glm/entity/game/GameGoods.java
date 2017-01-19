package com.imopan.glm.entity.game;

import java.util.Date;

/**
 * 游戏礼包, 游戏 gameCentre中的一个属性
 * @author 
 *
 */
public class GameGoods {
	

	/**
	 * 礼包ID
	 */
	private String goodsId;
	/**
	 * 旧礼包ID
	 */
	private String oldGoodsId;
	/**
	 * 游戏id
	 */
	private String gid;

	/**
	 * 游戏名称
	 */
	private String gname;

	/**
	 * 礼包名称
	 */
	private String goodsName;
	
	/**
	 * 1-置顶, 0-不置顶
	 */
	private Integer isTop;
	
	/**
	 * 状态 1-上架, 0-下架
	 */
	private Integer status;
	
	/**
	 * 更新时间
	 */
	private Date updateTime;

	/**
	 * 更新时间
	 */
	private Date finishTime;

	public String getGid() {
		return gid;
	}

	public void setGid(String gid) {
		this.gid = gid;
	}

	public String getOldGoodsId() {
		return oldGoodsId;
	}

	public void setOldGoodsId(String oldGoodsId) {
		this.oldGoodsId = oldGoodsId;
	}

	public String getGname() {
		return gname;
	}

	public void setGname(String gname) {
		this.gname = gname;
	}

	public Date getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Date finishTime) {
		finishTime = finishTime;
	}

	public Integer getIsTop() {
		return isTop;
	}

	public void setIsTop(Integer isTop) {
		this.isTop = isTop;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	
	

}
