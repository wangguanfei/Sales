/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2014
 */

package com.basis.core.domain;

import java.util.Date;
import java.util.Set;
import java.util.HashSet;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class Goods implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
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


	public Goods(){
	}

	public Goods(
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
	
	
	
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Name",getName())
			.append("Img",getImg())
			.append("Stock",getStock())
			.append("Introduction",getIntroduction())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Goods == false) return false;
		if(this == obj) return true;
		Goods other = (Goods)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

