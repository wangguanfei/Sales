<%@ page language="java" import="java.util.*" pageEncoding="utf-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- jstl 标签 -->
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<!-- struts2 标签
taglib prefix="s" uri="/struts-tags"
 -->
<%
String path = request.getContextPath();
if(!path.endsWith("/")){
	path = path+"/";
}
request.setAttribute("basePath", path);
%>