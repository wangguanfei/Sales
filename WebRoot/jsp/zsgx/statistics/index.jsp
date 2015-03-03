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
					text : "销售统计", 
					x : "center", 
					//正标题样式
					textStyle : {
						fontSize : 18
					}
				},
				//数据提示框配置
				tooltip : {
					trigger : 'axis'
				},
				//图例配置
				legend : {
					data : ["销售总额","利润总额"], //这里需要与series内的每一组数据的name值保持一致
					y : "top",
					x : "left",
					backgroundColor: "#eee",
			        borderColor: "#6CD7D9",
			        borderWidth: 2,
			        itemGap: 5,
			        textStyle: {color: "red"}
				},
				//x轴配置
				xAxis : [ {
					type : 'category',
					boundaryGap : false,
					data : [],
					name : "日期"
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
							name : "销售总额",
							type : "line",
							data : []
						  },
						  {
							name : "利润总额",
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
				url : "${basePath}zsgx/statistics!data.action",
				type : "post",
				data : $("#zsgx_purchaseRecord_form").serialize(),
				dataType : "json",
				success : function(jsondatas){
					if(jsondatas.data.noData == "yes"){
						alert("暂无数据");
						myChart.hideLoading();
						return;
					}
					options.xAxis[0].data = jsondatas.data.dayList;
					options.series[0].data = jsondatas.data.salesList;
					options.series[1].data = jsondatas.data.profitList;
					myChart.hideLoading();
					myChart.setOption(options);
					
					if($("#type").val() == "m"){
						$("#countTotalIn,#countTotalProfit").text($("#nowMonth").val()+"月");
					}else if($("#type").val() == "y"){
						$("#countTotalIn,#countTotalProfit").text($("#nowYear").val()+"年");
					}
					
					$("#total_in").text(jsondatas.data.total_in);
					$("#total_profit").text(jsondatas.data.total_profit);
				},
				error : function(){
					alert("加载图表出错！");
				}
			});
		}
		function statistics_search(){
			var nowMonth = $("#nowMonth").val();
			var type = $("#type").val();
			var nowYear = $("#nowYear").val();
			if((nowMonth == "" && type == "m") || (nowYear == "" && type == "y") ){
				alert("请选择查询日期!","warn",function(){
					return false;
				});
			}else{
				loadData(myChart,options);
			}
			
		}
		
		$(document).ready(function(){
			$("#type").change(function(){
				var type = $(this).val();
				if(type == "m"){
					$("#monthLi").show();
					$("#yearLi").hide();
				}
				if(type == "y"){
					$("#yearLi").show();
					$("#monthLi").hide();
				}
			});
		});
	</script>
	</head>
<body>
	<!-- search start -->
	<div class="content">
		<form id="zsgx_purchaseRecord_form" name="zsgx_purchaseRecord_form">
		   <ul>
			   <li>
				   <label>统计类型：</label>
				   <select name="type" id="type">
				   	<option value="m">按月统计</option>
				   	<option value="y">按年统计</option>
				   </select>
			  </li>
			   <li id="monthLi">
				   <label>月份：</label>
				   <input  type="text"  name="nowMonth"  onFocus="WdatePicker({isShowWeek:'true',dateFmt:'yyyy-MM'})"  class="Wdate search-input-text" id="nowMonth" value="${nowMonth}" />
			  </li>
			   <li id="yearLi" style="display: none;">
				   <label>年份：</label>
				   <input  type="text"  name="nowYear"  onFocus="WdatePicker({isShowWeek:'true',dateFmt:'yyyy'})"  class="Wdate search-input-text" id="nowYear" value="${nowYear}" />
			  </li>
		      <li><a class="l-button" onclick="statistics_search()">查询</a></li>
		   </ul>
		</form>
		 <ul>
			   <li>
				   <label><span id="countTotalIn">${nowMonth}</span>销售总额：<font color="red"><span id="total_in"></span>元</font></label>
				</li>
			   <li>
				   <label><span id="countTotalProfit">${nowMonth}</span>利润总额：<font color="red"><span id="total_profit"></span>元</font></label>
				</li>
		   </ul>
	</div>
	
	<div id="total" style="height: 600px; width:800px; border: 1px solid #ccc; padding: 10px; margin: auto;"></div>
</body>
</html>