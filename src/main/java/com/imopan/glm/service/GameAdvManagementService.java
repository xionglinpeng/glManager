/** 
 * Project Name:game-link-manager 
 * File Name:GameAdvManagementService.java 
 * Package Name:com.imopan.glm.service 
 * Date:2017年1月13日下午3:39:10 
 * Copyright (c) 2017, zhangjiakun@imopan.com All Rights Reserved. 
 * 
*/ 

package com.imopan.glm.service;

import java.util.List;

import com.imopan.glm.bean.TableResult;
import com.imopan.glm.bean.TableSide;
import com.imopan.glm.entity.GameAdv;
import com.imopan.glm.entity.game.GameCentre;
import com.imopan.glm.vo.GameAdvBean;

/** 
 * ClassName:GameAdvManagementService <br/> 
 * Function: TODO ADD FUNCTION. <br/>  
 * Date:     2017年1月13日 下午3:39:10 <br/> 
 * @author   zhangjiakun 
 * @version   
 * @since    JDK 1.7       
 */
public interface GameAdvManagementService {

	TableResult gameAdvManagementLists(GameAdvBean gameAdv, TableSide tableSide);

	Integer offlineGameAdv(String id, Integer status);

	List<GameCentre> getAllGame();

}
