package com.imopan.glm.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@RequestMapping(value="lists",method=RequestMethod.GET)
	@ResponseBody
	public TableResult adviceFeedbackLists(Feedback feedback,HttpServletRequest request){
		TableSide tableSide = new TableSide(request);
		return iAdviceFeedbackService.adviceFeedbackListService(feedback, tableSide);
	}
	
	@RequestMapping(value="dispose/{adviceFeedbackId}",method=RequestMethod.PUT)
	@ResponseBody
	public ResultBean disposeAdviceFeedback(@PathVariable("adviceFeedbackId")String adviceFeedbackId){
		return iAdviceFeedbackService.disposeAdviceFeedbackService(adviceFeedbackId);
	}
}
