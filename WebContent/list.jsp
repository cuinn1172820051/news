<%@page import="com.entity.Nav"%>
<%@page import="com.entity.ArticleNav"%>
<%@page import="com.entity.Article"%>
<%@ page language="java" import ="java.util.*" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<%
	List <Article>list = (List)request.getAttribute("articleList");
	List <ArticleNav>articles = (List)request.getAttribute("articles");
	int l = 0;
	int rows = (Integer)request.getAttribute("rows");
	int p = (Integer)request.getAttribute("p");
	String nav_id = request.getAttribute("nav_id").toString();
	Nav nav = (Nav)request.getAttribute("nav");
	if(nav==null){
		response.sendRedirect("index.html");
		return;
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><%=nav.getNav_name() %></title>
<link type="text/css" href="css/style.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="top.jsp"></jsp:include>
	<jsp:include page="nav.jsp"></jsp:include>
	<div class="w list-box clearfix">
		<div class="left-box">
			<h2>最新发布</h2>
			<ul>
			<%for(Article art : list){ %>
				<li><a href="item.html?article_id=<%=art.getArticle_id() %>" target="_blank" title="<%=art.getArticle_title() %>"><%=art.getArticle_title() %></a></li>
			<%} %>
			</ul>		
		</div>
		<div class="right-box">
			<h2><%=nav.getNav_name() %></h2>
			<ul>
			<%
			int i = 10*p;
			int totalPages = (rows%10==0 ? rows/10 : rows/10 +1);
			for(ArticleNav arn : articles) {%>
				<li><a href="item.html?article_id=<%=arn.getArticle_id() %>" target="_blank" title=<%=arn.getArticle_title() %>><%=++i %><%=arn.getArticle_title() %></a><span><%=arn.getArticle_date() %></span></li>
			<%} %> 	
			</ul>
			<p class="page_footer">
				<span>总共<%=rows %>篇文章</span><span>当前为第<%=p+1 %>页</span>
					<%if(p>0) {%>
						<a href="articleList.html?nav_id=<%=nav_id%>&p=0">第一页</a>
						<a href="articleList.html?nav_id=<%=nav_id%>&p=<%=p-1 %>">上一页</a>
					<%} %>
					<%if(p<totalPages-1) {%>
						<a href="articleList.html?nav_id=<%=nav_id%>&p=<%=p+1 %>">下一页</a>
						<a href="articleList.html?nav_id=<%=nav_id%>&p=<%=totalPages-1 %>">最后一页</a>
					<%} %> 
						<span>总共<%=totalPages %>页</span>
						<%if(totalPages>1){ %>
						<span>
							转到第
							<select onchange="go2Page(this.value)">
							<%for(int j=0;j<totalPages;j++){ %>
								<option value="<%=j %>"<%=j==p?"selected='selected'":""%>><%=j+1 %></option>
							<%}%>
							</select>
							页
						</span>
						<%} %>
			</p>
		</div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
	<script language="javascript" type="text/javascript">
	function go2Page(n){
		location = "articleList.html?nav_id=<%=nav_id%>&p="+n;
	}
	</script>
</body>
</html>