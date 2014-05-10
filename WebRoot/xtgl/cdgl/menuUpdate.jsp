<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	var menuname = document.getElementById("menuname").value;
	var supermenu = document.getElementById("supermenu").value;
	if("" == menuname){
		alert("菜单名称不可为空");
		return false;
	}else if("" == supermenu){
		alert("上级菜单不可为空");
		return false;
	}else{
		return true;
	}
}
$(function() {
	var x = '<%=request.getParameter("result")%>';
		if (x != "" && x != null) {
			if (x == "success")
				alert("修改成功!");
			else if (x == "failed")
				alert("修改失败！");
		}
	})
</script>
</head>

<body>
	<div style="float: right;">
		<font size="5">菜单修改&nbsp<a href="xtgl/yhgl/user.jsp">返回</a></font>
	</div>
	<br>
	<form action="Menu!edit.do?methodType=update" method="post" onsubmit="return check()">
	<input type="hidden" name="id" value="${sessionScope.menu.id }">
	<table>
		<tr>
			<td>菜单名</td>
			<td><input name="menuname" id="menuname" value="${sessionScope.menu.powerColumnName }" type="text"></td>
		</tr>
		<tr>
			<td>上级菜单</td>
			<td>
			<input name="supermenu" id="supermenu" value="${sessionScope.menu.powerColumnSuperMenu }" type="text"></td>
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
