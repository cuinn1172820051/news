<%@ page language="java" import ="java.util.*" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>添加新闻类别</title>
<link type="text/css" href="../css/style.css" rel="stylesheet">

</head>
<body>
	<div class="main-frame">
	<jsp:include page="top.jsp"></jsp:include>
	<jsp:include page="left.jsp"></jsp:include>
	<div class="right">
	<div class="admin">
		<h1 class="title">添加新闻类别 <a href="navList.jsp" class="back-btn">返回</a></h1>
		<form action="/news/InsertNav" id="frm" method="post">
			<table>
				<tr><td>新闻类别：</td><td><input id="nav_name" name="nav_name" autocomplete="off" maxlength="50" type="text"/></td><td id="err_nav_name"></td></tr>
				<tr><td>排序权重：</td><td><input id="nav_feight" name="nav_feight" autocomplete="off" maxlength="10" type="text"/></td><td id="err_nav_feight"></td></tr>
				<tr><td></td><td><a class="btn" onclick="startPost()"  href="javascript:void(0)">保存</a></td></tr>
			</table>
		</form>
	</div>
	</div>
	</div>
	<script type="text/javascript" src="../js/nav.js"></script>
</body>
</html>