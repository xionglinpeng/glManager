package com.imopan.glm.service.impl;

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
		long count = datastore.createQuery(GlUser.class).count();
		return new TableResult(tableSide.getDraw(), count, count, query.asList().toArray());
	}

}
