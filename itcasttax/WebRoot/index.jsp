<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
//重定向到登录页面
response.sendRedirect(path+"/sys/login_toLoginUI.action");
%>

