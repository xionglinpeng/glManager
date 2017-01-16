/** 
 * Project Name:game-link-manager 
 * File Name:UgcManagementAction.java 
 * Package Name:com.imopan.glm.action.ugcmanagement 
 * Date:2017年1月10日下午2:30:19 
 * Copyright (c) 2017, zhangjiakun@imopan.com All Rights Reserved. 
 * 
*/ 

package com.imopan.glm.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.imopan.glm.bean.TableResult;
import com.imopan.glm.bean.TableSide;
import com.imopan.glm.service.GameAdvManagementService;
import com.imopan.glm.vo.GameAdvBean;

/** 
 * ClassName:UgcManagementAction <br/> 
 * Function: 专题管理 <br/>  
 * Date:     2017年1月10日 下午2:30:19 <br/> 
 * @author   zhangjiakun 
 * @version   
 * @since    JDK 1.7       
 */
@Controller
@RequestMapping("/gameAdvManagement")
public class GameAdvManagementAction {
	
	@Autowired
	private GameAdvManagementService gameAdvManagementServiceImpl;
	
	/**
	 * 
	 * gameAdvManagementLists:专题列表. <br/> 
	 * 
	 * @author zhangjiakun
	 * @param gameAdv
	 * @param request
	 * @return 
	 * @since JDK 1.7
	 */
	@RequestMapping(value="/lists",method=RequestMethod.GET)
	@ResponseBody
	public TableResult gameAdvManagementLists( GameAdvBean gameAdv,HttpServletRequest request){
		TableSide tableSide = new TableSide(request);
		TableResult list =  gameAdvManagementServiceImpl.gameAdvManagementLists(gameAdv, tableSide);
		return list;
	}
	
	/**
	 * 
	 * offlineGameAdv:下架. <br/> 
	 * 
	 * @author zhangjiakun
	 * @param map
	 * @return 
	 * @since JDK 1.7
	 */
	@RequestMapping(value="/offlineGameAdv",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> offlineGameAdv(@RequestBody Map<String,String> map){
		Map<String,Object> result = new HashMap<String, Object>(16,0.75f);
		String id = "";
		Integer status = 0;
		if(map.containsKey("id")){
			 id = map.get("id");
		}
		if(map.containsKey("status")){
			 status = Integer.valueOf(map.get("status"));
		}
		if(id == "" && status == 0){
			result.put("resultStatus", 0);
			return result;
		}
		Integer i = gameAdvManagementServiceImpl.offlineGameAdv(id,status);
		result.put("resultStatus", i);
		return result;
	}
	
	/**
	 * 
	 * getAllGame:获取所有的游戏. <br/> 
	 * 
	 * @author zhangjiakun
	 * @return 
	 * @since JDK 1.7
	 */
	@RequestMapping(value="/getAllGame",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> getAllGame(){
		Map<String,Object> result = new HashMap<String, Object>(16,0.75f);
		
		return null;
	}
	
}
