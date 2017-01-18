package com.imopan.glm.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.Sort;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imopan.glm.bean.ResultBean;
import com.imopan.glm.bean.TableResult;
import com.imopan.glm.bean.TableSide;
import com.imopan.glm.entity.Feedback;
import com.imopan.glm.entity.GlUser;
import com.imopan.glm.service.IAdviceFeedbackService;

@Service
public class AdviceFeedbackServiceImpl implements IAdviceFeedbackService {

	@Autowired
	private Datastore datastore;
	
	@SuppressWarnings("deprecation")
	@Override
	public TableResult adviceFeedbackListService(Feedback feedback,Date startDate,Date endDate,TableSide tableSide) {
		Query<Feedback> query = datastore.createQuery(Feedback.class);
		
		List<String> uids = new ArrayList<>();
		if(feedback.getGlUser()!=null&&StringUtils.isNotBlank(feedback.getGlUser().getMobile())){
			List<GlUser> lists = datastore.createQuery(GlUser.class)
					.field("mobile").equal(feedback.getGlUser().getMobile()).asList();
			if(!lists.isEmpty()){
				for (GlUser glUser : lists) {
					uids.add(glUser.getId());
				}
			}
			query.field("uid").in(uids);
		}
		if(endDate!=null&&startDate!=null){
			query.field("insertDate").lessThanOrEq(endDate);
			query.field("insertDate").greaterThanOrEq(startDate);
		}
		
		if(StringUtils.isNotBlank(feedback.getNickname())){
			query.field("nickname").equal(feedback.getNickname());
		}
		if(StringUtils.isNotBlank(feedback.getFeedbackId())){
			ObjectId objectId = null;
			try {
				objectId = new ObjectId(feedback.getFeedbackId());
			} catch (IllegalArgumentException e) {
			}
			query.field("_id").equal(objectId);
		}
		if(StringUtils.isNotBlank(feedback.getStatus())){
			if(feedback.getStatus().equals("processed")){
				query.field("status").equal("processed");
			}else{
				query.field("status").notEqual("processed");
			}
		}
		
		long count = query.count();
		
		for (Sort sort : tableSide.getSorts()) {
			query.order(sort); 
		}
		query.offset(tableSide.getStart()).limit(tableSide.getLength());
		
		List<Feedback> feedbacks = query.asList();
		for (Feedback fb : feedbacks) {
			GlUser glUser = datastore.createQuery(GlUser.class)
					.field("_id").equal(new ObjectId(fb.getUid())).get();
			fb.setGlUser(glUser);
		}
		
		return new TableResult(tableSide.getDraw(), count, count, feedbacks);
	}

	@Override
	public ResultBean disposeAdviceFeedbackService(String adviceFeedbackId) {
		Query<Feedback> query = datastore.createQuery(Feedback.class).field("_id").equal(new ObjectId(adviceFeedbackId));
		UpdateOperations<Feedback> operation = datastore.createUpdateOperations(Feedback.class).set("status", "processed");
		UpdateResults results = datastore.update(query, operation);
		return new ResultBean(results);
	}

}
