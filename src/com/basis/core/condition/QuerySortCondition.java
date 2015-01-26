package com.basis.core.condition;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;

import com.basis.core.common.Condition;
/**
 * 查询序号最大sort  所有的
 * @author wxliu
 *
 */
public class QuerySortCondition extends Condition {
	private Class clazz;//要查询sort类的class 如：查询City  clazz=City.class
	private String where;//where 语句，添加查询条件
	
	@Override
	public String getInitialHql() {
		StringBuffer buffer=new StringBuffer();
		buffer.append("select max(clazz.sort) from ");
		buffer.append(clazz.getName()+" clazz ");
		if(StringUtils.isNotBlank(where)){
			buffer.append(where);
		}
		return buffer.toString();
	}

	public Query prepareParams(Query query) {
		return query;
	}

	public QuerySortCondition() {
		super();
	}

	public QuerySortCondition(Class domainClazz) {
		super();
		this.clazz = domainClazz;
	}
	public QuerySortCondition(Class domainClazz,String where) {
		super();
		this.clazz = domainClazz;
		this.where=where;
	}

	public String getDataFilterSql() {
		// TODO Auto-generated method stub
		return null;
	}
}
