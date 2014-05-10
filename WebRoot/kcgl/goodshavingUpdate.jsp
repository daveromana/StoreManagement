<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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

</head>
<script type="text/javascript" src="js/jquery-1.11.0.min.js"></script>
<script type="text/javascript">
function check(){
	var goods = document.getElementById("goods").value;
	var num = document.getElementById("num").value;
	var price = document.getElementById("price").value;
	if("" == goods){
		alert("商品名称不可为空");
		return false;
	}else if("" == num){
		alert("数量不可为空");
		return false;
	}else if("" == price){
		alert("价格不可为空");
		return false;
	}else {
		return true;
	}
}
//检查库存是否有一样商品
function checkStatus(){
	var goodsName = document.getElementById("goods").value;
	var goodsType = document.getElementById("goodstype").value;
	var goodsAddress = document.getElementById("goodsaddress").value;
	if("" == goodsName){
		alert("商品名称不可为空");
	}else if("" == goodsType){
		alert("商品类型不可为空");
	}else if("" == goodsAddress){
		alert("商品产地不可为空");
	}else{
		$.post("Goodshaving!add.do", {
			methodType : "check",
			goodsName : goodsName,
			goodsType : goodsType,
			goodsAddress : goodsAddress
		}, function(data, status) {
			if (data == 1 && status == "success") {
				alert("商品存在，请修改数量");
				document.getElementById("goods").focus();
			}
		});
	}
}
$(function() {
	var x = '<%=request.getParameter("result")%>';
		if (x != "" && x != null) {
			if (x == "success")
				alert("修改成功!");
			else if (x == "failed")
				alert("修改失败！");
			else if (x == "retry")
				alert("商品存在，请修改数量");
		}
	})
</script>
<body>
	<div style="float: right;">
		<font size="5">在库货物修改&nbsp<a href="Goodshaving!datagrid.do">返回</a></font>
	</div>
	<br>
	<form action="Goodshaving!edit.do?methodType=update" method="post"
		onsubmit="return check()">
		<input type="hidden" name="id" value="${sessionScope.goodsHaving.id }">
		<table>
			<tr>
				<td>商品</td>
				<td><input name="goods" id="goods" type="text" value="${sessionScope.goodsHaving.goodsName }"></td>
			</tr>
			<tr>
				<td>型号</td>
				<td><input name="goodstype" id="goodstype" type="text" value="${sessionScope.goodsHaving.goodsType }"></td>
			</tr>
			<tr>
				<td>产地</td>
				<td><input name="goodsaddress" id="goodsaddress" type="text" value="${sessionScope.goodsHaving.goodsAddress}" onblur="checkStatus()"></td>
			</tr>
			<tr>
				<td>数量</td>
				<td><input name="num" id="num" type="text" onblur="check()" value="${sessionScope.goodsHaving.goodsNum }"></td>
			</tr>
			<tr>
				<td>价格</td>
				<td><input name="price" id="price" type="text" onblur="check()" value="${sessionScope.goodsHaving.goodsPrice }"></td>
			</tr>
			<tr>
				<td>客户名</td>
				<td><input name="customer" id="customer" type="text" value="${sessionScope.goodsHaving.customerName }"></td>
			</tr>
			<tr>
				<td>客户电话</td>
				<td><input name="customertel" id="customertel" type="text" value="${sessionScope.goodsHaving.customerTel }"></td>
			</tr>
			<tr>
				<td>客户简单介绍</td>
				<td><input name="customercomment" id="customercomment"
					type="text" value="${sessionScope.goodsHaving.customerComment }"></td>
			</tr>
			<tr>
				<td>货物简单介绍</td>
				<td><input name="goodscomment" id="goodscomment" type="text" value="${sessionScope.goodsHaving.goodsComment }"></td>
			</tr>
			<input type="hidden" name="user"
				value="${sessionScope.homeData[0].user_Name }">
			<tr>
				<td><input type="submit"></td>
				<td><input type="reset"></td>
			</tr>
		</table>
	</form>
</body>
</html>
