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

import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.FindOptions;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imopan.glm.bean.TableResult;
import com.imopan.glm.bean.TableSide;
import com.imopan.glm.entity.Broadcast;
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
		
		Integer start = tableSide.getStart();
		Integer length = tableSide.getLength();

		FindOptions fo = new FindOptions().skip(start).limit(length);
		
		//添加查询条件
		if(StringUtils.isNotBlank(broadcastBean.getContent())){
			query.field("content").contains(broadcastBean.getContent());
		}
		if(StringUtils.isNotBlank(broadcastBean.getBid())){
			query.filter("_id", new ObjectId(broadcastBean.getBid()));
		}
		if(broadcastBean.getUserInfo() != null && StringUtils.isNotBlank(broadcastBean.getUserInfo().getNickname())){
			query.field("userInfo.nickname").contains(broadcastBean.getUserInfo().getNickname());
		}
		if(broadcastBean.getUserInfo() != null && broadcastBean.getUserInfo().getConcernedGame() != null && broadcastBean.getUserInfo().getConcernedGame().size()>0 && StringUtils.isNoneEmpty(broadcastBean.getUserInfo().getConcernedGame().get(0).getGname())){
			query.field("userInfo.concernedGame.gname").contains(broadcastBean.getUserInfo().getConcernedGame().get(0).getGname());
		}
		if(broadcastBean.getIsReport() != null){
			query.field("isReport").equal(broadcastBean.getIsReport());
		}
		//状态查询设置，如果查询为1则查询状态为1的和不存在状态的（status为初始化），查询为2则查询状态为2的
		if(broadcastBean.getStatus() != null){
			if(broadcastBean.getStatus() == 1){
				query.or(
						query.criteria("status").equal(1), 
						query.criteria("status").doesNotExist()
				);
			}else if(broadcastBean.getStatus() == 2){
				query.or(
						query.criteria("status").equal(2)
				);
			}
		}
		List<Broadcast> asList = query.asList(fo);
		
		long count = datastore.createQuery(Broadcast.class).count();
		return new TableResult(tableSide.getDraw(), count, count, asList);
	}

	@Override
	public Integer closeBroadcast(String id,Integer status) {
		Query<Broadcast> query = datastore.createQuery(Broadcast.class);
		query.filter("_id", new ObjectId(id));
		UpdateOperations<Broadcast> createUpdateOperations = datastore.createUpdateOperations(Broadcast.class);
		createUpdateOperations.set("status", status);
		
		UpdateResults update = datastore.update(query, createUpdateOperations,true);
		int updatedCount = update.getUpdatedCount();
		return updatedCount;
	}

		
}
