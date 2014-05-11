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

<title>My JSP 'menu.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link href="images/left.css" rel="stylesheet" type="text/css" />
<script src="js/prototype.lite.js" type="text/javascript"></script>
<script src="js/moo.fx.js" type="text/javascript"></script>
<script src="js/moo.fx.pack.js" type="text/javascript"></script>
<script type="text/javascript" src="js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="js/menu.js"></script>

</head>

<body>
	<div id="left">
		<div id="left_menu"></div>
		<div id="left_tree">
			<table width="100%" height="270" border="0" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="182" valign="top" >
						<!-- 系统管理 -->
						<h1 id="xtgl">
							<a href="javascript:void(0)">系统管理 </a>
						</h1>
						<div class="xtgl">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td><img src="images/menu_topline.gif" width="182"
										height="5" /></td>
								</tr>
							</table>
							<h1 id="yhgl" class="type">
								<a href="javascript:void(0)">用户管理</a>
							</h1>
							<div class="yhgl content">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td><img src="images/menu_topline.gif" width="182"
											height="5" /></td>
									</tr>
								</table>
								<c:forEach var="homeData" items="${sessionScope.homeData }">
									<c:if test="${homeData.powerColumn_name == '用户管理'}">
										<ul class="MM">
											<li><a href="${homeData.power_uri }" target="main">${homeData.power_name }</a></li>
										</ul>
									</c:if>
								</c:forEach>
							</div>
							<h1 id="jsgl" class="type">
								<a href="javascript:void(0)">角色管理</a>
							</h1>
							<div class="jsgll content">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td><img src="images/menu_topline.gif" width="182"
											height="5" /></td>
									</tr>
								</table>
								<c:forEach var="homeData" items="${sessionScope.homeData }">
									<c:if test="${homeData.powerColumn_name == '角色管理'}">
										<ul class="MM">
											<li><a href="${homeData.power_uri }" target="main">${homeData.power_name }</a></li>
										</ul>
									</c:if>
								</c:forEach>
							</div>
							<h1 id="qxgl" class="type">
								<a href="javascript:void(0)">权限管理</a>
							</h1>
							<div class="qxgl content">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td><img src="images/menu_topline.gif" width="182"
											height="5" /></td>
									</tr>
								</table>
								<c:forEach var="homeData" items="${sessionScope.homeData }">
									<c:if test="${homeData.powerColumn_name == '权限管理'}">
										<ul class="MM">
											<li><a href="${homeData.power_uri }?"
												+Math.random() target="main">${homeData.power_name }</a></li>
										</ul>
									</c:if>
								</c:forEach>
							</div>
							<h1 id="cdgl" class="type">
								<a href="javascript:void(0)">菜单管理</a>
							</h1>
							<div class="cdgl content">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td><img src="images/menu_topline.gif" width="182"
											height="5" /></td>
									</tr>
								</table>
								<c:forEach var="homeData" items="${sessionScope.homeData }">
									<c:if test="${homeData.powerColumn_name == '菜单管理'}">
										<ul class="MM">
											<li><a href="${homeData.power_uri }" target="main">${homeData.power_name }</a></li>
										</ul>
									</c:if>
								</c:forEach>
							</div>
							<h1 id="zhgl" class="type">
								<a href="javascript:void(0)">登陆账号管理</a>
							</h1>
							<div class="zhgl content">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td><img src="images/menu_topline.gif" width="182"
											height="5" /></td>
									</tr>
								</table>
								<c:forEach var="homeData" items="${sessionScope.homeData }">
									<c:if test="${homeData.powerColumn_name == '登陆账号管理'}">
										<ul class="MM">
											<li><a href="${homeData.power_uri }" target="main">${homeData.power_name }</a></li>
										</ul>
									</c:if>
								</c:forEach>
							</div>
						</div> <!-- 库存管理 -->
						<h1 id="kcgl" class="type">
							<a href="javascript:void(0)">库存管理</a>
						</h1>
						<div class="kcgl content">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td><img src="images/menu_topline.gif" width="182"
										height="5" /></td>
								</tr>
							</table>
							<c:forEach var="homeData" items="${sessionScope.homeData }">
								<c:if test="${homeData.powerColumn_name == '库存管理'}">
									<ul class="MM">
										<li><a href="${homeData.power_uri }" target="main">${homeData.power_name }</a></li>
									</ul>
								</c:if>
							</c:forEach>
						</div> <!-- 基础信息管理 -->
						<h1 id="jcxxgl">
							<a href="javascript:void(0)">基础信息管理</a>
						</h1>
						<div class="jcxxgl">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td><img src="images/menu_topline.gif" width="182"
										height="5" /></td>
								</tr>
							</table>
							<h1 id="hwgl" class="type">
								<a href="javascript:void(0)">货物管理</a>
							</h1>
							<div class="hwgl content">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td><img src="images/menu_topline.gif" width="182"
											height="5" /></td>
									</tr>
								</table>
								<c:forEach var="homeData" items="${sessionScope.homeData }">
									<c:if test="${homeData.powerColumn_name == '货物管理'}">
										<ul class="MM">
											<li><a href="${homeData.power_uri }" target="main">${homeData.power_name }</a></li>
										</ul>
									</c:if>
								</c:forEach>
							</div>
						</div> <!-- 购买销售管理 -->
						<h1 id="gmxsgl">
							<a href="javascript:void(0)">购买销售管理</a>
						</h1>
						<div class="gmxsgl">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td><img src="images/menu_topline.gif" width="182"
										height="5" /></td>
								</tr>
							</table>
							<h1 id="cggl" class="type">
								<a href="javascript:void(0)">采购管理</a>
							</h1>
							<div class="cggl content">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td><img src="images/menu_topline.gif" width="182"
											height="5" /></td>
									</tr>
								</table>
								<c:forEach var="homeData" items="${sessionScope.homeData }">
									<c:if test="${homeData.powerColumn_name == '采购管理'}">
										<ul class="MM">
											<li><a href="${homeData.power_uri }" target="main">${homeData.power_name }</a></li>
										</ul>
									</c:if>
								</c:forEach>
							</div>
							<h1 id="xsgl" class="type">
								<a href="javascript:void(0)">销售管理</a>
							</h1>
							<div class="xsgl content">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td><img src="images/menu_topline.gif" /></td>
									</tr>
								</table>
								<c:forEach var="homeData" items="${sessionScope.homeData }">
									<c:if test="${homeData.powerColumn_name == '销售管理'}">
										<ul class="MM">
											<li><a href="${homeData.power_uri }" target="main">${homeData.power_name }</a></li>
										</ul>
									</c:if>
								</c:forEach>
							</div>
						</div> <!-- 客户管理 -->
						<h1 id="khgl" class="type">
								<a href="javascript:void(0)">客户管理</a>
							</h1>
							<div class="khgl content">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td><img src="images/menu_topline.gif" width="182"
											height="5" /></td>
									</tr>
								</table>
								<c:forEach var="homeData" items="${sessionScope.homeData }">
									<c:if test="${homeData.powerColumn_name == '客户管理'}">
										<ul class="MM">
											<li><a href="${homeData.power_uri }" target="main">${homeData.power_name }</a></li>
										</ul>
									</c:if>
								</c:forEach>
							</div>
							<h1 id="zpgl" class="type">
								<a href="javascript:void(0)">照片管理</a>
							</h1>
							<div class="zpgl content">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td><img src="images/menu_topline.gif" width="182"
											height="5" /></td>
									</tr>
								</table>
								<c:forEach var="homeData" items="${sessionScope.homeData }">
									<c:if test="${homeData.powerColumn_name == '照片管理'}">
										<ul class="MM">
											<li><a href="${homeData.power_uri }" target="main">${homeData.power_name }</a></li>
										</ul>
									</c:if>
								</c:forEach>
							</div>
				</tr>
			</table>
		</div>
		<div id="tree_down"></div>
	</div>
	<script type="text/javascript">
		
	</script>
</body>
</html>
