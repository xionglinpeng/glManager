package com.imopan.glm.entity;

import java.util.List;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity(value="User",noClassnameStored=false)
public class GlUser {
	
	@Id
	private String id;
	/**
	 * <p>用户昵称</p>
	 */
	private String nickname;
	/**
	 * <p>用户手机号</p>
	 */
	private String mobile;
	/**
	 *  用户密码
	 */
	private String password;
	/**
	 * <p>用户状态</p>
	 */
	private String status;
	/**
	 * <p>用户正在玩的游戏=>对应所属游戏</p>
	 */
	private Game playingGame;
	
	/**
	 * <p>用户关注的游戏=>对应所属游戏</p>
	 */
	private List<Game> concernedGame;
	/**
	 * <p>用户头像</p>
	 */
	private String avatarImage;
	/**
	 * <p>用户交友宣言</p>
	 */
    private String motto;
    /**
     * <p>游戏类型</p>
     */
    private List<String> favoriteTypes;
	/**
	 * <p>用户性别 </p>
	 *  M 男性   F 女性
	 */
	private String gender;
	/**
     * <p>正在玩游戏的服务器</p>
     */
	private String gameServer;
	/**
     * <p>正在玩游戏的角色昵称</p>
     */
	private String gameNickname;
	/**
	 * <p>用户绑定的机锋账户名称</p>
	 */
    private String gfAccount;
    /**
	 * <p>本地用户与云信服务交互的token</p>
	 */
	private String yunxinToken;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Game getPlayingGame() {
		return playingGame;
	}
	public void setPlayingGame(Game playingGame) {
		this.playingGame = playingGame;
	}
	public List<Game> getConcernedGame() {
		return concernedGame;
	}
	public void setConcernedGame(List<Game> concernedGame) {
		this.concernedGame = concernedGame;
	}
	public String getAvatarImage() {
		return avatarImage;
	}
	public void setAvatarImage(String avatarImage) {
		this.avatarImage = avatarImage;
	}
	public String getMotto() {
		return motto;
	}
	public void setMotto(String motto) {
		this.motto = motto;
	}
	public List<String> getFavoriteTypes() {
		return favoriteTypes;
	}
	public void setFavoriteTypes(List<String> favoriteTypes) {
		this.favoriteTypes = favoriteTypes;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getGameServer() {
		return gameServer;
	}
	public void setGameServer(String gameServer) {
		this.gameServer = gameServer;
	}
	public String getGameNickname() {
		return gameNickname;
	}
	public void setGameNickname(String gameNickname) {
		this.gameNickname = gameNickname;
	}
	public String getGfAccount() {
		return gfAccount;
	}
	public void setGfAccount(String gfAccount) {
		this.gfAccount = gfAccount;
	}
	public String getYunxinToken() {
		return yunxinToken;
	}
	public void setYunxinToken(String yunxinToken) {
		this.yunxinToken = yunxinToken;
	}
	@Override
	public String toString() {
		return "GlUser [id=" + id + ", nickname=" + nickname + ", mobile=" + mobile + ", password=" + password
				+ ", status=" + status + ", playingGame=" + playingGame + ", concernedGame=" + concernedGame
				+ ", avatarImage=" + avatarImage + ", motto=" + motto + ", favoriteTypes=" + favoriteTypes + ", gender="
				+ gender + ", gameServer=" + gameServer + ", gameNickname=" + gameNickname + ", gfAccount=" + gfAccount
				+ ", yunxinToken=" + yunxinToken + "]";
	}
	
	
	
	
	
	
	
}
