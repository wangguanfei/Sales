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

import com.basis.core.service.IFactoryInfoService;
import com.basis.core.service.IGoodsService;
import com.basis.core.util.ApplicationContextHolder;

public class PurchaseRecord implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	/** id*/
	private java.lang.Long id;
	
	/** 商品*/
	private java.lang.Long goodsId;
	private java.lang.Long factoryId;
	
	/** 进货数量*/
	private java.lang.Integer goodsNum;
	//是否结账
	private java.lang.Integer purchaseStatus;
	
	/** 进货价*/
	private java.math.BigDecimal purchasePrice;
	/** 总支出**/
	private java.math.BigDecimal totalMoney;
	
	/** 进货时间*/
	private java.lang.Long purchaseDate;
	
	/** 进货时间*/
	private java.lang.String purchaseDateStr;
	/** 进货年份*/
	private java.lang.String purchaseYear;
	
	/** 进货月份*/
	private java.lang.String purchaseMonth;
	
	/** 进货日期*/
	private java.lang.String purchaseDay;
	private java.lang.String remain;
	//columns END


	public PurchaseRecord(){
	}

	public PurchaseRecord(
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

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("GoodsId",getGoodsId())
			.append("GoodsNum",getGoodsNum())
			.append("PurchasePrice",getPurchasePrice())
			.append("PurchaseDate",getPurchaseDate())
			.append("PurchaseDateStr",getPurchaseDateStr())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof PurchaseRecord == false) return false;
		if(this == obj) return true;
		PurchaseRecord other = (PurchaseRecord)obj;
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
	 * @Description: 查询厂家
	 * @author wgf
	 * @date 2014-10-13上午10:24:42
	 * @param @return   
	 * @return Goods  
	 * @throws
	 */
	@Transient
	public FactoryInfo getFactoryInfo(){
		if (null != this.factoryId && this.factoryId !=0) {
			IFactoryInfoService factoryInfoService = (IFactoryInfoService)ApplicationContextHolder.getBean("factoryInfoService");
			return factoryInfoService.queryFactoryInfoById(factoryId);
		}
		return null;
		
	}
}

