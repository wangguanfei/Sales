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
	<script type="text/javascript" src="${basePath}jsp/zsgx/statistics/statistics.js"></script>
	<script type="text/javascript">
		function statistics_add_submit(dialog,frm,_url){
			commonFrmSubmit(
					"#zsgx_statistics_add_form",
					basePath+"zsgx/statistics!doAdd.action",
					function(json){
						if(json.success){
		        			home_fresh();
						}else{
							alert(json.message,"error");
						}
					}
			);
		}
	</script>
</head>
<body>
 	<form id="zsgx_statistics_add_form" name="zsgx_statistics_add_form" class="l-form validate" method="post">
	 <input type="hidden" name="statistics.id" value="${statistics.id}"/>
	    <div class="content">
		
	    	<ul>
	    		<li>
	    			<label><font color="red">*</font>年份：</label>
			  	    <input type="text" name="statistics.syear" value="${statistics.syear}" class="search-input-text " />
					<!-- <font color="red">年份提示</font> -->
				</li>
	    	</ul>
	    	<ul>
	    		<li>
	    			<label><font color="red">*</font>月份：</label>
			  	    <input type="text" name="statistics.smonth" value="${statistics.smonth}" class="search-input-text " />
					<!-- <font color="red">月份提示</font> -->
				</li>
	    	</ul>
	    	<ul>
	    		<li>
	    			<label><font color="red">*</font>日：</label>
			  	    <input type="text" name="statistics.sday" value="${statistics.sday}" class="search-input-text " />
					<!-- <font color="red">日提示</font> -->
				</li>
	    	</ul>
	    	<ul>
	    		<li>
	    			<label><font color="red">*</font>总收入：</label>
			  	    <input type="text" name="statistics.sin" value="${statistics.sin}" class="search-input-text digits " />
					<!-- <font color="red">总收入提示</font> -->
				</li>
	    	</ul>
	    	<ul>
	    		<li>
	    			<label><font color="red">*</font>总利润：</label>
			  	    <input type="text" name="statistics.sprofit" value="${statistics.sprofit}" class="search-input-text digits " />
					<!-- <font color="red">总利润提示</font> -->
				</li>
	    	</ul>
	    </div>
    </form>
</body>
</html>