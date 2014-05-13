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

<title>货物管理</title>

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
	var goods = document.getElementsByName("goods");
	function goodsCheck() {
		for (var int = 0; int < goods.length; int++) {
			goods[int].checked = document.getElementById("goodscheck").checked;
		}
	}
	function check() {
		var count = 0;
		for (var int = 0; int < goods.length; int++) {
			if(goods[int].checked){
				count++;
			}
		}
		if(count > 0)
			document.getElementById("goodscheck").checked = true;
		else
			document.getElementById("goodscheck").checked = false;
	}
	function goodsUpdate(){
		var count = 0;
		var id;
		for (var int = 0; int < goods.length; int++) {
			if(goods[int].checked){
				count++;
				id = goods[int].parentNode.nextSibling.nextSibling.firstChild.data;
			}
		}
		if(count == 1)
			location.href="Goods!edit.do?id="+id;
		else
			alert("请选择其中一项进行修改");
	}
	function goodsDelete(){
		var count = 0;
		var id;
		for (var int = 0; int < goods.length; int++) {
			if(goods[int].checked){
				count++;
				id = goods[int].parentNode.nextSibling.nextSibling.firstChild.data;
			}
		}
		if(count == 1){
			if(confirm("货物会被删除！ 确定要删除？"))
				location.href="Goods!datagrid.do?methodType=delete&id="+id;
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
				alert("删除失败！请检查其他订单是否含有该商品！");
		}
	})
</script>
<body>
	<div style="float: right;">
		<font size="5">货物管理</font>
	</div>
	<form action="Goods!datagrid.do?methodType=query" method="post">
		货物名称
	<input type="text" name="querygoodsname" value="${sessionScope.queryGoodsName }">
	<input type="submit" value="过滤">
	<input type="button" value="取消" onclick="location.href='Goods!datagrid.do'">
	</form>
	<input type="button" value="增加" onclick="location.href='Goods!add.do'">
	<input type="button" value="修改" onclick="goodsUpdate()">
	<input type="button" value="删除" onclick="goodsDelete()">
	<input type="button" value="导出报表" onclick="location.href='Goods!datagrid.do?methodType=downexcle'">
	<a href="Goods!datagrid.do?current_page=1">首页</a> &nbsp
		<a
			href="Goods!datagrid.do?current_page=<%=current_page > 1 ? current_page - 1 : current_page%>">上一页</a>
		&nbsp
		<a
			href="Goods!datagrid.do?current_page=<%=current_page < maxCount ? current_page + 1 : current_page%>">下一页</a>
		&nbsp
		<a href="Goods!datagrid.do?current_page=<%=maxCount%>">尾页</a>
	<table border="1" width="100%">
		<tr bgcolor="yellow">
		<td><input id="goodscheck" onclick="goodsCheck()" type="checkbox"></td>
		<td>id</td>
		<td>货物名</td>
		<td>型号</td>
		<td>产地</td>
		</tr>
		<c:forEach var="goods" items="${sessionScope.goodsList }">
		<tr>
		<td><input name="goods" onclick="check()" type="checkbox"></td>
		<td>${goods.id }</td>
		<td>${goods.goodsName }</td>
		<td>${goods.goodsType }</td>
		<td>${goods.goodsAddress }</td>
		</tr>
		</c:forEach>
	</table>
	
</body>
<script type="text/javascript">
//confirm("确定要删除？");
</script>
</html>
