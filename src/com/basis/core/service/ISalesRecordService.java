/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2014
 */

package com.basis.core.service;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import com.basis.core.common.Page;
import com.basis.core.domain.SalesRecord;
import com.basis.core.common.Result;

import com.basis.core.condition.PurchaseRecordCondition;
import com.basis.core.condition.SalesRecordCondition;
/**
 * InnoDB free: 11264 kB业务处理
 * 
 * @author wgf
 * @version 1.0
 * @since 1.0
 */

public interface ISalesRecordService {
	public static Logger logger = Logger.getLogger(ISalesRecordService.class);
		
	/**
	 * 保存 SalesRecord
	 */
	public Result addSalesRecord(SalesRecord salesRecord);
	
	/**
	 * 删除 SalesRecord
	 */
	public Result removeSalesRecord(SalesRecord salesRecord);
	
	/**
	 * 更新 SalesRecord
	 */
	public Result modifySalesRecord(SalesRecord salesRecord);
	/**
	 * 查询列表
	 */
	public List querySalesRecordList(SalesRecordCondition condition);
	/**
	 * 分页查询
	 */
	public Page querySalesRecordPage(SalesRecordCondition condition);
	
	/**
	 * 根据ID 查询
	 */
	public SalesRecord querySalesRecordById(Long id);	
	/**
	 * 数据导出
	 * @param condition 查询条件
	 * @return 导出的excel路径
	 */
	public String exportDatas(String rootpath,SalesRecordCondition condition);
	/**
	* @Description: 根据时间统计总额
	* @author wgf
	* @date 2015-3-4 下午2:04:20  
	* @return String
	* @throws
	*/ 
	public Map<String,Object> countByDate(SalesRecordCondition condition);
	
	/**
	* @Description:查询最新六条销售记录
	* @author wgf
	* @date 2015-3-6 下午1:51:25  
	* @return String
	* @throws
	*/ 
	public  List<SalesRecord> queryNew6();
}
