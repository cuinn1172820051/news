<%@page import="com.entity.Nav"%>
<%@page import="com.action.bean.ActionBean"%>
<%@ page language="java" import ="java.util.*" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	ActionBean actionBean = new ActionBean();
	List <Nav>list = actionBean.queryNavList();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>添加新闻</title>
<link type="text/css" href="../css/style.css" rel="stylesheet">
<link rel="stylesheet" href="kindeditor/themes/default/default.css" />
	<link rel="stylesheet" href="kindeditor/plugins/code/prettify.css" />
	<script charset="utf-8" src="kindeditor/kindeditor-all-min.js"></script>
	<script charset="utf-8" src="kindeditor/lang/zh-CN.js"></script>
	<script charset="utf-8" src="kindeditor/plugins/code/prettify.js"></script>
	<script>
		KindEditor.ready(function(K) {
			var editor1 = K.create('textarea[name="content1"]', {
				cssPath : 'kindeditor/plugins/code/prettify.css',
				uploadJson : 'upload_json.jsp',
				fileManagerJson : 'file_manager_json.jsp',
				allowFileManager : true,
				afterBlur : function(){
					this.sync();
				},
				afterCreate : function() {
					var self = this;
					K.ctrl(document, 13, function() {
						self.sync();
						document.forms['frm'].submit();
					});
					K.ctrl(self.edit.doc, 13, function() {
						self.sync();
						document.forms['frm'].submit();
					});
				}
			});
			prettyPrint();
		});
	</script>
	<script language="javascript" type="text/javascript" src="../My97DatePicker/WdatePicker.js"></script>
</head>
<body>
	<div class="main-frame">
	<jsp:include page="top.jsp"></jsp:include>
	<jsp:include page="left.jsp"></jsp:include>
	<div class="right">
	<div class="admin">
		<h1 class="title">添加新闻<a href="articleList.jsp" class="back-btn">返回</a></h1>
		<form name="frm" action="/news/InsertArticle" id="frm" method="post">
			<table>
				<tr><td>新闻标题：</td><td><input id="article_title" name="article_title" autocomplete="off" maxlength="300" type="text"/></td><td id="err_article_title"></td></tr>
				<tr><td>新闻类别：</td><td>
				<select id="nav_id" name="nav_id" autocomplete="off">
					<%
						for(Nav nav : list){
							%>
							<option value="<%= nav.getNav_id()%>"><%=nav.getNav_name() %></option>
							<%
						}
					%>
				</select>
				</td>
				<td id="err_nav_id"></td>
				</tr>
				<tr><td>发布时间：</td><td><input id="article_date" name="article_date" autocomplete="off" maxlength="20" type="text" onclick="WdatePicker()" readonly="readonly"/></td><td id="err_article_date"></td></tr>
				<tr>
					<td valign="top">新闻正文：</td>
					<td colspan="2">
						<textarea name="content1" id="content1" rows="" cols="" style="width:680px;height:320px;"></textarea>
					</td>
				</tr>
				<tr><td><a class="btn" onclick="startPost()" href="javascript:void(0)">保存</a></td></tr>
			</table>
		</form>
	</div>
	</div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
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
	function startPost(){
		var ipts = ["article_title","nav_id","article_date"];
		var msg = ["请输入标题，至少2个字符。","请选择新闻类别","请输入发布时间"];
		var lens = [2,1,10];
		var flag = true;
		for(var i=0;i<ipts.length;i++){
			flag = valiInput(ipts[i],msg[i],lens[i]) && flag;
		}
		if(!flag) return;
		document.getElementById('frm').submit();
	}
	</script>
	
</body>
</html>