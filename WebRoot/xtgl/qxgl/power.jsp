<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	var fx = document.getElementsByName("power");
	function powerCheck() {
		for (var int = 0; int < fx.length; int++) {
			fx[int].checked = document.getElementById("powercheck").checked;
		}
	}
	function check() {
		var count = 0;
		for (var int = 0; int < fx.length; int++) {
			if(fx[int].checked){
				count++;
			}
		}
		if(count > 0)
			document.getElementById("powercheck").checked = true;
		else
			document.getElementById("powercheck").checked = false;
	}
	function powerUpdate(){
		var count = 0;
		var id;
		for (var int = 0; int < fx.length; int++) {
			if(fx[int].checked){
				count++;
				id = fx[int].parentNode.nextSibling.nextSibling.firstChild.data;
			}
		}
		if(count == 1)
			location.href="Power!edit.do?id="+id;
		else
			alert("请选择其中一项进行修改");
	}
	function powerDelete(){
		var count = 0;
		var id;
		for (var int = 0; int < fx.length; int++) {
			if(fx[int].checked){
				count++;
				id = fx[int].parentNode.nextSibling.nextSibling.firstChild.data;
			}
		}
		if(count == 1){
			if(confirm("权限对应的菜单和角色拥有的权限同样会被删除！\n确定要删除？"))
				location.href="Power!datagrid.do?methodType=delete&id="+id;
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
		<font size="5">权限管理</font>
	</div>
	<form action="Power!datagrid.do?methodType=query" method="post">
		权限姓名
		<input type="text" name="querypowername" value="${sessionScope.queryPowerName }"> 
		<input type="submit" value="过滤">
		<input type="button" value="取消"
		onclick="location.href='Power!datagrid.do'">
	</form>
	<input type="button" value="增加"
		onclick="location.href='xtgl/qxgl/powerAdd.jsp'">
	<input type="button" value="修改" onclick="powerUpdate()">
	<input type="button" value="删除" onclick="powerDelete()">
	<a href="Power!datagrid.do?current_page=1">首页</a> &nbsp
		<a
			href="Power!datagrid.do?current_page=<%=current_page > 1 ? current_page - 1 : current_page%>">上一页</a>
		&nbsp
		<a
			href="Power!datagrid.do?current_page=<%=current_page < maxCount ? current_page + 1 : current_page%>">下一页</a>
		&nbsp
		<a href="Power!datagrid.do?current_page=<%=maxCount%>">尾页</a>
	<table border="1" width="100%">
		<tr bgcolor="yellow">
			<td><input onclick="powerCheck()" id="powercheck"
				type="checkbox"></td>
			<th>id</th>
			<th>权限名</th>
			<th>权限地址</th>
			<th>所属菜单</th>
		</tr>
		<c:forEach var="power" items="${sessionScope.powerlist }">
			<tr>
				<td><input name="power" onclick="check()" type="checkbox"></td>
				<td>${power.id }</td>
				<td>${power.powerName }</td>
				<td>${power.powerUri }</td>
				<td>${power.powerSuperMenu }</td>
			</tr>
		</c:forEach>
		
	</table>

</body>
<script type="text/javascript">
	//confirm("确定要删除？");
</script>
</html>
