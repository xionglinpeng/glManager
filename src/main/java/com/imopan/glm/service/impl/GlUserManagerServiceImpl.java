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
import com.imopan.glm.entity.GameTask;
import com.imopan.glm.entity.GlUser;
import com.imopan.glm.service.IGlUserManagerService;

@Service
public class GlUserManagerServiceImpl implements IGlUserManagerService {
	
	@Autowired
	private Datastore datastore;
	
	@SuppressWarnings("deprecation")
	@Override
	public TableResult glUserListService(GlUser glUser, TableSide tableSide) {
		Query<GlUser> query = datastore.createQuery(GlUser.class);
		
		if(StringUtils.isNotBlank(glUser.getNickname())){
			query.field("nickname").equal(glUser.getNickname());
		}
		if(StringUtils.isNotBlank(glUser.getMobile())){
			query.field("mobile").equal(glUser.getMobile());
		}
//		if(StringUtils.isNotBlank(glUser.getStauts().value())){
//			query.field("status").equal(glUser.getStauts().value());
//		}
		if(StringUtils.isNotBlank(glUser.getGender())){
			query.field("gender").equal(glUser.getGender());
		}
		
		long count = query.count();
		
		for (Sort sort : tableSide.getSorts()) {
			query.order(sort); 
		}
		query.offset(tableSide.getStart()).limit(tableSide.getLength());
		
		return new TableResult(tableSide.getDraw(), count, count, query.asList());
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
	public ResultBean glUserGiftBagService() {
		// TODO Auto-generated method stub
		return null;
	}



	@SuppressWarnings("deprecation")
	@Override
	public TableResult glUserPioneerService(GameTask gameTask,TableSide tableSide) {
		Query<GameTask> query = datastore.createQuery(GameTask.class);
		if(StringUtils.isNotBlank(gameTask.getStatus())){
			//2表示已经完成的尖兵任务
			if(gameTask.getUid().equals("2")){
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

}
