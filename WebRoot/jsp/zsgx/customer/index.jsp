<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/inc/meta.inc" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<head>
	<%@ include file="/jsp/inc/head.inc" %>
	<script type="text/javascript">
		basePath = "${basePath}";
	</script>
	<script type="text/javascript" src="${basePath}jsp/zsgx/common/common.js"></script>
	<script type="text/javascript" src="${basePath}jsp/zsgx/customer/customer.js"></script>
	</head>
<body>
	<!-- search start -->
	<div class="content">
		<form id="zsgx_customer_form" name="zsgx_customer_form">
		   <ul>
		   <!-- search param 参数过长请拆分UL -->
			   <li>
				   <label>客户姓名：</label>
				   <input id="customerCondition.name" class="search-input-text" name="customerCondition.name" value="${customerCondition.name}" />
				   <label>客户电话：</label>
				   <input id="customerCondition.phone" class="search-input-text" name="customerCondition.phone" value="${customerCondition.phone}" />
				   <label>客户QQ：</label>
				   <input id="customerCondition.qq" class="search-input-text" name="customerCondition.qq" value="${customerCondition.qq}" />
				   </li>
			<!-- search btn -->
		   	<li><a class="l-button" onclick="customer_search()">查询</a></li>
		   	<li><a class="l-button" onclick="$('#zsgx_customer_form')[0].reset()">重置</a></li>
		   </ul>
		</form>
	</div>
	
	<!-- table start -->
    <div id="zsgx_customer_grid" class="grid">
    </div>
</body>
</html>