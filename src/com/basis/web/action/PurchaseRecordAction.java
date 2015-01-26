/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2014
 */


package com.basis.web.action;

import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;
import com.basis.web.common.Authority;
import com.basis.web.common.BaseAction;


import com.basis.core.common.Result;
import com.basis.core.constants.EMessageCode;
import com.basis.core.constants.EOperator;

import com.basis.core.condition.PurchaseRecordCondition;
import com.basis.core.domain.PurchaseRecord;
import com.basis.core.service.IPurchaseRecordService;
import com.basis.core.util.DateUtil;

public class PurchaseRecordAction extends BaseAction{
	
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 5974251269173302708L;

	public static Logger logger = Logger.getLogger(PurchaseRecordAction.class);
	
	private IPurchaseRecordService purchaseRecordService;
	
	private PurchaseRecordCondition purchaseRecordCondition = new PurchaseRecordCondition();

	private PurchaseRecord purchaseRecord = new PurchaseRecord();
	
	
	/**
	 * 列表
	 */
	public String index() {
		return "purchaseRecord-index";
	}
	
	/**
	 * 调查项目（校区）列表
	 * 
	 * @return
	 */
	@Authority(operator = EOperator.SELECT)
	public String data() {
		try {
			if(null==purchaseRecordCondition){
				purchaseRecordCondition = new PurchaseRecordCondition();
			}
			if (null != purchaseRecordCondition.getPurchaseDateBeaginStr() && !"".equals(purchaseRecordCondition.getPurchaseDateBeaginStr())) {
				purchaseRecordCondition.setPurchaseDateBeagin(DateUtil.parse(purchaseRecordCondition.getPurchaseDateBeaginStr(), new SimpleDateFormat("yyyy-MM-dd")).getTime());
			}
			if (null != purchaseRecordCondition.getPurchaseDateEndStr() && !"".equals(purchaseRecordCondition.getPurchaseDateEndStr())) {
				purchaseRecordCondition.setPurchaseDateEnd(DateUtil.addDays(DateUtil.parse(purchaseRecordCondition.getPurchaseDateEndStr(), new SimpleDateFormat("yyyy-MM-dd")), 1).getTime());
			}
			purchaseRecordCondition.setSortname("purchaseDate");
			purchaseRecordCondition.setSortorder("desc");
			//分页查询
			page = purchaseRecordService.queryPurchaseRecordPage(purchaseRecordCondition);
		} catch (Exception e) {
			logger.error(e);	
			result = new Result<Object>(false, EMessageCode.EXCEPTION.getCode());
		}
		return "json-page";
	}
	
	/**
	 * 删除
	 */
	@Authority(operator=EOperator.DELETE)
	public String doDelete(){
		try{
			result = purchaseRecordService.removePurchaseRecord(purchaseRecord);
		}catch(Exception e){
			logger.error(e);
			result = new Result<Object>(false, EMessageCode.EXCEPTION.getCode());
		}
		return "json-result";
	}
	
	/**
	 * 添加
	 */
	@Authority(operator=EOperator.SELECT)
	public String toAdd(){
		return "purchaseRecord-add";
	}
	/**
	 * 添加处理
	 */
	@Authority(operator=EOperator.ADD)
	public String doAdd(){
		try{
			result = purchaseRecordService.addPurchaseRecord(purchaseRecord);
		}catch(Exception e){
			logger.error(e);
			result = new Result<Object>(false, EMessageCode.EXCEPTION.getCode());
		}
		return "json-result";
	}
	
	/**
	 * 编辑
	 * @return
	 */
	@Authority(operator=EOperator.SELECT)
	public String toEdit(){
		try{
			purchaseRecord = purchaseRecordService.queryPurchaseRecordById(purchaseRecord.getId());
		}catch(Exception e){
			logger.error(e);
		}
		return "purchaseRecord-edit";
	}
	
	
	/**
	 * 编辑
	 * @return
	 */
	@Authority(operator=EOperator.MODIFY)
	public String doEdit(){
		try{
			result = purchaseRecordService.modifyPurchaseRecord(purchaseRecord);
		}catch(Exception e){
			logger.error(e);
			result = new Result<Object>(false, EMessageCode.EXCEPTION.getCode());
		}
		return "json-result";
	}
	
	/** 
	 * get set 
	 **/
	public void setPurchaseRecordService(IPurchaseRecordService service) {
		this.purchaseRecordService = service;
	}
	
	public IPurchaseRecordService getPurchaseRecordService() {
		return this.purchaseRecordService;
	}
	
	public void setPurchaseRecord(PurchaseRecord purchaseRecord){
		this.purchaseRecord=purchaseRecord;
	}
	
	public PurchaseRecord getPurchaseRecord(){
		return purchaseRecord;
	}
	
	public void setPurchaseRecordCondition(PurchaseRecordCondition purchaseRecordCondition){
		this.purchaseRecordCondition=purchaseRecordCondition;
	}
	
	public PurchaseRecordCondition getPurchaseRecordCondition(){
		return purchaseRecordCondition;
	}
	
	
}

