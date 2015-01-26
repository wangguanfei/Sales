<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/inc/meta.inc" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta name="title" content="后台管理系统"/>
    <meta name="application-name" content="后台管理系统" />


  </head>
  
  <body>
    	<c:if test="${not empty actionErrors }">
    		<c:forEach items="${actionErrors }" var="error">
    			${error }&nbsp;&nbsp;
    		</c:forEach>
   		</c:if>
   		<c:if test="${not empty actionMessages }">
    		<c:forEach items="${actionMessages }" var="message">
    			${message }&nbsp;&nbsp;
    		</c:forEach>
   		</c:if>
  </body>
</html>

