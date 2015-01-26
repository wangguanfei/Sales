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
	<script type="text/javascript">
		function goods_edit_submit(dialog,frm,_url){
			commonFrmSubmit(
					"#zsgx_goods_edit_form",
					basePath+"zsgx/salesRecord!goodsSales.action",
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
 	<form id="zsgx_goods_edit_form" name="zsgx_goods_edit_form" class="l-form validate" method="post">
	 <input type="hidden" name="salesRecord.goodsId" value="${goods.id}"/>
	 <input type="hidden" name="goods.id" value="${goods.id}"/>
	    <div class="content">
	    	<ul>
	    		<li>
	    			<label><font color="red">*</font>商品名称：</label>
			  	   	 ${goods.name}
				</li>
	    	</ul>
	    	<ul>
	    		<li>
	    			<label><font color="red">*</font>销售数量：</label>
			  	    <input type="text" name="salesRecord.salesNumber" value="" class="search-input-text required  znumber" />
				</li>
	    	</ul>
	    	<ul>
	    		<li>
	    			<label><font color="red">*</font>进货价格：</label>
			  	    <%--<input type="text" name="salesRecord.purchasePrice" value="" class="search-input-text  znumber" />--%>
			  	    	<select  name="salesRecord.purchasePrice" class="input-select">
			  	    		<c:forEach items="${purchasePriceList }" var="price">
			  	    			<option value="${price}" class="option">${price}</option>
			  	    		</c:forEach>
			  	    	</select>
			  	    </li>
	    	</ul>
	    	<ul>
	    		<li>
	    			<label><font color="red">*</font>销售价格：</label>
			  	    <input type="text" name="salesRecord.salesPrice" value="" class="search-input-text  znumber" />
				</li>
	    	</ul>
	    	<ul>
	    		<li>
	    			<label><font color="red"></font>备注：</label>
			  	    <input type="text" name="salesRecord.remain" value="" class="search-input-text " />
				</li>
	    	</ul>
	    </div>
    </form>
</body>
</html>