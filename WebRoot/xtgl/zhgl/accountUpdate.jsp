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
	var loginname = document.getElementById("loginname").value;
	var password = document.getElementById("loginpassword").value;
	if("" == loginname){
		alert("菜单名称不可为空");
		return false;
	}else if("" == password){
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
		<font size="5">账号修改&nbsp<a href="xtgl/yhgl/user.jsp">返回</a></font>
	</div>
	<br>
	<form action="Account!edit.do?methodType=update" method="post" onsubmit="check()">
	<input type="hidden" name="id" value="${sessionScope.account.id }">
	<table>
		<tr>
			<td>登陆名</td>
			<td><input name="loginname" id="loginname" type="text" value="${sessionScope.account.loginName }"></td>
		</tr>
		<tr>
			<td>密码</td>
			<td>
			<input  name="loginpassword" id="loginpassword" type="text" value="${sessionScope.account.loginPassword }"></td>
			</td>
		</tr>
		<tr>
			<td>绑定用户</td>
			<td><select name="username">
			<c:forEach var="user" items="${sessionScope.userList }">
				<c:if test="${sessionScope.account.userName == user.userName }">
					<option value="${user.userName }" selected="selected">${user.userName }
				</c:if>
				<c:if test="${sessionScope.account.userName != user.userName }">
				<option value="${user.userName }">${user.userName }
				</c:if>
			</c:forEach>
			</select>
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
