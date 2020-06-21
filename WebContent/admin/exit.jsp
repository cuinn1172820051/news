<%@page import="com.conflg.*" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	HttpSession sess = request.getSession();
	sess.removeAttribute(Conflg.MANAGER_ID);
	sess.removeAttribute(Conflg.LOGIN_SUCCESS);
	response.sendRedirect("../login.jsp");
%>
<%= basePath%>