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



public class GoodsCondition  extends Condition{
	

		//columns START
		/** ID*/
		private java.lang.Long id;
		
		/** 商品名称*/
		private java.lang.String name;
		
		/** 商品图片*/
		private java.lang.String img;
		
		/** 库存*/
		private java.lang.Integer stock;
		
		/** 简介*/
		private java.lang.String introduction;
		
		//columns END
		public java.lang.String getName() {
			return this.name;
		}
		
		public void setName(java.lang.String value) {
			this.name = value;
		}
		
		public java.lang.String getImg() {
			return this.img;
		}
		
		public void setImg(java.lang.String value) {
			this.img = value;
		}
		
		public java.lang.Integer getStock() {
			return this.stock;
		}
		
		public void setStock(java.lang.Integer value) {
			this.stock = value;
		}
		
		public java.lang.String getIntroduction() {
			return this.introduction;
		}
		
		public void setIntroduction(java.lang.String value) {
			this.introduction = value;
		}
		

	
	@Override
	public String getInitialHql() {
		setIsSQL(false);
		StringBuffer buffer=new StringBuffer();
		buffer.append("select t from Goods t where 1=1 ");
		
			if(id != null) {
	            buffer.append(" and  t.id = :id ");
	        }
	        if(StringUtils.isNotBlank(name)) {
	            buffer.append(" and  t.name like :name ");
	        }
	        if(StringUtils.isNotBlank(img)) {
	            buffer.append(" and  t.img = :img ");
	        }
			if(stock != null) {
	            buffer.append(" and  t.stock = :stock ");
	        }
	        if(StringUtils.isNotBlank(introduction)) {
	            buffer.append(" and  t.introduction = :introduction ");
	        }
		return buffer.toString();
	}
	
		
	@Override
	public Query prepareParams(Query query) {
		try {
					if(id!=null) {
						query.setParameter("id", id);
					}
					if(StringUtils.isNotBlank(name)) {
						query.setParameter("name", "%"+name+"%");
					}
					if(StringUtils.isNotBlank(img)) {
						query.setParameter("img", img);
					}
					if(stock!=null) {
						query.setParameter("stock", stock);
					}
					if(StringUtils.isNotBlank(introduction)) {
						query.setParameter("introduction", introduction);
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
