package com.imopan.glm.service.impl;

import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imopan.glm.bean.ResultBean;
import com.imopan.glm.entity.User;
import com.imopan.glm.service.IManagerService;

@Service
public class ManagerServiceImpl implements IManagerService {
	
	@Autowired
	private Datastore datastore;
	
	@Override
	public ResultBean registerService(User user) {
		//创建一个UUID作为盐
		String salt = UUID.randomUUID().toString();
		//加密密码
		String cryptPasswd = DigestUtils.md5Hex(user.getPassword()+salt);
		//重置对象密码和盐
		user.setPassword(cryptPasswd);
		user.setSalt(salt);
		//保存
		datastore.save(user);
		return new ResultBean("OK");
	}

	
	
}
