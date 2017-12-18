<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'index.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<style type="text/css">
		table tr,table td{
			border: 1px solid #000;
		}
	</style>
</head>

<body>
	<span style="color:red;">${msg}<c:remove var="msg" scope="session"/></span>
	<hr>
	<table>
		<thead>
			<tr>
				<td>ID</td>
				<td>名称</td>
				<td>年龄</td>
				<td>成绩</td>
				<td>操作</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${students}" var="student">
				<tr>
					<td>${student.id }</td>
					<td>${student.name }</td>
					<td>${student.age }</td>
					<td>${student.score }</td>
					<td>
						<a href="<%=basePath %>list?op=getOne&id=${student.id}">修改</a>
						<a href="<%=basePath %>list?op=del&id=${student.id}" onclick="return confirm('是否删除');">删除</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<hr>
	<a href="<%=basePath%>add.jsp">添加学生</a>
</body>
</html>
