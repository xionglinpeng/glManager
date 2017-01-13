package com.imopan.glm.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.imopan.glm.bean.ResultBean;
import com.imopan.glm.entity.Keywords;
import com.imopan.glm.service.IRecommendService;

/**
 * <p>推荐系统控制器</p>
 * @author xlp
 *
 */
@Controller
@RequestMapping(value="recommend")
public class RecommendAction {
	
	@Autowired
	private IRecommendService iRecommendService;
	
	/**
	 * <p>获取所有的搜索关键字。</p>
	 * @return
	 */
	@RequestMapping(value="/keywords",method=RequestMethod.GET)
	@ResponseBody
	public ResultBean keywords(){
		return iRecommendService.getKeywordService();
	}
	
	/**
	 * <p>保存编辑的关键字</p>
	 * @param keywords 关键字组集合。
	 * @return 保存结果。
	 */
	@RequestMapping(value="/saveKeywords",method=RequestMethod.POST)
	@ResponseBody
	public ResultBean saveKeywords(@RequestBody List<Keywords> keywords){
		System.out.println(keywords.size());
		for (Keywords keywords2 : keywords) {
			System.out.println(keywords2);
		}
		return iRecommendService.saveKeywordService(keywords);
	}
}
