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
	<script type="text/javascript" src="${basePath}jsp/zsgx/salesRecord/salesRecord.js"></script>
	<script type="text/javascript">
		function salesRecord_edit_submit(dialog,frm,_url){
			commonFrmSubmit(
					"#zsgx_salesRecord_edit_form",
					basePath+"zsgx/salesRecord!doEdit.action",
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
 	<form id="zsgx_salesRecord_edit_form" name="zsgx_salesRecord_edit_form" class="l-form validate" method="post">
	 <input type="hidden" name="salesRecord.id" value="${salesRecord.id}"/>
	    <div class="content">
		
	    	<ul>
	    		<li>
	    			<label><font color="red">*</font>商品：</label>
			  	    <input type="text" name="salesRecord.goodsId" value="${salesRecord.goodsId}" class="search-input-text digits " />
					<!-- <font color="red">商品提示</font> -->
				</li>
	    	</ul>
	    	<ul>
	    		<li>
	    			<label><font color="red">*</font>销售数量：</label>
			  	    <input type="text" name="salesRecord.salesNumber" value="${salesRecord.salesNumber}" class="search-input-text digits " />
					<!-- <font color="red">销售数量提示</font> -->
				</li>
	    	</ul>
	    	<ul>
	    		<li>
	    			<label><font color="red">*</font>销售价格：</label>
			  	    <input type="text" name="salesRecord.salesPrice" value="${salesRecord.salesPrice}" class="search-input-text digits " />
					<!-- <font color="red">销售价格提示</font> -->
				</li>
	    	</ul>
	    	<ul>
	    		<li>
	    			<label><font color="red">*</font>商品进价：</label>
			  	    <input type="text" name="salesRecord.purchasePrice" value="${salesRecord.purchasePrice}" class="search-input-text digits " />
					<!-- <font color="red">商品进价提示</font> -->
				</li>
	    	</ul>
	    	<ul>
	    		<li>
	    			<label><font color="red">*</font>销售时间：</label>
			  	    <input type="text" name="salesRecord.salesDate" value="${salesRecord.salesDate}" class="search-input-text " />
					<!-- <font color="red">销售时间提示</font> -->
				</li>
	    	</ul>
	    	<ul>
	    		<li>
	    			<label><font color="red">*</font>销售年份：</label>
			  	    <input type="text" name="salesRecord.salesYear" value="${salesRecord.salesYear}" class="search-input-text " />
					<!-- <font color="red">销售年份提示</font> -->
				</li>
	    	</ul>
	    	<ul>
	    		<li>
	    			<label><font color="red">*</font>销售月份：</label>
			  	    <input type="text" name="salesRecord.salesMonth" value="${salesRecord.salesMonth}" class="search-input-text " />
					<!-- <font color="red">销售月份提示</font> -->
				</li>
	    	</ul>
	    	<ul>
	    		<li>
	    			<label><font color="red">*</font>销售日期：</label>
			  	    <input type="text" name="salesRecord.salesDay" value="${salesRecord.salesDay}" class="search-input-text " />
					<!-- <font color="red">销售日期提示</font> -->
				</li>
	    	</ul>
	    	<ul>
	    		<li>
	    			<label><font color="red">*</font>收入：</label>
			  	    <input type="text" name="salesRecord.income" value="${salesRecord.income}" class="search-input-text digits " />
					<!-- <font color="red">收入提示</font> -->
				</li>
	    	</ul>
	    	<ul>
	    		<li>
	    			<label><font color="red">*</font>利润：</label>
			  	    <input type="text" name="salesRecord.profit" value="${salesRecord.profit}" class="search-input-text digits " />
					<!-- <font color="red">利润提示</font> -->
				</li>
	    	</ul>
	    	<ul>
	    		<li>
	    			<label><font color="red">*</font>备注：</label>
			  	    <input type="text" name="salesRecord.remain" value="${salesRecord.remain}" class="search-input-text " />
					<!-- <font color="red">备注提示</font> -->
				</li>
	    	</ul>
	    </div>
    </form>
</body>
</html>