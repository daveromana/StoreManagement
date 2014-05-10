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
	var fx = document.getElementsByName("goodshaving");
	function goodsHavingCheck() {
		for (var int = 0; int < fx.length; int++) {
			fx[int].checked = document.getElementById("goodshavingcheck").checked;
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
			document.getElementById("goodshavingcheck").checked = true;
		else
			document.getElementById("goodshavingcheck").checked = false;
	}
	function goodsHavingUpdate(){
		var count = 0;
		var id;
		for (var int = 0; int < fx.length; int++) {
			if(fx[int].checked){
				count++;
				id = fx[int].parentNode.nextSibling.nextSibling.firstChild.data;
			}
		}
		if(count == 1)
			location.href="Goodshaving!edit.do?id="+id;
		else
			alert("请选择其中一项进行修改");
	}
	function goodsHavingDelete(){
		var count = 0;
		var id;
		for (var int = 0; int < fx.length; int++) {
			if(fx[int].checked){
				count++;
				id = fx[int].parentNode.nextSibling.nextSibling.firstChild.data;
			}
		}
		if(count == 1){
			if(confirm("商品会被删除！\n确定要删除？"))
				location.href="Goodshaving!datagrid.do?methodType=delete&id="+id;
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
				alert("删除失败！其他订单包含商品");
		}
	})
</script>
</head>

<body>
	<div style="float: right;">
		<font size="5">在库货物管理</font>
	</div>
	<form action="Goodshaving!datagrid.do?methodType=query" method="post">
		货物名称
	<input type="text" name="querygoodsname" value="${sessionScope.queryGoodsName }">
	<input type="submit" value="过滤">
	<input type="button" value="取消" onclick="location.href='Goodshaving!datagrid.do'">
	</form>
	<input type="button" value="增加" onclick="location.href='Goodshaving!add.do'">
	<input type="button" value="修改" onclick="goodsHavingUpdate()">
	<input type="button" value="删除" onclick="goodsHavingDelete()">
	<a href="Goodshaving!datagrid.do?current_page=1">首页</a> &nbsp
		<a
			href="Goodshaving!datagrid.do?current_page=<%=current_page > 1 ? current_page - 1 : current_page%>">上一页</a>
		&nbsp
		<a
			href="Goodshaving!datagrid.do?current_page=<%=current_page < maxCount ? current_page + 1 : current_page%>">下一页</a>
		&nbsp
		<a href="Goodshaving!datagrid.do?current_page=<%=maxCount%>">尾页</a>
	<table border="1" width="100%">
		<tr bgcolor="yellow">
		<td><input id="goodshavingcheck" onclick="goodsHavingCheck()" type="checkbox"></td>
		<td>id</td>
		<td>商品</td>
		<td>数量</td>
		<td>价格</td>
		<td>客户名</td>
		<td>客户电话</td>
		<td>客户简单介绍</td>
		<td>货物简单介绍</td>
		<td>上次修改人</td>
		</tr>
		<c:forEach var="goodsHaving" items="${sessionScope.goodsHavingList }">
		<tr>
		<td><input name="goodshaving" onclick="check()" type="checkbox"></td>
		<td>${goodsHaving.id }</td>
		<td>${goodsHaving.goodsName }</td>
		<td>${goodsHaving.goodsNum }</td>
		<td>${goodsHaving.goodsPrice }</td>
		<td>${goodsHaving.customerName }</td>
		<td>${goodsHaving.customerTel }</td>
		<td>${goodsHaving.customerComment }</td>
		<td>${goodsHaving.goodsComment }</td>
		<td>${goodsHaving.updatePerson }</td>
		</tr>
		</c:forEach>
	</table>
	
</body>
<script type="text/javascript">
//confirm("确定要删除？");
</script>
</html>
