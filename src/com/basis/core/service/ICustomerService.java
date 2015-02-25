/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2015
 */

package com.basis.core.service;

import java.util.List;

import org.apache.log4j.Logger;
import com.basis.core.common.Page;
import com.basis.core.domain.Customer;
import com.basis.core.common.Result;

import com.basis.core.condition.CustomerCondition;
/**
 * 客户信息业务处理
 * 
 * @author wgf
 * @version 1.0
 * @since 1.0
 */

public interface ICustomerService {
	public static Logger logger = Logger.getLogger(ICustomerService.class);
		
	/**
	 * 保存 Customer
	 */
	public Result addCustomer(Customer customer);
	
	/**
	 * 删除 Customer
	 */
	public Result removeCustomer(Customer customer);
	
	/**
	 * 更新 Customer
	 */
	public Result modifyCustomer(Customer customer);
	/**
	 * 查询列表
	 */
	public List queryCustomerList(CustomerCondition condition);
	/**
	 * 分页查询
	 */
	public Page queryCustomerPage(CustomerCondition condition);
	
	/**
	 * 根据ID 查询
	 */
	public Customer queryCustomerById(Long id);	
	/**
	 * 数据导出
	 * @param condition 查询条件
	 * @return 导出的excel路径
	 */
	public String exportDatas(String rootpath,CustomerCondition condition);
}
