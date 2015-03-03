package com.basis.core.dto;

public class StatisticsDto {

	private String statisticMonth;
	private java.math.BigDecimal income;
	private java.math.BigDecimal profit;

	public String getStatisticMonth() {
		return statisticMonth;
	}

	public void setStatisticMonth(String statisticMonth) {
		this.statisticMonth = statisticMonth;
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

}
