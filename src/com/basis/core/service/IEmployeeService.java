/**
*Generated by StarUML(tm) Java Add-In
*  @ Project : basis
*  @ File Name : IEmployeeService.java
*  @ Date : 2012/9/21
*  @ Author : wxliu
*/



package com.basis.core.service;

import com.basis.core.common.Page;
import com.basis.core.common.Result;
import com.basis.core.condition.EmployeeCondition;
import com.basis.core.domain.Employee;

/**
 * 员工接口
 * @author wxliu
 *
 */
public interface IEmployeeService {
	/**
	 * 新增员工
	 * @param employee
	 * @return
	 */
	public Result<String> addEmployee(Employee employee);
	/**
	 * 修改员工
	 * @param employee
	 * @return
	 */
	public Result<Object> modifyEmployee(Employee employee);
	/**
	 * 删除员工
	 * @param employee
	 * @return
	 */
	public Result<Object> removeEmployee(Employee employee);
	/**
	 * 重置密码
	 * @param employee
	 * @return
	 */
	public Result<Object> modifyResetPassword(Employee employee,String nowpassword);
	/**
	 * 初始化密码
	 * @param employee
	 * @return
	 */
	public Result<Object> modifyInitPassword(Employee employee);
	/**
	 * 冻结员工
	 * @param employee
	 * @return
	 */
	public Result<Object> modifyLockEmployee(Employee employee);
	/**
	 * 开启员工
	 * @param employee
	 * @return
	 */
	public Result<Object> modifyUnlockEmployee(Employee employee);
	/**
	 * 查询员工
	 * @param id
	 * @return
	 */
	public Employee queryEmployeeById(String id);
	/**
	 * 分页查询员工列表
	 * @param condition
	 * @return
	 */
	public Page<Employee> queryEmployeePage(EmployeeCondition condition);
}
