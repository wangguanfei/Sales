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
		private java.lang.Long salesDate;
		private java.lang.Long salesDateBeagin;
		private java.lang.Long salesDateEnd;
		private java.lang.String salesYear;
		private java.lang.String salesDateBeaginStr;
		private java.lang.String salesDateEndStr;
		
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
		
		/** 客户*/
		private java.lang.Long customerId;
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
		
		public java.lang.Long getSalesDate() {
			return salesDate;
		}

		public void setSalesDate(java.lang.Long salesDate) {
			this.salesDate = salesDate;
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
		
     	public java.lang.Long getCustomerId() {
			return customerId;
		}

		public void setCustomerId(java.lang.Long customerId) {
			this.customerId = customerId;
		}
		
	   public java.lang.Long getSalesDateBeagin() {
			return salesDateBeagin;
		}

		public void setSalesDateBeagin(java.lang.Long salesDateBeagin) {
			this.salesDateBeagin = salesDateBeagin;
		}

		public java.lang.Long getSalesDateEnd() {
			return salesDateEnd;
		}

		public void setSalesDateEnd(java.lang.Long salesDateEnd) {
			this.salesDateEnd = salesDateEnd;
		}

    	public java.lang.String getSalesDateBeaginStr() {
			return salesDateBeaginStr;
		}

		public void setSalesDateBeaginStr(java.lang.String salesDateBeaginStr) {
			this.salesDateBeaginStr = salesDateBeaginStr;
		}

		public java.lang.String getSalesDateEndStr() {
			return salesDateEndStr;
		}

		public void setSalesDateEndStr(java.lang.String salesDateEndStr) {
			this.salesDateEndStr = salesDateEndStr;
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
			if(salesDateBeagin != null) {
	            buffer.append(" and  t.salesDate >= :salesDateBeagin ");
	        }
			if(salesDateEnd != null) {
	            buffer.append(" and  t.salesDate <= :salesDateEnd ");
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
			if(customerId != null) {
	            buffer.append(" and  t.customerId = :customerId ");
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
					if(salesDateBeagin != null) {
						query.setParameter("salesDateBeagin", salesDateBeagin);
			        }
					if(salesDateEnd != null) {
						query.setParameter("salesDateEnd", salesDateEnd);
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
					if(customerId != null) {
						query.setParameter("customerId", customerId);
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
