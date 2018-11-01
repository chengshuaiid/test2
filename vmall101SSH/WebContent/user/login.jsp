<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="utf-8" />
		<title>登录</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css" />
		<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.js" type="text/javascript" charset="utf-8"></script>
		<script src="${pageContext.request.contextPath}/js/bootstrap.js" type="text/javascript" charset="utf-8"></script>
		<script src="${pageContext.request.contextPath}/js/login.js" type="text/javascript" charset="utf-8"></script>
	</head>

	<body style="overflow:-Scroll;overflow:hidden">
		<div>
			<div id="headers">
				<div class="logo">
					<div class="logo_img">
						<a href="IndexProductServlet"><img src="${pageContext.request.contextPath}/img/login/huaweiLogo.png" align="bottom" /></a>
					</div>
					<div class="logo_font">
						<b style="font-size: 18px; color: #CCCCCC;">|</b>
						<span style="font-size: 18px; color: #333333;">华为商城</span>
					</div>
				</div>
			</div>
			<div id="content">
				<div class="row">
					<div class="login">
						<div class="login_img col-sm-4 col-sm-offset-3">

						</div>
						<div class="login_content col-sm-5">
							<div class="login_header">
								<span><input type="button" id="span_login" value="账号登录" style="border: 0px;background-color: white;outline: none;"/></span>
								<span class="quick_login" style="border-left: 1px solid #CCCCCC;"><input type="button" id="span_quick_login" value="快速登录" style="border: 0px;background-color: white;outline: none;"/></span>
							</div>
							<div class="login_form">
								<form action="${pageContext.request.contextPath }/user/user_login" id="loginform" method="post" onsubmit="return (CCodeBack() && loginCheck())">
									<table>
										<tr style="height:30px;"><th><span id="accout_span"></span></th></tr>
										<tr>
											<th><input type="text" id="username" placeholder="用户名" name="username" style="height: 35px; width: 300px;" /></th>
										</tr>
										<!-- <tr style="height: 20px;">
											<th><span id="username_span"></span></th>
										</tr> -->
										<tr>
											<th><input type="password" id="password" placeholder="密码" name="password" style="height: 35px; width: 300px;" /></th>
										</tr>
										<tr style="height: 40px;">
											<th><input type="checkbox" name="remenber"/>记住密码<br/></th>
										</tr>
										<tr style="height: 40px;">
											<th>验证码：<img src="ValCodeServlet" onclick="this.src=this.src+'?'" name="VCimg" id="VCimg">
											&nbsp;&nbsp;<input type="button" value="看不清，换一张" onclick="document.getElementById('VCimg').src=document.getElementById('VCimg').src+'?'"/></th>
										</tr>
										<tr>
											<th><input type="text" id="VCInput" name="VCInput" onblur="checkCode()"/>&nbsp;&nbsp;<span id="vcinputResult"></span></th>
										</tr>
										<tr>
											<th><input type="submit" value="登录" id="login_submit" style="width: 300px;height:44px;border: 0px;border-radius:26px;background-color: #B40707;font-size: 14px;color: white;outline: none;" /></th>
										</tr>
										<tr>
											<th><span style="display: inline-block;text-align: center;color: #CCCCCC;float: right;cursor: pointer;"><a href="register.jsp" style="text-decoration: none;">注册账号</a>|<a style="text-decoration: none;">忘记密码？</a></span></th>
										</tr>
									</table>
								</form>
							</div>
							<div class="login_quick col-sm-5" style="display: none;">
								<div class="login_quick_img"><img src="img/login/getqrWeb.png" /></div>
								<div class="login_quick_text">使用华为移动服务APP扫一扫<br />华为手机可进入[设置]-[华为帐号]扫码登录</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div id="foot">(c) Copyright 2017 13103. All Rights Reserved. </div>
		</div>
		${actionMessages[0]}
	</body>
</html>