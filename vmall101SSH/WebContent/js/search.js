$(document).ready(function() {
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
	/*$("#default_order").click(function() {
		$("#default_order").css("color", "red");
		$("#time_order").css("color", "black");
		$("#hot_order").css("color", "black");
		$("#price_order").css("color", "black");
	});
	$("#time_order").click(function() {
		$("#time_order").css("color", "red");
		$("#default_order").css("color", "black");
		$("#hot_order").css("color", "black");
		$("#price_order").css("color", "black");
	});
	$("#hot_order").click(function() {
		$("#hot_order").css("color", "red");
		$("#default_order").css("color", "black");
		$("#time_order").css("color", "black");
		$("#price_order").css("color", "black");
	});
	$("#price_order").click(function() {
		$("#price_order").css("color", "red");
		$("#default_order").css("color", "black");
		$("#time_order").css("color", "black");
		$("#hot_order").css("color", "black");
	});*/
});
function checkAll(){
	var pid = document.getElementsByName("pid");
	var price = document.getElementsByName("price");
	var sumPrice = document.getElementById("sumPrice");
	var clear_shopcart = document.getElementById("clear_shopcart");
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
function priceOrder(price,keyWord){
	var price_order = document.getElementsByName("price_order");
	alert("价格："+price+" 关键字："+keyword);
	if("1"==price){
		price_order.href="ProductSearchServlet?keyWord="+keyWord+"&price=2";
	}else if("2"==price){
		price_order.href="ProductSearchServlet?keyWord="+keyWord+"&price=1";
	}
	
}