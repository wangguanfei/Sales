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
					basePath+"zsgx/goods!goodsPurchase.action",
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
	 <input type="hidden" name="purchaseRecord.goodsId" value="${goods.id}"/>
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
	    			<label><font color="red">*</font>厂家名称：</label>
			  	    <select name="purchaseRecord.factoryId" class="input-select" style="width: 132px;">
			  	    	<c:forEach items="${factoryList}" var="factory">
		  	    			<option value="${factory.id}" class="option">${factory.name}</option>
		  	    		</c:forEach>
			  	    </select>
				</li>
	    	</ul>
	    	<ul>
	    		<li>
	    			<label><font color="red">*</font>进货数量：</label>
			  	    <input type="text" name="purchaseRecord.goodsNum" value="" class="search-input-text required digits znumber" />
				</li>
	    	</ul>
	    	<ul>
	    		<li>
	    			<label><font color="red">*</font>进货价格：</label>
			  	    <input type="text" name="purchaseRecord.purchasePrice" value="" class="search-input-text required znumber" />
				</li>
	    	</ul>
	    	<ul>
	    		<li>
	    			<label><font color="red">*</font>是否结账：</label>
			  	    <input type="radio" name="purchaseRecord.purchaseStatus" value="0" checked="checked"/>否
			  	    <input type="radio" name="purchaseRecord.purchaseStatus" value="1" />是
				</li>
	    	</ul>
	    	<ul>
	    		<li>
	    			<label><font color="red"></font>备注：</label>
			  	    <input type="text" name="purchaseRecord.remain" value="" class="search-input-text " />
				</li>
	    	</ul>
	    </div>
    </form>
</body>
</html>