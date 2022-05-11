<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>首页面</title>
</head>
<body>
	<c:if test="${mymessage == 'noLogin'}">
		<h2>没登录，您没有权限访问，请<a href="user/toLogin">登录</a>！</h2>
	</c:if>
	<c:if test="${mymessage == 'noError}">
		<h2>服务器内部错误或资源不存在</h2>
	</c:if>
</body>
</html>