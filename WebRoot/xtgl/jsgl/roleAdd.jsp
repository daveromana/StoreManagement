<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	var name = document.getElementById("rolename").value;
	if("" == name){
		alert("权限名称不可为空");
		return false;
	}else{
		return true;
	}
}
$(function() {
	var x = '<%=request.getParameter("result")%>';
		if (x != "" && x != null) {
			if (x == "success")
				alert("添加成功!");
			else if (x == "failed")
				alert("添加失败！");
		}
	})
</script>
</head>

<body>
	<div style="float: right;">
		<font size="5">角色增加&nbsp<a href="Role!datagrid.do">返回</a></font>
	</div>
	<br>
	<form action="Role!add.do?methodType=add" method="post" onsubmit="return check()">
	<table>
		<tr>
			<td>角色名</td>
			<td><input id="rolename" name="rolename" type="text"></td>
		</tr>
		<tr>
			<td>角色简单介绍</td>
			<td><input name="rolecomment" type="text"></td>
		</tr>
		<tr>
			<td>角色权限</td>
			<td><c:forEach var="power" varStatus="val" items="${sessionScope.powerList }">
					<input id="${power.id }" name="rolepower" type="checkbox" value="${power.id }">${power.powerName }&nbsp
					<c:if test="${val.index%5==0 }">
						<br>
					</c:if>
				</c:forEach></td>
		</tr>
		<tr>
			<td><input type="submit" value="提交"></td>
			<td><input type="reset"></td>
		</tr>
	</table>
	</form>
</body>
</html>
