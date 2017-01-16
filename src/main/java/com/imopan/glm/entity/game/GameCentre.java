package com.imopan.glm.entity.game;

import java.util.Date;
import java.util.List;

import org.mongodb.morphia.annotations.Entity;

/**
 * GL游戏库
 * @author
 *
 */
@Entity(value="GameCentre",noClassnameStored = true)
public class GameCentre extends BaseEntity {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 机锋游戏充值和登录的appKey
	 */
	private String appKey;

	/**
	 * 游戏名称
	 */
	private String gname;

	/**
	 * 游戏图标地址
	 */
	private String iconUrl;

	/**
	 * 游戏类型
	 */
	private String type;

	/**
	 * 游戏介绍
	 */
	private String intro;

	/**
	 * 游戏平台 android, ios
	 */
	private String platform;

	/**
	 * 游戏厂商
	 */
	private String manufacturer;

	/**
	 * 游戏下载地址
	 */
	private String downloadUrl;

	/**
	 * 游戏宣传图
	 */
	private String adsUrl;

	/**
	 * 游戏宣传视频
	 */
	private String adsVideoUrl;

	/**
	 * 游戏状态 1-上架，0-下架
	 */
	private String status;

	/**
	 * 机锋的游戏ID
	 */
	private String gfanId;

	/**
	 * 来源
	 */
	private String source;

	/**
	 * 游戏热度
	 */
	private Integer hot;

	/**
	 * 游戏截图
	 */
	private List<GamePicture> pics;

	/**
	 * 游戏关联的礼包
	 */
	private List<GameGoods> goods;

	/**
	 * 游戏关联的尖兵任务
	 */
	private List<GamePioneer> pioneers;
	/**
	 * 上架时间
	 */
	private Date putawayTime;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 更新时间
	 */
	private Date updateTime;

	/**
	 * 创建人

	 */
	private String createUser;

	/**
	 * 任务完成数
	 */
	private Integer finnum;

	/**
	 * 礼包领取数
	 */
	private Integer goodsgetnum;

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Integer getFinnum() {
		return finnum;
	}

	public void setFinnum(Integer finnum) {
		this.finnum = finnum;
	}

	public Integer getGoodsgetnum() {
		return goodsgetnum;
	}

	public void setGoodsgetnum(Integer goodsgetnum) {
		this.goodsgetnum = goodsgetnum;
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getGname() {
		return gname;
	}

	public void setGname(String gname) {
		this.gname = gname;
	}

	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getDownloadUrl() {
		return downloadUrl;
	}

	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}

	public String getAdsUrl() {
		return adsUrl;
	}

	public void setAdsUrl(String adsUrl) {
		this.adsUrl = adsUrl;
	}

	public String getAdsVideoUrl() {
		return adsVideoUrl;
	}

	public void setAdsVideoUrl(String adsVideoUrl) {
		this.adsVideoUrl = adsVideoUrl;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getGfanId() {
		return gfanId;
	}

	public void setGfanId(String gfanId) {
		this.gfanId = gfanId;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public List<GamePicture> getPics() {
		return pics;
	}

	public void setPics(List<GamePicture> pics) {
		this.pics = pics;
	}

	public Integer getHot() {
		return hot;
	}

	public void setHot(Integer hot) {
		this.hot = hot;
	}

	public List<GameGoods> getGoods() {
		return goods;
	}

	public void setGoods(List<GameGoods> goods) {
		this.goods = goods;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getPutawayTime() {
		return putawayTime;
	}

	public void setPutawayTime(Date putawayTime) {
		this.putawayTime = putawayTime;
	}
	public List<GamePioneer> getPioneers() {
		return pioneers;
	}

	public void setPioneers(List<GamePioneer> pioneers) {
		this.pioneers = pioneers;
	}
}

