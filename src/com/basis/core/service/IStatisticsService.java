/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2015
 */

package com.basis.core.service;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import com.basis.core.common.Page;
import com.basis.core.domain.Statistics;
import com.basis.core.common.Result;

import com.basis.core.condition.StatisticsCondition;
/**
 * 经营统计业务处理
 * 
 * @author wgf
 * @version 1.0
 * @since 1.0
 */

public interface IStatisticsService {
	public static Logger logger = Logger.getLogger(IStatisticsService.class);
		
	/**
	 * 保存 Statistics
	 */
	public Result addStatistics(Statistics statistics);
	
	/**
	 * 删除 Statistics
	 */
	public Result removeStatistics(Statistics statistics);
	
	/**
	 * 更新 Statistics
	 */
	public Result modifyStatistics(Statistics statistics);
	/**
	 * 查询列表
	 */
	public List queryStatisticsList(StatisticsCondition condition);
	/**
	 * 分页查询
	 */
	public Page queryStatisticsPage(StatisticsCondition condition);
	
	/**
	 * 根据ID 查询
	 */
	public Statistics queryStatisticsById(Long id);	
	/**
	 * 数据导出
	 * @param condition 查询条件
	 * @return 导出的excel路径
	 */
	public String exportDatas(String rootpath,StatisticsCondition condition);
	
	/**
	* @Description: 查询是否有当天统计记录
	* @author wgf
	* @date 2015-3-3 上午9:55:55  
	* @throws
	*/ 
	public Statistics countRecord(String year,String month,String day);
	
	/**
	* @Description: 按月统计
	* @author wgf
	* @date 2015-3-3 下午2:05:26  
	* @return Map<String,Object>
	* @throws
	*/ 
	public Map<String,Object> getMonthMap(String nowMonth,StatisticsCondition statisticsCondition);
	/**
	* @Description: 按年统计
	* @author wgf
	* @date 2015-3-3 下午2:17:26  
	* @return Map<String,Object>
	* @throws
	*/ 
	public Map<String,Object> getYearMap(String nowYear);
}
