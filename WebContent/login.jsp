<%@page import="com.conflg.*" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	String msg = request.getParameter("msg")==null?"":("2").equals(request.getParameter("msg"))?"错误的用户名或密码":("0").equals(request.getParameter("msg"))?"信息填写不完整":"错误的用户名或密码";
	if(null!=session.getAttribute(Conflg.MANAGER_ID)&&"".equals(session.getAttribute(Conflg.MANAGER_ID).toString())){
		response.sendRedirect("admin/main.jsp");
		return; 
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>登录新闻管理系统后台</title>
<link type="text/css" href="css/style.css" rel="stylesheet">
</head>
<body>
	<div class="login-title">新闻管理系统后台<small>登录</small></div> 
	<div class="login-main">
		<div class="login-box">
			<div class="box">
				<h2>用户登录</h2>
				<form action="/news/LoginServlet" method="post" id="frm">
					<p><label>账号:&nbsp&nbsp</label><input name="manager_id" id="manager_id" type="text"/></p>
					<p><label>密码:&nbsp&nbsp</label><input name="manager_pwd" id="manager_pwd" type="password"/></p>
					<p><a href="javascript:void(0)" class="btn" onclick="postForm()">登录</a></p>
					<p><span id="msg" class="msg"><%=msg %></span></p>
				</form>
			</div>
		</div>
	</div>
	<jsp:include page="admin/footer.jsp"></jsp:include>
</body>
	<script type="text/javascript">
		function postForm(){
			var manager_id = document.getElementById('manager_id').value;
			var manager_pwd = document.getElementById('manager_pwd').value;
			document.getElementById('msg').innerHTML = "";
			if(manager_id==""||manager_pwd==""){
				document.getElementById('msg').innerHTML = "请输入用户名或密码";
				return;
			}
			document.getElementById('frm').submit();
		}	
	</script>

</html>