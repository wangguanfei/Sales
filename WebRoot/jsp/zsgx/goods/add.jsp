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
	  <!-- 文件的上传处理 -->
    <script type="text/javascript" src="${basePath }swfupload/jquery.swfupload.js"></script>
    <script type="text/javascript" src="${basePath }swfupload/swfupload.js"></script>
	<script type="text/javascript">
		function goods_add_submit(dialog,frm,_url){
			commonFrmSubmit(
					"#zsgx_goods_add_form",
					basePath+"zsgx/goods!doAdd.action",
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
			commonUpload("uploadFile",uploadImgPathCall,{
                file_size_limit: 1024 * 50,//允许上传文件大小
                file_types: "*.jpg;*.jpeg;*.gif;*.png;",//允许上传文件类型
                uploadFolder: "upload/goodsImg/",//自定义上传目录 默认 upload
            });
			
			function uploadImgPathCall(event, fileObj, result) {
	                $("#img").val(result.data.file);
	                $("#viewImg").prop("src",basePath+result.data.file);
	                $("#view").show();
	        }
		});
	</script>
</head>
<body>
 	<form id="zsgx_goods_add_form" name="zsgx_goods_add_form" class="l-form validate" method="post">
	 <input type="hidden" name="goods.id" value="${goods.id}"/>
	    <div class="content">
		
	    	<ul>
	    		<li>
	    			<label><font color="red">*</font>商品名称：</label>
			  	    <input type="text" name="goods.name" value="${goods.name}" class="search-input-text required" />
					<!-- <font color="red">商品名称提示</font> -->
				</li>
	    	</ul>
	    	<ul>
	    		<li>
	    			<label><font color="red"></font>商品图片：</label>
			  	    <input type="text" name="goods.img" id="img" value="${goods.img}" class="search-input-text " />
			  	    <div style="display:inline-block; vertical-align:-6px;"><a type="button" id="uploadFile"></a></div>
				</li>
	    	</ul>
	    	<ul style="display: none;" id="view">
	    		<li>
	    		<label><font color="red"></font>预览图片：</label>
	    			<img alt="" src="" id="viewImg">
				</li>
	    	</ul>
	    	<ul>
	    		<li>
	    			<label><font color="red"></font>简介：</label>
			  	    <input type="text" name="goods.introduction" value="${goods.introduction}" class="search-input-text " />
					<!-- <font color="red">简介提示</font> -->
				</li>
	    	</ul>
	    </div>
    </form>
</body>
</html>