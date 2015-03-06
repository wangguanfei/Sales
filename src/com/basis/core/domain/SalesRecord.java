/*
 * Powered By [up72-framework]
 * Web Site: http://www.up72.com
 * Since 2006 - 2014
 */

package com.basis.core.domain;

import javax.persistence.Transient;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.basis.core.service.ICustomerService;
import com.basis.core.service.IGoodsService;
import com.basis.core.util.ApplicationContextHolder;
import com.basis.core.util.DateUtil;

public class SalesRecord implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
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
	
	/** 客户*/
	private java.lang.Long customerId;
	
	//columns END


	public SalesRecord(){
	}

	public SalesRecord(
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
	
	@Transient
	public String getSalesDateStr(){
		if (null!=salesDate) {
			return DateUtil.format(DateUtil.longToDate(salesDate), "yyyy-MM-dd HH:mm");
		}
		return "";
		
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

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("GoodsId",getGoodsId())
			.append("SalesNumber",getSalesNumber())
			.append("SalesPrice",getSalesPrice())
			.append("PurchasePrice",getPurchasePrice())
			.append("SalesDate",getSalesDate())
			.append("SalesYear",getSalesYear())
			.append("SalesMonth",getSalesMonth())
			.append("SalesDay",getSalesDay())
			.append("Income",getIncome())
			.append("Profit",getProfit())
			.append("Remain",getRemain())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof SalesRecord == false) return false;
		if(this == obj) return true;
		SalesRecord other = (SalesRecord)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
	
	/**
	 * @Description: 查询商品
	 * @author wgf
	 * @date 2014-10-13上午10:24:42
	 * @param @return   
	 * @return Goods  
	 * @throws
	 */
	@Transient
	public Goods getGoods(){
		if (null != this.goodsId && this.goodsId !=0) {
			IGoodsService goodsService = (IGoodsService)ApplicationContextHolder.getBean("goodsService");
			return goodsService.queryGoodsById(this.goodsId);
		}
		return null;
		
	}
	/**
	 * @Description: 查询顾客
	 * @author wgf
	 * @date 2014-10-13上午10:24:42
	 * @param @return   
	 * @return Goods  
	 * @throws
	 */
	@Transient
	public Customer getCustomer(){
		if (null != this.customerId && this.customerId !=0) {
			ICustomerService customerService = (ICustomerService)ApplicationContextHolder.getBean("customerService");
			return customerService.queryCustomerById(this.customerId);
		}
		return null;
		
	}
}

