package com.imopan.glm.vo.user;

import java.util.List;

import com.imopan.glm.entity.LGame;
import com.imopan.glm.vo.PersistentObject;

/**
 * 用户bean
 * 
 *
 * UserBean
 *
 * @author bin
 *
 * @since 2016年12月27日 下午3:14:15
 *
 * @version 1.0.0
 */
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserBean extends PersistentObject{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -6072882102457142987L;

	/**
	 * 用户id
	 */
	private String uid;
	
	/**
	 * 用户手机号码
	 */
	private String mobile;
	
	/**
	 * 验证码
	 */
	private String verifyCode;
	
	/**
	 * 密码
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
	private String avatarImage;
	/**
	 *  用户交友宣言
	 */
    private String motto;
    
    /**
	 *  用户地理位置信息
	 */
    private String region;
    
    /**
     * 用户喜欢的游戏类型
     */
    private List<String> favoriteTypes;
    
    /**
     * 正在玩游戏的服务器
     */
	private String gameServer;
	
	/**
     * 正在玩游戏的角色昵称
     */
	private String gameNickname;
	

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
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

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public List<String> getFavoriteTypes() {
		return favoriteTypes;
	}

	public void setFavoriteTypes(List<String> favoriteTypes) {
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
	
}
