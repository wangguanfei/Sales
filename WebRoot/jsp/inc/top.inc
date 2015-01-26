<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="topmenu" class="l-topmenu">
    <div class="l-topmenu-logo"><!--<img src="${basePath }image/logo.png"/>--></div>
    <div class="l-topmenu-welcome">
    	<span class="l-link2" style="text-decoration:none;">${employee.name }</span>
    	<span class="space">|</span>
        <a href="javascript:f_modify_password('${employee.id }');" class="l-link2">修改密码</a>
        <span class="space">|</span>
         <a href="${basePath }login!logout.action" class="l-link2">退出</a>
    </div> 
</div>
<script type="text/javascript">
	function f_modify_password(id){
 			var win = {
					title : "修改密码",
					name : "employee-reset-password-window",
					url : "${basePath}permission/employee!toResetPassword.action?employee.id="+id,
					buttons : [ {
						text : "确定",
						onclick : function(item, dialog) {
							gg("employee-reset-password-window").contentWindow.f_submit(dialog);
						}
					}, {
						text : "取消",
						onclick : function(item, dialog) {
							dialog.close();
						}
					} ]
				};
		showWindow(win);
	}
</script>