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
			var stock = $("#stock").val();
			var salesNumber = $("#salesNumber").val();
			if(salesNumber > stock){
				alert("库存不足，请进货！", "error",function(){
					$("#salesNumber").focus();
				});
				return false;
			}
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
		$(document).ready(function(){
			$("#notExist").click(function(){
				$(".customerName").show();
			});
			/*$("#salesNumber").blur(function(){
				var stock = $("#stock").val();
				var salesNumber = $("#salesNumber").val();
				if(salesNumber > stock){
					alert("库存不足，请进货！", "error",function(){
						$("#salesNumber").focus();
					});
				}
			});*/
			//添加客户
			$("#addCustomer").click(function(){
				if($("#addName").val() != "" && $("#addName").val() != null){
					alert("<span style=\"width: 100%; display:inline-block;\">服务器处理中...<img src='/image/loading.gif' width='30' height='30' style=\" display:inline-block; position:relative; top:10px;\"/></span>");
					$.ajax({
						url :basePath+"zsgx/customer!doAdd.action",
						type : "post",
						data : $("#zsgx_goods_edit_form").serialize(),
						dataType : "json",
						success : function(json) {
							closeAllBox();
							$(".customerName").hide();
							
							var id = json.data;
							var name = $("#addName").val();
							$("#customerSel").append("<option value='"+id+"' class='option'  selected='selected'>"+name+"</option>");
						},
						error : function(){
							closeAllBox();
							alert("网络错误，提交失败。");
						}
					});
				}else{
					alert("客户姓名不能为空", "error",function(){
						$("#addName").focus();
					});
				}
			});
		});
	</script>
</head>
<body>
 	<form id="zsgx_goods_edit_form" name="zsgx_goods_edit_form" class="l-form validate" method="post">
	 <input type="hidden" name="salesRecord.goodsId" value="${goods.id}"/>
	 <input type="hidden" name="goods.id" value="${goods.id}"/>
	 <input type="hidden" name="goods.stock" id="stock" value="${goods.stock}"/>
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
			  	    <input type="text" name="salesRecord.salesNumber" id="salesNumber" value="" class="search-input-text required  znumber" />
				</li>
	    	</ul>
	    	<ul>
	    		<li>
	    			<label><font color="red"></font>客户姓名：</label>
			  	    <select  name="salesRecord.customerId" id="customerSel" class="input-select">
			  	    <option value="" class="option">==请选择==</option>
		  	    		<c:forEach items="${customerList}" var="customer">
		  	    			<option value="${customer.id}" class="option">${customer.name}</option>
		  	    		</c:forEach>
			  	   </select>
			  	   <a href="javascript:;" style='text-decoration:none;' id="notExist">不存在？</a>
				</li>
				<div  style="display: none;" class="customerName">
					<li>
					<label><font color="red">*</font>客户姓名：</label>
			  	    <input type="text" name="customer.name" id="addName" value="" class="search-input-text" />
				</li>
				<li>
	    			<label><font color="red"></font>客户性别：</label>
			  	    <input type="radio" name="customer.sex" value="0" checked="checked"/>男
			  	    <input type="radio" name="customer.sex" value="1" />女
				</li>
				<li>
	    			<label><font color="red"></font>客户电话：</label>
			  	    <input type="text" name="customer.phone" value="${customer.phone}" class="search-input-text phone " />
				</li>
				<li>
	    			<label><font color="red"></font>客户地址：</label>
			  	    <input type="text" name="customer.address" value="${customer.address}" class="search-input-text " />
				</li>
				<li>
	    			<label><font color="red"></font>客户QQ：</label>
			  	    <input type="text" name="customer.qq" value="${customer.qq}" class="search-input-text " />
				</li>
				<li>
	    			<label><font color="red"></font>备注：</label>
			  	    <input type="text" name="customer.remain" value="${customer.remain}" class="search-input-text " />
				</li>
				<li>
	    			<label>
	    				<input type="button" value="添加" id="addCustomer"/>
	    			</label>
				</li>
				</div>
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