<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/inc/meta.inc" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/jsp/inc/head.inc" %>
</head>

<body>
 <form class="l-form validate">
    <div class="content">
    	<ul>
    		<li>
				<label>角色：</label>
    			<c:if test="${employee.role != null}">${employee.role.name}</c:if>
    		</li>
    	</ul>
   	   <ul>
    		<li>
    			<label>所在区域：</label>
    			<c:if test="${employee.organization != null && employee.organization.region != null}">${employee.organization.region.regionName} > </c:if>
    			<c:if test="${employee.organization != null && employee.organization.parentOrg != null}">${employee.organization.parentOrg.orgName} > </c:if>
    			<c:if test="${employee.organization != null}">${employee.organization.orgName} </c:if>
    			<c:if test="${employee.organization == null}">全国</c:if>
    		</li>
    	</ul>
    		
    	<ul>
    		<li>
    			<label>用户名：</label>
    			${employee.name}
    		</li>
    	</ul>
    	<ul>
    		<li>
    			<label>联系人姓名：</label>
    			${employee.linkman}
    		</li>
    	</ul>
    	<ul>
    		<li>
    			<label>办公电话：</label>
    			${employee.tel}
    		</li>
    	</ul>
    	<ul>
    		<li>
    			<label>手机：</label>
    			${employee.phone}
    		</li>
    	</ul>
    	<ul>
    		<li>
    			<label>邮箱：</label>
    			${employee.email}
    		</li>
    	</ul>
    	<ul>
    		<li>
    			<label>传真：</label>
    			${employee.fax}
    		</li>
    	</ul>
    </div>
    </form>
</body>
</html>