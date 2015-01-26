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
	<script type="text/javascript" src="${basePath}jsp/zsgx/salesRecord/salesRecord.js"></script>
	</head>
<body>
	<!-- search start -->
	<div class="content">
		<form id="zsgx_salesRecord_form" name="zsgx_salesRecord_form">
		   <ul>
		   <!-- search param 参数过长请拆分UL -->
			   <li>
				   <input id="salesRecordCondition.goodsId" type="hidden" class="search-input-text" name="salesRecordCondition.goodsId" value="${salesRecordCondition.goodsId}" />
				   <label>销售时间：</label>
				   <input id="salesRecordCondition.salesDate" class="search-input-text" name="salesRecordCondition.salesDate" value="${salesRecordCondition.salesDate}" />
				   </li>
			<!-- search btn -->
		   	<li><a class="l-button" onclick="salesRecord_search()">查询</a></li>
		   	<li><a class="l-button" onclick="$('#zsgx_salesRecord_form')[0].reset()">重置</a></li>
		   </ul>
		</form>
	</div>
	
	<!-- table start -->
    <div id="zsgx_salesRecord_grid" class="grid">
    </div>
</body>
</html>