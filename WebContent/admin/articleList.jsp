<%@page import="com.entity.Nav"%>
<%@page import="com.tools.MyFuns"%>
<%@page import="com.entity.ArticleNav"%>
<%@page import="com.entity.Article"%>
<%@page import="com.action.bean.ActionBean"%>
<%@page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	String nav_id = request.getParameter("nav_id");
	String article_title=MyFuns.convert2utf8(request.getParameter("article_title"));
	
	ActionBean actionBean = new ActionBean();
	int p = MyFuns.string2Int(request.getParameter("p"));
	if(p<0){
		response.sendRedirect("articleList.jsp");
	}
	List <ArticleNav>list = actionBean.queryArticleByPage(p, nav_id, article_title);
	int rows = actionBean.queryCount(nav_id,article_title);
	if(p>0 && list.size()<1){
		response.sendRedirect("articleList.jsp");
	}
	List <Nav>navList = actionBean.queryNavList();
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>新闻信息管理</title>
<link type="text/css" href="../css/style.css" rel="stylesheet">
</head>
<body>
	<div class="main-frame">
		<jsp:include page="top.jsp"></jsp:include>
		<jsp:include page="left.jsp"></jsp:include>
		<div class="right">
		<div class="admin">
		<h1 class="title">新闻信息管理<a href="main.jsp" class="back-btn">返回主页</a></h1> 
			<table class="tab" cellspacing="0">
				<tr>
					<td colspan="5">
						<form action="articleList.jsp" method="post" id="frm" class="query">
							<label>类别</label>
							<select name="nav_id">
								<option value="">请选择新闻类别</option>
								<%
									for(Nav nav : navList){
										out.print("<option "+(nav.getNav_id().equals(nav_id)?"selected='selected' ":"")+" value='"+nav.getNav_id()+"'>"+nav.getNav_name()+"</option>");
									}
								%>
							</select>
							<label>标题</label>
							<input type="text" name="article_title" id="article_title" value='<%=article_title==null ? "":article_title%>'/>
							<input type="hidden" name="p" value="<%=p%>" id="go_page_number"/>
							<input type="submit" value="查询"/ class="query_btn">
						</form>
					</td>
				</tr>
				<tr>
					<td>选择</td>
					<td class='text-center'>序号</td>
					<td class='text-center'>新闻标题</td>
					<td class='text-center'>类别名称</td>
					<td class='text-center'>发布时间</td>
				</tr>
				<%
					int i = 10*p;
					int totalPages = (rows%10==0 ? rows/10 : rows/10 +1);
					for(ArticleNav art:list ){
						%>
							<tr>
								<td><input type='radio' name='rad' autocomplete='off' value='<%=art.getArticle_id() %>' id='rad'/></td>
								<td class='text-center'><%=++i %></td>
								<td class='text-center'><%=art.getArticle_title() %></td>
								<td class='text-center'><%=art.getNav_name()%></td>
								<td class='text-center'><%=art.getArticle_date() %></td>
							</tr>
						<%
					}
				%>
				<tr>
					<td colspan="5" class="page">
					<span>总共<%=rows %>篇文章</span><span>当前为第<%=p+1 %>页</span>
					<%if(p>0) {%>
						<a href="javascript:void(0)" onclick="go2Page(0)">第一页</a>
						<a href="javascript:void(0)" onclick="go2Page(<%=p-1 %>)">上一页</a>
					<%} %>
					<%if(p<totalPages-1) {%>
						<a href="javascript:void(0)" onclick="go2Page(<%=p+1 %>)">下一页</a>
						<a href="javascript:void(0)" onclick="go2Page(<%=totalPages-1 %>)">最后一页</a>
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
					</td>
				</tr>
				<tr>
					<td colspan="4" style="border-bottom:0;">
						<a class="btn" href="addArticle.jsp">添加新闻</a>
						<a class="btn" href="javascript:void(0)" onclick="updateNews()">修改</a>
						<a class="btn" href="javascript:void(0)" onclick="deleteNews()">删除</a>
					</td>
				</tr>
			</table>
		</div>
		</div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
	<script type="text/javascript">
		function go2Page(n){
			document.getElementById('go_page_number').value = n;
			document.getElementById('frm').submit();
		}
		function selectedRow(){
			var ms = document.getElementsByName('rad');
			var obj=null;
			for(var i=0;i<ms.length;i++){
				if(ms[i].checked){
					obj=ms[i];
					break;
				}
			}
			if(obj==null){
				alert("请选择一行再操作");
			}
			return obj;
		}
		function updateNews(){
			var obj = selectedRow();
			if(obj==null)return;
			location = "editArticle.jsp?article_id="+obj.value;	
		}
		function updateManager(){
			var obj = selectedRow();
			if(obj==null)return;
			location = "editManager.jsp?manager_id="+obj.value;
		}
		function deleteNews(){
			var obj = selectedRow();
			if(obj==null)return;
			if(confirm("您确定要删除当前新闻吗？")){
				location = "../DeleteArticle?article_id="+obj.value;
			}
		}
	</script>
</body>
</html>