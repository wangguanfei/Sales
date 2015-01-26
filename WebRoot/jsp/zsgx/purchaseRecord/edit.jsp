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
	<script type="text/javascript">
		function purchaseRecord_edit_submit(dialog,frm,_url){
			commonFrmSubmit(
					"#zsgx_purchaseRecord_edit_form",
					basePath+"zsgx/purchaseRecord!doEdit.action",
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
 	<form id="zsgx_purchaseRecord_edit_form" name="zsgx_purchaseRecord_edit_form" class="l-form validate" method="post">
	 <input type="hidden" name="purchaseRecord.id" value="${purchaseRecord.id}"/>
	    <div class="content">
		
	    	<ul>
	    		<li>
	    			<label><font color="red">*</font>商品：</label>
			  	    <input type="text" name="purchaseRecord.goodsId" value="${purchaseRecord.goodsId}" class="search-input-text digits " />
					<!-- <font color="red">商品提示</font> -->
				</li>
	    	</ul>
	    	<ul>
	    		<li>
	    			<label><font color="red">*</font>进货数量：</label>
			  	    <input type="text" name="purchaseRecord.goodsNum" value="${purchaseRecord.goodsNum}" class="search-input-text digits " />
					<!-- <font color="red">进货数量提示</font> -->
				</li>
	    	</ul>
	    	<ul>
	    		<li>
	    			<label><font color="red">*</font>进货价：</label>
			  	    <input type="text" name="purchaseRecord.purchasePrice" value="${purchaseRecord.purchasePrice}" class="search-input-text digits " />
					<!-- <font color="red">进货价提示</font> -->
				</li>
	    	</ul>
	    	<ul>
	    		<li>
	    			<label><font color="red">*</font>进货时间：</label>
			  	    <input type="text" name="purchaseRecord.purchaseDate" value="${purchaseRecord.purchaseDate}" class="search-input-text " />
					<!-- <font color="red">进货时间提示</font> -->
				</li>
	    	</ul>
	    	<ul>
	    		<li>
	    			<label><font color="red">*</font>进货时间：</label>
			  	    <input type="text" name="purchaseRecord.purchaseDateStr" value="${purchaseRecord.purchaseDateStr}" class="search-input-text " />
					<!-- <font color="red">进货时间提示</font> -->
				</li>
	    	</ul>
	    </div>
    </form>
</body>
</html>