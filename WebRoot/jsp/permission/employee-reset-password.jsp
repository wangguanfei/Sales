<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/inc/meta.inc" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/jsp/inc/head.inc" %>
    <script type="text/javascript">
         function f_submit(dialog){
        	if($("form.validate").valid()){
	        	var param=$("form.validate").serialize();
	        	$.getJSON("${basePath}permission/employee!resetPassword.action", param,function(json){
	        		if(json.success){
	        			alert(json.message,"success",function(){dialog.close();});
					}else{
						alert(json.message,"error");
					}
				});
			} 
          }
    </script>
</head>

<body>
 <form class="l-form validate">
 <input type="hidden" name="employee.id" value="${employee.id }">
 <div style="display:none;"><!-- 附加  样式的问题 -->
 	<input type="text" />
 	<input type="password"/>
 </div>
    <div class="content">
    <!-- 
    	<ul>
    		<li>
				<label>用户组：</label>
    			${employee.role.name }
    		</li>
    	</ul> -->
    	<ul>
    		<li>
    			<label>用户名称：</label>
				${employee.name }
    		</li>
    	</ul>
    	<ul>
    		<li>
    			<label>现在的密码：</label>
    			<input type="password" name="nowPassword" id="nowpassword" class="required search-input-text" minlength="6" maxlength="12">
    		</li>
    	</ul>
    	<ul>
    		<li>
    			<label>设置新的密码：</label>
    			<input type="password" name="employee.password" id="password" class="required search-input-text" minlength="6" maxlength="12">
    		</li>
    	</ul>
    	<ul>
    		<li>
    			<label>重复新的密码：</label>
    			<input type="password" name="employee.repassword" id="repassword" class="required search-input-text {equalTo:'#password'}" minlength="6" maxlength="12">
    		</li>
    	</ul>
    </div>
    </form>
</body>
</html>