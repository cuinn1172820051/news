<%@page import="com.action.bean.ActionBean"%>
<%@page import="com.entity.Manager"%>
<%@ page language="java" import ="java.util.*" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	String manager_id = request.getParameter("manager_id");
	ActionBean actionBean = new ActionBean();
	Manager manager = actionBean.queryManagerById(manager_id);
	if(null==manager){
		response.sendRedirect("ManagerList.jsp");
		return;
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>修改管理员</title>
<link type="text/css" href="<%=basePath %>css/style.css" rel="stylesheet">
</head>
<body>
	<div class="main-frame">
	<jsp:include page="top.jsp"></jsp:include>
	<jsp:include page="left.jsp"></jsp:include>
	<div class="right">
	<div class="admin">
		<h1 class="title">修改管理员 <a href="ManagerList.jsp" class="back-btn">返回</a></h1>
		<form action="../EditManager" id="frm" method="post">
			<table>
				<tr><td>登录账号：</td><td><input id="manager_id" name="manager_id" readonly="readonly" value="<%=manager.getManager_id() %>" autocomplete="off" maxlength="10" type="text"/></td><td id="err_manager_id"></td></tr>
				<tr><td>真实姓名：</td><td><input id="manager_name" name="manager_name" autocomplete="off" value="<%=manager.getManager_name() %>" maxlength="20" type="text"/></td><td id="err_manager_name"></td></tr>
				<tr><td></td><td><a class="btn" onclick="startPost()" href="javascript:void(0)">保存</a></td></tr>
			</table>
		</form>
	</div>
	</div>
	</div>
	<script type="text/javascript">
		function valiInput(id,msg,n){
			var val = document.getElementById(id).value;
			if(val==""||val.length<n){ 
				document.getElementById("err_"+id).innerHTML="<span class='err'>"+msg+"</span>";
				return false;
			}else{
				document.getElementById("err_"+id).innerHTML="<span class='success'>验证成功。</span>";
				return true;
			}
			
		}
		function valiPwd(){
			if(document.getElementById('manager_pwd').value != document.getElementById('re_manager_pwd').value)
			{
				document.getElementById("err_re_manager_pwd").innerHTML="<span class='err'>两次密码不一致</span>";
				return false;
			}
			return true;
		}
		function startPost(){
			var ipts = ["manager_name"];
			var msg = ["请输入真实姓名，至少2位"];
			var lens = [2];
			var flag = true;
			for(var i=0;i<ipts.length;i++){
				flag = valiInput(ipts[i],msg[i],lens[i]) && flag;
			}
			if(flag){
				document.getElementById('frm').submit();
			}
		}
	</script>
</body>
</html>