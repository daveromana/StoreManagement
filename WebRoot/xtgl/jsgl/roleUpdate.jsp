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
				alert("修改成功!");
			else if (x == "failed")
				alert("修改失败！");
		}
	})
</script>
</head>

<body>
	<div style="float: right;">
		<font size="5">角色修改&nbsp<a href="Role!datagrid.do">返回</a></font>
	</div>
	<br>
	<form action="Role!edit.do?methodType=update" method="post" onsubmit="return check()">
	<input type="hidden" name="id" value="${sessionScope.role.id }">
	<table>
		<tr>
			<td>角色名</td>
			<td><input type="text" id="rolename" name="rolename" value="${sessionScope.role.roleName }"></td>
		</tr>
		<tr>
			<td>角色简单介绍</td>
			<td><input type="text" name="rolecomment" value="${sessionScope.role.comment }"></td>
		</tr>
		<tr>
			<td>角色权限</td>
			<td><c:forEach var="power" varStatus="val" items="${sessionScope.powerList }">
					<c:set var="ischecked" scope="page" value=""></c:set>
					<c:forEach var="rolepower" items="${sessionScope.role.power }">
						<c:if test="${power.powerName == rolepower }">
							<c:set var="ischecked" scope="page" value="checked='checked'"></c:set>
						</c:if>
					</c:forEach>
						<input id="${power.id }" name="rolepower" ${ischecked } type="checkbox" value="${power.id }">${power.powerName }&nbsp
					
					<c:if test="${val.index%5==0 }">
						<br>
					</c:if>
				</c:forEach></td>
			</td>
		</tr>
		<tr>
			<td><input type="submit"></td>
			<td><input type="reset"></td>
		</tr>
	</table>
	</form>
</body>
</html>
