/** 
 * Project Name:game-link-manager 
 * File Name:UgcManagementServiceImpl.java 
 * Package Name:com.imopan.glm.service.impl 
 * Date:2017年1月10日下午2:46:11 
 * Copyright (c) 2017, zhangjiakun@imopan.com All Rights Reserved. 
 * 
*/ 

package com.imopan.glm.service.impl;

import java.util.List;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imopan.glm.bean.TableResult;
import com.imopan.glm.bean.TableSide;
import com.imopan.glm.entity.Broadcast;
import com.imopan.glm.entity.GlUser;
import com.imopan.glm.service.UgcManagementService;
import com.imopan.glm.vo.broadcast.BroadcastBean;

/** 
 * ClassName:UgcManagementServiceImpl <br/> 
 * Function: TODO ADD FUNCTION. <br/>  
 * Date:     2017年1月10日 下午2:46:11 <br/> 
 * @author   zhangjiakun 
 * @version   
 * @since    JDK 1.7       
 */
@Service
public class UgcManagementServiceImpl implements UgcManagementService{

	@Autowired
	private Datastore datastore;
	
	@Override
	public TableResult ugcManagementLists(BroadcastBean broadcastBean,
			TableSide tableSide) {
		Query<Broadcast> query = datastore.createQuery(Broadcast.class);
		List<Broadcast> asList = query.asList();
		
		long count = datastore.createQuery(Broadcast.class).count();
		return new TableResult(tableSide.getDraw(), count, count, asList);
	}

		
}
