package com.basis.core.service;

import java.io.Serializable;
import java.util.List;

import com.basis.core.common.Condition;
import com.basis.core.common.Page;

/**
 * dao基类接口
 */
public interface IBaseService {
	
	/**
	 * 增加实例
	 * 
	 * @param o
	 * @throws DaoException
	 */
	public String addObject(Object o);
	
	/**
	 * 修改实例
	 * 
	 * @param o
	 * @throws DaoException
	 */
	public void modifyObject(Object o);
	

	/**
	 * 删除实例
	 * 
	 * @param o
	 * @throws DaoException
	 */
	public void deleteObject(Object o);
	
	/**
	 * 通过主键获得对象
	 *
	 * @param c
	 * @param id
	 * @return T
	 */
	public <T extends Object> T queryObject(Class<T> c ,Serializable id);
	
	/**
	 * 查询数据列表
	 * 
	 * @param condition
	 * @return List
	 */
	public <E>List<E> queryObjectList(Condition condition);
	
	/**
	 * 查询分页列表
	 * 
	 * @param condition
	 * @return Page
	 */
	public <E>Page<E> queryPage(Condition condition);
	
	/**
	 * 查询记录总数
	 * 
	 * @param condition
	 * @return long
	 */
	public long queryCount(Condition condition);
	
	/**
	 * 查询返回唯一一个值
	 * @param <E>
	 * @param condition
	 * @return
	 */
	public <E> E queryUniqueResult(Condition condition);
}
