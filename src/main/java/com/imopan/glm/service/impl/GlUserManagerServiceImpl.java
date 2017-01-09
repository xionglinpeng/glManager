package com.imopan.glm.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
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
	
	@Override
	public TableResult glUserListService(GlUser glUser, TableSide tableSide) {
		Query<GlUser> query = datastore.createQuery(GlUser.class);
		
		if(StringUtils.isNotBlank(glUser.getNickname())){
			query.field("nickname").equal(glUser.getNickname());
		}
		if(StringUtils.isNotBlank(glUser.getMobile())){
			query.field("mobile").equal(glUser.getMobile());
		}
		
		
		if(StringUtils.isNotBlank(glUser.getGender())){
			query.field("gender").equal(glUser.getGender());
		}
		
		
		
		
		
		
		long count = datastore.createQuery(GlUser.class).count();
		return new TableResult(tableSide.getDraw(), count, count, query.asList());
	}

}
