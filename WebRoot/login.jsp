<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/jsp/inc/meta.inc" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>艺奇模特管理后台系统</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="title" content="艺奇模特管理后台系统"/>
    <meta name="application-name" content="艺奇模特管理后台系统" />
	<link rel="Shortcut Icon" href="${basePath}image/logo.ico"  type=”image/x-icon”/>
	
    <script src="${basePath }ligerui/jquery/jquery-1.5.2.min.js" type="text/javascript"></script>
   	<link rel="stylesheet" type="text/css" href="${basePath }grind/css/style.css"  />
    <script type="text/javascript">
       $(document).ready(function(){
    	   $("#name").focus();
       });
        function f_submit(){
        	if($("#name").val()==""){
        		$("#msg").html("用户名不能为空");
        		return false;
        	}
        	if($("#password").val()==""){
        		$("#msg").html("密码不能为空");
        		return false;
        	}
        	/*if($("#verifyCode").val()==""){
        		$("#msg").html("验证码不能为空");
        		return false;
        	}*/
        	$("#form1").submit();
        }
        $(function(){
        	$(document).keydown(function(event){
        		  if(event.keyCode==13){
        			  f_submit();
        		  }
        	}); 
        });
        	 
    </script>
    <script type="text/javascript">
     //点击切换验证码
	  $(function() {
	    $('#code').click(
	        function() {
	          $(this).hide().attr("src", "${basePath}kaptcha.jpg?" + Math.floor(Math.random() * 100)).fadeIn("slow");
	        });
	  });
</script>
</head>
<body class="bulbg">
<form id="form1" action="${basePath }login!login.action" method="post">
<div class="loginTop">
	<div class="topLogo"><img src="${basePath}image/login.png" alt="" /></div>
</div>
<div class="loginBd">
	<div class="box">
    	<div class="user">
    		<p class="tisfont" id="msg">
               		<c:if test="${not empty actionErrors }">
                		<c:forEach items="${actionErrors }" var="error">
                			${error }&nbsp;&nbsp;
                		</c:forEach>
               		</c:if>
                 </p>
    		<%--<div class="userName"><input style="line-height:18px;" type="text" id="name" name="employee.name" value="用户名" onFocus="this.className='hoverinput';if(this.value=='用户名') this.value='';" onBlur="if (this.value=='') this.value='用户名';this.className='linkinput';"/></div>
            <div class="passWord"><input style="line-height:18px;" type="password" id="password" name="employee.password" value="密码密码密码" onFocus="this.className='hoverinput';if(this.value=='密码密码密码') this.value='';" onblur="if (this.value=='') this.value='密码密码密码';this.className='linkinput';"/></div>
            <div class="code">
            	<input type="text" name="verifyCode" id="verifyCode" style="line-height:18px;" value="验证码" onFocus="this.className='hoverinput';if(this.value=='验证码') this.value='';" onBlur="if (this.value=='') this.value='验证码';this.className='linkinput';"/>
            	<img id="code" width="90" height="30" src="${basePath}kaptcha.jpg?ran=${ran}">
            </div>
            --%>
            <div class="userName"><input style="line-height:18px;" type="text" id="name" name="employee.name" value="${employee.name}" /></div>
            <div class="passWord"><input style="line-height:18px;" type="password" id="password" name="employee.password" value="${employee.password}" /></div>
            <%--<div class="code">
            	<input type="text" name="verifyCode" id="verifyCode" style="line-height:18px;" />
            	<img id="code" width="90" height="30" src="${basePath}kaptcha.jpg?ran=${ran}">
            </div>
            --%><p><a href="#" class="loginBtn" onclick="f_submit();" type="button" class="loginlink" onMouseOver="this.className='loginhover'" onMouseOut="this.className='loginlink'"><img src="${basePath}image/button.png" alt="" /></a>
   </div>
    </div>
</div>
</form>
</body>
</html>

