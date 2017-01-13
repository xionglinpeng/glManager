package com.imopan.glm.service.impl;

import java.util.List;

import org.mongodb.morphia.Datastore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imopan.glm.bean.ResultBean;
import com.imopan.glm.entity.Keywords;
import com.imopan.glm.service.IRecommendService;

@Service
public class RecommendServiceImpl implements IRecommendService {
	
	@Autowired
	private Datastore datastore;
	
	@Override
	public ResultBean saveKeywordService(List<Keywords> keywords) {
		
		for (Keywords keyword : keywords) {
			datastore.save(keyword);
		}
		return new ResultBean("OK");
	}

	@Override
	public ResultBean getKeywordService() {
		return new ResultBean(datastore.createQuery(Keywords.class).asList());
	}

}
