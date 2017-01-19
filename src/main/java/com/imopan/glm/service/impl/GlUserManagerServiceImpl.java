package com.imopan.glm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.Sort;
import org.mongodb.morphia.query.UpdateOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imopan.glm.bean.ResultBean;
import com.imopan.glm.bean.TableResult;
import com.imopan.glm.bean.TableSide;
import com.imopan.glm.entity.Attachement;
import com.imopan.glm.entity.Broadcast;
import com.imopan.glm.entity.GameGiftTask;
import com.imopan.glm.entity.GameTask;
import com.imopan.glm.entity.GlUser;
import com.imopan.glm.entity.UserStatus;
import com.imopan.glm.entity.game.GameCentre;
import com.imopan.glm.entity.game.GamePioneer;
import com.imopan.glm.entity.game.GameType;
import com.imopan.glm.service.IGlUserManagerService;

@Service
public class GlUserManagerServiceImpl implements IGlUserManagerService {
	
	@Autowired
	private Datastore datastore;
	
	@SuppressWarnings("deprecation")
	@Override
	public TableResult glUserListService(GlUser glUser,String gtName,TableSide tableSide) {
		Query<GlUser> query = datastore.createQuery(GlUser.class);
		
		if(StringUtils.isNotBlank(glUser.getNickname())){
			query.field("nickname").equal(glUser.getNickname());
		}
		if(StringUtils.isNotBlank(glUser.getMobile())){
			query.field("mobile").equal(glUser.getMobile());
		}
		if(glUser.getStatus()!=null){
			query.disableValidation().field("status").equal(glUser.getStatus());
		}
		if(StringUtils.isNotBlank(glUser.getGender())){
			query.field("gender").equal(glUser.getGender());
		}
		if(StringUtils.isNotBlank(gtName)){
			query.field("favoriteTypes.gtName").equal(gtName);
		}
		if(glUser.getPlayingGame()!=null&&StringUtils.isNotBlank(glUser.getPlayingGame().getGname())){
			query.field("playingGame.gname").equal(glUser.getPlayingGame().getGname());
		}
		
		
		long count = query.count();
		
		for (Sort sort : tableSide.getSorts()) {
			query.order(sort); 
		}
		query.offset(tableSide.getStart()).limit(tableSide.getLength());
		List<GlUser> glUsers = query.asList();
		return new TableResult(tableSide.getDraw(), count, count, glUsers);
	}

	
	@Override
	public ResultBean gameTypeService() {
		return new ResultBean(datastore.createQuery(GameType.class).asList());
	}
	
	
	
	
	@Override
	public ResultBean glUserForbidNormalService(UserStatus status, String userid) {
		Query<GlUser> query = datastore.createQuery(GlUser.class);
		query.field("_id").equal(new ObjectId(userid));
		UpdateOperations<GlUser> operations = datastore.createUpdateOperations(GlUser.class);
		if(status==null){
			status = UserStatus.NORMAL;
		}
		operations.disableValidation().set("status", status);
		datastore.update(query, operations);
		return new ResultBean("OK");
	}
	
	
	
	
	@Override
	public Map<String, Object> glUserDetailService(String userid) {
		Map<String, Object> result = new HashMap<>();
		//查询用户详情
		Query<GlUser> query = datastore.createQuery(GlUser.class);
		query.field("_id").equal(new ObjectId(userid));
		GlUser glUser = query.get();
		result.put("glUser", glUser);
		//查询用户相册数量
		Query<Attachement> photo = datastore.createQuery(Attachement.class);
		photo.disableValidation().field("uid").equal(userid);
		result.put("photo_num", photo.count());
		//查询用户动态数量
		Query<Broadcast> dynamic = datastore.createQuery(Broadcast.class);
		dynamic.disableValidation().field("userInfo.uid").equal(userid);
		result.put("dynamic_num", dynamic.count());
		//查询用户礼包数量
		Query<GameGiftTask> gameGiftTask = datastore.createQuery(GameGiftTask.class);
		gameGiftTask.field("uid").equal(userid);
		result.put("giftBag_num", gameGiftTask.count());
		//查询用户尖兵任务数量
		Query<GameTask> pioneer = datastore.createQuery(GameTask.class);
		pioneer.field("uid").equal(userid);
		result.put("pioneer_num", pioneer.count());
		
		return result;
	}



	@Override
	public ResultBean glUserPhotoService(String userid) {
		Query<Attachement> query = datastore.createQuery(Attachement.class);
		query.disableValidation().field("uid").equal(userid);
		List<Attachement> attachements = query.asList();
		return new ResultBean(attachements);
	}



	@Override
	public ResultBean glUserDynamicService(String userid) {
		Query<Broadcast> query = datastore.createQuery(Broadcast.class);
		query.disableValidation().field("userInfo.uid").equal(userid);
		List<Broadcast> broadcasts = query.asList();
		return new ResultBean(broadcasts);
	}

	
	
	@Override
	public ResultBean colseDynamicService(String dynamicId, String status) {
		Query<Broadcast> query = datastore.createQuery(Broadcast.class);
		query.disableValidation().field("_id").equal(new ObjectId(dynamicId));
		
		UpdateOperations<Broadcast> operations = datastore.createUpdateOperations(Broadcast.class);
		//值判断，防止传递错误的参数。
		if(!"2".equals(status)&&!"1".equals(status)){
			status = "1";
		}
		operations.set("status", status);
		datastore.update(query, operations);
		return new ResultBean("OK");
	}
	

	@Override
	public ResultBean glUserGiftBagService(String userid) {
		Query<GameGiftTask> query = datastore.createQuery(GameGiftTask.class);
		query.field("uid").equal(userid);
		List<GameGiftTask> GameGiftTasks = query.asList();
		return new ResultBean(GameGiftTasks);
	}



	@SuppressWarnings("deprecation")
	@Override
	public TableResult glUserPioneerService(GameTask gameTask,TableSide tableSide) {
		Query<GameTask> query = datastore.createQuery(GameTask.class);
		if(StringUtils.isNotBlank(gameTask.getStatus())){
			//2表示已经完成的尖兵任务
			if(gameTask.getStatus().equals("2")){
				query.field("status").equal(gameTask.getStatus());
			}else{
				query.field("status").notEqual('2');
			}
		}
		if(StringUtils.isNotBlank(gameTask.getUid())){
			query.field("uid").equal(gameTask.getUid());
		}
		
		long count = query.count();
		
		for (Sort sort : tableSide.getSorts()) {
			query.order(sort);
		}
		query.offset(tableSide.getStart()).limit(tableSide.getLength());
		
		List<GameTask> tasks = query.asList();
		
		return new TableResult(tableSide.getDraw(), count, count, tasks);
	}



	@Override
	public ResultBean pioneerDetailService(String pioneerGid) {
		Query<GameCentre> query = datastore.createQuery(GameCentre.class);
		query.field("_id").equal(new ObjectId(pioneerGid));
		GameCentre gameCentre = query.get();
		List<GamePioneer> pioneers = gameCentre.getPioneers();
		if(pioneers!=null&&!pioneers.isEmpty()){
			GamePioneer gamePioneer = null;
			for (GamePioneer pioneer : pioneers) {
				if(pioneer.getStatus().equals("1")){
					gamePioneer = pioneer;
					break;
				}
			}
			return new ResultBean(gamePioneer);
		}else{
			return new ResultBean(0);
		}
	}

}
