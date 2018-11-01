<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="UTF-8">
		<title>注册</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/register.css"/>
		<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.js" type="text/javascript" charset="utf-8"></script>
		<script src="${pageContext.request.contextPath}/js/bootstrap.js" type="text/javascript" charset="utf-8"></script>
		<script src="${pageContext.request.contextPath}/js/register.js" type="text/javascript" charset="utf-8"></script>
	</head>

	<body>
		<div id="headers">
			<div class="logo">
				<div class="logo_img">
					<a href="IndexProductServlet"><img src="${pageContext.request.contextPath}/img/register/head-top.png" align="bottom" /></a>
				</div>
				<div class="logo_font">
					<span style="font-size: 18px; color: #CCCCCC;margin-left: 12px;margin-right: 6px;">|</span>
					<span style="font-size: 18px; color: white;">华为商城</span>
				</div>
			</div>
		</div>
		<div class="container">
			<div class="nav" style="margin-left: 120px;">
				<h3><b>欢迎注册华为商城</b></h3>
				<a href="login.jsp" style="float: right;text-decoration: none;">已有账号，登录></a>
			</div>
			<div class="content">
				<form action="${pageContext.request.contextPath }/user/user_register" method="post" onsubmit="return (checkRegister() && CUServletBack() && CCodeBack() && CEservletback()) ">
					<table border="0px" cellspacing="" cellpadding="" align="center">
						<tr>
							<th>
								<div class="input-group">
									<div class="input-group-btn">
										<button type="button" class="btn btn-default from-button-border" style="height:50px;width:124px;outline: none;" >
											国&nbsp;&nbsp;&nbsp;家 
										</button>
									</div>
									<input type="button" class="form-control from-text-border" value="中国" style="height: 50px;width: 298px"/>
								</div>
							</th>
						</tr>
						<tr>
							<th>
								<div class="input-group">
									<div class="input-group-btn">
										<button type="button" class="btn btn-default from-button-border" style="height:50px;width:124px;outline: none;" >
											用户名 
										</button>
									</div>
									<input type="text" class="form-control from-text-border" id="username" name="username" onblur="checkName()" placeholder="请输入用户名" style="height: 50px;width: 298px"/>
								</div>
							</th>
						</tr>
						<tr style="height: 20px;">
							<th><span id="username_span"></span></th>
						</tr>
						<tr>
							<th>
								<div class="input-group">
									<div class="input-group-btn">
										<button type="button" class="btn btn-default from-button-border" style="height:50px;width:124px;outline: none;" >
											登录密码
										</button>
									</div>
									<input type="password" class="form-control from-text-border" id="password" name="password" placeholder="登录密码" style="height: 50px;width: 298px"/>
								</div>
							</th>
						</tr>
						<tr style="height: 20px;">
							<th><span id="pwd_span"></span></th>
						</tr>
						<tr>
							<th>
								<div class="input-group">
									<div class="input-group-btn">
										<button type="button" class="btn btn-default from-button-border" style="height:50px;width:124px;outline: none;" >
											确认密码
										</button>
									</div>
									<input type="password" class="form-control from-text-border" id="repwd" name="repwd" placeholder="确认密码" style="height: 50px;width: 298px"/>
								</div>
							</th>
						</tr>
						<tr style="height: 20px;">
							<th><span id="repwd_span"></span></th>
						</tr>
						<tr>
							<th>
								<div class="input-group">
									<div class="input-group-btn">
										<button type="button" class="btn btn-default from-button-border" style="height:50px;width:124px;outline: none;" >
											图形验证码
										</button>
									</div>
									<input type="text" class="form-control from-text-border" id="VCInput" name="VCInput" style="height: 50px;width: 204px" onblur="checkCode()"/>
									&nbsp;<img src="ValCodeServlet" onclick="this.src=this.src+'?'" name="VCimg" id="VCimg" style="backgroud-color:#C0C0C0;height:50px;width:90px;">
								</div>
							</th>
						</tr>
						<tr style="height:20px;">
							<th><span id="vcinputResult"></span></th>
						</tr>
						<tr>
							<th>
								<div class="input-group">
									<div class="input-group-btn">
										<button type="button" class="btn btn-default from-button-border" style="height:50px;width:124px;outline: none;" >
											邮箱&nbsp;<span class="caret"></span> 
										</button>
									</div>
									<input type="text" class="form-control from-text-border" id="email" name="email" onblur="checkEmail()" placeholder="请输入邮箱" style="height: 50px;width: 298px"/>
								</div>
							</th>
						</tr>
						<tr style="height: 20px;">
							<th><span id="email_span"></span></th>
						</tr>
						<tr>
							<th>
								<div class="input-group">
									<div class="input-group-btn">
										<button type="button" class="btn btn-default from-button-border" style="height:50px;width:124px;outline: none;" >
											邮箱验证码
										</button>
									</div>
									<input type="text" class="form-control" id="pci_frame" name="" style="height: 50px;width: 190px;background-color:#F7F7F7;border-right: 0px;" onblur=""/>
									<div class="input-group-btn">
										<button type="button" id="phone_ver_code" class="btn btn-default" style="height:50px;width:110px;color:blue;background-color:#F7F7F7;border-left:0px;border-top-right-radius:2em;border-bottom-right-radius:2em;outline: none;" >
											获取验证码
										</button>
									</div>
								</div>
							</th>
						</tr>
						<tr>
							<th style="text-align: center;"><input type="submit" value="注册" id="register_submit" style="width: 150px;height:44px;border: 0px;border-radius:26px;background-color: #B40707;font-size: 14px;color: white;outline: none;" /></th>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<hr>
		<div id="foot">(c) Copyright 2017 13103. All Rights Reserved. </div>
		${actionMessages[0]}
		<!-- <div class="foot">
			<a id="foot_EULA" href="https://hwid1.vmall.com/CAS/portal/agreements/userAgreement/zh-cn_userAgreement.html?version=china" class="rule" target="_blank">用户协议</a><em class="foot_em">|</em>
			<a id="foot_privacy" href="https://hwid1.vmall.com/CAS/portal/agreements/userPrivacyPolicy/zh-cn_userPrivacyPolicy.html?version=europe" class="rule" target="_blank">隐私政策</a><a href="#" target="_blank"><em style="font-style: normal">|</em> </a>
			<a style="padding:0 10px;" target="blank" href="https://hwid1.vmall.com/CAS/portal/faq/faq.html">常见问题</a>
		</div> -->
	</body>

</html>