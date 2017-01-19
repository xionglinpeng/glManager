package com.imopan.glm.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.imopan.glm.bean.ResultBean;
import com.imopan.glm.entity.User;
import com.imopan.glm.service.IManagerService;

/**
 * <p>Game Link 后台管理<p>
 * @author xlp
 *
 */
@Controller
@RequestMapping(value="/glmanager")
public class ManagerAction {
	
	@Autowired
	private IManagerService managerService;
	
	
	@RequestMapping(value="/register",method=RequestMethod.POST)
	@ResponseBody
	public ResultBean register(@RequestBody User user){
		return managerService.registerService(user);
	}
}
