package com.imopan.glm.service;

import java.util.Map;

import com.imopan.glm.bean.ResultBean;
import com.imopan.glm.bean.TableResult;
import com.imopan.glm.bean.TableSide;
import com.imopan.glm.entity.GameTask;
import com.imopan.glm.entity.GlUser;
import com.imopan.glm.entity.UserStatus;

/**
 * <p>用户管理服务</p>
 * @author xlp
 *
 */
public interface IGlUserManagerService {
	
	/**
	 * <p>用户列表服务</p>
	 * @param glUser 查询条件
	 * @param request 表格参数
	 * @return
	 */
	public TableResult glUserListService(GlUser glUser,String gtName,TableSide tableSide);
	
	
	
	/**
	 * <p>获取所有游戏类型。</p>
	 * @return
	 */
	public ResultBean gameTypeService();
	
	
	/**
	 * <p>封禁或者解封用户状态。</p>
	 * @param status 状态。
	 * @param userid 指定用户。
	 * @return
	 */
	public ResultBean glUserForbidNormalService(UserStatus status,String userid);
	
	
	
	/**
	 * <p>用户详情。</p>
	 * @param userid 当前用户集合的_id。
	 * @return
	 */
	public Map<String,Object> glUserDetailService(String userid);
	
	/**
	 * <p>获取指定用户的相册。</p>
	 * @param userid
	 * @return
	 */
	public ResultBean glUserPhotoService(String userid);
	
	/**
	 * <p>获取指定用户的动态。</p>
	 * @param userid
	 * @return
	 */
	public ResultBean glUserDynamicService(String userid);
	
	
	/**
	 * <p>关闭指定的动态。</p>
	 * @param dynamicId 动态id。
	 * @param status 状态。
	 * @return
	 */
	public ResultBean colseDynamicService(String dynamicId,String status);
	
	
	/**
	 * <p>获取指定用户的礼包。</p>
	 * @param userid
	 * @return
	 */
	public ResultBean glUserGiftBagService(String userid);
	
	/**
	 * <p>获取指定用户正在做，或者已经完成的尖兵任务。</p>
	 * @param gameTask
	 * @param tableSide
	 * @return
	 */
	public TableResult glUserPioneerService(GameTask gameTask,TableSide tableSide);
	
	
	/**
	 * <p>获取指定尖兵任务的详情。</p>
	 * @param pioneerGid
	 * @return
	 */
	public ResultBean pioneerDetailService(String pioneerGid);
}
