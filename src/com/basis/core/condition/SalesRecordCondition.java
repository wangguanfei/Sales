/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2014
 */

package com.basis.core.condition;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.basis.core.common.Condition;
import com.basis.core.constants.ESortType;



public class SalesRecordCondition  extends Condition{
	

		//columns START
		/** id*/
		private java.lang.Long id;
		
		/** 商品*/
		private java.lang.Long goodsId;
		
		/** 销售数量*/
		private java.lang.Integer salesNumber;
		
		/** 销售价格*/
		private java.math.BigDecimal salesPrice;
		
		/** 商品进价*/
		private java.math.BigDecimal purchasePrice;
		
		/** 销售时间*/
		private java.util.Date salesDate;
		
		/** 销售年份*/
		private java.lang.String salesYear;
		
		/** 销售月份*/
		private java.lang.String salesMonth;
		
		/** 销售日期*/
		private java.lang.String salesDay;
		
		/** 收入*/
		private java.math.BigDecimal income;
		
		/** 利润*/
		private java.math.BigDecimal profit;
		
		/** 备注*/
		private java.lang.String remain;
		
		//columns END
		public java.lang.Long getGoodsId() {
			return this.goodsId;
		}
		
		public void setGoodsId(java.lang.Long value) {
			this.goodsId = value;
		}
		
		public java.lang.Integer getSalesNumber() {
			return this.salesNumber;
		}
		
		public void setSalesNumber(java.lang.Integer value) {
			this.salesNumber = value;
		}
		
		public java.util.Date getSalesDate() {
			return this.salesDate;
		}
		
		public void setSalesDate(java.util.Date value) {
			this.salesDate = value;
		}
		
		public java.lang.String getSalesYear() {
			return this.salesYear;
		}
		
		public void setSalesYear(java.lang.String value) {
			this.salesYear = value;
		}
		
		public java.lang.String getSalesMonth() {
			return this.salesMonth;
		}
		
		public void setSalesMonth(java.lang.String value) {
			this.salesMonth = value;
		}
		
		public java.lang.String getSalesDay() {
			return this.salesDay;
		}
		
		public void setSalesDay(java.lang.String value) {
			this.salesDay = value;
		}
		
		public java.lang.String getRemain() {
			return this.remain;
		}
		
		public void setRemain(java.lang.String value) {
			this.remain = value;
		}
		public java.math.BigDecimal getSalesPrice() {
			return salesPrice;
		}

		public void setSalesPrice(java.math.BigDecimal salesPrice) {
			this.salesPrice = salesPrice;
		}

		public java.math.BigDecimal getPurchasePrice() {
			return purchasePrice;
		}

		public void setPurchasePrice(java.math.BigDecimal purchasePrice) {
			this.purchasePrice = purchasePrice;
		}

		public java.math.BigDecimal getIncome() {
			return income;
		}

		public void setIncome(java.math.BigDecimal income) {
			this.income = income;
		}

		public java.math.BigDecimal getProfit() {
			return profit;
		}

		public void setProfit(java.math.BigDecimal profit) {
			this.profit = profit;
		}

	@Override
	public String getInitialHql() {
		setIsSQL(false);
		StringBuffer buffer=new StringBuffer();
		buffer.append("select t from SalesRecord t where 1=1 ");
		
			if(id != null) {
	            buffer.append(" and  t.id = :id ");
	        }
			if(goodsId != null) {
	            buffer.append(" and  t.goodsId = :goodsId ");
	        }
			if(salesNumber != null) {
	            buffer.append(" and  t.salesNumber = :salesNumber ");
	        }
			if(salesPrice != null) {
	            buffer.append(" and  t.salesPrice = :salesPrice ");
	        }
			if(purchasePrice != null) {
	            buffer.append(" and  t.purchasePrice = :purchasePrice ");
	        }
			if(salesDate != null) {
	            buffer.append(" and  t.salesDate = :salesDate ");
	        }
	        if(StringUtils.isNotBlank(salesYear)) {
	            buffer.append(" and  t.salesYear = :salesYear ");
	        }
	        if(StringUtils.isNotBlank(salesMonth)) {
	            buffer.append(" and  t.salesMonth = :salesMonth ");
	        }
	        if(StringUtils.isNotBlank(salesDay)) {
	            buffer.append(" and  t.salesDay = :salesDay ");
	        }
			if(income != null) {
	            buffer.append(" and  t.income = :income ");
	        }
			if(profit != null) {
	            buffer.append(" and  t.profit = :profit ");
	        }
	        if(StringUtils.isNotBlank(remain)) {
	            buffer.append(" and  t.remain = :remain ");
	        }
		return buffer.toString();
	}
	
		
	@Override
	public Query prepareParams(Query query) {
		try {
					if(id!=null) {
						query.setParameter("id", id);
					}
					if(goodsId!=null) {
						query.setParameter("goodsId", goodsId);
					}
					if(salesNumber!=null) {
						query.setParameter("salesNumber", salesNumber);
					}
					if(salesPrice!=null) {
						query.setParameter("salesPrice", salesPrice);
					}
					if(purchasePrice!=null) {
						query.setParameter("purchasePrice", purchasePrice);
					}
					if(salesDate!=null) {
						query.setParameter("salesDate", salesDate);
					}
					if(StringUtils.isNotBlank(salesYear)) {
						query.setParameter("salesYear", salesYear);
					}
					if(StringUtils.isNotBlank(salesMonth)) {
						query.setParameter("salesMonth", salesMonth);
					}
					if(StringUtils.isNotBlank(salesDay)) {
						query.setParameter("salesDay", salesDay);
					}
					if(income!=null) {
						query.setParameter("income", income);
					}
					if(profit!=null) {
						query.setParameter("profit", profit);
					}
					if(StringUtils.isNotBlank(remain)) {
						query.setParameter("remain", remain);
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
