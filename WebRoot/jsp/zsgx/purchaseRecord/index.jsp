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
	<script type="text/javascript" src="${basePath}jsp/zsgx/purchaseRecord/purchaseRecord.js"></script>
	</head>
<body>
	<!-- search start -->
	<div class="content">
		<form id="zsgx_purchaseRecord_form" name="zsgx_purchaseRecord_form">
		   <ul>
		   <!-- search param 参数过长请拆分UL -->
			   <li>
				   <input id="purchaseRecordCondition.goodsId" class="search-input-text" name="purchaseRecordCondition.goodsId" value="${purchaseRecordCondition.goodsId}" type="hidden"/>
				   <label>进货数量：</label>
				   <input id="purchaseRecordCondition.goodsNum" class="search-input-text" name="purchaseRecordCondition.goodsNum" value="${purchaseRecordCondition.goodsNum}" />
				   <label>进货时间：</label>
				   <input  type="text"  name="purchaseRecordCondition.purchaseDateBeaginStr"  onFocus="WdatePicker({isShowWeek:true,maxDate:'#F{$dp.$D(\'endTimeStr\')}'})"  class="Wdate search-input-text" id="beginTimeStr" value="${purchaseRecordCondition.purchaseDateBeaginStr}" />
				   <label>~</label>
				   <input  type="text"  name="purchaseRecordCondition.purchaseDateEndStr"  onFocus="WdatePicker({isShowWeek:true,minDate:'#F{$dp.$D(\'beginTimeStr\')}'})"  class="Wdate search-input-text" id="endTimeStr" value="${purchaseRecordCondition.purchaseDateEndStr}" />
				   </li>
			<!-- search btn -->
		   	<li><a class="l-button" onclick="purchaseRecord_search()">查询</a></li>
		   	<li><a class="l-button" onclick="$('#zsgx_purchaseRecord_form')[0].reset()">重置</a></li>
		   </ul>
		</form>
	</div>
	
	<!-- table start -->
    <div id="zsgx_purchaseRecord_grid" class="grid">
    </div>
</body>
</html>