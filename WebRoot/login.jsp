<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
<title>用户登录</title>
<link href="images/login.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.4.1.js"></script>
<script type="text/javascript" src="js/login.js"></script>
</head>
<body>

    <div id="login">
	
	     <div id="top">
		      <div id="top_left"><img src="images/login_03.gif" /></div>
			  <div id="top_center"></div>
		 </div>
		 
		 <div id="center">
		      <div id="center_left"></div>
			  <div id="center_middle">
			       <div id="user">用 户
			         <input type="text" id="uname" name="textfield" />
			       </div>
				   <div id="password">密   码
				     <input type="password" id="pwd" name="textfield2" />
				   </div>
				   <div id="loginerror" >
						<span>&nbsp提示：用户名或密码错误</span>
				   </div>
				   <div id="inputerror" >
						<span>&nbsp提示：请输入用户名或密码</span>
				   </div>
				   <div id="btn"><a id="loginbutton" href="#">登录</a><a href="#">清空</a></div>
			  
			  </div>
			  <div id="center_right"></div>		 
		 </div>
		 <div id="down">
		      <div id="down_left">
			      <div id="inf">
                       <span class="inf_text">版本信息</span>
					   <span class="copyright">仓储管理信息系统 2014 v1.0</span>
			      </div>
			  </div>
			  <div id="down_center"></div>		 
		 </div>

	</div>
</body>

</html>
