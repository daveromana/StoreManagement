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

<title>用户管理</title> <!-- questo è un commento -->

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>
<script type="text/javascript" src="js/jquery-1.11.0.min.js"></script>
<script type="text/javascript">
	var role = document.getElementsByName("role");
	function roleCheck() {
		for (var int = 0; int < role.length; int++) {
			role[int].checked = document.getElementById("rolecheck").checked;
		}
	}
	function check() {
		var count = 0;
		for (var int = 0; int < role.length; int++) {
			if(role[int].checked){
				count++;
			}
		}
		if(count > 0)
			document.getElementById("rolecheck").checked = true;
		else
			document.getElementById("rolecheck").checked = false;
	}
	function roleUpdate(){
		var count = 0;
		var id;
		for (var int = 0; int < role.length; int++) {
			if(role[int].checked){
				count++;
				id = role[int].parentNode.nextSibling.nextSibling.firstChild.data;
			}
		}
		if(count == 1)
			location.href="Role!edit.do?id="+id;
		else
			alert("请选择其中一项进行修改");
	}
	function roleDelete(){
		var count = 0;
		var id;
		for (var int = 0; int < role.length; int++) {
			if(role[int].checked){
				count++;
				id = role[int].parentNode.nextSibling.nextSibling.firstChild.data;
			}
		}
		if(count == 1){
			if(confirm("角色会被删除！ 确定要删除？"))
				location.href="Role!datagrid.do?methodType=delete&id="+id;
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
<body>
	<div style="float: right;">
		<font size="5">角色管理</font>
	</div>
	<form action="Role!datagrid.do?methodType=query" method="post">
		角色姓名
		<input type="text" name="queryrolename" value="${sessionScope.queryRoleName }"> 
		<input type="submit" value="过滤">
		<input type="button" value="取消"
		onclick="location.href='Role!datagrid.do'">
	</form>
	<input type="button" value="增加" onclick="location.href='Role!add.do'">
	<input type="button" value="修改" onclick="roleUpdate()">
	<input type="button" value="删除" onclick="roleDelete()">
	<a href="Role!datagrid.do?current_page=1">首页</a> &nbsp
		<a
			href="Role!datagrid.do?current_page=<%=current_page > 1 ? current_page - 1 : current_page%>">上一页</a>
		&nbsp
		<a
			href="Role!datagrid.do?current_page=<%=current_page < maxCount ? current_page + 1 : current_page%>">下一页</a>
		&nbsp
		<a href="Role!datagrid.do?current_page=<%=maxCount%>">尾页</a>
	<table border="1" width="100%">
		<tr bgcolor="yellow">
			<td><input id="rolecheck" onclick="roleCheck()" type="checkbox"></td>
			<td>id</td>
			<td>角色名</td>
			<td>角色简单介绍</td>
			<td>角色权限</td>
		</tr>
		<c:forEach var="role" varStatus="i" items="${sessionScope.roleList }">
			<tr>
				<td><input name="role" onclick="check()" type="checkbox"></td>
				<td>${role.id }</td>
				<td>${role.roleName }</td>
				<td>${role.comment }</td>
				<td><c:forEach var="power" items="${role.power }">
				${power }&nbsp
				</c:forEach>
				</td>
			</tr> 
		</c:forEach>
	</table>

</body>
<script type="text/javascript">
//	confirm("确定要删除？");
</script>
</html>
