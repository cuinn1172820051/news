<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div class="left-menu">
	<h1 class="title">导航菜单</h1>
	<ul>
		<li><a href="ManagerList.jsp">用户管理</a></li>
		<li><a href="navList.jsp">类别管理</a></li>
		<li><a href="articleList.jsp">文章管理</a></li>
		<li><a href="exit.jsp">退出系统</a></li> 
	</ul>
</div>