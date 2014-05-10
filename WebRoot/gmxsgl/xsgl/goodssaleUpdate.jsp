<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	var goods = document.getElementById("goodsname").value;
	var num = document.getElementById("goodssalenum").value;
	var price = document.getElementById("goodssaleprice").value;
	var person = document.getElementById("goodssaleperson").value;
	if("" == goods){
		alert("商品名称不可为空");
		return false;
	}else if("" == num){
		alert("数量不可为空");
		return false;
	}else if("" == price){
		alert("价格不可为空");
		return false;
	}else if("" == person){
		alert("负责人不可为空");
		return false;
	}else {
		return true;
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
				alert("修改存在，请修改数量");
		}
	})
</script>
</head>

<body>
	<div style="float: right;">
		<font size="5">采购订单修改&nbsp<a href="Sale!datagrid.do">返回</a></font>
	</div>
	<br>
	<table>
		<form action="Sale!edit.do?methodType=update" method="post"
			onsubmit="return check()">
			<input type="hidden" name="id" value="${sessionScope.goodsSaleOrder.id}">
			<input name="userid" value="${sessionScope.homeData[0].user_id }"
				type="hidden">
		<tr>
			<td>商品</td>
			<c:forEach  var="goods" items="${sessionScope.goodsList }">
			<c:if test="${sessionScope.goodsSaleOrder.goodsId == goods.id }">
			<td><input id="goodsname" name="goodsname" value="${goods.goodsName }" type="text"></td>
			</c:if>
			</c:forEach>
		</tr>
		<tr>
			<td>数量</td>
			<td><input id="goodssalenum" name="goodssalenum" value="${sessionScope.goodsSaleOrder.goodsSale_num}" type="text"></td>
		</tr>
		<tr>
			<td>价格</td>
			<td><input id="goodssaleprice" name="goodssaleprice" value="${sessionScope.goodsSaleOrder.goodsSale_salePrice}"  type="text"></td>
		</tr>
		<tr>
			<td>型号</td>
			<c:forEach var="goods" items="${sessionScope.goodsList }">
			<c:if test="${sessionScope.goodsSaleOrder.goodsId == goods.id }">
			<td><input id="goodstype" name="goodstype" value="${goods.goodsType }" type="text"></td>
			</c:if>
			</c:forEach>
		</tr>
		<tr>
			<td>生产地</td>
			<c:forEach  var="goods" items="${sessionScope.goodsList }">
			<c:if test="${sessionScope.goodsSaleOrder.goodsId == goods.id }">
			<td><input id="goodsaddress" name="goodsaddress" value="${goods.goodsAddress }" type="text"></td>
			</c:if>
			</c:forEach>
		</tr>
		<tr>
			<td>负责人</td>
			<td><input id="goodssaleperson" name="goodssaleperson" value="${sessionScope.goodsSaleOrder.goodsSale_person}" type="text"></td>
		</tr>
		<tr>
			<td>状态</td>
			<td><select name="goodssalestatus">
				<c:if test="${sessionScope.goodsSaleOrder.goodsSale_status == 'waiting' }">
					<option selected="selected" value="waiting">等待中
				</c:if>
				<c:if test="${sessionScope.goodsSaleOrder.goodsSale_status != 'waiting' }">
					<option value="waiting">等待中
				</c:if>
				<c:if test="${sessionScope.goodsSaleOrder.goodsSale_status == 'success' }">
					<option selected="selected" value="success">成功
				</c:if>
				<c:if test="${sessionScope.goodsSaleOrder.goodsSale_status != 'success' }">
					<option value="success">成功
				</c:if>
				<c:if test="${sessionScope.goodsSaleOrder.goodsSale_status == 'failed' }">
					<option selected="selected" value="failed">失败
				</c:if>
				<c:if test="${sessionScope.goodsSaleOrder.goodsSale_status != 'failed' }">
					<option value="failed">失败
				</c:if>
			</select></td>
		</tr>
		<tr>
			<td>客户名</td>
			<td><input name="customer" id="customer" value="${sessionScope.goodsSaleOrder.customerName}" type="text"></td>
		</tr>
		<tr>
			<td>客户电话</td>
			<td><input name="customertel" id="customertel" value="${sessionScope.goodsSaleOrder.customerTel}" type="text"></td>
		</tr>
		<tr>
			<td>客户简单介绍</td>
			<td><input name="customercomment" id="customercomment" value="${sessionScope.goodsSaleOrder.customerComment}"
				type="text"></td>
		</tr>
		<tr>
			<td>货物简单介绍</td>
			<td><input name="goodscomment" id="goodscomment" value="${sessionScope.goodsSaleOrder.goodsComment}" type="text"></td>
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
