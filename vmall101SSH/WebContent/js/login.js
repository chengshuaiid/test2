var xhr = new XMLHttpRequest();
function checkCode(){
	var VCInput = document.getElementById("VCInput");
	var vcinputResult = document.getElementById("vcinputResult");
	if(VCInput.value.length==0){
		return false;
	}
	if(VCInput.value.length!=4){
		return false;
		vcinputResult.innerHTML="<font color='red'><b>验证码不正确!</b></font>";
	}else{
		xhr.open("get", "CheckValCodeServlet?time="+new Date()+"&VCInput="+encodeURI(VCInput.value),true); //?,&符号一个不能少
		xhr.send(null);
		xhr.onreadystatechange = CCodeBack;
	}
}
function CCodeBack(){
	if(xhr.readyState==4 && xhr.status==200){
		var vcinputResult = document.getElementById("vcinputResult");
		if(xhr.responseText=="yes"){
			vcinputResult.innerHTML = "<font color='green'><b>验证码正确！</b></font>";
			return true;
		}else{
			vcinputResult.innerHTML = "<font color='red'><b>验证码不正确！</b></font>";
			return false;
		}
	}
	return false;
}
$(document).ready(function() {
	$("#span_login").click(function() {
		$("#span_login").css("color", "red");
		$("#span_quick_login").css("color", "black");
		$(".login_quick").css("display", "none");
		$(".login_form").css("display", "block");
	});
	$("#span_quick_login").click(function() {
		$("#span_login").css("color", "black");
		$("#span_quick_login").css("color", "red");
		$(".login_form").css("display", "none");
		$(".login_quick").css("display", "block");
	});
	$("#username").blur(function(){
		var username = $("#username").val();
		if (username=="") {
			$("#accout_span").css("color","red");
			$("#accout_span").html("用户名不能为空！");
		}else{
			$("#accout_span").html("");
		}
	});
	$("#password").blur(function(){
		var pwd = $("#password").val();
		if (pwd=="") {
			$("#accout_span").css("color","red");
			$("#accout_span").html("请输入密码!");
		}else{
			$("#accout_span").html("");
		}
	});
	/*$("#login_submit").click(function() {
		var username = $("#username").val();
		var pwd = $("#pwd").val();
		if(username == "" || pwd == "") {
			alert("用户名或密码不能为空！");
		}else{
			$("#loginform").attr('action', 'index.html');
		}
	});*/
//	$(".login_check").click(function(){
//		$(".login_check").load("#login_check");
//	});
});

function loginCheck(){
	var username = document.getElementById("username");
	var pwd = document.getElementById("password");
	var VCInput = document.getElementById("VCInput");
	if(username.value == "" || pwd.value == "" || VCInput.value == ""){
		return false;
	}
	return true;
	
}