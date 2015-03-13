/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2014
 */

package com.basis.core.condition;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;

import com.basis.core.common.Condition;
import com.basis.core.constants.ESortType;



public class PurchaseRecordCondition  extends Condition{
		/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 2617537740644381947L;

		//columns START
		/** id*/
		private java.lang.Long id;
		
		/** 商品*/
		private java.lang.Long goodsId;
		private java.lang.Long factoryId;
		/** 进货数量*/
		private java.lang.Integer goodsNum;
		
		/** 进货价*/
		private java.math.BigDecimal purchasePrice;
		/** 总支出**/
		private java.math.BigDecimal totalMoney;
		
		/** 进货时间*/
		private java.lang.Long purchaseDate;
		private java.lang.Long purchaseDateBeagin;
		private java.lang.String purchaseDateBeaginStr;
		private java.lang.String purchaseDateEndStr;
		private java.lang.Long purchaseDateEnd;
		
		/** 进货时间*/
		private java.lang.String purchaseDateStr;
		/** 进货年份*/
		private java.lang.String purchaseYear;
		
		/** 进货月份*/
		private java.lang.String purchaseMonth;
		
		/** 进货日期*/
		private java.lang.String purchaseDay;
		
		private java.lang.Integer purchaseStatus;
		private java.lang.String remain;
		//columns END
		public java.lang.Long getGoodsId() {
			return this.goodsId;
		}
		
		public void setGoodsId(java.lang.Long value) {
			this.goodsId = value;
		}
		
		public java.lang.Integer getGoodsNum() {
			return this.goodsNum;
		}
		
		public void setGoodsNum(java.lang.Integer value) {
			this.goodsNum = value;
		}
		
		
		public java.math.BigDecimal getPurchasePrice() {
			return purchasePrice;
		}

		public void setPurchasePrice(java.math.BigDecimal purchasePrice) {
			this.purchasePrice = purchasePrice;
		}

		public java.lang.Long getPurchaseDate() {
			return this.purchaseDate;
		}
		
		public void setPurchaseDate(java.lang.Long value) {
			this.purchaseDate = value;
		}
		
		public java.lang.String getPurchaseDateStr() {
			return this.purchaseDateStr;
		}
		
		public void setPurchaseDateStr(java.lang.String value) {
			this.purchaseDateStr = value;
		}
		
		public java.lang.Long getPurchaseDateBeagin() {
			return purchaseDateBeagin;
		}

		public void setPurchaseDateBeagin(java.lang.Long purchaseDateBeagin) {
			this.purchaseDateBeagin = purchaseDateBeagin;
		}

		public java.lang.Long getPurchaseDateEnd() {
			return purchaseDateEnd;
		}

		public void setPurchaseDateEnd(java.lang.Long purchaseDateEnd) {
			this.purchaseDateEnd = purchaseDateEnd;
		}

		
		public java.lang.String getPurchaseDateBeaginStr() {
			return purchaseDateBeaginStr;
		}

		public void setPurchaseDateBeaginStr(java.lang.String purchaseDateBeaginStr) {
			this.purchaseDateBeaginStr = purchaseDateBeaginStr;
		}

		public java.lang.String getPurchaseDateEndStr() {
			return purchaseDateEndStr;
		}

		public void setPurchaseDateEndStr(java.lang.String purchaseDateEndStr) {
			this.purchaseDateEndStr = purchaseDateEndStr;
		}

		
		public java.math.BigDecimal getTotalMoney() {
			return totalMoney;
		}

		public void setTotalMoney(java.math.BigDecimal totalMoney) {
			this.totalMoney = totalMoney;
		}

		public java.lang.String getPurchaseYear() {
			return purchaseYear;
		}

		public void setPurchaseYear(java.lang.String purchaseYear) {
			this.purchaseYear = purchaseYear;
		}

		public java.lang.String getPurchaseMonth() {
			return purchaseMonth;
		}

		public void setPurchaseMonth(java.lang.String purchaseMonth) {
			this.purchaseMonth = purchaseMonth;
		}

		public java.lang.String getPurchaseDay() {
			return purchaseDay;
		}

		public void setPurchaseDay(java.lang.String purchaseDay) {
			this.purchaseDay = purchaseDay;
		}

		
	   public java.lang.Integer getPurchaseStatus() {
			return purchaseStatus;
		}

		public void setPurchaseStatus(java.lang.Integer purchaseStatus) {
			this.purchaseStatus = purchaseStatus;
		}

     	  public java.lang.Long getFactoryId() {
			return factoryId;
		}

		public void setFactoryId(java.lang.Long factoryId) {
			this.factoryId = factoryId;
		}

	public java.lang.String getRemain() {
			return remain;
		}

		public void setRemain(java.lang.String remain) {
			this.remain = remain;
		}

	@Override
	public String getInitialHql() {
		setIsSQL(false);
		StringBuffer buffer=new StringBuffer();
		buffer.append("select t from PurchaseRecord t where 1=1 ");
		
			if(id != null) {
	            buffer.append(" and  t.id = :id ");
	        }
			if(goodsId != null) {
	            buffer.append(" and  t.goodsId = :goodsId ");
	        }
			if(factoryId != null) {
				buffer.append(" and  t.factoryId = :factoryId ");
			}
			if(goodsNum != null) {
	            buffer.append(" and  t.goodsNum = :goodsNum ");
	        }
			if(purchaseStatus != null) {
				buffer.append(" and  t.purchaseStatus = :purchaseStatus ");
			}
			if(purchasePrice != null) {
	            buffer.append(" and  t.purchasePrice = :purchasePrice ");
	        }
			if(totalMoney != null) {
				buffer.append(" and  t.totalMoney = :totalMoney ");
			}
			if(purchaseDateBeagin != null) {
	            buffer.append(" and  t.purchaseDate >= :purchaseDateBeagin ");
	        }
			if(purchaseDateEnd != null) {
				buffer.append(" and  t.purchaseDate <= :purchaseDateEnd ");
			}
	        if(StringUtils.isNotBlank(purchaseDateStr)) {
	            buffer.append(" and  t.purchaseDateStr = :purchaseDateStr ");
	        }
	        if(StringUtils.isNotBlank(purchaseYear)) {
	            buffer.append(" and  t.purchaseYear = :purchaseYear ");
	        }
	        if(StringUtils.isNotBlank(purchaseMonth)) {
	            buffer.append(" and  t.purchaseMonth = :purchaseMonth ");
	        }
	        if(StringUtils.isNotBlank(purchaseDay)) {
	            buffer.append(" and  t.purchaseDay = :purchaseDay ");
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
					if(factoryId!=null) {
						query.setParameter("factoryId", factoryId);
					}
					if(goodsNum!=null) {
						query.setParameter("goodsNum", goodsNum);
					}
					if(purchaseStatus!=null) {
						query.setParameter("purchaseStatus", purchaseStatus);
					}
					if(purchasePrice!=null) {
						query.setParameter("purchasePrice", purchasePrice);
					}
					if(totalMoney!=null) {
						query.setParameter("totalMoney", totalMoney);
					}
					if(purchaseDate!=null) {
						query.setParameter("purchaseDate", purchaseDate);
					}
					if(purchaseDateBeagin!=null) {
						query.setParameter("purchaseDateBeagin", purchaseDateBeagin);
					}
					if(purchaseDateEnd!=null) {
						query.setParameter("purchaseDateEnd", purchaseDateEnd);
					}
					if(StringUtils.isNotBlank(purchaseDateStr)) {
						query.setParameter("purchaseDateStr", purchaseDateStr);
					}
					if(StringUtils.isNotBlank(purchaseYear)) {
						query.setParameter("purchaseYear", purchaseYear);
					}
					if(StringUtils.isNotBlank(purchaseMonth)) {
						query.setParameter("purchaseMonth", purchaseMonth);
					}
					if(StringUtils.isNotBlank(purchaseDay)) {
						query.setParameter("purchaseDay", purchaseDay);
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
