package com.imopan.glm.service;

import java.util.Map;

import com.imopan.glm.bean.TableResult;
import com.imopan.glm.bean.TableSide;
import com.imopan.glm.entity.GlUser;

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
	public TableResult glUserListService(GlUser glUser,TableSide tableSide);
	
	
	/**
	 * <p>用户详情。</p>
	 * @param userid 当前用户集合的_id。
	 * @return
	 */
	public Map<String,Object> glUserDetailService(String userid);
}
