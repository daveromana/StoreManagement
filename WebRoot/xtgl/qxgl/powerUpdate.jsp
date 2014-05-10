<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	var name = document.getElementById("powername").value;
	var uri = document.getElementById("poweruri").value;
	if("" == name){
		alert("权限名称不可为空");
		return false;
	}else if("" == uri){
		alert("权限地址不可为空");
		return false;
	}
	return true;

}
$(function() {
	var x = '<%=request.getParameter("result")%>';
		if (x != "" && x != null) {
			if (x == "success")
				alert("修改成功!");
			else if (x == "failed")
				alert("修改错误！");
		}
	})
</script>
</head>

<body>
	<div style="float: right;">
		<font size="5">权限修改&nbsp<a href="Power!datagrid.do">返回</a></font>
	</div>
	<br>
	<form action="Power!edit.do?methodType=update" method="post"
		onsubmit="return check()">
		<table>
			<input type="hidden" name="id" value="${sessionScope.power.id }">
			<tr>
				<td>权限名</td>
				<td><input name="powername" id="powername" type="text"
					value="${sessionScope.power.powerName }"></td>
			</tr>
			<tr>
				<td>权限地址</td>
				<td><input type="text" name="poweruri" id="poweruri"
					value="${sessionScope.power.powerUri }"></td>
			</tr>
			<tr>
				<td>所属菜单</td>
				<td><select name="powersupermenu">
						<c:forEach var="menulist" items="${sessionScope.menuList }">
							<c:if
								test="${sessionScope.power.powerSuperMenu == menulist.powerColumnName }">
								<option value="${menulist.powerColumnName }" selected="selected">${menulist.powerColumnName }
							</c:if>
							<c:if
								test="${sessionScope.power.powerSuperMenu != menulist.powerColumnName }">
								<option value="${menulist.powerColumnName }">${menulist.powerColumnName }
							</c:if>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td>权限简单介绍</td>
				<td><input name="powercomment" type="text"
					value="${sessionScope.power.powerComment }"></td>
			</tr>
			<tr>
				<td><input type="submit"></td>
				<td><input type="reset"></td>
			</tr>
		</table>
	</form>
</body>
</html>
