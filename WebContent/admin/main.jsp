<%@page import="com.action.bean.ActionBean"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>管理员信息列表</title>
<link type="text/css" href="../css/style.css" rel="stylesheet">
</head>
<body>
	<div class="main-frame">
		<jsp:include page="top.jsp"></jsp:include>
		<jsp:include page="left.jsp"></jsp:include>
		<div class="right">
			<div class="admin">
				<h1 class="title">后台主页</h1>
				<div class="welcome">欢迎您使用新闻管理系统！</div>
			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>