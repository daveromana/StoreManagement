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

<title>My JSP 'userAdd.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript" src="js/jquery-1.11.0.min.js"></script>
<script type="text/javascript">
function check(){
	var loginname = document.getElementById("loginname").value;
	var password = document.getElementById("loginpassword").value;
	if("" == loginname){
		alert("登录名不可为空");
		return false;
	}else if("" == password){
		alert("密码不可为空");
		return false;
	}else{
		return true;
	}
}
$(function() {
	var x = '<%=request.getParameter("result")%>';
		if (x != "" && x != null) {
			if (x == "success")
				alert("添加成功!");
			else if (x == "failed")
				alert("添加失败！");
		}
	})
</script>
</head>

<body>
	<div style="float: right;">
		<font size="5">账号增加&nbsp<a href="xtgl/yhgl/user.jsp">返回</a></font>
	</div>
	<br>
	<form action="Account!add.do?methodType=add" method="post" onsubmit="check()">
	<table>
		<tr>
			<td>登陆名</td>
			<td><input id="loginname" name="loginname" type="text"></td>
		</tr>
		<tr>
			<td>密码</td>
			<td>
			<input id="loginpassword" name="loginpassword" type="text"></td>
			</td>
		</tr>
		<tr>
			<td><input type="submit"></td>
			<td><input type="reset"></td>
		</tr>
	</table>
	</form>
</body>
</html>
