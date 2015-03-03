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
	<script src="${basePath}js/esl.js" type="text/javascript"></script>
	<script type="text/javascript">
	
	var myChart ;
	var options ;
		require.config({
			paths : {
				echarts : '${basePath}js/echarts',
				'echarts/chart/line' : '${basePath}js/echarts'
			}
		});
		
		require([ 'echarts', 'echarts/chart/line' ],DrawEChart);
		
		function DrawEChart(ec) {
			initData(ec);
		}
		
		
		function initData(ec){
			var chartContainer = document.getElementById("total");
			 myChart = ec.init(chartContainer);
			 options = {
				title : {
					text : "进货统计", 
					x : "center", 
					//正标题样式
					textStyle : {
						fontSize : 18
					}
				},
				//数据提示框配置
				tooltip : {
					trigger : 'axis',
					  formatter: '{b}<br/>{c}元'
				},
				//图例配置
				legend : {
					data : ["进货总额"], //这里需要与series内的每一组数据的name值保持一致
					y : "bottom"
				},
				//x轴配置
				xAxis : [ {
					type : 'category',
					boundaryGap : false,
					data : [],
					name : "月份"
				} ],
				//Y轴配置
				yAxis : [ {
					type : 'value',
					splitArea : {
						show : true
					},
					name : "金额",
					 axisLabel : {
			                formatter: '{value} 元'
			            }
				} ],
				//图表Series数据序列配置
				series : [
				          {
							name : "进货总额",
							type : "line",
							data : []
						  }
				          ]
			};
			loadData(myChart,options);
		}
		/*加载json数据*/
		function loadData(myChart,options){
			myChart.showLoading({text: "正在努力加载...",effect:"bubble"});
			myChart.clear();
			$.ajax({
				url : "${basePath}zsgx/purchaseRecord!loadData.action",
				type : "post",
				data : $("#zsgx_purchaseRecord_form").serialize(),
				dataType : "json",
				success : function(jsondatas){
					if(jsondatas.data.noData == "yes"){
						alert("暂无数据");
						myChart.hideLoading();
						return;
					}
					options.xAxis[0].data = jsondatas.data.monthList;
					options.series[0].data = jsondatas.data.moneyList;
					myChart.hideLoading();
					myChart.setOption(options);
					
					$("#countYear").text($("#year").val());
					$("#countMoney").text(jsondatas.data.total);
				},
				error : function(){
					alert("加载图表出错！");
				}
			});
		}
		function purchaseRecord_search(){
			var year = $("#year").val();
			if(year == ""){
				alert("请选择查询年份!","warn",function(){
					return false;
				});
			}else{
				loadData(myChart,options);
			}
			
		}
	</script>
	</head>
<body>
	<!-- search start -->
	<div class="content">
		<form id="zsgx_purchaseRecord_form" name="zsgx_purchaseRecord_form">
		   <ul>
			   <li>
				   <label>年份：</label>
				   <input  type="text"  name="year"  onFocus="WdatePicker({isShowWeek:'true',dateFmt:'yyyy'})"  class="Wdate search-input-text" id="year" value="${year}" />
				   </li>
			<!-- search btn -->
		   	<li><a class="l-button" onclick="purchaseRecord_search()">查询</a></li>
		   </ul>
		</form>
		 <ul>
			   <li>
				   <label><span id="countYear">${year}</span>年进货总额：<font color="red"><span id="countMoney"></span>元</font></label>
				</li>
		   </ul>
	</div>
	
	<div id="total" style="height: 600px; width:800px; border: 1px solid #ccc; padding: 10px; margin: auto;"></div>
</body>
</html>