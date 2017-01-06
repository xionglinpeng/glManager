package com.imopan.glm.entity;

import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity(value="User")
public class GlUser {
	
	@Id
	private ObjectId id;
	/**
	 * <p>用户昵称</p>
	 */
	private String nickname;
	/**
	 * <p>用户手机号</p>
	 */
	private String mobile;
	
	/**
	 * <p>用户正在玩的游戏=>对应所属游戏</p>
	 */
	private Game playingGame;
	
	/**
	 * <p>用户关注的游戏=>对应所属游戏</p>
	 */
	private List<Game> concernedGame;
    /**
     * <p>游戏类型</p>
     */
    private List<String> favoriteTypes;
	/**
	 * <p>用户性别 </p>
	 *  M 男性   F 女性
	 */
	private String gender;
	
	
	
	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
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
	
	
	
	
}
