package com.basis.core.dto;

public class StatisticsDto {

	private String statisticMonth;
	private java.math.BigDecimal income = new java.math.BigDecimal(0);
	private java.math.BigDecimal profit = new java.math.BigDecimal(0);
	private java.math.BigDecimal total_money = new java.math.BigDecimal(0);
	private java.math.BigInteger num ;
	

	public String getStatisticMonth() {
		return statisticMonth;
	}

	public void setStatisticMonth(String statisticMonth) {
		this.statisticMonth = statisticMonth;
	}

	public java.math.BigDecimal getIncome() {
		return null!=income?income:new java.math.BigDecimal(0);
	}

	public void setIncome(java.math.BigDecimal income) {
		this.income = income;
	}

	public java.math.BigDecimal getProfit() {
		return null!=profit?profit:new java.math.BigDecimal(0);
	}

	public void setProfit(java.math.BigDecimal profit) {
		this.profit = profit;
	}

	public java.math.BigDecimal getTotal_money() {
		return null!=total_money?total_money:new java.math.BigDecimal(0);
	}

	public void setTotal_money(java.math.BigDecimal total_money) {
		this.total_money = total_money;
	}

	public java.math.BigInteger getNum() {
		return num;
	}

	public void setNum(java.math.BigInteger num) {
		this.num = num;
	}

	
	
}
