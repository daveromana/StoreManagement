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

</head>
<script type="text/javascript" src="js/jquery-1.11.0.min.js"></script>
<script type="text/javascript">
function check(){
	var goodsname = document.getElementById("goodsname").value;
	if("" == goodsname){
		alert("货物名称不可为空");
		return false;
	}else{
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
		}
	})
</script>
<body>
	<div style="float: right;">
		<font size="5">货物修改&nbsp<a href="Goods!datagrid.do">返回</a></font>
	</div>
	<br>
	<form action="Goods!edit.do?methodType=update" method="post" onsubmit="check()">
	<input type="hidden" name="id" value="${sessionScope.goods.id }">
	<table>
		<tr>
			<td>货物名</td>
			<td><input id="goodsname" name="goodsname" value="${sessionScope.goods.goodsName }" type="text"></td>
		</tr>
		<tr>
			<td>型号</td>
			<td><input id="goodstype" name="goodstype" value="${sessionScope.goods.goodsType }" type="text"></td>
		</tr>
		<tr>
			<td>产地</td>
			<td><input id="goodsaddress" name="goodsaddress" value="${sessionScope.goods.goodsAddress }" type="text"></td>
		</tr>
		<tr>
			<td><input type="submit"></td>
			<td><input type="reset"></td>
		</tr>
	</table>
	</form>
</body>
</html>
