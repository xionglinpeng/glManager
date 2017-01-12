package com.imopan.glm.service;

import com.imopan.glm.bean.ResultBean;
import com.imopan.glm.bean.TableResult;
import com.imopan.glm.bean.TableSide;
import com.imopan.glm.entity.Feedback;

/**
 * <p>意见反馈信息服务。</p>
 * @author xlp
 *
 */
public interface IAdviceFeedbackService {
	
	/**
	 * <p>查询意见反馈集合。</p>
	 * @param feedback 意见反馈查询参数。
	 * @param tableSide 表格参数。
	 * @return
	 */
	public TableResult adviceFeedbackListService(Feedback feedback,TableSide tableSide);
	
	/**
	 * <p>处理指定的意见反馈信息</p>
	 * @param adviceFeedbackId 被处理帖子的ID。
	 * @return
	 */
	public ResultBean disposeAdviceFeedbackService(String adviceFeedbackId);
}
