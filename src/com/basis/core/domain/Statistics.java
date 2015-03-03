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

public class Statistics implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
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


	public Statistics(){
	}

	public Statistics(
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
	
	
	
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Syear",getSyear())
			.append("Smonth",getSmonth())
			.append("Sday",getSday())
			.append("Sin",getSin())
			.append("Sprofit",getSprofit())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Statistics == false) return false;
		if(this == obj) return true;
		Statistics other = (Statistics)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

