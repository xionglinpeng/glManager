package com.imopan.glm.service;

import com.imopan.glm.bean.ResultBean;
import com.imopan.glm.entity.User;

/**
 * <p>Game Link后台管理系统服务。</p>
 * @author xlp
 *
 */
public interface IManagerService {

	
	/**
	 * <p>Game Link后台管理系统注册用户。</p>
	 * @param user
	 * @return
	 */
	public ResultBean registerService(User user);
}
