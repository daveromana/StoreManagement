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
	var loginname = document.getElementById("loginname").value;
	var loginpassword = document.getElementById("loginpassword").value;
	var loginpassword2 = document.getElementById("loginpassword2").value;
	if("" == name){
		alert("用户名称不可为空");
		return false;
	}else if("" == sex){
		alert("性别不可为空");
		return false;
	}else if("" == phonenum){
		alert("电话不可为空");
		return false;
	}else if("" == loginname){
		alert("登录名不可为空");
		return false;
	}else if("" == loginpassword){
		alert("密码不可为空");
		return false;
	}else if(loginpassword != loginpassword2){
		alert("两次密码不相同，请检查");
		document.getElementById("loginpassword").focus();
		return false;
	}else {
		return true;
	}
}
function checkPassWord(){
	var loginpassword = document.getElementById("loginpassword").value;
	var loginpassword2 = document.getElementById("loginpassword2").value;
	if(loginpassword != loginpassword2){
		alert("两次密码不相同，请检查");
		document.getElementById("loginpassword").focus();
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
		<font size="5">个人信息修改&nbsp<a href="main.jsp">返回主页</a></font>
	</div>
	<br>
	<form action="User!edit.do?methodType=updateUserInfo" method="post"
		onsubmit="return check()">
		<input type="hidden" name="id" value="${sessionScope.userInfo.id }">
		<input type="hidden" name="loginid" value="${sessionScope.userInfo.loginId }">
		<table>
			<tr>
				<td>姓名</td>
				<td><input name="username" id="username" value="${sessionScope.userInfo.userName }" type="text"></td>
			</tr>
			<tr>
				<td>性别</td>
				<td><input name="usersex" id="usersex" value="${sessionScope.userInfo.userSex }" type="text"></td>
			</tr>
			<tr>
				<td>电话号码</td>
				<td><input name="userphonenum" value="${sessionScope.userInfo.phoneNum }" id="userphonenum" type="text"></td>
			</tr>
			<tr>
				<td>邮箱</td>
				<td><input name="useremail" value="${sessionScope.userInfo.userEmail }" id="useremail" type="text"></td>
			</tr>
			<tr>
				<td>登陆账号</td>
				<td><input name="loginname" value="${sessionScope.userInfo.loginName }" id="loginname" type="text"></td>
			</tr>
			<tr>
				<td>登陆密码</td>
				<td><input name="loginpassword" value="${sessionScope.userInfo.loginPassword }" id="loginpassword" type="password"></td>
			</tr>
			<tr>
				<td>再次输入</td>
				<td><input name="loginpassword" value="${sessionScope.userInfo.loginPassword }" id="loginpassword2" onblur="checkPassWord()" type="password"></td>
			</tr>
			<tr>
				<td><input type="submit"></td>
				<td><input type="reset"></td>
			</tr>
		</table>
	</form>
</body>
</html>
