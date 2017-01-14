package com.imopan.glm.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.imopan.glm.bean.ResultBean;
import com.imopan.glm.bean.TableResult;
import com.imopan.glm.bean.TableSide;
import com.imopan.glm.entity.GlUser;
import com.imopan.glm.service.IGlUserManagerService;

/**
 * <p>用户管理</p>
 * @author xlp
 *
 */
@Controller
@RequestMapping("/glUser")
public class GlUserManagerAction {
	
	@Autowired
	private IGlUserManagerService iGlUserManagerService;
	
	
	
	/**
	 * <p>用户列表</p>
	 * @param glUser 查询条件
	 * @param request 表格参数
	 * @return
	 */
	@RequestMapping(value="/lists",method=RequestMethod.GET)
	@ResponseBody
	public TableResult glUserLists(GlUser glUser,HttpServletRequest request){
		TableSide tableSide = new TableSide(request);
		return iGlUserManagerService.glUserListService(glUser, tableSide);
	}
	
	
	
	
	
	
	/**
	 * <p>用户详情。</p>
	 * @param userid 当前用户集合的_id。
	 * @return
	 */
	@RequestMapping(value="/glUserDetail",method=RequestMethod.GET)
	@ResponseBody
	public ResultBean glUserDetail(@RequestParam("userid")String userid){
		return new ResultBean(iGlUserManagerService.glUserDetailService(userid));
	}
	
	
	
	
	@RequestMapping(value="/photo",method=RequestMethod.GET)
	@ResponseBody
	public ResultBean photo(){
		
		return null;
	}
	
	
	
	
	@RequestMapping(value="/dynamic",method=RequestMethod.GET)
	@ResponseBody
	public ResultBean dynamic(){
		
		return null;
	}
	
	
	
	
	
	@RequestMapping(value="/giftBag",method=RequestMethod.GET)
	@ResponseBody
	public ResultBean giftBag(){
		
		return null;
	}
	
	
	
	
	
	@RequestMapping(value="/pioneer",method=RequestMethod.GET)
	@ResponseBody
	public ResultBean pioneer(){
		
		return null;
	}
}
