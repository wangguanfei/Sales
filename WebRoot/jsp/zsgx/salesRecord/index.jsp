<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/inc/meta.inc" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<head>
	<%@ include file="/jsp/inc/head.inc" %>
	<script type="text/javascript">
		basePath = "${basePath}";
		function salesRecord_export(){
			alert("<span style=\"width: 100%; display:inline-block;\">服务器处理中...<img src='/image/loading.gif' width='30' height='30' style=\" display:inline-block; position:relative; top:10px;\"/></span>");
			$.ajax({
				url : basePath+"zsgx/salesRecord!exportData.action",
				type : "post",
				data : $("#zsgx_salesRecord_form").serialize(),
				dataType : "json",
				success : function(json){
					if (json.success) {
						closeAllBox();
						alert("导出成功","success",function(){
							var begin = $("#beginTimeStr").val();
							var end = $("#endTimeStr").val();
							var fileName = "销售记录.xls";
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
		function salesRecord_search() {
			countTotal();
			salesRecord_grid.loadData();
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
				url : basePath+"zsgx/salesRecord!countByDate.action",
				type : "post",
				data : $("#zsgx_salesRecord_form").serialize(),
				dataType : "json",
				success : function(json){
					if (json.success) {
						$("#salesMoney").html("<font color='red'>"+json.data.income+"元</font>");
						$("#profitMoney").html("<font color='red'>"+json.data.profit+"元</font>");
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
	<script type="text/javascript" src="${basePath}jsp/zsgx/salesRecord/salesRecord.js"></script>
	</head>
<body>
	<!-- search start -->
	<div class="content">
		<form id="zsgx_salesRecord_form" name="zsgx_salesRecord_form">
		 <input type="hidden" name="salesRecordCondition.customerId" value="${salesRecordCondition.customerId}"/>
		   <ul>
		   <!-- search param 参数过长请拆分UL -->
			   <li>
				   <label>销售时间：</label>
				   <input  class="search-input-text Wdate" name="salesRecordCondition.salesDateBeaginStr" id="beginTimeStr" onFocus="WdatePicker({isShowWeek:true,maxDate:'#F{$dp.$D(\'endTimeStr\')}'})" />
				     <label>~</label>
				    <input class="search-input-text Wdate" name="salesRecordCondition.salesDateEndStr"  id="endTimeStr" onFocus="WdatePicker({isShowWeek:true,minDate:'#F{$dp.$D(\'beginTimeStr\')}'})" />
				   </li>
			<!-- search btn -->
		   	<li><a class="l-button" onclick="salesRecord_search()">查询</a></li>
		   	<li><a class="l-button" onclick="$('#zsgx_salesRecord_form')[0].reset()">重置</a></li>
		   	<li><a class="l-button" onclick="salesRecord_export()">导出</a></li>
		   </ul>
		   <ul>
		   	<li>
		   		<label><span id="countData"></span>
		   		 &nbsp;销售总额：<span id="salesMoney"><img src="${basePath}image/loading.gif" width='20' height='20' style=" position: relative; top: 5px;"/></span>
		   		 &nbsp;利润总额：<span id="profitMoney"><img src="${basePath}image/loading.gif" width='20' height='20' style=" position: relative; top: 5px;"/></span></label>
		   	</li>
		   </ul>
		</form>
	</div>
	
	<!-- table start -->
    <div id="zsgx_salesRecord_grid" class="grid">
    </div>
</body>
</html>