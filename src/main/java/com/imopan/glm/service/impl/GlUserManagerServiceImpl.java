package com.imopan.glm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imopan.glm.bean.TableResult;
import com.imopan.glm.bean.TableSide;
import com.imopan.glm.entity.Attachement;
import com.imopan.glm.entity.Broadcast;
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
//		if(StringUtils.isNotBlank(glUser.getGender())){
//			query.field("gender").equal(glUser.getGender());
//		}
		
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
		GlUser glUser = getByGlUserId(new ObjectId(userid));
		System.out.println(glUser);
		result.put("glUser", glUser);
		
		List<Attachement> attachements = getByAttachementUid(userid);
		result.put("attachements", attachements);
		
		List<Broadcast> broadcasts = getByBroadcastUid(userid);
		result.put("broadcasts", broadcasts);
		
		return result;
	}
	
	private GlUser getByGlUserId(ObjectId objectId){
		Query<GlUser> query = datastore.createQuery(GlUser.class);
		query.field("_id").equal(objectId);
		return query.get();
	}
	
	private List<Attachement> getByAttachementUid(String uid){
		Query<Attachement> query = datastore.createQuery(Attachement.class);
		query.disableValidation().field("uid").equal(uid);
		return query.asList();
	}
	
	private List<Broadcast> getByBroadcastUid(String uid){
		Query<Broadcast> query = datastore.createQuery(Broadcast.class);
		query.disableValidation().field("userInfo.uid").equal(uid);
		return query.asList();
	}

}
