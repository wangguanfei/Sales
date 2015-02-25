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



public class CustomerCondition  extends Condition{
	

		//columns START
		/** id*/
		private java.lang.Long id;
		
		/** 客户姓名*/
		private java.lang.String name;
		
		/** 客户性别*/
		private java.lang.Integer sex;
		
		/** 客户电话*/
		private java.lang.String phone;
		
		/** 客户地址*/
		private java.lang.String address;
		
		/** 客户QQ*/
		private java.lang.String qq;
		
		/** 消费金额*/
		private java.lang.Float totalSpend;
		
		/** 备注*/
		private java.lang.String remain;
		
		//columns END
		public java.lang.String getName() {
			return this.name;
		}
		
		public void setName(java.lang.String value) {
			this.name = value;
		}
		
		public java.lang.Integer getSex() {
			return this.sex;
		}
		
		public void setSex(java.lang.Integer value) {
			this.sex = value;
		}
		
		public java.lang.String getPhone() {
			return this.phone;
		}
		
		public void setPhone(java.lang.String value) {
			this.phone = value;
		}
		
		public java.lang.String getAddress() {
			return this.address;
		}
		
		public void setAddress(java.lang.String value) {
			this.address = value;
		}
		
		public java.lang.String getQq() {
			return this.qq;
		}
		
		public void setQq(java.lang.String value) {
			this.qq = value;
		}
		
		public java.lang.Float getTotalSpend() {
			return this.totalSpend;
		}
		
		public void setTotalSpend(java.lang.Float value) {
			this.totalSpend = value;
		}
		
		public java.lang.String getRemain() {
			return this.remain;
		}
		
		public void setRemain(java.lang.String value) {
			this.remain = value;
		}
		

	
	@Override
	public String getInitialHql() {
		setIsSQL(false);
		StringBuffer buffer=new StringBuffer();
		buffer.append("select t from Customer t where 1=1 ");
		
			if(id != null) {
	            buffer.append(" and  t.id = :id ");
	        }
	        if(StringUtils.isNotBlank(name)) {
	            buffer.append(" and  t.name = :name ");
	        }
			if(sex != null) {
	            buffer.append(" and  t.sex = :sex ");
	        }
	        if(StringUtils.isNotBlank(phone)) {
	            buffer.append(" and  t.phone = :phone ");
	        }
	        if(StringUtils.isNotBlank(address)) {
	            buffer.append(" and  t.address = :address ");
	        }
	        if(StringUtils.isNotBlank(qq)) {
	            buffer.append(" and  t.qq = :qq ");
	        }
			if(totalSpend != null) {
	            buffer.append(" and  t.totalSpend = :totalSpend ");
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
					if(StringUtils.isNotBlank(name)) {
						query.setParameter("name", name);
					}
					if(sex!=null) {
						query.setParameter("sex", sex);
					}
					if(StringUtils.isNotBlank(phone)) {
						query.setParameter("phone", phone);
					}
					if(StringUtils.isNotBlank(address)) {
						query.setParameter("address", address);
					}
					if(StringUtils.isNotBlank(qq)) {
						query.setParameter("qq", qq);
					}
					if(totalSpend!=null) {
						query.setParameter("totalSpend", totalSpend);
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
