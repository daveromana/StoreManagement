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
	var image = document.getElementsByName("image");
	function imageCheck() {
		for (var int = 0; int < image.length; int++) {
			image[int].checked = document.getElementById("imagecheck").checked;
		}
	}
	function check() {
		var count = 0;
		for (var int = 0; int < image.length; int++) {
			if(image[int].checked){
				count++;
			}
		}
		if(count > 0)
			document.getElementById("imagecheck").checked = true;
		else
			document.getElementById("imagecheck").checked = false;
	}
	function imageDownload(){
		var count = 0;
		var id;
		for (var int = 0; int < image.length; int++) {
			if(image[int].checked){
				count++;
				id = image[int].parentNode.nextSibling.nextSibling.firstChild.data;
			}
		}
		if(count == 1)
			location.href="Image!download.do?id="+id;
		else
			alert("请选择其中一项下载");
	}
	function imageDelete(){
		var count = 0;
		var id;
		for (var int = 0; int < image.length; int++) {
			if(image[int].checked){
				count++;
				id = image[int].parentNode.nextSibling.nextSibling.firstChild.data;
			}
		}
		if(count == 1){
			if(confirm("图片会被删除！ 确定要删除？"))
				location.href="Image!datagrid.do?methodType=delete&id="+id;
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
				alert("删除失败！");
		}
	})
</script>
<body>
	<div style="float: right;">
		<font size="5">图片管理</font>
	</div>
	<form action="Image!datagrid.do?methodType=query" method="post">
		图片上传者
	<input type="text" name="queryimagename" value="${sessionScope.queryImageName }">
	<input type="submit" value="过滤">
	<input type="button" value="取消" onclick="location.href='Image!datagrid.do'">
	</form>
	<input type="button" value="增加" onclick="location.href='Image!add.do'">
	<input type="button" value="下载" onclick="imageDownload()">
	<input type="button" value="删除" onclick="imageDelete()">
	<a href="Image!datagrid.do?current_page=1">首页</a> &nbsp
		<a
			href="Image!datagrid.do?current_page=<%=current_page > 1 ? current_page - 1 : current_page%>">上一页</a>
		&nbsp
		<a
			href="Image!datagrid.do?current_page=<%=current_page < maxCount ? current_page + 1 : current_page%>">下一页</a>
		&nbsp
		<a href="Image!datagrid.do?current_page=<%=maxCount%>">尾页</a>
	<table border="1" width="100%">
		<tr bgcolor="yellow">
		<td><input id="imagecheck" onclick="imageCheck()" type="checkbox"></td>
		<td>id</td>
		<td>图片</td>
		<td>上传时间</td>
		<td>上传者</td>
		</tr>
		<c:forEach var="image" items="${sessionScope.imageList }">
		<tr>
		<td><input name="image" onclick="check()" type="checkbox"></td>
		<td>${image.id }</td>
		<td><img width="100px" height="100px" src="${image.imgUrl }"></td>
		<td>${image.uploadDate }</td>
		<td>${image.imgUser }</td>
		</tr>
		</c:forEach>
	</table>
</body>
<script type="text/javascript">
//confirm("确定要删除？");
</script>
</html>
