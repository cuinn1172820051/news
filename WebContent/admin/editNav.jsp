<%@page import="com.entity.Nav"%>
<%@page import="com.action.bean.ActionBean"%>
<%@page import="com.entity.Manager"%>
<%@ page language="java" import ="java.util.*" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	ActionBean actionBean = new ActionBean();
	String nav_id = request.getParameter("nav_id");
	if(nav_id==null||"".equals(nav_id)){
		response.sendRedirect("navList.jsp");
		return;
	}
	Nav nav = actionBean.queryNavById(nav_id);
	if(nav==null){
		response.sendRedirect("navList.jsp");
		return;
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>修改新闻类别</title>
<link type="text/css" href="<%=basePath %>css/style.css" rel="stylesheet">
</head>
<body>
	<div class="main-frame">
	<jsp:include page="top.jsp"></jsp:include>
	<jsp:include page="left.jsp"></jsp:include>
	<div class="right">
	<div class="admin">
		<h1 class="title">修改新闻类别 <a href="ManagerList.jsp" class="back-btn">返回</a></h1>
		<form action="../EditNav" id="frm" method="post">
			<input type="hidden" value="<%=nav.getNav_id() %>" name="nav_id"/>
			<table>
				<tr><td>新闻类别：</td><td><input id="nav_name" name="nav_name"
				 value="<%=nav.getNav_name() %>" autocomplete="off" maxlength="10" type="text"/></td><td id="err_nav_name"></td></tr>
				<tr><td>排序权重：</td><td><input id="nav_feight" name="nav_feight" autocomplete="off" value="<%=nav.getNav_feight() %>" maxlength="20" type="text"/></td><td id="err_nav_feight"></td></tr>
				<tr><td></td><td><a class="btn" onclick="startPost()" href="javascript:void(0)">保存</a></td></tr>
			</table>
		</form>
	</div>
	</div>
	</div>
	<script type="text/javascript" src="../js/nav.js">
	</script>
</body>
</html>