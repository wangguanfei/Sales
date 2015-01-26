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
	<script type="text/javascript" src="${basePath}jsp/zsgx/goods/goods.js"></script>
	<script src="${basePath}js/lightbox.min.js"></script>
	
	<link href="${basePath}css/lightbox.css" rel="stylesheet" />
	</head>
<body>
	<!-- search start -->
	<div class="content">
		<form id="zsgx_goods_form" name="zsgx_goods_form">
		   <ul>
		   <!-- search param 参数过长请拆分UL -->
			   <li>
				   <label>商品名称：</label>
				  	 <input id="goodsCondition.name" name="goodsCondition.name" value="${goodsCondition.name}" class="search-input-text"/>
				   </li>
			<!-- search btn -->
		   	<li><a class="l-button" onclick="goods_search()">查询</a></li>
		   	<li><a class="l-button" onclick="$('#zsgx_goods_form')[0].reset()">重置</a></li>
		   </ul>
		</form>
	</div>
	
	<!-- table start -->
    <div id="zsgx_goods_grid" class="grid">
    </div>
</body>
</html>