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
	<script type="text/javascript" src="${basePath}jsp/zsgx/factoryInfo/factoryInfo.js"></script>
	</head>
<body>
	<!-- search start -->
	<div class="content">
		<form id="zsgx_factoryInfo_form" name="zsgx_factoryInfo_form">
		   <ul>
		   <!-- search param 参数过长请拆分UL -->
			   <li>
				   <label>名称：</label>
				   <input id="factoryInfoCondition.name" class="search-input-text" name="factoryInfoCondition.name" value="${factoryInfoCondition.name}" />
				   <label>电话：</label>
				   <input id="factoryInfoCondition.phone" class="search-input-text" name="factoryInfoCondition.phone" value="${factoryInfoCondition.phone}" />
				   <label>地址：</label>
				   <input id="factoryInfoCondition.address" class="search-input-text" name="factoryInfoCondition.address" value="${factoryInfoCondition.address}" />
				   <label>备注：</label>
				   <input id="factoryInfoCondition.remain" class="search-input-text" name="factoryInfoCondition.remain" value="${factoryInfoCondition.remain}" />
				   </li>
			<!-- search btn -->
		   	<li><a class="l-button" onclick="factoryInfo_search()">查询</a></li>
		   	<li><a class="l-button" onclick="$('#zsgx_factoryInfo_form')[0].reset()">重置</a></li>
		   </ul>
		</form>
	</div>
	
	<!-- table start -->
    <div id="zsgx_factoryInfo_grid" class="grid">
    </div>
</body>
</html>