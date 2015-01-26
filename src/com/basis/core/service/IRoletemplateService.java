/**
*Generated by StarUML(tm) Java Add-In
*  @ Project : basis
*  @ File Name : IRoletemplateService.java
*  @ Date : 2012/9/21
*  @ Author : wxliu
*/



package com.basis.core.service;

import java.util.List;

import com.basis.core.common.Page;
import com.basis.core.common.Result;
import com.basis.core.condition.RoletemplateCondition;
import com.basis.core.domain.Roletemplate;

/**
 * 权限模版接口
 * @author wxliu
 *
 */
public interface IRoletemplateService {
	/**
	 * 查询权限模版
	 * @param id
	 * @return
	 */
	public Roletemplate queryRoletemplateById(String id);
	/**
	 * 分页查询权限模版
	 * @param condition
	 * @return
	 */
	public Page<Roletemplate> queryRoletemplatePage(RoletemplateCondition condition);
	
	public List<Roletemplate> queryRoletemplateList(RoletemplateCondition condition);
	
	/**
	 * 新增权限模版
	 * @param template
	 * @return
	 */
	public Result<String> addRoletemplate(Roletemplate template);
	/**
	 * 删除权限模版
	 * @param template
	 * @return
	 */
	public Result<Object> removeRoletemplate(Roletemplate template);
	/**
	 * 修改权限模版
	 * @param template
	 * @return
	 */
	public Result<Object> modifyRoletemplate(Roletemplate template);
}
