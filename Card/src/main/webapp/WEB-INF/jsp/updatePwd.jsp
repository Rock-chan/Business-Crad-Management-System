<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="UTF-8">
<title>修改密码页面</title>
<link href="static/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-info">
	<div class="container">
	  	<div class="bg-primary"  style="width:70%; height: 80px;padding-top: 5px;">
	       <h2 align="center">密码修改</h2>
	   </div>
		<br>
		<form:form action="card/updatePwd" method="post" cssClass="form-horizontal" modelAttribute="myuser">
			<div class="form-group has-success">
				<label class="col-sm-2 col-md-2 control-label">用户名</label>
				<div class="col-sm-4 col-md-4">
					<form:input  cssClass="form-control" path="uname" disabled="true"/>
					<form:hidden path="id"/>
				</div>
			</div>
			<div class="form-group has-success">
				  <label class="col-sm-2 col-md-2 control-label">新密码</label>
				  <div class="col-sm-4 col-md-4">
				  	<form:password path="upwd" cssClass="form-control" placeholder="请输入新密码"/>
				  </div>
			 </div>	
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-success" >修改</button>
					<button type="reset" class="btn btn-primary" >重置</button>
				</div>
			</div>
		</form:form>
	</div>
</body>
</html>