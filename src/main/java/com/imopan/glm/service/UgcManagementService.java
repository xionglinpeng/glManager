/** 
 * Project Name:game-link-manager 
 * File Name:UgcManagementService.java 
 * Package Name:com.imopan.glm.service 
 * Date:2017年1月10日下午2:45:34 
 * Copyright (c) 2017, zhangjiakun@imopan.com All Rights Reserved. 
 * 
*/ 

package com.imopan.glm.service;

import com.imopan.glm.bean.TableResult;
import com.imopan.glm.bean.TableSide;
import com.imopan.glm.vo.broadcast.BroadcastBean;

/** 
 * ClassName:UgcManagementService <br/> 
 * Function: TODO ADD FUNCTION. <br/>  
 * Date:     2017年1月10日 下午2:45:34 <br/> 
 * @author   zhangjiakun 
 * @version   
 * @since    JDK 1.7       
 */
public interface UgcManagementService {

	TableResult ugcManagementLists(BroadcastBean broadcastBean,
			TableSide tableSide);

	Integer closeBroadcast(String id, Integer status);

}
