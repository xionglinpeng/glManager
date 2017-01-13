package com.imopan.glm.service;

import java.util.List;

import com.imopan.glm.bean.ResultBean;
import com.imopan.glm.entity.Keywords;

/**
 * <p>推荐系统服务。</p>
 * @author xlp
 *
 */
public interface IRecommendService {
	
	
	/**
	 * <p>获取所有的搜索关键字。</p>
	 * @return
	 */
	public ResultBean getKeywordService();
	
	/**
	 * <p>保存搜索关键字。</p>
	 * @param keywords
	 * @return
	 */
	public ResultBean saveKeywordService(List<Keywords> keywords);
}
