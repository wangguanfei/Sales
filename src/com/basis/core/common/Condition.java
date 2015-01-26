package com.basis.core.common;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;

import com.basis.core.constants.ESortType;

/**
 */
@SuppressWarnings("serial")
public abstract class Condition implements  Serializable, Cloneable {
	
	private String sortname;//排序字段
	private String sortorder=ESortType.DESC.getCode();//升序/降序(默认)
	private int pageSize = Page.DEFAULT_PAGE_SIZE;
	private int pageNo =Page.DEFAULT_PAGE_NO;
	private boolean isSQL = false;
	
	private boolean dataFilter=false;//是否开启数据过滤权限
	private long cityBitDataFilter=0;//城市数据过滤bit值
	private long gradegroupBitDataFilter=0;//年部数据过滤bit值
	private long subjectBitDataFilter=0;//学科数据过滤bit值
	
	private String dataFilterSql="";//数据过滤sql语句
	/**
	 * sql/hql
	 * @return
	 */
	public abstract String getInitialHql();
	
	/**
	 * 设置参数
	 * @param query
	 * @return
	 */
	public abstract Query prepareParams(Query query); 
	
	/**
	 * 封装数据过滤的sql语句
	 * @return
	 */
	public abstract String getDataFilterSql();
	
	
	public String getFilterHql(){
		if(dataFilter&&StringUtils.isNotBlank(getDataFilterSql())){
			return getInitialHql() + getDataFilterSql();
		}else{
			return getInitialHql();
		}
	}

	public String getCompleteHql() {
		String completeHql=getFilterHql();
		if (StringUtils.isNotBlank(getSortname()))
			completeHql += " order by " + getSortname()+" "+getSortorder();
		return completeHql;
	}

	
	public Query getHibernateQuery(Session session){
		Query query = null;
		String queryString=this.getCompleteHql();
		if(isSQL){
			query = session.createSQLQuery(queryString);
		}else{
			query = session.createQuery(queryString);
		}
		query = this.prepareParams(query);
		return query;	
	}

	public Query getHibernateCountQuery(Session session){
		Query query = null;
		String countQueryString = "select count(*) " + trimSelect(this.getCompleteHql());
		if(isSQL){
			query = session.createSQLQuery(countQueryString);
		}else{
			query = session.createQuery(countQueryString);
		}
		query = this.prepareParams(query);
		return query;
	}
	
	private String trimSelect(String hql){
		return StringUtils.mid(hql,StringUtils.indexOf(hql,"from"),hql.length());
	}
	
	
	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public boolean getIsSQL() {
		return isSQL;
	}

	public void setIsSQL(boolean isSQL) {
		this.isSQL = isSQL;
	}
	
	public String getSortname() {
		return sortname;
	}
	public void setSortname(String sortname) {
		this.sortname = sortname;
	}
	public String getSortorder() {
		return sortorder;
	}
	public void setSortorder(String sortorder) {
		this.sortorder = sortorder;
	}
	public long getCityBitDataFilter() {
		return cityBitDataFilter;
	}
	public void setCityBitDataFilter(long cityBitDataFilter) {
		this.cityBitDataFilter = cityBitDataFilter;
	}
	public long getGradegroupBitDataFilter() {
		return gradegroupBitDataFilter;
	}
	public void setGradegroupBitDataFilter(long gradegroupDataFilter) {
		this.gradegroupBitDataFilter = gradegroupDataFilter;
	}
	public long getSubjectBitDataFilter() {
		return subjectBitDataFilter;
	}
	public void setSubjectBitDataFilter(long subjectDataFilter) {
		this.subjectBitDataFilter = subjectDataFilter;
	}

	public boolean isDataFilter() {
		return dataFilter;
	}

	public void setDataFilter(boolean dataFilter) {
		this.dataFilter = dataFilter;
	}

	public void setDataFilterSql(String dataFilterSql) {
		this.dataFilterSql = dataFilterSql;
	}
	
}
