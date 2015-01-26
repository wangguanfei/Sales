package com.basis.core.service.impl;
import java.io.Serializable;
import java.util.List;
import com.basis.core.common.Condition;
import com.basis.core.common.Page;
import com.basis.core.dao.IBaseDao;
import com.basis.core.service.IBaseService;
/**
 * REST基类
 */
public class BaseServiceImpl implements IBaseService{
	private IBaseDao dao;
	
	
	public void setDao(IBaseDao dao) {
		this.dao = dao;
	}

	public void modifyObject(Object o) {
		dao.updateObject(o);
	}

	public void deleteObject(Object o) {
		dao.deleteObject(o);
	}

	public String addObject(Object o) {
		return dao.addObject(o);
	}

	
	@SuppressWarnings("unchecked")
	public Object queryObject(Class c, Serializable id) {
		return dao.getObject(c, id);
	}
	
	@SuppressWarnings("unchecked")
	public List queryObjectList(Condition condition) {
		return dao.queryObjectList(condition);
	}

	@SuppressWarnings("unchecked")
	public Page queryPage(Condition condition) {
		return dao.queryPage(condition);
	}

	public long queryCount(Condition condition) {
		return dao.queryCount(condition);
	}

	public Object queryUniqueResult(Condition condition) {
		return dao.uniqueResult(condition);
	}
	
	
}
