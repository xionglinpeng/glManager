package com.imopan.glm.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imopan.glm.bean.ResultBean;
import com.imopan.glm.entity.FocusByFigure;
import com.imopan.glm.entity.FocusByFigureUrl;
import com.imopan.glm.entity.Keywords;
import com.imopan.glm.service.IRecommendService;

@Service
public class RecommendServiceImpl implements IRecommendService {
	
	@Autowired
	private Datastore datastore;
	
	@Override
	public ResultBean saveKeywordService(List<Keywords> keywords) {
		//迭代所有上传的关键字组
		for (Keywords keyword : keywords) {
			//判断是否有KeywordId
			if(StringUtils.isNotBlank(keyword.getKeywordId()))
			{//有KeywordId，表示当前关键字组是数据库中已经有的关键字组。
			//所以执行修改或删除操作
				Query<Keywords> query = datastore.createQuery(Keywords.class)
						.field("_id").equal(new ObjectId(keyword.getKeywordId()));
				if(keyword.getSave())
				{//修改当前关键字组。
					UpdateOperations<Keywords> operations = datastore.createUpdateOperations(Keywords.class);
					operations.set("keyword1", keyword.getKeyword1());
					operations.set("keyword2", keyword.getKeyword2());
					operations.set("keyword3", keyword.getKeyword3());
					operations.set("save", keyword.getSave());
					operations.set("publish", keyword.getPublish());
					operations.set("sort", keyword.getSort());
					datastore.update(query, operations);
				}
				else
				{//delete为ture，表示要删除当前关键字组。
					datastore.delete(query);
				}
			}
			else
			{//没有KeywordId，表示新增加的关键字组，执行保存操作。
				if(StringUtils.isNotBlank(keyword.getKeyword1())&&
						StringUtils.isNotBlank(keyword.getKeyword2())&&
						StringUtils.isNotBlank(keyword.getKeyword3())){
					datastore.save(keyword);
				}
			}
		}
		return new ResultBean("OK");
	}

	@Override
	public ResultBean getKeywordService() {
		return new ResultBean(datastore.createQuery(Keywords.class).order("sort").asList());
	}
	
	
	
	@Override
	public ResultBean getFocusByFigureService() {
		return new ResultBean(datastore.createQuery(FocusByFigure.class).order("sort").asList());
	}

	
	
	@Override
	public ResultBean saveFocusByFigureService(List<FocusByFigure> focusByFigures) {
		//迭代所有上传的焦点轮播图组
		for (FocusByFigure focusByFigure : focusByFigures) {
			//判断是否有FocusByFigureId
			if(StringUtils.isNotBlank(focusByFigure.getFocusByFigureId()))
			{//有FocusByFigureId，表示当前焦点轮播图组是数据库中已经有的焦点轮播图组。
			//所以执行修改或删除操作
				Query<FocusByFigure> query = datastore.createQuery(FocusByFigure.class)
						.field("_id").equal(new ObjectId(focusByFigure.getFocusByFigureId()));
				if(focusByFigure.isSave())
				{//修改当前焦点轮播图组。
					UpdateOperations<FocusByFigure> operations = datastore.createUpdateOperations(FocusByFigure.class);
					operations.set("title", focusByFigure.getTitle());
					operations.set("link", focusByFigure.getLink());
					operations.set("imageUrl", focusByFigure.getImageUrl());
					operations.set("save", focusByFigure.isSave());
					operations.set("publish", focusByFigure.isPublish());
					operations.set("sort", focusByFigure.getSort());
					datastore.update(query, operations);
				}
				else
				{//delete为ture，表示要删除当前焦点轮播图组。
					datastore.delete(query);
				}
			}
			else
			{//没有FocusByFigureId，表示新增加的焦点轮播图组，执行保存操作。
				if(StringUtils.isNotBlank(focusByFigure.getTitle())&&
						StringUtils.isNotBlank(focusByFigure.getLink())&&
						StringUtils.isNotBlank(focusByFigure.getImageUrl())){
					datastore.save(focusByFigure);
				}
			}
		}
		return new ResultBean("OK");
	}
	
	
	@Override
	public ResultBean focusByFigureImageService() {
		return new ResultBean(datastore.createQuery(FocusByFigureUrl.class).asList());
	}
	
	

	@Override
	public ResultBean saveFocusByFigureUrlService(String imageUrl) {
		FocusByFigureUrl url = new FocusByFigureUrl();
		url.setFocusByFigureUrl(imageUrl);
		datastore.save(url);
		return new ResultBean("OK");
	}

}
