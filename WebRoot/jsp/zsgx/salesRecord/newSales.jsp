<%@page import="com.basis.core.domain.SalesRecord"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  </head>
  <body>
  	<%
  		List<SalesRecord> list = (List<SalesRecord>)request.getAttribute("list");
  		for(int i = list.size() - 1; i >= 0; i--){
  			SalesRecord sr = list.get(i);
  			SalesRecord sr2 = null;
  			boolean flag = false;
  			if(i > 0){
  				sr2 = list.get(i-1);
  				flag = true;
  				i--;
  			}
  	%>
  		<div class="fl">
			<div class="oddLine">
				<div class="box">
					<div class="content">
						商品名称：<%=sr.getGoods().getName() %><br>
						销售数量：<%=sr.getSalesNumber() %><br>
						销售价格：<%=sr.getSalesPrice() %><br>
						销售总额：<%=sr.getIncome() %><br>
						利润总额：<%=sr.getProfit()%><br>
						客户名称：<%=sr.getCustomer().getName()%><br>
					</div>
					<div class="angle"></div>
					<div class="time fontDeepBlue"><%=sr.getSalesDateStr()%></div>
				</div>
			</div>
			<%
				if(flag){
			%>		
			<div class="evenLine">
				<div class="box">
					<div class="time fontDeepBlue"><%=sr2.getSalesDateStr()%></div>
					<div class="angle"></div>
					<div class="content">
						商品名称：<%=sr2.getGoods().getName() %><br>
						销售数量：<%=sr2.getSalesNumber() %><br>
						销售价格：<%=sr2.getSalesPrice() %><br>
						销售总额：<%=sr2.getIncome() %><br>
						利润总额：<%=sr2.getProfit()%><br>
						客户名称：<%=sr2.getCustomer().getName()%><br>
					</div>
				</div>
			</div>
			<%
				}
			%>
		</div>
  	<%
  		}
  	%>
  </body>
</html>
