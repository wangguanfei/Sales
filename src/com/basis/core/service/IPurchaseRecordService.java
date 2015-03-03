/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2014
 */

package com.basis.core.service;

import java.util.List;

import org.apache.log4j.Logger;
import com.basis.core.common.Page;
import com.basis.core.domain.PurchaseRecord;
import com.basis.core.dto.PurchaseDto;
import com.basis.core.common.Result;

import com.basis.core.condition.PurchaseRecordCondition;
/**
 * 进货表业务处理
 * 
 * @author wgf
 * @version 1.0
 * @since 1.0
 */

public interface IPurchaseRecordService {
	public static Logger logger = Logger.getLogger(IPurchaseRecordService.class);
		
	/**
	 * 保存 PurchaseRecord
	 */
	public Result addPurchaseRecord(PurchaseRecord purchaseRecord);
	
	/**
	 * 删除 PurchaseRecord
	 */
	public Result removePurchaseRecord(PurchaseRecord purchaseRecord);
	
	/**
	 * 更新 PurchaseRecord
	 */
	public Result modifyPurchaseRecord(PurchaseRecord purchaseRecord);
	/**
	 * 查询列表
	 */
	public List queryPurchaseRecordList(PurchaseRecordCondition condition);
	/**
	 * 分页查询
	 */
	public Page queryPurchaseRecordPage(PurchaseRecordCondition condition);
	
	/**
	 * 根据ID 查询
	 */
	public PurchaseRecord queryPurchaseRecordById(Long id);	
	/**
	 * 数据导出
	 * @param condition 查询条件
	 * @return 导出的excel路径
	 */
	public String exportDatas(String rootpath,PurchaseRecordCondition condition);
	
	/**
	 * @Description: 查询商品历史进价
	 * @author wgf
	 * @date 2014-10-13下午4:50:11
	 * @param @param goodsId
	 * @param @return   
	 * @return List<String>  
	 * @throws
	 */
	public List<String> queryPurchasePrice(Long goodsId);
	
	/**
	* @Description: 按月统计
	* @author wgf
	* @date 2015-3-2 上午11:19:18  
	* @return List<PurchaseRecord>
	* @throws
	*/ 
	public List<PurchaseDto> queryPurchaseRecordByMonth(String year);
	
	/**
	* @Description: 年份总计
	* @author wgf
	* @date 2015-3-2 下午3:29:16  
	* @return String
	* @throws
	*/ 
	public String SumPurchaseRecordByYear(String year);
	
}
