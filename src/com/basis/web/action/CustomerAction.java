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

import com.basis.core.condition.CustomerCondition;
import com.basis.core.domain.Customer;
import com.basis.core.service.ICustomerService;

public class CustomerAction extends BaseAction{
	
	public static Logger logger = Logger.getLogger(CustomerAction.class);
	
	private ICustomerService customerService;
	
	private CustomerCondition customerCondition = new CustomerCondition();

	private Customer customer = new Customer();
	
	
	/**
	 * 列表
	 */
	public String index() {
		return "customer-index";
	}
	
	/**
	 * 
	 * @return
	 */
	@Authority(operator = EOperator.SELECT)
	public String data() {
		try {
			if(null==customerCondition){
				customerCondition = new CustomerCondition();
			}
			//分页查询
			page = customerService.queryCustomerPage(customerCondition);
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
			result = customerService.removeCustomer(customer);
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
		return "customer-add";
	}
	/**
	 * 添加处理
	 */
	@Authority(operator=EOperator.ADD)
	public String doAdd(){
		try{
			result = customerService.addCustomer(customer);
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
			customer = customerService.queryCustomerById(customer.getId());
		}catch(Exception e){
			logger.error(e);
		}
		return "customer-edit";
	}
	
	
	/**
	 * 编辑
	 * @return
	 */
	@Authority(operator=EOperator.MODIFY)
	public String doEdit(){
		try{
			result = customerService.modifyCustomer(customer);
		}catch(Exception e){
			logger.error(e);
			result = new Result<Object>(false, EMessageCode.EXCEPTION.getCode());
		}
		return "json-result";
	}
	
	/** 
	 * get set 
	 **/
	public void setCustomerService(ICustomerService service) {
		this.customerService = service;
	}
	
	public ICustomerService getCustomerService() {
		return this.customerService;
	}
	
	public void setCustomer(Customer customer){
		this.customer=customer;
	}
	
	public Customer getCustomer(){
		return customer;
	}
	
	public void setCustomerCondition(CustomerCondition customerCondition){
		this.customerCondition=customerCondition;
	}
	
	public CustomerCondition getCustomerCondition(){
		return customerCondition;
	}
	
	
}

