<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="static/css/bootstrap.min.css" />
</head>
<body>
	<!-- 加载header.jsp -->
	<div>
		<jsp:include page="header.jsp"></jsp:include>
	</div>
	<br><br><br>
	<div class="container">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title">名片详情</h3>
			</div>
			<div class="panel-body">
				<div class="table table-responsive">
					<table class="table table-bordered table-hover">
						<tbody class="text-center">
							<tr>
								<th>姓名</th>
								<td>${card.name}</td>
							</tr>
							<tr>
								<th>电话</th>
								<td>${card.telephone}</td>
							</tr>
							<tr>
								<th>E-Mail</th>
								<td>${card.email}</td>
							</tr>
							<tr>
								<th>单位</th>
								<td>${card.company}</td>
							</tr>
							<tr>
								<th>职位</th>
								<td>${card.post}</td>
							</tr>
							<tr>
								<th>地址</th>
								<td>${card.address}</td>
							</tr>																																										
							<tr>
								<th>照片</th>
								<td>
									<img src="static/images/${card.logoName}"
									style="height: 50px; width: 50px; display: block;">
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>