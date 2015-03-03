/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2015
 */

package com.basis.core.condition;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.basis.core.common.Condition;
import com.basis.core.constants.ESortType;



public class StatisticsCondition  extends Condition{
	

		//columns START
		/** id*/
		private java.lang.Long id;
		
		/** 年份*/
		private java.lang.String syear;
		
		/** 月份*/
		private java.lang.String smonth;
		
		/** 日*/
		private java.lang.String sday;
		
		/** 总收入*/
		private java.math.BigDecimal sin;
		
		/** 总利润*/
		private java.math.BigDecimal sprofit;
		
		//columns END
		public java.lang.String getSyear() {
			return this.syear;
		}
		
		public void setSyear(java.lang.String value) {
			this.syear = value;
		}
		
		public java.lang.String getSmonth() {
			return this.smonth;
		}
		
		public void setSmonth(java.lang.String value) {
			this.smonth = value;
		}
		
		public java.lang.String getSday() {
			return this.sday;
		}
		
		public void setSday(java.lang.String value) {
			this.sday = value;
		}
		
		public java.math.BigDecimal getSin() {
			return this.sin;
		}
		
		public void setSin(java.math.BigDecimal value) {
			this.sin = value;
		}
		
		public java.math.BigDecimal getSprofit() {
			return this.sprofit;
		}
		
		public void setSprofit(java.math.BigDecimal value) {
			this.sprofit = value;
		}
		

	
	@Override
	public String getInitialHql() {
		setIsSQL(false);
		StringBuffer buffer=new StringBuffer();
		buffer.append("select t from Statistics t where 1=1 ");
		
			if(id != null) {
	            buffer.append(" and  t.id = :id ");
	        }
	        if(StringUtils.isNotBlank(syear)) {
	            buffer.append(" and  t.syear = :syear ");
	        }
	        if(StringUtils.isNotBlank(smonth)) {
	            buffer.append(" and  t.smonth = :smonth ");
	        }
	        if(StringUtils.isNotBlank(sday)) {
	            buffer.append(" and  t.sday = :sday ");
	        }
			if(sin != null) {
	            buffer.append(" and  t.sin = :sin ");
	        }
			if(sprofit != null) {
	            buffer.append(" and  t.sprofit = :sprofit ");
	        }
		return buffer.toString();
	}
	
		
	@Override
	public Query prepareParams(Query query) {
		try {
					if(id!=null) {
						query.setParameter("id", id);
					}
					if(StringUtils.isNotBlank(syear)) {
						query.setParameter("syear", syear);
					}
					if(StringUtils.isNotBlank(smonth)) {
						query.setParameter("smonth", smonth);
					}
					if(StringUtils.isNotBlank(sday)) {
						query.setParameter("sday", sday);
					}
					if(sin!=null) {
						query.setParameter("sin", sin);
					}
					if(sprofit!=null) {
						query.setParameter("sprofit", sprofit);
					}
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		return query;
	}
	
	@Override
	public String getSortname(){
		if(StringUtils.isBlank(super.getSortname())){
			super.setSortorder(ESortType.ASC.getCode());
			return "id";
		}
		return super.getSortname();
	}
	
	@Override
	public String getDataFilterSql() {
		long bit=getCityBitDataFilter();
		if(bit>0){
			return " and bitand(item.bit,"+bit+")<>0 ";
		}
		return "";
	}

}
