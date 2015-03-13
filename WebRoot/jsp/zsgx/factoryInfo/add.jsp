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
	<script type="text/javascript">
		function factoryInfo_add_submit(dialog,frm,_url){
			commonFrmSubmit(
					"#zsgx_factoryInfo_add_form",
					basePath+"zsgx/factoryInfo!doAdd.action",
					function(json){
						if(json.success){
							window.parent.factoryInfo_grid.loadData();
		        			 closeAllBox();
		        			 closeWindow();
						}else{
							alert(json.message,"error");
						}
					}
			);
		}
	</script>
</head>
<body>
 	<form id="zsgx_factoryInfo_add_form" name="zsgx_factoryInfo_add_form" class="l-form validate" method="post">
	 <input type="hidden" name="factoryInfo.id" value="${factoryInfo.id}"/>
	    <div class="content">
		
	    	<ul>
	    		<li>
	    			<label><font color="red">*</font>名称：</label>
			  	    <input type="text" name="factoryInfo.name" value="${factoryInfo.name}" class="search-input-text " />
					<!-- <font color="red">名称提示</font> -->
				</li>
	    	</ul>
	    	<ul>
	    		<li>
	    			<label><font color="red">*</font>电话：</label>
			  	    <input type="text" name="factoryInfo.phone" value="${factoryInfo.phone}" class="search-input-text phone " />
					<!-- <font color="red">电话提示</font> -->
				</li>
	    	</ul>
	    	<ul>
	    		<li>
	    			<label><font color="red">*</font>地址：</label>
			  	    <input type="text" name="factoryInfo.address" value="${factoryInfo.address}" class="search-input-text " />
					<!-- <font color="red">地址提示</font> -->
				</li>
	    	</ul>
	    	<ul>
	    		<li>
	    			<label><font color="red">*</font>备注：</label>
			  	    <input type="text" name="factoryInfo.remain" value="${factoryInfo.remain}" class="search-input-text " />
					<!-- <font color="red">备注提示</font> -->
				</li>
	    	</ul>
	    </div>
    </form>
</body>
</html>