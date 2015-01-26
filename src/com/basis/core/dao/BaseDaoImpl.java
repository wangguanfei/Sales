package com.basis.core.dao;
import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.basis.core.common.Condition;
import com.basis.core.common.Page;
import com.basis.core.condition.QuerySameNameCountCondition;
import com.basis.core.condition.QuerySameSortCountCondition;
import com.basis.core.condition.QuerySortCondition;
import com.basis.core.exception.NameIsExistException;
import com.basis.core.exception.SortIsExistException;
/**
 * DAO基类
 */
public class BaseDaoImpl implements IBaseDao{
	
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public void updateObject(Object o) {
		getSession().update(o);
	}

	public void addOrUpdate(Object o) {
		getSession().saveOrUpdate(o);
	}

	public void deleteObject(Object o) {
		getSession().delete(o);
	}

	public String addObject(Object o) {
		return getSession().save(o).toString();
	}

	public int updateOrDeleteAll(Condition condition) {
		Query q = condition.getHibernateQuery(getSession());
		return q.executeUpdate();
	}
	
	@SuppressWarnings("unchecked")
	public <T extends Object> T getObject(Class<T> c, Serializable id) {
		return (T)getSession().get(c, id);
	}
	
	@SuppressWarnings("unchecked")
	public <E> List<E> queryObjectList(Condition condition) {
		Query query = condition.getHibernateQuery(getSession());
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public <E> Page<E> queryPage(Condition condition) {
		int pageNo = condition.getPageNo();
		int pageSize = condition.getPageSize();
		long totalCount = this.queryCount(condition);
		if (totalCount < 1)
			 return new Page<E>();
		int startIndex = Page.getStartOfPage(pageNo, pageSize);
		Query query = condition.getHibernateQuery(getSession());
		List<E> list = query.setFirstResult(startIndex).setMaxResults(pageSize).list();
		return new Page<E>(pageNo, pageSize , totalCount , list);
	}

	public long queryCount(Condition condition) {
		Query query = condition.getHibernateCountQuery(getSession());
		long totalCount=0;
		try {
			Object uniqueResult = query.uniqueResult();
			totalCount = null==uniqueResult?0L:Long.valueOf(uniqueResult.toString());
		} catch (Exception e) {
			e.printStackTrace();
			totalCount = 0;
		} catch (Error e) {
			e.printStackTrace();
		}
		return totalCount;
	}

	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}

	public <E extends Object> E uniqueResult(Condition condition) {
		Query query = condition.getHibernateQuery(getSession());
		return (E)query.uniqueResult();
	}

	public void validHasSameName(Class domainClazz,String name,String where) throws NameIsExistException {
		QuerySameNameCountCondition condition=new QuerySameNameCountCondition(domainClazz, name,where);
		Object object=this.uniqueResult(condition);
		long size=object==null?0l:(Long)object;
		if(size>0l){
			throw new NameIsExistException("the name is exist!");
		}
	}

	public int getNextSort(Class domainClazz,String where) {
		QuerySortCondition condition=new QuerySortCondition(domainClazz,where);
		Object mxsort=this.uniqueResult(condition);
		int sort=mxsort==null?1:(Integer)mxsort+1;
		return sort;
	}

	public void validHasSameSort(Class domainClazz, Integer sort, String where)
			throws SortIsExistException {
		QuerySameSortCountCondition condition=new QuerySameSortCountCondition(domainClazz, sort,where);
		Object object=this.uniqueResult(condition);
		long size=object==null?0l:(Long)object;
		if(size>0l){
			throw new SortIsExistException("the sort is exist!");
		}
	}
	
}
