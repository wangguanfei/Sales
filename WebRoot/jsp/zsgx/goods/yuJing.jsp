<%@page import="com.basis.core.domain.Goods"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  </head>
  <body>
  	<%
  		List<Goods> list = new ArrayList<Goods>();
  		list = (List<Goods>)request.getAttribute("list");
  		
  	%>
   	<ul>
	<li><a href="javascript:;"> <span
			class="icon inventoryLow"></span>
			<div id="div_inventory" class="text font666">
				<span class="font666">您有<span class="fontRed "><%=list.size() %></span>个商品库存低于预警线
				</span>
			</div> </a>
	</li>
	<%
		for(int i = 0;i<list.size();i++){
			Goods good = list.get(i);
	%>
	
	<li id="warn_serial" style="display: list-item;"><a href="javascript:;"> <span
			class="icon SNdeviant"></span>
			<div id="div_errorserialnumber" style="padding-top:8px;"
				class="text font666">
				<span class="font666 fontRed"><span class="fontRed"><%=good.getName()%></span>库存数量为：<span class="fontRed"><%=good.getStock()%></span>个,请尽快进货。 </span>
			</div> </a>
	</li>
	<%
		} %>
</ul>
  </body>
</html>
