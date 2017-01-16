package com.imopan.glm.action;

import com.imopan.glm.bean.ResultBean;
import com.imopan.glm.bean.TableResult;
import com.imopan.glm.bean.TableSide;
import com.imopan.glm.entity.game.GamePioneer;
import com.imopan.glm.entity.game.ParamVo;
import com.imopan.glm.service.IGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/game")
public class GameAction {
	
	@Autowired
	private IGameService gameService;
	/*
			游戏列表
	 */
	@RequestMapping(value="/lists",method=RequestMethod.GET)
	@ResponseBody
	public TableResult glUserLists(ParamVo paramVo, HttpServletRequest request){
		TableSide tableSide = new TableSide(request);
		return gameService.getGameList(paramVo,tableSide);
	}
	/*
			尖兵列表
	 */
	@RequestMapping(value="/pioneerList",method=RequestMethod.GET)
	@ResponseBody
	public TableResult getPioneerList(ParamVo paramVo, HttpServletRequest request){
		TableSide tableSide = new TableSide(request);
		return gameService.getGamePioneerList(paramVo,tableSide);
	}
	/*
			尖兵操作增加/修改
	 */
	@RequestMapping(value="/doGamePioneer",method=RequestMethod.POST)
	@ResponseBody
	public ResultBean doGamePioneer(@RequestBody GamePioneer paramVo){
		return gameService.doGamePioneer(paramVo);
	}
	/*
        获取尖兵
 	*/
	@RequestMapping(value="/getGamePioneer",method={RequestMethod.GET})
	@ResponseBody
	public ResultBean getGamePioneer(GamePioneer paramVo){
		return gameService.getGamePioneer(paramVo);
	}
	/*
    获取尖兵
 */
	@RequestMapping(value="/doGamePioneerStatus",method={RequestMethod.POST})
	@ResponseBody
	public ResultBean doGamePioneerStatus(@RequestBody GamePioneer paramVo){
		return gameService.doGamePioneerStatus(paramVo);
	}
}
