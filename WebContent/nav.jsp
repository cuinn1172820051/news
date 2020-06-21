<%@page import="com.entity.Nav"%>
<%@ page language="java" import ="java.util.*" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	List <Nav>list = (List)request.getAttribute("navList");
	if(list==null)return ;
	String activeDiv = "active";
	String currNavId = "";
	if(request.getAttribute("nav_id")!=null){
		activeDiv = "";
		currNavId = request.getAttribute("nav_id").toString();
	}
%>
<link type="text/css" href="css/style.css" rel="stylesheet">
<div class="nav-box clearfix" style="width:100%;">
	<ul class="clearfix">
		<li class="activeDiv"><a href="index.html">首页</a></li>
		<%for(Nav nav : list){%>
				<li <%=nav.getNav_id().equals(currNavId)?"class=\"active\"":"" %>><a href="articleList.html?nav_id=<%=nav.getNav_id() %>" target="_blank"><%=nav.getNav_name()%></a></li>
		<%}%>
	</ul>
</div>