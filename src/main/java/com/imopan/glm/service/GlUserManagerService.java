package com.imopan.glm.service;

import com.imopan.glm.bean.TableResult;
import com.imopan.glm.bean.TableSide;
import com.imopan.glm.entity.GlUser;

public interface GlUserManagerService {

	public TableResult glUserListService(GlUser glUser,TableSide tableSide);
}
