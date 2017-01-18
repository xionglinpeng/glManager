package com.imopan.glm.action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.imopan.glm.bean.ResultBean;
import com.imopan.glm.bean.TableResult;
import com.imopan.glm.bean.TableSide;
import com.imopan.glm.entity.Feedback;
import com.imopan.glm.service.IAdviceFeedbackService;

/**
 * 
 * @author xlp
 *
 */
@Controller
@RequestMapping(value="/adviceFeedback")
public class adviceFeedbackAction {
	
	@Autowired
	private IAdviceFeedbackService iAdviceFeedbackService;
	
	/**
	 * <p>查询意见反馈集合。</p>
	 * @param feedback 意见反馈查询参数。
	 * @param startDate 查询条件，开始时间。
	 * @param endDate 查询条件，结束时间。
	 * @param request
	 * @return
	 */
	@RequestMapping(value="lists",method=RequestMethod.GET)
	@ResponseBody
	public TableResult adviceFeedbackLists(Feedback feedback,
			@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
			@RequestParam(value="startDate",required=false)Date startDate,
			@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
			@RequestParam(value="endDate",required=false)Date endDate,
			HttpServletRequest request){
		TableSide tableSide = new TableSide(request);
		return iAdviceFeedbackService.adviceFeedbackListService(feedback,startDate,endDate,tableSide);
	}
	
	
	/**
	 * <p>处理对应的反馈信息。</p>
	 * @param adviceFeedbackId 对应反馈信息的id
	 * @return
	 */
	@RequestMapping(value="dispose/{adviceFeedbackId}",method=RequestMethod.PUT)
	@ResponseBody
	public ResultBean disposeAdviceFeedback(@PathVariable("adviceFeedbackId")String adviceFeedbackId){
		return iAdviceFeedbackService.disposeAdviceFeedbackService(adviceFeedbackId);
	}
}
