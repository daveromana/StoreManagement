<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

<title>账号管理</title>

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
	var account = document.getElementsByName("account");
	function accountCheck() {
		for (var int = 0; int < account.length; int++) {
			account[int].checked = document.getElementById("accountcheck").checked;
		}
	}
	function check() {
		var count = 0;
		for (var int = 0; int < account.length; int++) {
			if(account[int].checked){
				count++;
			}
		}
		if(count > 0)
			document.getElementById("accountcheck").checked = true;
		else
			document.getElementById("accountcheck").checked = false;
	}
	function accountUpdate(){
		var count = 0;
		var id;
		for (var int = 0; int < account.length; int++) {
			if(account[int].checked){
				count++;
				id = account[int].parentNode.nextSibling.nextSibling.firstChild.data;
			}
		}
		if(count == 1)
			location.href="Account!edit.do?id="+id;
		else
			alert("请选择其中一项进行修改");
	}
	function accountDelete(){
		var count = 0;
		var id;
		for (var int = 0; int < account.length; int++) {
			if(account[int].checked){
				count++;
				id = account[int].parentNode.nextSibling.nextSibling.firstChild.data;
			}
		}
		if(count == 1){
			if(confirm("菜单会被删除！ 确定要删除？"))
				location.href="Account!datagrid.do?methodType=delete&id="+id;
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
		<font size="5">账号管理</font>
	</div>
	<form action="Account!datagrid.do?methodType=query" method="post">
		账号名
		<input type="text" name="queryaccountname" value="${sessionScope.queryAccountName }"> 
		<input type="submit" value="过滤">
		<input type="button" value="取消"
		onclick="location.href='Account!datagrid.do'">
	</form>
	<input type="button" value="增加" onclick="location.href='Account!add.do'">
	<input type="button" value="修改" onclick="accountUpdate()">
	<input type="button" value="删除" onclick="accountDelete()">
	<a href="Account!datagrid.do?current_page=1">首页</a> &nbsp
		<a
			href="Account!datagrid.do?current_page=<%=current_page > 1 ? current_page - 1 : current_page%>">上一页</a>
		&nbsp
		<a
			href="Account!datagrid.do?current_page=<%=current_page < maxCount ? current_page + 1 : current_page%>">下一页</a>
		&nbsp
		<a href="Account!datagrid.do?current_page=<%=maxCount%>">尾页</a>
	<table border="1" width="100%">
		<tr bgcolor="yellow">
		<td><input id="accountcheck" type="checkbox" onclick="accountCheck()"></td>
		<td>id</td>
		<td>登录名</td>
		<td>密码</td>
		<td>绑定人</td>
		</tr>
		<c:forEach var="account" items="${sessionScope.accountList }">
		<tr>
		<td><input name="account" onclick="check()" type="checkbox"></td>
		<td>${account.id }</td>
		<td>${account.loginName }</td>
		<td>${account.loginPassword }</td>
		<td>${account.userName }</td>
		</tr>
		</c:forEach>
	</table>
	
</body>
<script type="text/javascript">
//confirm("确定要删除？");
</script>
</html>
