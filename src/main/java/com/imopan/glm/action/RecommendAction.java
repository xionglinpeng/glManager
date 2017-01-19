package com.imopan.glm.action;

import java.io.IOException;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.imopan.glm.bean.ResultBean;
import com.imopan.glm.entity.FocusByFigure;
import com.imopan.glm.entity.FocusByFigureUrl;
import com.imopan.glm.entity.Keywords;
import com.imopan.glm.service.IRecommendService;
import com.imopan.glm.util.QiniuUploadUtil;

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
		return iRecommendService.saveKeywordService(keywords);
	}
	
	
	
	/**
	 * <p>获取所有的焦点轮播图。</p>
	 * @return
	 */
	@RequestMapping(value="/focusByFigures",method=RequestMethod.GET)
	@ResponseBody
	public ResultBean focusByFigures(){
		return iRecommendService.getFocusByFigureService();
	}
	
	
	/**
	 * <p>获取所有的焦点轮播图片。</p>
	 * @return
	 */
	@RequestMapping(value="/focusByFigureImages",method=RequestMethod.GET)
	@ResponseBody
	public ResultBean focusByFigureImages(){
		return iRecommendService.focusByFigureImageService();
	}
	
	/**
	 * <p>上传焦点轮播图</p>
	 * @param focusByFigureUrl 轮播图对象,作为参数，用于接收从客户端传递过来的图片二进制字符串。
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/uploadFocusByFigure",method=RequestMethod.POST)
	@ResponseBody
	public ResultBean uploadFocusByFigure(@RequestBody FocusByFigureUrl focusByFigureUrl) throws IOException{
		String imageSrc = focusByFigureUrl.getFocusByFigureUrl().split(",")[1];
		byte[] decodeBase64 = Base64.decodeBase64(imageSrc);
		String imageUrl = QiniuUploadUtil.uploadUtil(decodeBase64, null, null);
		return iRecommendService.saveFocusByFigureUrlService(imageUrl);
	}
	
	
	
	/**
	 * <p>保存修改焦点轮播图。</p>
	 * @param focusByFigures
	 * @return
	 */
	@RequestMapping(value="/saveFocusByFigure",method=RequestMethod.POST)
	@ResponseBody
	public ResultBean savefocusByFigures(@RequestBody List<FocusByFigure> focusByFigures){
		return iRecommendService.saveFocusByFigureService(focusByFigures);
	}
}
