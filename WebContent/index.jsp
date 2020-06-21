<%@page import="com.entity.Article"%>
<%@page import="com.entity.Nav"%>
<%@ page language="java" import ="java.util.*" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	List <Map>resultList = (List)request.getAttribute("resultList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新闻管理系统</title>
<link type="text/css" href="css/style.css" rel="stylesheet">
</head>
<body style="background-color:#ddd;">
	<jsp:include page="top.jsp"></jsp:include>
	<jsp:include page="nav.jsp"></jsp:include>
	<jsp:include page="banner.jsp"></jsp:include>
	<div class="index-main-box w">
		<% for(Map map : resultList){ 
			Nav nav = (Nav)map.get("nav");
			List <Article>articleList = (List)map.get("articleList");
		%>
		<div class="panel">
			<div>
				<h2><%=nav.getNav_name() %><a href="articleList.html?nav_id=<%=nav.getNav_id()%>&p=" class="more">更多</a></h2>
				<ul>
					<%for(Article article : articleList) {%>
					<li><a href="item.html?article_id=<%=article.getArticle_id() %>" target="_blank" title="<%=article.getArticle_title() %>"><%=article.getArticle_title() %></a><span><%=article.getArticle_date() %></span></li>
					<%} %>
				</ul>
			</div>
		</div>
		<%} %>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
	
</body>
</html>