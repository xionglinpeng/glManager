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
import com.imopan.glm.service.UgcManagementService;
import com.imopan.glm.vo.broadcast.BroadcastBean;

/** 
 * ClassName:UgcManagementAction <br/> 
 * Function: ugc管理 <br/>  
 * Date:     2017年1月10日 下午2:30:19 <br/> 
 * @author   zhangjiakun 
 * @version   
 * @since    JDK 1.7       
 */
@Controller
@RequestMapping("/ugcManagement")
public class UgcManagementAction {
	
	@Autowired
	private UgcManagementService ugcManagementServiceImpl;
	
	/**
	 * 
	 * ugcManagementLists:ugc列表. <br/> 
	 * 
	 * @author zhangjiakun
	 * @param broadcastBean
	 * @param request
	 * @return 
	 * @since JDK 1.7
	 */
	@RequestMapping(value="/lists",method=RequestMethod.GET)
	@ResponseBody
	public TableResult ugcManagementLists( BroadcastBean broadcastBean,HttpServletRequest request){
		TableSide tableSide = new TableSide(request);
		TableResult ugcManagementLists = ugcManagementServiceImpl.ugcManagementLists(broadcastBean, tableSide);
		return ugcManagementLists;
	}
	
	@RequestMapping(value="/closeBroadcast",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> closeBroadcast(@RequestBody Map<String,String> map){
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
		Integer i = ugcManagementServiceImpl.closeBroadcast(id,status);
		result.put("resultStatus", i);
		return result;
	}
	
	
	
	
	
}
