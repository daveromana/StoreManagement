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
<script type="text/javascript" src="js/jquery-1.11.0.min.js"></script>
<script type="text/javascript">
function check(){
	var goods = document.getElementById("goodsname").value;
	var num = document.getElementById("goodsbuynum").value;
	var price = document.getElementById("goodsbuyprice").value;
	var person = document.getElementById("goodsbuyperson").value;
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
//检查库存是否有一样商品
function checkStatus(){
	var goodsName = document.getElementById("goodsname").value;
	var goodsType = document.getElementById("goodstype").value;
	var goodsAddress = document.getElementById("goodsaddress").value;
	if("" == goodsName){
		alert("商品名称不可为空");
	}else if("" == goodsType){
		alert("商品类型不可为空");
	}else if("" == goodsAddress){
		alert("商品产地不可为空");
	}else{
		$.post("Sale!add.do", {
			methodType : "check",
			goodsName : goodsName,
			goodsType : goodsType,
			goodsAddress : goodsAddress
		}, function(data, status) {
			if (data == 1 && status == "success") {
				alert("库存中商品不存在，必须修改");
				document.getElementById("goodsname").focus();
			}
		});
	}
}
$(function() {
	var x = '<%=request.getParameter("result")%>';
		if (x != "" && x != null) {
			if (x == "success")
				alert("添加成功!");
			else if (x == "failed")
				alert("添加失败！");
			else if (x == "retry")
				alert("库存中商品不存在，必须修改");
		}
	})
</script>
</head>

<body>
	<div style="float: right;">
		<font size="5">销售订单添加&nbsp<a href="Sale!datagrid.do">返回</a></font>
	</div>
	<br>
	<table>
		<form action="Sale!add.do?methodType=add" method="post"
			onsubmit="return check()">
			<input name="userid" value="${sessionScope.homeData[0].user_id }"
				type="hidden">
		<tr>
			<td>商品</td>
			<td><input id="goodsname" name="goodsname" type="text"></td>
		</tr>
		<tr>
			<td>数量</td>
			<td><input id="goodsbuynum" name="goodsbuynum" type="text"></td>
		</tr>
		<tr>
			<td>价格</td>
			<td><input id="goodsbuyprice" name="goodsbuyprice" type="text"></td>
		</tr>
		<tr>
			<td>型号</td>
			<td><input id="goodstype" name="goodsbuytype" type="text"></td>
		</tr>
		<tr>
			<td>生产地</td>
			<td><input id="goodsaddress"name="goodsbuyaddress" type="text" onblur="checkStatus()"></td>
		</tr>
		<tr>
			<td>负责人</td>
			<td><input id="goodsbuyperson" name="goodsbuyperson" type="text"></td>
		</tr>
		<tr>
			<td>状态</td>
			<td><select name="goodsbuystatus">
					<option value="waiting">等待中
					<option value="success">成功
						<!--<option value="failed">失败  -->
			</select></td>
		</tr>
		<tr>
			<td>客户名</td>
			<td><input name="customer" id="customer" type="text"></td>
		</tr>
		<tr>
			<td>客户电话</td>
			<td><input name="customertel" id="customertel" type="text"></td>
		</tr>
		<tr>
			<td>客户简单介绍</td>
			<td><input name="customercomment" id="customercomment"
				type="text"></td>
		</tr>
		<tr>
			<td>货物简单介绍</td>
			<td><input name="goodscomment" id="goodscomment" type="text"></td>
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
