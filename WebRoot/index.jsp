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

<title>北京市仓储管理信息系统</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>
<frameset rows="60,*" cols="*" frameborder="no" border="0"
	framespacing="0">
	<frame src="top.jsp" name="top" scrolling="no" noresize="noresize"
		id="top" />
	<frameset rows="*" cols="188,*" framespacing="0" frameborder="no"
		border="0">
		<frame src="menu.jsp" name="menu" scrolling="auto" noresize="noresize"
			id="menu" />
		<frameset rows="73,*" cols="*">
			<frame src="maintop.jsp" name="maintop" id="maintop" />
			<frame src="main.jsp" name="main" id="main" />
		</frameset>
	</frameset>
</frameset>
<noframes>
	<body style="margin: 0">
		<iframe width="100%" height="60px" frameborder="no" scrolling="no"
			src="top.jsp"></iframe>
		<iframe width="188px" height="85%" frameborder="no" scrolling="auto"
			src="menu.jsp""></iframe>
		<iframe style="margin:0;position: absolute;" width="86%" height="73px"
			frameborder="no" scrolling="auto" src="maintop.jsp" name="main"></iframe>
		<iframe style="margin:0;margin-top:73px;position: absolute;"
			width="86%" height="85%" frameborder="no" scrolling="auto"
			src="main.jsp" name="main"></iframe>
		<!-- <div>
		<iframe
			style="float: left; position: absolute; width: 15%; height: 87%;"
			frameborder="0" scrolling="auto" src="menu.jsp""></iframe>
		<iframe
			style="float: left; margin-left:15%; position: absolute; width: 84%; height: 87%"
			frameborder="0" scrolling="auto" src="main.jsp" name="main"></iframe>
	</div> -->
	</body>
</noframes>
</html>
