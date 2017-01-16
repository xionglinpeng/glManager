package com.imopan.glm.service.impl;

import com.imopan.glm.bean.ResultBean;
import com.imopan.glm.bean.TableResult;
import com.imopan.glm.bean.TableSide;
import com.imopan.glm.constant.Login;
import com.imopan.glm.entity.game.GameCentre;
import com.imopan.glm.entity.game.GamePioneer;
import com.imopan.glm.entity.game.ParamVo;
import com.imopan.glm.service.IGameService;
import com.imopan.glm.util.SpringWebUtil;
import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Service
public class GameServiceImpl implements IGameService {
	
	@Autowired
	private Datastore datastore;

	@Override
	public TableResult getGameList(ParamVo pv, TableSide tableSide) {
		Query<GameCentre> query = datastore.createQuery(GameCentre.class);

		Integer start = tableSide.getStart();
		Integer length = tableSide.getLength();

		FindOptions fo = new FindOptions().skip(start).limit(length);
		//游戏名称like
		if(StringUtils.isNotBlank(pv.getGameName())){
			query.field("gname").contains(pv.getGameName());
		}
		if(StringUtils.isNotBlank(pv.getStatus())){
			//Valid operators are ["=", "==","!=", "<>", ">", "<", ">=", "<=", "in", "nin", "all", "size", "exists"]
			query.filter("status =",pv.getStatus());
		}

		long count = query.count();
		query.order("-putawayTime");
		List<GameCentre> gameCentres = query.asList(fo);

		return new TableResult(tableSide.getDraw(), count, count, gameCentres);

	}

	@Override
	public TableResult getGamePioneerList(ParamVo pv, TableSide tableSide) {
        Query<GameCentre> query = datastore.find(GameCentre.class);
		Query<GameCentre> query1 = datastore.find(GameCentre.class);
		Integer start = tableSide.getStart();
		Integer length = tableSide.getLength();
		if(StringUtils.isNotBlank(pv.getGid())){
			query.filter("_id", new ObjectId(pv.getGid()));
			query1.filter("_id", new ObjectId(pv.getGid()));
		}
		GameCentre gameCentre = query.get();
		int size = gameCentre.getPioneers().size();
		query1.project("pioneers",new ArraySlice(start,length));
		//TODO 排序
		GameCentre gameCentre1 = query1.get();
		List<GamePioneer> pioneers = gameCentre1.getPioneers();
		for (GamePioneer gp:pioneers) {
			gp.setGname(gameCentre.getGname());
		}
		return new TableResult(tableSide.getDraw(), size, size, pioneers);
	}

	@Override
	public ResultBean doGamePioneer(GamePioneer pv) {
		boolean hasOnLine = isHasOnLine(pv);
		if (hasOnLine){
			pv.setStatus("0");
		}
		Query<GameCentre> query = datastore.createQuery(GameCentre.class);

		if(StringUtils.isNotBlank(pv.getGid())){
			query.filter("_id", new ObjectId(pv.getGid()));
		}
		UpdateOperations<GameCentre> ops = datastore.createUpdateOperations(GameCentre.class);
		if(StringUtils.isNotBlank(pv.getPid())){//存在pid 修改
			//
			pv.setUpdateTime(new Date());
			GamePioneer gamePioneerForRemove = new GamePioneer();
			gamePioneerForRemove.setPid(pv.getPid());
			ops.removeAll("pioneers", gamePioneerForRemove);
			datastore.update(query, ops);
			UpdateOperations<GameCentre> ops1 = datastore.createUpdateOperations(GameCentre.class);
			ops1.push("pioneers", pv);
			datastore.update(query, ops1);

		}else{//不存在pid 添加
			pv.setCreateTime(new Date());
			String username = "";
			HttpSession session = SpringWebUtil.getSession();
			if(session!=null){
				Object attribute = session.getAttribute(Login.USER_EMAIL_KEY);
				username = attribute==null?"":attribute.toString();
			}
			pv.setCreateUser(username);
			pv.setPid(new ObjectId().toString());
			ops.push("pioneers", pv);
			datastore.update(query, ops);
		}
		if (hasOnLine){
			return new ResultBean("2");
		}
		return new ResultBean("1");

	}

	@Override
	public ResultBean getGamePioneer(GamePioneer pv) {
		Query<GameCentre> query = datastore.createQuery(GameCentre.class);
		if(StringUtils.isNotBlank(pv.getGid())){
			query.filter("_id", new ObjectId(pv.getGid()));
		}
		Query<GamePioneer> query1 = datastore.createQuery(GamePioneer.class);
		if(StringUtils.isNotBlank(pv.getPid())){
			query1.filter("pid = ", pv.getPid());
		}
		query.field("pioneers").elemMatch(query1);
		query.project("pioneers.$",true);
		GameCentre gameCentre = query.get();
		GamePioneer gamePioneer = gameCentre.getPioneers().get(0);
		return new ResultBean(gamePioneer);
	}

	@Override
	public ResultBean doGamePioneerStatus(GamePioneer pv) {
		// 任务上线   预先判断是否有已经上线的尖兵 如果有给出提示
		if (isHasOnLine(pv)) return new ResultBean("2");

		//修改状态操作
		Query<GameCentre> query = datastore.createQuery(GameCentre.class);
		if(StringUtils.isNotBlank(pv.getGid())){
			query.filter("_id", new ObjectId(pv.getGid()));
		}
		if(StringUtils.isNotBlank(pv.getPid())){
			query.filter("pioneers.pid", pv.getPid());
		}
		UpdateOperations<GameCentre> ops = datastore.createUpdateOperations(GameCentre.class);
		ops.set("pioneers.$.status",pv.getStatus());
		datastore.update(query, ops);
		return new ResultBean("1");
	}
	/*
		是否有上线状态的游戏尖兵 用于上线前校验
	 */
	private boolean isHasOnLine(GamePioneer pv) {
		if("1".equals(pv.getStatus())){
			Query<GameCentre> query1 = datastore.createQuery(GameCentre.class);
			if(StringUtils.isNotBlank(pv.getGid())){
				query1.filter("_id", new ObjectId(pv.getGid()));
			}
			query1.filter("pioneers.status", "1");
			query1.project("pioneers.$",true);

			GameCentre gameCentre = query1.get();
			if(gameCentre!=null&&gameCentre.getPioneers()!=null&&gameCentre.getPioneers().size()>=1){
				return true;
			}
		}
		return false;
	}
}
