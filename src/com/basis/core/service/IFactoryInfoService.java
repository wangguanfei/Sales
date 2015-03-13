/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2015
 */

package com.basis.core.service;

import java.util.List;

import org.apache.log4j.Logger;
import com.basis.core.common.Page;
import com.basis.core.domain.FactoryInfo;
import com.basis.core.common.Result;

import com.basis.core.condition.FactoryInfoCondition;
/**
 * 厂家信息业务处理
 * 
 * @author wgf
 * @version 1.0
 * @since 1.0
 */

public interface IFactoryInfoService {
	public static Logger logger = Logger.getLogger(IFactoryInfoService.class);
		
	/**
	 * 保存 FactoryInfo
	 */
	public Result addFactoryInfo(FactoryInfo factoryInfo);
	
	/**
	 * 删除 FactoryInfo
	 */
	public Result removeFactoryInfo(FactoryInfo factoryInfo);
	
	/**
	 * 更新 FactoryInfo
	 */
	public Result modifyFactoryInfo(FactoryInfo factoryInfo);
	/**
	 * 查询列表
	 */
	public List queryFactoryInfoList(FactoryInfoCondition condition);
	/**
	 * 分页查询
	 */
	public Page queryFactoryInfoPage(FactoryInfoCondition condition);
	
	/**
	 * 根据ID 查询
	 */
	public FactoryInfo queryFactoryInfoById(Long id);	
	/**
	 * 数据导出
	 * @param condition 查询条件
	 * @return 导出的excel路径
	 */
	public String exportDatas(String rootpath,FactoryInfoCondition condition);
}
