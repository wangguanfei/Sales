package com.basis.core.condition;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;

import com.basis.core.common.Condition;
import com.basis.core.constants.ESortType;

public class MenuCondition extends Condition {

	@Override
	public String getInitialHql() {
		StringBuffer buffer=new StringBuffer();
		buffer.append("select menu from Menu menu where 1=1 ");
		
		return buffer.toString();
	}

	@Override
	public Query prepareParams(Query query) {
		return query;
	}
	
	@Override
	public String getSortname() {
		if(StringUtils.isBlank(super.getSortname())){
			super.setSortorder(ESortType.ASC.getCode());
			return "sort";
		}
		return super.getSortname();
	}

	@Override
	public String getDataFilterSql() {
		// TODO Auto-generated method stub
		return null;
	}

}
