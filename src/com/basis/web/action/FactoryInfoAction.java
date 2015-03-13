/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2015
 */


package com.basis.web.action;

import org.apache.log4j.Logger;
import com.basis.web.common.Authority;
import com.basis.web.common.BaseAction;


import com.basis.core.common.Result;
import com.basis.core.constants.EMessageCode;
import com.basis.core.constants.EOperator;

import com.basis.core.condition.FactoryInfoCondition;
import com.basis.core.domain.FactoryInfo;
import com.basis.core.service.IFactoryInfoService;

public class FactoryInfoAction extends BaseAction{
	
	public static Logger logger = Logger.getLogger(FactoryInfoAction.class);
	
	private IFactoryInfoService factoryInfoService;
	
	private FactoryInfoCondition factoryInfoCondition = new FactoryInfoCondition();

	private FactoryInfo factoryInfo = new FactoryInfo();
	
	
	/**
	 * 首页
	 */
	public String index() {
		return "factoryInfo-index";
	}
	
	/**
	 * 数据列表
	 * 
	 * @return
	 */
	@Authority(operator = EOperator.SELECT)
	public String data() {
		try {
			if(null==factoryInfoCondition){
				factoryInfoCondition = new FactoryInfoCondition();
			}
			//分页查询
			page = factoryInfoService.queryFactoryInfoPage(factoryInfoCondition);
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
			result = factoryInfoService.removeFactoryInfo(factoryInfo);
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
		return "factoryInfo-add";
	}
	/**
	 * 添加处理
	 */
	@Authority(operator=EOperator.ADD)
	public String doAdd(){
		try{
			result = factoryInfoService.addFactoryInfo(factoryInfo);
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
			factoryInfo = factoryInfoService.queryFactoryInfoById(factoryInfo.getId());
		}catch(Exception e){
			logger.error(e);
		}
		return "factoryInfo-edit";
	}
	
	
	/**
	 * 编辑
	 * @return
	 */
	@Authority(operator=EOperator.MODIFY)
	public String doEdit(){
		try{
			result = factoryInfoService.modifyFactoryInfo(factoryInfo);
		}catch(Exception e){
			logger.error(e);
			result = new Result<Object>(false, EMessageCode.EXCEPTION.getCode());
		}
		return "json-result";
	}
	
	/** 
	 * get set 
	 **/
	public void setFactoryInfoService(IFactoryInfoService service) {
		this.factoryInfoService = service;
	}
	
	public IFactoryInfoService getFactoryInfoService() {
		return this.factoryInfoService;
	}
	
	public void setFactoryInfo(FactoryInfo factoryInfo){
		this.factoryInfo=factoryInfo;
	}
	
	public FactoryInfo getFactoryInfo(){
		return factoryInfo;
	}
	
	public void setFactoryInfoCondition(FactoryInfoCondition factoryInfoCondition){
		this.factoryInfoCondition=factoryInfoCondition;
	}
	
	public FactoryInfoCondition getFactoryInfoCondition(){
		return factoryInfoCondition;
	}
	
	
}

