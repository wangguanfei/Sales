/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2015
 */

package com.basis.core.domain;

import java.util.Date;
import java.util.Set;
import java.util.HashSet;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class Customer implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
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


	public Customer(){
	}

	public Customer(
		java.lang.Long id
	){
		this.id = id;
	}

	

	public void setId(java.lang.Long value) {
		this.id = value;
	}
	
	public java.lang.Long getId() {
		return this.id;
	}
	
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
	
	
	
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Name",getName())
			.append("Sex",getSex())
			.append("Phone",getPhone())
			.append("Address",getAddress())
			.append("Qq",getQq())
			.append("TotalSpend",getTotalSpend())
			.append("Remain",getRemain())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Customer == false) return false;
		if(this == obj) return true;
		Customer other = (Customer)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

