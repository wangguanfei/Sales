package com.basis.core.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;

import com.basis.core.common.Condition;
import com.basis.core.common.Page;
import com.basis.core.exception.NameIsExistException;
import com.basis.core.exception.SortIsExistException;

/**
 * dao基类接口
 */
public interface IBaseDao {
	
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
	public void updateObject(Object o);
	
	/**
	 * 保存或修改实例
	 * 
	 * @param o
	 * @throws DaoException
	 */
	public void addOrUpdate(Object o);
	
	/**
	 * 更新或删除符合条件的所有对象
	 *
	 * @param condition
	 * @return int
	 */
	public int updateOrDeleteAll(Condition condition);

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
	public <T extends Object> T getObject(Class<T> c ,Serializable id);
	
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
	public <E> E uniqueResult(Condition condition);
	/**
	 * 判断名称是否重复
	 * @param domainClazz 必填
	 * @param name 必填
	 * @param where 选填
	 * @throws NameIsExistException
	 */
	public void validHasSameName(Class domainClazz,String name,String where) throws NameIsExistException;
	
	/**
	 * 判断序号是否重复
	 * @param domainClazz 必填
	 * @param name 必填
	 * @param where 选填
	 * @throws NameIsExistException
	 */
	public void validHasSameSort(Class domainClazz,Integer sort,String where) throws SortIsExistException;
	
	/**
	 *  查询下一个顺延序号sort   ==max(sort)+1
	 * @param domainClazz 必填
	 * @param where 选填
	 * @return
	 */
	public int getNextSort(Class domainClazz,String where) ;
	
	
	public Session getSession();
}
