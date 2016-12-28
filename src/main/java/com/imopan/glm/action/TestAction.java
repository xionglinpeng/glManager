package com.imopan.glm.action;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestAction {
	
	@RequestMapping("/test")
	@ResponseBody
	public Map<String,Object> test(){
		System.out.println("请求成功");
		Map<String,Object> map = new HashMap<>();
		map.put("msg", "请求成功");
		return map;
	}
}
