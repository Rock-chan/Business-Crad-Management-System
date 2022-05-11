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
<title>注册页面</title>
<link href="static/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="static/js/jquery.min.js"></script>
<script type="text/javascript">
function checkUname() {
	//获取输入的值pname为id
	var uname = $("#uname").val();
	$.ajax({
		//发送请求的URL字符串
		url : "user/checkUname",
		//请求类型
		type : "post",
		//定义发送请求的数据格式为JSON字符串
		contentType : "application/json",
		//data表示发送的数据
		data : JSON.stringify({uname:uname}),
		//成功响应的结果
		success : function(obj){//obj响应数据
			if(obj == "no"){
				$("#isExit").html("<font color=red size=5>×</font>");
				alert("用户已存在，请修改！");
			}else{
				$("#isExit").html("<font color=green size=5>√</font>");
				alert("用户可用");
			}
		},
		//请求出错
		error:function(){
			alert("数据发送失败");
		}
	});
}
</script>
</head>
<body class="bg-info">
	<div class="container">
	  	<div class="bg-primary"  style="width:70%; height: 80px;padding-top: 5px;">
	       <h2 align="center">用户注册</h2>
	   </div>
		<br>
		<form:form action="user/register" method="post" cssClass="form-horizontal" modelAttribute="myUser">
			<div class="form-group has-success">
				<label class="col-sm-2 col-md-2 control-label">用户名</label>
				<div class="col-sm-4 col-md-4">
				<table style="width:100%">
						<tr>
							<td>
								<form:input  cssClass="form-control" id="uname"  onblur="checkUname()"  placeholder="请输入您的用户名" path="uname"/>
							</td>
					 		<td>
					 			<span id="isExit"></span>
					 		</td>
						</tr>
					</table>
				</div>
			</div>
			<div class="form-group has-success">
				  <label class="col-sm-2 col-md-2 control-label">密码</label>
				  <div class="col-sm-4 col-md-4">
				  	<form:password path="upwd" cssClass="form-control" placeholder="请输入密码"/>
				  </div>
			 </div>	
			<div class="form-group has-success">
				  <label class="col-sm-2 col-md-2 control-label">确认密码</label>
				  <div class="col-sm-4 col-md-4">
		  				<form:password path="reupwd" cssClass="form-control" placeholder="请输入确认密码"/>
				  </div>
			 </div>	
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-success" >注册</button>
					<button type="reset" class="btn btn-primary" >重置</button>
				</div>
			</div>
		</form:form>
	</div>
</body>
</html>