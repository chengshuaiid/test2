$(document).ready(function() {
	$("#categroy-info1").mouseover(function() {
		$("#nav_right1").css("display", "block");
		$("#nav_right1").mouseover(function() {
			$("#nav_right1").css("display", "block");
		});
		$("#nav_right1").mouseout(function() {
			$("#nav_right1").css("display", "none");
		});
	});
	$("#categroy-info1").mouseout(function() {
		$("#nav_right1").css("display", "none");
	});

	$("#categroy-info2").mouseover(function() {
		$("#nav_right2").css("display", "block");
		$("#nav_right2").mouseover(function() {
			$("#nav_right2").css("display", "block");
		});
		$("#nav_right2").mouseout(function() {
			$("#nav_right2").css("display", "none");
		});
	});
	$("#categroy-info2").mouseout(function() {
		$("#nav_right2").css("display", "none");
	});

	$("#categroy-info3").mouseover(function() {
		$("#nav_right3").css("display", "block");
		$("#nav_right3").mouseover(function() {
			$("#nav_right3").css("display", "block");
		});
		$("#nav_right3").mouseout(function() {
			$("#nav_right3").css("display", "none");
		});
	});
	$("#categroy-info3").mouseout(function() {
		$("#nav_right3").css("display", "none");
	});

	$("#categroy-info4").mouseover(function() {
		$("#nav_right4").css("display", "block");
		$("#nav_right4").mouseover(function() {
			$("#nav_right4").css("display", "block");
		});
		$("#nav_right4").mouseout(function() {
			$("#nav_right4").css("display", "none");
		});
	});
	$("#categroy-info4").mouseout(function() {
		$("#nav_right4").css("display", "none");
	});

	$("#categroy-info5").mouseover(function() {
		$("#nav_right5").css("display", "block");
		$("#nav_right5").mouseover(function() {
			$("#nav_right5").css("display", "block");
		});
		$("#nav_right5").mouseout(function() {
			$("#nav_right5").css("display", "none");
		});
	});
	$("#categroy-info5").mouseout(function() {
		$("#nav_right5").css("display", "none");
	});

	$("#categroy-info6").mouseover(function() {
		$("#nav_right6").css("display", "block");
		$("#nav_right6").mouseover(function() {
			$("#nav_right6").css("display", "block");
		});
		$("#nav_right6").mouseout(function() {
			$("#nav_right6").css("display", "none");
		});
	});
	$("#categroy-info6").mouseout(function() {
		$("#nav_right6").css("display", "none");
	});

	$("#dropdown").mouseover(function() {
		$("#dropdown-menu").css("display", "block");
		$("#dropdown-menu").mouseover(function() {
			$("#dropdown-menu").css("display", "block");
		});
		$("#dropdown-menu").mouseout(function() {
			$("#dropdown-menu").css("display", "none");
		});
	});
	$("#dropdown").mouseout(function() {
		$("#dropdown-menu").css("display", "none");
	});
});

window.onscroll = function() {
	var t = document.documentElement.scrollTop || document.body.scrollTop;
	var top_div = document.getElementById("shortcut");
	var navs = document.getElementById("navs");
	if (t >= 560) {
		navs.style.display = "inline";
	} else {
		navs.style.display = "none";
	}
}
function checkAll(){
	var pid = document.getElementsByName("pid");
	var price = document.getElementsByName("price");
	var sumPrice = document.getElementById("sumPrice");
	var count=0;
	for(var i=0;i<pid.length;i++){
		if(pid[i].checked){
			count=count+parseInt(price[i].value);
		}
	}
	sumPrice.innerHTML="¥"+count;
	if(count==0){
		clear_shopcart.disabled=true;
	}else{
		clear_shopcart.disabled=false;
	}
}
