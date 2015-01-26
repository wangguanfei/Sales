package com.basis.core.condition;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;

import com.basis.core.common.Condition;
import com.basis.core.constants.ESortType;

public class RoletemplateCondition extends Condition {
	private String likename;
	
	@Override
	public String getInitialHql() {
		StringBuffer buffer=new StringBuffer();
		buffer.append("select roletemplate from Roletemplate roletemplate where roletemplate.state=1 ");
		if(StringUtils.isNotBlank(likename)){
			buffer.append(" and roletemplate.name like :likename ");
		}
		return buffer.toString();
	}

	@Override
	public Query prepareParams(Query query) {
		if(StringUtils.isNotBlank(likename)){
			query.setString("likename", "%"+likename+"%");
		}
		return query;
	}
	
	@Override
	public String getSortname(){
		if(StringUtils.isBlank(super.getSortname())){
			super.setSortorder(ESortType.DESC.getCode());
			return "roletemplate.createTime";
		}
		return super.getSortname();
	}

	public String getLikename() {
		return likename;
	}

	public void setLikename(String likename) {
		this.likename = likename;
	}

	@Override
	public String getDataFilterSql() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
