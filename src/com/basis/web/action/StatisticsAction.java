/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2015
 */


package com.basis.web.action;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import com.basis.web.common.Authority;
import com.basis.web.common.BaseAction;


import com.basis.core.common.Result;
import com.basis.core.constants.EMessageCode;
import com.basis.core.constants.EOperator;

import com.basis.core.condition.StatisticsCondition;
import com.basis.core.domain.Statistics;
import com.basis.core.service.IStatisticsService;
import com.basis.core.util.DateUtil;
import com.basis.core.util.StringUtil;

public class StatisticsAction extends BaseAction{
	
	public static Logger logger = Logger.getLogger(StatisticsAction.class);
	
	private IStatisticsService statisticsService;
	
	private StatisticsCondition statisticsCondition = new StatisticsCondition();

	private Statistics statistics = new Statistics();
	
	
	/**
	 * 首页
	 */
	public String index() {
		request.setAttribute("nowMonth", DateUtil.format(new Date(), "yyyy-MM"));
		request.setAttribute("nowYear", DateUtil.format(new Date(), "yyyy"));
		return "statistics-index";
	}
	
	/**
	 * 数据列表
	 * 
	 * @return
	 */
	@Authority(operator = EOperator.SELECT)
	public String data() {
		try {
			if(null==statisticsCondition){
				statisticsCondition = new StatisticsCondition();
			}
			Map<String,Object> map = new HashMap<String, Object>();
			String type = request.getParameter("type");
			String nowMonth = request.getParameter("nowMonth");
			String nowYear = request.getParameter("nowYear");
			
			if (!StringUtil.stringIsNull(type)&&!StringUtil.stringIsNull(nowMonth)&& "m".equals(type)) {
				map = statisticsService.getMonthMap(nowMonth, statisticsCondition);
			}else if (!StringUtil.stringIsNull(type)&&!StringUtil.stringIsNull(nowYear)&& "y".equals(type)) {
				map = statisticsService.getYearMap(nowYear);
			}else{
				map.put("noData", "yes");
			}
			result.setData(map);
		} catch (Exception e) {
			logger.error(e);	
			result = new Result<Object>(false, EMessageCode.EXCEPTION.getCode());
		}
		return "json-result";
	}
	
	
	@Authority(operator = EOperator.SELECT)
	public String statisticsTotal() {
		try {
			String type = request.getParameter("type");
			result.setData(statisticsService.statisticAll(type));
		} catch (Exception e) {
			logger.error(e);	
			result = new Result<Object>(false, EMessageCode.EXCEPTION.getCode());
		}
		return "json-result";
	}
	
	/** 
	 * get set 
	 **/
	public void setStatisticsService(IStatisticsService service) {
		this.statisticsService = service;
	}
	
	public IStatisticsService getStatisticsService() {
		return this.statisticsService;
	}
	
	public void setStatistics(Statistics statistics){
		this.statistics=statistics;
	}
	
	public Statistics getStatistics(){
		return statistics;
	}
	
	public void setStatisticsCondition(StatisticsCondition statisticsCondition){
		this.statisticsCondition=statisticsCondition;
	}
	
	public StatisticsCondition getStatisticsCondition(){
		return statisticsCondition;
	}
	
	
}

