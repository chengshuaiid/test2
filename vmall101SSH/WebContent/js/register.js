var xhr = new XMLHttpRequest();
var xhrt = new XMLHttpRequest();
var xhrs = new XMLHttpRequest();
function checkName(){
	var username = document.getElementById("username");
	var userspan = document.getElementById("username_span");
	if(username.value.length<3 || username.value.length>10){
		userspan.innerHTML="<font color='red'><b>用户名长度不合法!</b></font>";
		return;
	}else{
		xhr.open("get", "user_checkusername?time="+new Date()+"&username="+encodeURI(username.value),true); 
		xhr.send(null);
		xhr.onreadystatechange = CUServletBack;
	}
}
function CUServletBack(){
	if(xhr.readyState==4 && xhr.status==200){
		var userspan = document.getElementById("username_span");
		if(xhr.responseText=="yes"){
			userspan.innerHTML = "";
			return true;
		}else{
			userspan.innerHTML = "<font color='red'><b>用户名已经被注册！</b></font>";
			return false;
		}
		return false;
	}
}
function checkCode(){
	var VCInput = document.getElementById("VCInput");
	var vcinputResult = document.getElementById("vcinputResult");
	if(VCInput.value.length==0){
		return;
	}
	if(VCInput.value.length!=4){
		vcinputResult.innerHTML="<font color='red'><b>验证码不正确!</b></font>";
	}else{
		xhrt.open("get", "ValCodeAction?time="+new Date()+"&VCInput="+encodeURI(VCInput.value),true); //?,&符号一个不能少
		xhrt.send(null);
		xhrt.onreadystatechange = CCodeBack;
	}
}
function CCodeBack(){
	if(xhrt.readyState==4 && xhrt.status==200){
		var vcinputResult = document.getElementById("vcinputResult");
		if(xhrt.responseText=="yes"){
			vcinputResult.innerHTML = "<font color='green'><b>验证码正确！</b></font>";
			return true;
		}else{
			vcinputResult.innerHTML = "<font color='red'><b>验证码不正确！</b></font>";
			return false;
		}
	}
	return false;
}

function checkEmail(){
	/*var reg=/^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/;*/
	var reg=/^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
	var email = document.getElementById("email");
	var email_span = document.getElementById("email_span");
	if(email.value==""){
		email_span.innerHTML="<font color='red'><b>请输入你的邮箱号!</b></font>";
		return;
	}
	if(!reg.test(email.value)){
		email_span.innerHTML="<font color='red'><b>邮箱号不合法!</b></font>";
	}else{
		xhrs.open("get", "user_checkemail?time="+new Date()+"&email="+encodeURI(email.value),true); 
		xhrs.send(null);
		xhrs.onreadystatechange = CEservletback;
	}
}
function CEservletback(){
	if(xhrs.readyState==4 && xhrs.status==200){
		var email_span = document.getElementById("email_span");
		if(xhrs.responseText=="yes"){
			email_span.innerHTML = "";
			return true;
		}else{
			email_span.innerHTML = "<font color='red'><b>邮箱已被注册！</b></font>";
			return false;
		}
	}
	return false;
}

$(document).ready(function(){
	/*$("#username").blur(function(){
		var username = $("#username").val();
		if (username=="") {
			$("#username_span").html("请输入用户名！");
		}else{
			$("#username_span").html("");
		}
	});*/
	$("#password").blur(function(){
		var pwd = $("#password").val();
		if (pwd=="") {
			$("#pwd_span").html("请输入密码!");
		}else if(pwd.length<8||pwd.length>32){
			$("#pwd_span").html("请设置密码长度8~32位！");
		}else{
			$("#pwd_span").html("");
		}
	});
	$("#repwd").blur(function(){
		var pwd = $("#password").val();
		var repwd = $("#repwd").val();
		if (pwd!="" && repwd=="") {
			$("#repwd_span").html("请确认密码!");
		}else if(repwd!=pwd){
			$("#repwd_span").html("密码必须一致！");
		}else{
			$("#repwd_span").html("");
		}
	});
	/*$("#email").blur(function(){
		var email = $("#email").val();
		if (email=="") {
			$("#email_span").html("请输入你的邮箱号！");
		}else{
			var reg = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
			if(!reg.test(email)){
				$("#email_span").html("不存在的邮箱号！");
			}
			$("#email_span").html("");
		}
	});*/
	$("#pci_frame").blur(function(){
		var pci_frame = $("#pci_frame").val();
		if (pci_frame.length>0 && pci_frame.length!=4) {
			$("#email_span").html("邮箱验证码错误!");
		}else{
			$("#email_span").html("");
		}
	});
}); 
function checkRegister(){
	var reg = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
	var username = document.getElementById("username");
	var pwd = document.getElementById("password");
	var repwd = document.getElementById("repwd");
	var email = document.getElementById("email");
	var pci_frame = document.getElementById("pci_frame");
	if(pwd.value=="" || repwd.value=="" || pci_frame.value==""){
		return false;
	}
	if(!pwd.value==repwd.value){
		return false;
	}
	if(!reg.test(email.value)){
		return false;
	}
	if(pci_frame.value.length!=4){
		return false;
	}
	return true;
	
}