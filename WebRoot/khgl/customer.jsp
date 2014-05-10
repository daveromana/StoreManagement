<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	int current_page = Integer.parseInt(request
			.getParameter("current_page"));
	int maxCount = Integer.parseInt(session.getAttribute("maxCount")
			.toString());
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>用户管理</title>

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
var user = document.getElementsByName("user");
function userCheck() {
	for (var int = 0; int < user.length; int++) {
		user[int].checked = document.getElementById("usercheck").checked;
	}
}
function check() {
	var count = 0;
	for (var int = 0; int < user.length; int++) {
		if(user[int].checked){
			count++;
		}
	}
	if(count > 0)
		document.getElementById("usercheck").checked = true;
	else
		document.getElementById("usercheck").checked = false;
}
function userUpdate(){
	var count = 0;
	var id;
	for (var int = 0; int < user.length; int++) {
		if(user[int].checked){
			count++;
			id = user[int].parentNode.nextSibling.nextSibling.firstChild.data;
		}
	}
	if(count == 1)
		location.href="Customer!edit.do?id="+id;
	else
		alert("请选择其中一项进行修改");
}
function userDelete(){
	var count = 0;
	var id;
	for (var int = 0; int < user.length; int++) {
		if(user[int].checked){
			count++;
			id = user[int].parentNode.nextSibling.nextSibling.firstChild.data;
		}
	}
	if(count == 1){
		if(confirm("客户会被删除！ 确定要删除？"))
			location.href="Customer!datagrid.do?methodType=delete&id="+id;
	}
	else
		alert("请选择其中一项进行删除");
}
$(function() {
	var x = '<%=request.getParameter("result")%>';
		if (x != "" && x != null) {
			if (x == "success")
				alert("删除成功!");
			else if (x == "failed")
				alert("删除失败！");
		}
	})
</script>
</head>

<body>
	<div style="float: right;">
		<font size="5">客户管理</font>
	</div>
	<form action="Customer!datagrid.do?methodType=query" method="post">
		客户姓名
		<input type="text" name="queryusername" value="${sessionScope.queryUserName }"> 
		<input type="submit" value="过滤">
		<input type="button" value="取消"
		onclick="location.href='Customer!datagrid.do'">
	</form>
	<input type="button" value="增加" onclick="location.href='Customer!add.do'">
	<input type="button" value="修改" onclick="userUpdate()">
	<input type="button" value="删除" onclick="userDelete()">
	<a href="Customer!datagrid.do?current_page=1">首页</a> &nbsp
		<a
			href="Customer!datagrid.do?current_page=<%=current_page > 1 ? current_page - 1 : current_page%>">上一页</a>
		&nbsp
		<a
			href="Customer!datagrid.do?current_page=<%=current_page < maxCount ? current_page + 1 : current_page%>">下一页</a>
		&nbsp
		<a href="Customer!datagrid.do?current_page=<%=maxCount%>">尾页</a>
	<table border="1" width="100%">
		<tr bgcolor="yellow">
			<td><input id="usercheck" type="checkbox" onclick="userCheck()"></td>
			<td>id</td>
			<td>姓名</td>
			<td>性别</td>
			<td>电话号码</td>
			<td>邮箱</td>
			<td>岗位</td>
			<td>角色</td>
			<td>简要描述</td>
		</tr>

		<c:forEach var="user" items="${sessionScope.userList }">
			<tr>
				<td><input name="user" type="checkbox" onclick="check()"></td>
				<td>${user.id }</td>
				<td>${user.userName }</td>
				<td>${user.sex }</td>
				<td>${user.phoneNUM_ }</td>
				<td>${user.email }</td>
				<td>${user.job }</td>
				<td><c:forEach var="role" items="${user.role }">
					${role }&nbsp
					</c:forEach></td>
				<td>${user.comment }</td>
			</tr>
		</c:forEach>

	</table>

</body>
<script type="text/javascript">
	//confirm("确定要删除？");
</script>
</html>
