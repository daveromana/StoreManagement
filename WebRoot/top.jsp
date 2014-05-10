<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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

<title>My JSP 'top.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link href="images/top.css" rel="stylesheet" type="text/css" />

</head>

<body>
	<div id="top">
		<div id="logo"></div>
		<div id="user">
			欢迎您:${sessionScope.homeData[0].user_Name} <a target="main"
				href="User!edit.do?methodType=getUserInfo&id=${sessionScope.homeData[0].user_id }">个人信息</a>
			<!-- <a href="LoginServlet.do" target="_top"> 注销</a> -->
		</div>
	</div>
</body>
</html>
