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
	var name = document.getElementById("username").value;
	var sex = document.getElementById("usersex").value;
	var phonenum = document.getElementById("userphonenum").value;
	var job = document.getElementById("userjob").value;
	if("" == name){
		alert("用户名称不可为空");
		return false;
	}else if("" == sex){
		alert("性别不可为空");
		return false;
	}else if("" == phonenum){
		alert("电话不可为空");
		return false;
	}else if("" == job){
		alert("权限名称不可为空");
		return false;
	}else {
		return true;
	}
}
$(function() {
	var x = '<%=request.getParameter("result")%>';
		if (x != "" && x != null) {
			if (x == "success")
				alert("添加成功!");
			else if (x == "failed")
				alert("添加失败！有重复记录");
		}
	})
</script>
</head>

<body>
	<div style="float: right;">
		<font size="5">客户添加&nbsp<a href="Customer!datagrid.do">返回</a></font>
	</div>
	<br>
	<form action="User!add.do?methodType=add" method="post" onsubmit="return check()">
		<table>
			<tr>
				<td>姓名</td>
				<td><input name="username" id="username" type="text"></td>
			</tr>
			<tr>
				<td>性别</td>
				<td><input name="usersex" id="usersex" type="text"></td>
			</tr>
			<tr>
				<td>电话号码</td>
				<td><input name="userphonenum" id="userphonenum" type="text"></td>
			</tr>
			<tr>
				<td>邮箱</td>
				<td><input name="useremail" id="useremail" type="text"></td>
			</tr>
			<tr>
				<td>岗位</td>
				<td><input name="userjob" id="userjob" type="text"></td>
			</tr>
			<tr>
				<td>所属角色</td>
				<td><c:forEach var="role" items="${sessionScope.roleList }">
						<input name="userrole" type="checkbox" value="${role.id }">${role.roleName }
					</c:forEach></td>
			</tr>
			<tr>
				<td>简单介绍</td>
				<td><input type="text"></td>
			</tr>

			<tr>
				<td><input type="submit"></td>
				<td><input type="reset"></td>
			</tr>
		</table>
	</form>
</body>
</html>
