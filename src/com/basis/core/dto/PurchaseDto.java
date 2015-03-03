package com.basis.core.dto;

public class PurchaseDto {

	private java.math.BigDecimal total_money;
	private String purchase_month;

	public java.math.BigDecimal getTotal_money() {
		return total_money;
	}

	public void setTotal_money(java.math.BigDecimal total_money) {
		this.total_money = total_money;
	}

	public String getPurchase_month() {
		return purchase_month;
	}

	public void setPurchase_month(String purchase_month) {
		this.purchase_month = purchase_month;
	}

}
