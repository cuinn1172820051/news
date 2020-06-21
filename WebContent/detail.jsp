<%@page import="com.entity.Nav"%>
<%@page import="com.entity.ArticleNav"%>
<%@page import="com.entity.Article"%>
<%@ page language="java" import ="java.util.*" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<%
	List <Nav>list = (List)request.getAttribute("navList");
	Article article = (Article)request.getAttribute("article");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><%=article.getArticle_title()%></title>
<link type="text/css" href="css/style.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="top.jsp"></jsp:include>
	<jsp:include page="nav.jsp"></jsp:include>
	<div class="w list-box clearfix article-content">
		<h3><%=article.getArticle_title() %></h3>
		<p>发布日期：<%=article.getArticle_date() %></p>
		<div>
			<%=article.getArticle_content() %>
		</div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>