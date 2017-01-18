package com.imopan.glm.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.imopan.glm.bean.ResultBean;
import com.imopan.glm.bean.TableResult;
import com.imopan.glm.bean.TableSide;
import com.imopan.glm.entity.GameTask;
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
	public TableResult glUserLists(GlUser glUser,HttpServletRequest request,
			@RequestParam(value="gtName",required=false)String gtName){
		TableSide tableSide = new TableSide(request);
		return iGlUserManagerService.glUserListService(glUser, gtName, tableSide);
	}
	
	
	
	/**
	 * <p>获取所有游戏类型</p>
	 * @return
	 */
	@RequestMapping(value="/gameType",method=RequestMethod.GET)
	@ResponseBody
	public ResultBean gameType(){
		return iGlUserManagerService.gameTypeService();
	}
	
	
	
	
	/**
	 * <p>封禁或者解封用户状态。</p>
	 * @param status 状态。
	 * @param userid 指定用户。
	 * @return
	 */
	@RequestMapping(value="/forbidNormal/{status}/{userid}",method=RequestMethod.PUT)
	@ResponseBody
	public ResultBean glUserForbidNormal(@PathVariable("status")String status,
			@PathVariable("userid")String userid){
		return iGlUserManagerService.glUserForbidNormalService(status, userid);
	}
	
	
	
	
	
	/**
	 * <p>用户详情。</p>
	 * @param userid 当前用户集合的_id。
	 * @return
	 */
	@RequestMapping(value="/glUserDetail/{userid}",method=RequestMethod.GET)
	@ResponseBody
	public ResultBean glUserDetail(@PathVariable("userid")String userid){
		return new ResultBean(iGlUserManagerService.glUserDetailService(userid));
	}
	
	
	
	/**
	 * <p>获取指定用户的相册。</p>
	 * @param userid 指定的用户。
	 * @return
	 */
	@RequestMapping(value="/photo/{userid}",method=RequestMethod.GET)
	@ResponseBody
	public ResultBean photo(@PathVariable("userid")String userid){
		return iGlUserManagerService.glUserPhotoService(userid);
	}
	
	
	
	/**
	 * <p>获取指定的用户的动态。</p>
	 * @param userid 指定的用户。
	 * @return
	 */
	@RequestMapping(value="/dynamic/{userid}",method=RequestMethod.GET)
	@ResponseBody
	public ResultBean dynamic(@PathVariable("userid")String userid){
		return iGlUserManagerService.glUserDynamicService(userid);
	}
	
	
	/**
	 * <p>关闭指定的动态。</p>
	 * @param dynamicId 动态的ID。
	 * @param status 状态。
	 * @return
	 */
	@RequestMapping(value="/colseDynamic/{dynamicId}/{status}",method=RequestMethod.PUT)
	@ResponseBody
	public ResultBean colseDynamic(@PathVariable("dynamicId")String dynamicId,@PathVariable("status")String status){
		return iGlUserManagerService.colseDynamicService(dynamicId, status);
	}
	
	
	/**
	 * <p>获取指定用户的礼包。</p>
	 * @param userid
	 * @return
	 */
	@RequestMapping(value="/giftBag/{userid}",method=RequestMethod.GET)
	@ResponseBody
	public ResultBean giftBag(@PathVariable("userid")String userid){
		return iGlUserManagerService.glUserGiftBagService(userid);
	}
	
	
	
	
	/**
	 * <p>获取指定用户的正在做或者已经完成的尖兵任务。</p>
	 * @param gameTask 查询参数尖兵任务对象。
	 * @param request 表格对象。
	 * @return
	 */
	@RequestMapping(value="/pioneer",method=RequestMethod.GET)
	@ResponseBody
	public TableResult pioneer(GameTask gameTask,HttpServletRequest request){
		TableSide tableSide = new TableSide(request);
		return iGlUserManagerService.glUserPioneerService(gameTask, tableSide);
	}
	
	
	
	/**
	 * <p>获取指定尖兵任务的详情。</p>
	 * @param pioneerGid
	 * @return
	 */
	@RequestMapping(value="/pioneerDetail/{pioneerGid}",method=RequestMethod.GET)
	@ResponseBody
	public ResultBean pioneerDetail(@PathVariable(value="pioneerGid")String pioneerGid){
		return iGlUserManagerService.pioneerDetailService(pioneerGid);
	}
}
