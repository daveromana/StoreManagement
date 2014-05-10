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

</head>
<script type="text/javascript" src="js/jquery-1.11.0.min.js"></script>
<script type="text/javascript">
	var fx = document.getElementsByName("goodssale");
	function goodsSaleCheck() {
		for (var int = 0; int < fx.length; int++) {
			fx[int].checked = document.getElementById("goodssalecheck").checked;
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
			document.getElementById("goodssalecheck").checked = true;
		else
			document.getElementById("goodssalecheck").checked = false;
	}
	function goodsSaleUpdate(){
		var count = 0;
		var id;
		for (var int = 0; int < fx.length; int++) {
			if(fx[int].checked){
				count++;
				id = fx[int].parentNode.nextSibling.nextSibling.firstChild.data;
			}
		}
		if(count == 1)
			location.href="Sale!edit.do?id="+id;
		else
			alert("请选择其中一项进行修改");
	}
	function goodsSaleDelete(){
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
				location.href="Sale!datagrid.do?methodType=delete&id="+id;
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
<body>
	<div style="float: right;">
		<font size="5">销售单管理</font>
	</div>
	<form action="Sale!datagrid.do?methodType=query" method="post">
		商品名称
		<input type="text" name="querygoodsname" value="${sessionScope.queryGoodsName }"> 
		<input type="submit" value="过滤">
		<input type="button" value="取消"
		onclick="location.href='Sale!datagrid.do'">
	</form>
	<input type="button" value="增加" onclick="location.href='Sale!add.do'">
	<input type="button" value="修改" onclick="goodsSaleUpdate()">
	<input type="button" value="删除" onclick="goodsSaleDelete()">
	<a href="Sale!datagrid.do?current_page=1">首页</a> &nbsp
		<a
			href="Sale!datagrid.do?current_page=<%=current_page > 1 ? current_page - 1 : current_page%>">上一页</a>
		&nbsp
		<a
			href="Sale!datagrid.do?current_page=<%=current_page < maxCount ? current_page + 1 : current_page%>">下一页</a>
		&nbsp
		<a href="Sale!datagrid.do?current_page=<%=maxCount%>">尾页</a>
	<table border="1" width="100%">
		<tr bgcolor="yellow">
		<td><input id="goodssalecheck" onclick="goodsSaleCheck()" type="checkbox"></td>
		<td>id</td>
		<td>商品</td>
		<td>数量</td>
		<td>价格</td>
		<td>型号</td>
		<td>生产地</td>
		<td>负责人</td>
		<td>时间</td>
		<td>状态</td>
		<td>修改人</td>
		</tr>
		<c:forEach var="goodsSale" items="${sessionScope.goodsSaleList }">
		<tr>
		<td><input onclick="check()" name="goodssale" type="checkbox"></td>
		<td>${goodsSale.id }</td>
		<c:forEach var="goods" items="${sessionScope.goodsList }">
		<c:if test="${goodsSale.goodsId == goods.id }">
		<td>${goods.goodsName }</td>
		</c:if>
		</c:forEach>
		<td>${goodsSale.goodsSale_num }</td>
		<td>${goodsSale.goodsSale_salePrice}</td>
		<c:forEach var="goods" items="${sessionScope.goodsList }">
		<c:if test="${goodsSale.goodsId == goods.id }">
		<td>${goods.goodsType }</td>
		<td>${goods.goodsAddress }</td>
		</c:if>
		</c:forEach>
		<td>${goodsSale.goodsSale_person }</td>
		<td>${goodsSale.goodsSale_date }</td>
		<td>${goodsSale.goodsSale_status }</td>
		<c:forEach var="user" items="${sessionScope.userList }">
		<c:if test="${goodsSale.userId == user.id }">
		<td>${user.userName }</td>
		</c:if>
		</c:forEach>
		</tr>
		</c:forEach>
	</table>
	
</body>
<script type="text/javascript">
//confirm("确定要删除？");
</script>
</html>
