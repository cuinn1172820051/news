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
		<h1 class="title">管理员信息列表 <a href="main.jsp" class="back-btn">返回主页</a></h1> 
			<table class="tab" cellspacing="0">
				<tr><td>选择</td><td class='text-center'>序号</td><td class='text-center'>登录账号</td><td class='text-center'>真实姓名</td></tr>
				<%
					ActionBean actionBean = new ActionBean();
					out.print(actionBean.queryManagerAll());
				%>
				<tr><td colspan="4" style="border-bottom:0;"><a class="btn" href="addManager.jsp">增加管理员</a>
				<a href="javascript:void(0)" class="btn" id="update" onclick="updateManager()">修改</a>
				<a href="javascript:void(0)" class="btn" id="delete" onclick="deleteManager()">删除</a>
				</td></tr>
			</table>
		</div>
		</div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
	<script type="text/javascript">
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
		function updateManager(){
			var obj = selectedRow();
			if(obj==null)return;
			location = "editManager.jsp?manager_id="+obj.value;
		}
		function deleteManager(){
			var obj = selectedRow();
			if(obj==null)return;
			if(confirm("您确定要删除当前用户吗？"));{
				location = "../DeleteManager?manager_id="+obj.value;
			}
		}
	</script>
</body>
</html>