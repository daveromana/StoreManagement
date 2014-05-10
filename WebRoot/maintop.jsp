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

<title>My JSP 'maintop.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link href="images/maintop.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="js/maintop.js"></script>

</head>

<body>
	<div id="right_top">
		<div id="img" >
			<img src="images/close.gif" />
		</div>
		<span class="imgtext">打开/关闭左栏</span>
		<div id="loginout">
			<div id="loginoutimg">
				<img src="images/loginout.gif" />
			</div>
			<span class="logintext">退出系统</span>
		</div>
	</div>
	<div id="right_font">
		<img src="images/main_14.gif" /> 今天天气：<span
			class="bfont">晴转多云 23℃</span>
	</div>

</body>
</html>
