package com.imopan.glm.entity;

import java.util.Date;
import java.util.List;

import org.mongodb.morphia.annotations.Entity;

@Entity(value="User",noClassnameStored=false)
public class GlUser extends BaseEntity{
	/**
	 *  serialVersionUID
	 */
	private static final long serialVersionUID = 6651127687765149086L;
	
	/**
	 *  用户手机号
	 */
	private String mobile;
	/**
	 *  用户密码
	 */
	private String password;
	
	/**
	 *  用户昵称
	 */
	private String nickname;
	
	/**
	 *  用户性别  
	 *  M 男性   F 女性
	 */
	private String gender;  
	
	/**
	 *  用户正在玩的游戏
	 */
	private LGame playingGame;
	
	/**
	 *  用户关注的游戏
	 */
	private List<LGame> concernedGame;
	
	/**
	 *  用户头像
	 */
	private Attachement avatarImage;
	/**
	 *  用户交友宣言
	 */
    private String motto;
    
    /**
	 *  用户地理位置信息
	 */
    private String region;
    /**
	 *  用户绑定的机锋账户名称
	 */
    private String gfAccount;
    
    /**
     * 用户喜欢的游戏类型
     */
    private List<GameType> favoriteTypes;
    
    /**
     * 正在玩游戏的服务器
     */
	private String gameServer;
	
	/**
     * 正在玩游戏的角色昵称
     */
	private String gameNickname;
	
	/**
	 * 本地用户与云信服务交互的token
	 */
	private String yunxinToken;
	
	/**
	 * 用户的状态
	 */
	private UserStatus status;
	
	/**
	 * 用户创建时间
	 */
	private Date create;

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

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public LGame getPlayingGame() {
		return playingGame;
	}

	public void setPlayingGame(LGame playingGame) {
		this.playingGame = playingGame;
	}

	public List<LGame> getConcernedGame() {
		return concernedGame;
	}

	public void setConcernedGame(List<LGame> concernedGame) {
		this.concernedGame = concernedGame;
	}

	public Attachement getAvatarImage() {
		return avatarImage;
	}

	public void setAvatarImage(Attachement avatarImage) {
		this.avatarImage = avatarImage;
	}

	public String getMotto() {
		return motto;
	}

	public void setMotto(String motto) {
		this.motto = motto;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getGfAccount() {
		return gfAccount;
	}

	public void setGfAccount(String gfAccount) {
		this.gfAccount = gfAccount;
	}

	public List<GameType> getFavoriteTypes() {
		return favoriteTypes;
	}

	public void setFavoriteTypes(List<GameType> favoriteTypes) {
		this.favoriteTypes = favoriteTypes;
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

	public String getYunxinToken() {
		return yunxinToken;
	}

	public void setYunxinToken(String yunxinToken) {
		this.yunxinToken = yunxinToken;
	}

	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}

	public Date getCreate() {
		return create;
	}

	public void setCreate(Date create) {
		this.create = create;
	}
	
}
