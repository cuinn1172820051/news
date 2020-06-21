<%@ page language="java" import ="java.util.*" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<link type="text/css" href="css/style.css" rel="stylesheet">
<div class="top-box clearfix" style="width:100%;">
	<div class="sys-logo">
		新闻管理系统
	</div>
	<form id="myfrm" action="search.html" method="get">
		<input type="text" name="searchTxt" class="searchTxt" autocomplete="off" placeholder="请输入关键词"  value="<%=request.getAttribute("searchTxt")==null?"":request.getAttribute("searchTxt")%>"/>
		<input name="p" id="p" type="hidden" complete="off" value=""/>
		<input id="submit" class="submit" type="submit" value="搜索"/>
	</form>
</div>