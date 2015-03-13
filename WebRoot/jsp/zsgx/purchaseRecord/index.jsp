<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/inc/meta.inc" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<head>
	<%@ include file="/jsp/inc/head.inc" %>
	<script type="text/javascript">
		basePath = "${basePath}";
		function purchaseRecord_export(){
			alert("<span style=\"width: 100%; display:inline-block;\">服务器处理中...<img src='/image/loading.gif' width='30' height='30' style=\" display:inline-block; position:relative; top:10px;\"/></span>");
			$.ajax({
				url : basePath+"zsgx/purchaseRecord!exportData.action",
				type : "post",
				data : $("#zsgx_purchaseRecord_form").serialize(),
				dataType : "json",
				success : function(json){
					if (json.success) {
						closeAllBox();
						alert("导出成功","success",function(){
							var begin = $("#beginTimeStr").val();
							var end = $("#endTimeStr").val();
							var fileName = "进货记录.xls";
							if(begin != "" && end != ""){
								fileName = begin+"至"+end+fileName;
							}
							if(begin != "" && end == ""){
								fileName = begin+"至今"+fileName;
							}
							if(begin == "" && end != ""){
								fileName = "截止至"+end+fileName;
							}
							window.location.href=basePath+"download.action?file="+json.data+"&fileName="+fileName+"&isDelete=1";
						});
						
					} else {
						closeAllBox();
						alert(json.message, "error");
					}
				},
				error : function(){
					closeAllBox();
					alert("请求错误，请检查网络。");
				}
			});
		}
		
		function purchaseRecord_search() {
			countTotal();
			purchaseRecord_grid.loadData();
		}
		//删除
		function purchaseRecord_delete(id) {
			confirm("删除操作", "确认删除吗？", function() {
				$.getJSON(basePath+"zsgx/purchaseRecord!delete.action?purchaseRecord.id=" + id,function(json) {
					if (json.success) {
						countTotal();
						purchaseRecord_grid.loadData();
					}else{
						alert(json.message,"error");
					}
				});
			});
		}
		//结账
		function purchaseRecord_invoicing(id) {
			confirm("结账", "确认结账吗？", function() {
				$.getJSON(basePath+"zsgx/purchaseRecord!invoicing.action?purchaseRecord.id=" + id,function(json) {
					if (json.success) {
						alert("结账成功","success",function(){
							countTotal();
							purchaseRecord_grid.loadData();
						});
					}else{
						alert(json.message,"error");
					}
				});
			});
		}
		$(document).ready(function(){
			countTotal();
		});
		
		
		function countTotal(){
			var start = $("#beginTimeStr").val();
			var end = $("#endTimeStr").val();
			var timeStr = "";
			if(start != "" && end != ""){
				timeStr = start+"至"+end
			}
			if(start == "" && end != ""){
				timeStr = "截止至"+end
			}
			if(start != "" && end == ""){
				timeStr =start+"至今 "
			}
			$("#countData").html(timeStr);
			
			$.ajax({
				url : basePath+"zsgx/purchaseRecord!countByDate.action",
				type : "post",
				data : $("#zsgx_purchaseRecord_form").serialize(),
				dataType : "json",
				success : function(json){
					if (json.success) {
						$("#totalMoney").html("<font color='red'>"+json.data+"元</font>");
					} else {
						//alert(json.message, "error");
					}
				},
				error : function(){
					closeAllBox();
					alert("请求错误，请检查网络。");
				}
			});
		}
	</script>
	<script type="text/javascript" src="${basePath}jsp/zsgx/common/common.js"></script>
	<script type="text/javascript" src="${basePath}jsp/zsgx/purchaseRecord/purchaseRecord.js"></script>
	</head>
<body>
	<!-- search start -->
	<div class="content">
		<form id="zsgx_purchaseRecord_form" name="zsgx_purchaseRecord_form">
		<input type="hidden" id="purchaseRecordCondition.goodsId" name="purchaseRecordCondition.goodsId" value="${purchaseRecordCondition.goodsId}"/>
		   <ul>
		   <!-- search param 参数过长请拆分UL -->
			   <li>
				   <label>进货时间：</label>
				   <input  type="text"  name="purchaseRecordCondition.purchaseDateBeaginStr"  onFocus="WdatePicker({isShowWeek:true,maxDate:'#F{$dp.$D(\'endTimeStr\')}'})"  class="Wdate search-input-text" id="beginTimeStr" value="${purchaseRecordCondition.purchaseDateBeaginStr}" />
				   <label>~</label>
				   <input  type="text"  name="purchaseRecordCondition.purchaseDateEndStr"  onFocus="WdatePicker({isShowWeek:true,minDate:'#F{$dp.$D(\'beginTimeStr\')}'})"  class="Wdate search-input-text" id="endTimeStr" value="${purchaseRecordCondition.purchaseDateEndStr}" />
			  </li>
			  <li>
	    			<label>厂家名称：</label>
			  	    <select name="purchaseRecordCondition.factoryId" class="input-select" style="width: 132px;">
			  	    <option value="" class="option">==请选择==</option>
			  	    	<c:forEach items="${factoryList}" var="factory">
		  	    			<option value="${factory.id}" class="option">${factory.name}</option>
		  	    		</c:forEach>
			  	    </select>
			  </li>
			   <li>
	    			<label>是否结账：</label>
			  	    <select name="purchaseRecordCondition.purchaseStatus" class="input-select" style="width: 132px;">
			  	    <option value="" class="option">==请选择==</option>
			  	    <option value="0" class="option">未结账</option>
			  	    <option value="1" class="option">已结账</option>
			  	    </select>
			  </li>
			<!-- search btn -->
		   	<li><a class="l-button" onclick="purchaseRecord_search()">查询</a></li>
		   	<li><a class="l-button" onclick="$('#zsgx_purchaseRecord_form')[0].reset()">重置</a></li>
		   	<li><a class="l-button" onclick="purchaseRecord_export()">导出</a></li>
		   </ul>
		   <ul>
		   	<li>
		   		<label><span id="countData"></span>&nbsp;进货总额：
		   		<span id="totalMoney"><img src="${basePath}image/loading.gif" width='20' height='20' style=" position: relative; top: 5px;"/></span></label>
		   	</li>
		   </ul>
		</form>
	</div>
	
	<!-- table start -->
    <div id="zsgx_purchaseRecord_grid" class="grid">
    </div>
</body>
</html>