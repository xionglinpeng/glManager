/** 
 * Project Name:game-link-manager 
 * File Name:GameAdvManagementServiceImpl.java 
 * Package Name:com.imopan.glm.service.impl 
 * Date:2017年1月13日下午3:39:57 
 * Copyright (c) 2017, zhangjiakun@imopan.com All Rights Reserved. 
 * 
 */

package com.imopan.glm.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
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
import com.imopan.glm.constant.Login;
import com.imopan.glm.entity.Broadcast;
import com.imopan.glm.entity.GameAdv;
import com.imopan.glm.entity.game.GameCentre;
import com.imopan.glm.service.GameAdvManagementService;
import com.imopan.glm.util.BeanUtil;
import com.imopan.glm.util.QiniuUploadUtil;
import com.imopan.glm.util.SpringWebUtil;
import com.imopan.glm.vo.GameAdvBean;

/**
 * ClassName:GameAdvManagementServiceImpl <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Date: 2017年1月13日 下午3:39:57 <br/>
 * 
 * @author zhangjiakun
 * @version
 * @since JDK 1.7
 */
@Service
public class GameAdvManagementServiceImpl implements GameAdvManagementService {

	@Autowired
	private Datastore datastore;
	
	@Override
	public TableResult gameAdvManagementLists(GameAdvBean gameAdv,
			TableSide tableSide) {
		Query<GameAdv> query = datastore.createQuery(GameAdv.class);

		Integer start = tableSide.getStart();
		Integer length = tableSide.getLength();

		FindOptions fo = new FindOptions().skip(start).limit(length);

		// 添加查询条件
		if (StringUtils.isNotBlank(gameAdv.getId())) {
			query.filter("_id", new ObjectId(gameAdv.getId()));
		}
		
		List<GameAdv> asList = query.asList(fo);

		long count = datastore.createQuery(Broadcast.class).count();
		return new TableResult(tableSide.getDraw(), count, count, asList);
	}

	@Override
	public Integer offlineGameAdv(String id, Integer status) {
		Query<GameAdv> query = datastore.createQuery(GameAdv.class);
		query.filter("_id", new ObjectId(id));
		UpdateOperations<GameAdv> createUpdateOperations = datastore.createUpdateOperations(GameAdv.class);
		createUpdateOperations.set("status", status);
		
		UpdateResults update = datastore.update(query, createUpdateOperations,true);
		int updatedCount = update.getUpdatedCount();
		return updatedCount;
	}

	@Override
	public List<GameCentre> getAllGame() {
		Query<GameCentre> query = datastore.createQuery(GameCentre.class);
		
		/*Integer start = 1;
		Integer length = 100;

		FindOptions fo = new FindOptions().skip(start).limit(length);*/
		
		List<GameCentre> asList = query.asList();
		return asList;
	}

	@Override
	public Map<String,Object> saveAssociatedGame(GameAdvBean gameAdv) {
		Map<String,Object> result = new HashMap<String, Object>();
		//将图片上传至七牛云
		String imageSrc = gameAdv.getImageSrc().split(",")[1];
		byte[] decodeBase64 = Base64.decodeBase64(imageSrc);
		String u = null;
		try {
			  u = QiniuUploadUtil.uploadUtil(decodeBase64, gameAdv.getFileName(), null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(u == null){
			result.put("code", 0);
			return result;
		}
		gameAdv.setAdvImg(u);
		//对象赋值
		GameAdv ga = new GameAdv();
		BeanUtil.copyProperties(gameAdv, ga);
		ga.getGame().setGid(gameAdv.getGid());
		ga.setInsertTime(new Date());
		String userName = SpringWebUtil.getSessionAttribute(Login.USER_EMAIL_KEY).toString();
		ga.setCreater(userName);
		//插入数据库
		try {
			datastore.save(ga);
			result.put("code", 1);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
