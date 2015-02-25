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
	<script type="text/javascript" src="${basePath}jsp/zsgx/customer/customer.js"></script>
	<script type="text/javascript">
		function customer_add_submit(dialog,frm,_url){
			commonFrmSubmit(
					"#zsgx_customer_add_form",
					basePath+"zsgx/customer!doAdd.action",
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
 	<form id="zsgx_customer_add_form" name="zsgx_customer_add_form" class="l-form validate" method="post">
	 <input type="hidden" name="customer.id" value="${customer.id}"/>
	    <div class="content">
		
	    	<ul>
	    		<li>
	    			<label><font color="red">*</font>客户姓名：</label>
			  	    <input type="text" name="customer.name" value="${customer.name}" class="search-input-text " />
					<!-- <font color="red">客户姓名提示</font> -->
				</li>
	    	</ul>
	    	<ul>
	    		<li>
	    			<label><font color="red">*</font>客户性别：</label>
			  	    <input type="text" name="customer.sex" value="${customer.sex}" class="search-input-text digits " />
					<!-- <font color="red">客户性别提示</font> -->
				</li>
	    	</ul>
	    	<ul>
	    		<li>
	    			<label><font color="red">*</font>客户电话：</label>
			  	    <input type="text" name="customer.phone" value="${customer.phone}" class="search-input-text phone " />
					<!-- <font color="red">客户电话提示</font> -->
				</li>
	    	</ul>
	    	<ul>
	    		<li>
	    			<label><font color="red">*</font>客户地址：</label>
			  	    <input type="text" name="customer.address" value="${customer.address}" class="search-input-text " />
					<!-- <font color="red">客户地址提示</font> -->
				</li>
	    	</ul>
	    	<ul>
	    		<li>
	    			<label><font color="red">*</font>客户QQ：</label>
			  	    <input type="text" name="customer.qq" value="${customer.qq}" class="search-input-text " />
					<!-- <font color="red">客户QQ提示</font> -->
				</li>
	    	</ul>
	    	<ul>
	    		<li>
	    			<label><font color="red">*</font>消费金额：</label>
			  	    <input type="text" name="customer.totalSpend" value="${customer.totalSpend}" class="search-input-text " />
					<!-- <font color="red">消费金额提示</font> -->
				</li>
	    	</ul>
	    	<ul>
	    		<li>
	    			<label><font color="red">*</font>备注：</label>
			  	    <input type="text" name="customer.remain" value="${customer.remain}" class="search-input-text " />
					<!-- <font color="red">备注提示</font> -->
				</li>
	    	</ul>
	    </div>
    </form>
</body>
</html>