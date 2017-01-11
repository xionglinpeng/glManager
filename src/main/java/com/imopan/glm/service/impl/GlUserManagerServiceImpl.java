package com.imopan.glm.service.impl;

import java.util.HashMap;
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
import com.imopan.glm.entity.GlUser;
import com.imopan.glm.service.GlUserManagerService;

@Service
public class GlUserManagerServiceImpl implements GlUserManagerService {
	
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
		if(StringUtils.isNotBlank(glUser.getStatus())){
			query.field("status").equal(glUser.getStatus());
		}
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
		Query<GlUser> query = datastore.createQuery(GlUser.class);
		query.field("_id").equal(new ObjectId(userid));
		GlUser glUser = query.get();
		System.out.println(glUser);
		result.put("glUser", glUser);
		
		return result;
	}

}
