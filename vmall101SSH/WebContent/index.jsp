<%@page import="com.etc.vmall101.bean.User"%>
<%@page import="com.etc.vmall101.util.CookieUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/myfunctions" prefix="myfn" %>
<%
	request.setCharacterEncoding("utf-8");
	response.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>华为商城官网</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="css/index.css" />
<script type="text/javascript" src="js/jquery-1.11.3.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/index.js"></script>
<link rel="stylesheet" type="text/css" href="css/alltopbottom.css" />
</head>
<body style="overflow-x: hidden">
<%-- <%
	String userinfo = CookieUtil.queryCookie("userinfo",request);
	if(session.getAttribute("user")==null && userinfo!=null){
		String[] userArr = userinfo.split("#");
		request.setAttribute("username", userArr[0]);
		request.setAttribute("password", userArr[1]);
		request.setAttribute("remenber", "no");
		request.getRequestDispatcher("UserLoginServlet").forward(request,response);	
	}
%> --%>
	<ul id="navs">
		<li>
			<a href="#section-1">热销单品</a>
		</li>
		<li>
			<a href="#section-2">手机</a>
		</li>
		<li>
			<a href="#section-3">智能家居</a>
		</li>
	</ul>
	<div id="shortcut">
		<div class="shortcut_nav" style="min-width: 1200px;">
			<div class="shortcut_nav_left">
				<ul>
					<li><a href="">华为官网</a></li>
					<li><span>|</span></li>
					<li><a href="">荣耀官网</a></li>
					<li><span>|</span></li>
					<li><a href="">花粉俱乐部</a></li>
					<li><span>|</span></li>
					<li><a href="">帮助中心</a></li>
					<li><span>|</span></li>
					<li><a href="">V码(优购码)</a></li>
					<li><span>|</span></li>
					<li><a href="">Select Region</a></li>
					<li><span>|</span></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"> 更多精彩<b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="#">EMUI</a></li>
							<li><a href="#">应用市场</a></li>
							<li><a href="#">云服务</a></li>
							<li><a href="#">开发者联盟</a></li>
						</ul>
					</li>
				</ul>
			</div>
			<div class="shortcut_nav_right">
				<ul>
					<c:if test="${empty sessionScope.user}">
						<li><a href="register.jsp">请注册</a></li>
						<li><span>&nbsp;</span></li>
						<li><a href="login.jsp">登录</a></li>
					</c:if>
					<c:if test="${not empty sessionScope.user}">
						<li><a href="">${sessionScope.user.uname }</a></li>
						<li><span>&nbsp;</span></li>
						<li><a href="UserLogoutServlet">注销</a></li>
					</c:if>
					<li><span>|</span></li>
					<li><a href="">我的订单</a></li>
					<li><span>|</span></li>
					<li class="dropdown" id="dropdown"><a href="ShowShopcartServlet" id="dropdownli" class="dropdown-toggle"  onmouseover="checkAll()"> 
						<span class="glyphicon glyphicon-shopping-cart"></span>购物车<b class="caret"></b></a>
						<ul class="dropdown-menu" id="dropdown-menu" style="left:-370px;top:18px;">
							<c:if test="${not empty sessionScope.shopCartList }">
								<c:forEach items="${sessionScope.shopCartList }" var="scInfo">
									<li class="shopcart_show">
										<div class="shopcart_box"><input type="checkbox" name="pid" checked="checked" value="${scInfo.product.pid }" onclick="checkAll()"/></div>
										<div class="shopcart_img"><img src="/upload/middle/${myfn:getImgName(scInfo.product.image) }" width="100%"></div>
										<input type="hidden" value="${scInfo.product.price }" id="proPrice" name="proPrice"/>
										<div class="shopcart_details">
											<div class="p_name">
												<a href="#"><font size="3px" color="black">${scInfo.product.pname }&nbsp;${scInfo.product.stand }&nbsp;(${scInfo.product.color })</font></a>
											</div>
											<div class="p_dec">
												<span>${scInfo.product.color }</span>
											</div>
											<div class="p_status">
												<span style="float: right;margin-right: -30px;"><font size="2px" color="black">¥</font><input type="text" readonly="readonly" style="border:0px;width:25%;font-size: 10px;color:black;" name="price" value="${scInfo.product.price.intValue() }"/>&nbsp;&nbsp;&nbsp;&nbsp;<font>X${scInfo.count }</font></span>
											</div>
										</div>
									</li>
								</c:forEach>
								
								<li class="stop_sc_li">
									<div class="ssl_left">
										<font color="black">总计：</font><br/><font color="red" size="5px" id="sumPrice">¥0.00</font>
									</div>
									<div class="ssl_right">
										<input type="button" value="结算" class="stop_shopcart"  id="clear_shopcart"/>
									</div>
								</li>
							</c:if>
							<c:if test="${empty sessionScope.shopCartList }">
								<div class="shopcart_prompt_one">
									<img src="img/universal/shopcart.png" width="100px"/>
								</div>
								<div class="shopcart_prompt_two">
									<font size="">您的购物车是空的，赶紧选购吧~</font>
								</div>
							</c:if>
						</ul>
					</li>
				</ul>
			</div>
		</div>
	</div>
	<div class="container" id="header">
		<div class="headerline" style="min-width: 1200px;">
			<div class="header_left">
				<div class="header_left_logo">
					<a href="IndexProductServlet"><img src="img/index/logo20170801113951.png" /></a>
				</div>
				<div class="header_left_ul">
					<ul>
						<li><a href="">华为专区</a></li>
						<li><a href="">荣耀专区</a></li>
						<li><a href="">HUAWEI P10</a></li>
						<li><a href="">荣耀9</a></li>
						<li><a href="">麦芒6首销</a></li>
						<li><a href="">荣耀嘉年华</a></li>
					</ul>
				</div>
			</div>
			<div class="header_right">
				<form action="ProductSearchServlet" method="post" style="border: 0px;border-bottom: 1px solid #A4A4A4;float: left;line-height: 40px;">
					<input type="text" id="keyWord" name="keyWord" value="${requestScope.keyWord }"/>
					<a href="ProductSearchServlet?keyWord=HUAWEI Mate 9" id="hrf_link">HUAWEI Mate 9</a>&nbsp;
					<button type="submit" class="btn-default" id="proSearch">
						<span class="glyphicon glyphicon-search"></span> Start
					</button>
				</form>
			</div>
		</div>
	</div>
	<div id="nav" class="container">
		<div class="lunbo">
			<div class="row headers">
				<div id="lunbo1" class="carousel slide" style="width: 100%;" data-ride="carousel" data-interval="2000">
					<ol class="carousel-indicators">
						<li data-target="#lunbo1" data-slide-to="0" class="active"></li>
						<li data-target="#lunbo1" data-slide-to="1"></li>
						<li data-target="#lunbo1" data-slide-to="2"></li>
						<li data-target="#lunbo1" data-slide-to="3"></li>
						<li data-target="#lunbo1" data-slide-to="4"></li>
						<li data-target="#lunbo1" data-slide-to="5"></li>
					</ol>
					<div class="carousel-inner">
						<div class="item active">
							<a href="#"> <img src="img/index/FHeader_1.jpg"width="100%" style="min-height: 450px;min-width: 100%;" />
								<div class="carousel-caption">我是第一张图片</div>
							</a>
						</div>
						<div class="item">
							<a href="#"> <img src="img/index/FHeader_2.jpg" width="100%" style="min-height: 450px;min-width: 100%;" />
								<div class="carousel-caption">我是第2张图片</div>
							</a>
						</div>
						<div class="item">
							<a href="#"> <img src="img/index/FHeader_3.jpg" width="100%" style="min-height: 450px;min-width: 100%;" />
								<div class="carousel-caption">我是第3张图片</div>
							</a>
						</div>
						<div class="item">
							<a href="#"> <img src="img/index/FHeader_4.jpg" width="100%" style="min-height: 450px;min-width: 100%;" />
								<div class="carousel-caption">我是第4张图片</div>
							</a>
						</div>
						<div class="item">
							<a href="#"> <img src="img/index/FHeader_5.jpg" width="100%" style="min-height: 450px;min-width: 100%;" />
								<div class="carousel-caption">我是第5张图片</div>
							</a>
						</div>
						<div class="item">
							<a href="#"> <img src="img/index/FHeader_6.jpg" width="100%" style="min-height: 450px;min-width: 100%;" />
								<div class="carousel-caption">我是第6张图片</div>
							</a>
						</div>
					</div>
					<a class="carousel-control left" href="#lunbo1" data-slide="prev"
						style="font-size: 0px;">&lsaquo;</a> <a
						class="carousel-control right" href="#lunbo1" data-slide="next"
						style="font-size: 0px;">&rsaquo;</a>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="nav_group">
				<div class="nav_content">
					<div class="categroy-info" id="categroy-info1">
						<span class="categroy-info-span"><a href="ProductSearchServlet?keyWord=手机">手机</a></span><br />
						<span id="" class="categroy-info-span2"><a href="">荣耀</a></span> 
						<span id="" class="categroy-info-span2"><a href="">HUAWEI P系列</a></span> 
						<span id="" class="categroy-info-span3">></span>
					</div>
					<div class="categroy-info" id="categroy-info2">
						<span class="categroy-info-span"><a href="">笔记本 & 平板</a></span><br />
						<span id="" class="categroy-info-span2"><a href="">平板电脑</a></span>
						<span id="" class="categroy-info-span2"><a href="">笔记本电脑</a></span>
						<span id="" class="categroy-info-span3">></span>
					</div>
					<div class="categroy-info" id="categroy-info3">
						<span class="categroy-info-span"><a href="">穿戴</a></span><br /> 
						<span id="" class="categroy-info-span2"><a href="">手表</a></span> 
						<span id="" class="categroy-info-span2"><a href="">手环</a></span> 
						<span id="" class="categroy-info-span3">></span>
					</div>
					<div class="categroy-info" id="categroy-info4">
						<span class="categroy-info-span"><a href="">智能家居</a></span><br />
						<span id="" class="categroy-info-span2"><a href="">子母路由</a></span>
						<span id="" class="categroy-info-span2"><a href="">电视盒子</a></span>
						<span id="" class="categroy-info-span2"><a href="">路由器</a></span>
						<span id="" class="categroy-info-span3">></span>
					</div>
					<div class="categroy-info" id="categroy-info5">
						<span class="categroy-info-span"><a href="">通用配件</a></span><br />
						<span id="" class="categroy-info-span2"><a href="">移动电源</a></span>
						<span id="" class="categroy-info-span2"><a href="">耳机</a></span> <span
							id="" class="categroy-info-span2"><a href="">音响</a></span> <span
							id="" class="categroy-info-span3">></span>
					</div>
					<div class="categroy-info" id="categroy-info6">
						<span class="categroy-info-span"><a href="">专属配件</a></span><br />
						<span id="" class="categroy-info-span2"><a href="">保护壳</a></span>
						<span id="" class="categroy-info-span2"><a href="">保护套</a></span>
						<span id="" class="categroy-info-span2"><a href="">贴膜</a></span> 
						<span id="" class="categroy-info-span3">></span>
					</div>
				</div>
				
				<div class="nav_right_group">
					<div class="nav_right" id="nav_right1">
						<div class="nav_right_first">
							<div class="nav_right_span">
								<span class="nav_right_span1"><a href="ProductSearchServlet?keyWord=手机">浏览手机频道</a></span>
								<span class="nav_right_span2"><a href="ProductSearchServlet?keyWord=手机">></a></span>
							</div>
						</div>
						<div class="nav_right_second">
							<ul>
								<li><a href="">荣耀</a></li>
								<li><a href="">HUAWEI P系列</a></li>
								<li><a href="">畅玩</a></li>
								<li><a href="">HUAWEI Mate系列</a></li>
								<li><a href="">HUAWEI nova系列</a></li>
								<li><a href="">HUAWEI 麦芒系列</a></li>
								<li><a href="">华为畅享系列</a></li>
								<li><a href="">合约机</a></li>
							</ul>
						</div>
						<div class="nav_right_three">
							<div class="row">
								<c:set var="navPhonePros" value="${requestScope.navPhonePros }"></c:set>
								<c:forEach var="i" begin="0" end="3" step="1">  
									<div class="col-md-3 phone_flagship" align="center">
										<div class="nav_c_img">
											<a href="ProductQueryServlet?pid=${navPhonePros.get(i).pid }"><img src="/upload/middle/${myfn:getImgName(navPhonePros.get(i).image) }" width="85%" height="85%" /></a>
										</div>
										<div class="nav_c_name">${navPhonePros.get(i).pname }</div>
										<div class="nav_c_price">¥${navPhonePros.get(i).price.intValue() }</div>
									</div>
								</c:forEach>
							</div>
						</div>
					</div>

					<div class="nav_right" id="nav_right2">
						<div class="nav_right_first">
							<div class="nav_right_span">
								<span class="nav_right_span1"><a href="">浏览笔记本 & 平板频道</a></span><span
									class="nav_right_span2"><a href="">></a></span>
							</div>
						</div>
						<div class="nav_right_second">
							<ul>
								<li><a href="">平板电脑</a></li>
								<li><a href="">笔记本电脑</a></li>
								<li><a href="">笔记本配件</a></li>
							</ul>
						</div>
						<div class="nav_right_three">
							<div class="row">
								<c:set var="navComputerPros" value="${requestScope.navComputerPros }"></c:set>
								<c:forEach var="i" begin="0" end="3" step="1">  
									<div class="col-md-3 phone_flagship" align="center">
										<div class="nav_c_img">
											<a href="ProductQueryServlet?pid=${navComputerPros.get(i).pid }"><img src="/upload/middle/${myfn:getImgName(navComputerPros.get(i).image) }" width="85%" height="85%" /></a>
										</div>
										<div class="nav_c_name">${navComputerPros.get(i).pname }</div>
										<div class="nav_c_price">¥${navComputerPros.get(i).price.intValue() }</div>
									</div>
								</c:forEach>
							</div>
						</div>
					</div>

					<div class="nav_right" id="nav_right3">
						<div class="nav_right_first">
							<div class="nav_right_span">
								<span class="nav_right_span1"><a href="">浏览穿戴频道</a></span><span
									class="nav_right_span2"><a href="">></a></span>
							</div>
						</div>
						<div class="nav_right_second">
							<ul>
								<li><a href="">手环</a></li>
								<li><a href="">手表</a></li>
							</ul>
						</div>
						<div class="nav_right_three">
							<div class="row">
								<c:set var="navWearPros" value="${requestScope.navWearPros }"></c:set>
								<c:forEach var="i" begin="0" end="3" step="1">  
									<div class="col-md-3 phone_flagship" align="center">
										<div class="nav_c_img">
											<a href="ProductQueryServlet?pid=${navWearPros.get(i).pid }"><img src="/upload/middle/${myfn:getImgName(navWearPros.get(i).image) }" width="85%" height="85%" /></a>
										</div>
										<div class="nav_c_name">${navWearPros.get(i).pname }</div>
										<div class="nav_c_price">¥${navWearPros.get(i).price.intValue() }</div>
									</div>
								</c:forEach>
							</div>
						</div>
					</div>
					<div class="nav_right" id="nav_right4">
						<div class="nav_right_first">
							<div class="nav_right_span">
								<span class="nav_right_span1"><a href="">浏览智能家居频道</a></span>
								<span class="nav_right_span2"><a href="">></a></span>
							</div>
						</div>
						<div class="nav_right_second">
							<ul>
								<li><a href="">子母路由</a></li>
								<li><a href="">电视盒子</a></li>
								<li><a href="">路由器</a></li>
								<li><a href="">电力猫</a></li>
								<li><a href="">随行wifi</a></li>
								<li><a href="">HUAWEI HiLink生态产品</a></li>
							</ul>
						</div>
						<div class="nav_right_three">
							<div class="row">
								<c:set var="navFurnPros" value="${requestScope.navFurnPros }"></c:set>
								<c:forEach var="i" begin="0" end="3" step="1">  
									<div class="col-md-3 phone_flagship" align="center">
										<div class="nav_c_img">
											<a href="ProductQueryServlet?pid=${navFurnPros.get(i).pid }"><img src="/upload/middle/${myfn:getImgName(navFurnPros.get(i).image) }" width="85%" height="85%" /></a>
										</div>
										<div class="nav_c_name">${navFurnPros.get(i).pname }</div>
										<div class="nav_c_price">¥${navFurnPros.get(i).price.intValue() }</div>
									</div>
								</c:forEach>
							</div>
						</div>
					</div>
					<div class="nav_right" id="nav_right5">
						<div class="nav_right_first">
							<div class="nav_right_span">
								<span class="nav_right_span1"><a href="">浏览通用配件频道</a></span>
								<span class="nav_right_span2"><a href="">></a></span>
							</div>
						</div>
						<div class="nav_right_second">
							<ul>
								<li><a href="">移动电源</a></li>
								<li><a href="">耳机</a></li>
								<li><a href="">音箱</a></li>
								<li><a href="">自拍杆/支架</a></li>
								<li><a href="">充电器/线材</a></li>
								<li><a href="">U盘/存储卡</a></li>
								<li><a href="">排插</a></li>
								<li><a href="">摄像机/镜头</a></li>
								<li><a href="">智能硬件</a></li>
								<li><a href="">生活周边</a></li>
							</ul>
						</div>
						<div class="nav_right_three">
							<div class="row">
								<c:set var="navUniversalPros" value="${requestScope.navUniversalPros }"></c:set>
								<c:forEach var="i" begin="0" end="3" step="1">  
									<div class="col-md-3 phone_flagship" align="center">
										<div class="nav_c_img">
											<a href="ProductQueryServlet?pid=${navUniversalPros.get(i).pid }"><img src="/upload/middle/${myfn:getImgName(navUniversalPros.get(i).image) }" width="85%" height="85%" /></a>
										</div>
										<div class="nav_c_name">${navUniversalPros.get(i).pname }</div>
										<div class="nav_c_price">¥${navUniversalPros.get(i).price.intValue() }</div>
									</div>
								</c:forEach>
							</div>
						</div>
					</div>
					<div class="nav_right" id="nav_right6">
						<div class="nav_right_first">
							<div class="nav_right_span">
								<span class="nav_right_span1"><a href="">浏览专属配件频道</a></span>
								<span class="nav_right_span2"><a href="">></a></span>
							</div>
						</div>
						<div class="nav_right_second">
							<ul>
								<li><a href="">保护壳</a></li>
								<li><a href="">保护套</a></li>
								<li><a href="">贴膜</a></li>
								<li><a href="">盒子专属配件</a></li>
								<li><a href="">表带</a></li>
							</ul>
						</div>
						<div class="nav_right_three">
							<div class="row">
								<c:set var="navExclusivePros" value="${requestScope.navExclusivePros }"></c:set>
								<c:forEach var="i" begin="0" end="3" step="1">  
									<div class="col-md-3 phone_flagship" align="center">
										<div class="nav_c_img">
											<a href="ProductQueryServlet?pid=${navExclusivePros.get(i).pid }"><img src="/upload/middle/${myfn:getImgName(navExclusivePros.get(i).image) }" width="85%" height="85%" /></a>
										</div>
										<div class="nav_c_name">${navExclusivePros.get(i).pname }</div>
										<div class="nav_c_price">¥${navExclusivePros.get(i).price.intValue() }</div>
									</div>
								</c:forEach>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="container" id="allshops">
		<div class="contents" id="content" style="min-width: 1200px;">
			<div class="hotSales" id="section-1">
				<div class="row" style="height:40px;margin-top: 20px;margin-left: 0px;">
					<font size="5px">热销单品</font>
					<!-- <h4>
						<b>热销单品</b>
					</h4> -->
				</div>
				
				<div class="recommend">
					<a href="#"><img src="img/index/container/r1.jpg" /></a>
				</div>
				<div class="hot_commodity">
					<div class="hot_commodity_new">
						<c:set var="highSalesProduct" value="${requestScope.highSalesProduct }"></c:set>
						<c:forEach var="i" begin="0" end="3" step="1">
							<div class="recommend_shop">
								<div class="c_img">
									<a href="ProductQueryServlet?pid=${highSalesProduct.get(i).pid }"><img src="/upload/middle/${myfn:getImgName(highSalesProduct.get(i).image) }" width="65%"
										height="65%" /></a><br />
								</div>
								<div class="c_name">${highSalesProduct.get(i).pname }</div>
								<div class="c_special">赠送保护壳+流量卡</div>
								<div class="c_price">¥${highSalesProduct.get(i).price.intValue() }</div>
							</div>
						</c:forEach>
					</div>
					
					<div class="hot_commodity_hsale" style="margin-top: 20px;">
						<c:forEach var="i" begin="4" end="7" step="1">
							<div class="recommend_shop">
								<div class="c_img">
									<a href="ProductQueryServlet?pid=${highSalesProduct.get(i).pid }"><img src="/upload/middle/${myfn:getImgName(highSalesProduct.get(i).image) }" width="65%"
										height="65%" /></a><br />
								</div>
								<div class="c_name">${highSalesProduct.get(i).pname }</div>
								<div class="c_special">赠送保护壳+流量卡</div>
								<div class="c_price">¥${highSalesProduct.get(i).price.intValue() }</div>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
			<%-- <font>${pageContext.getServletContext()}</font> --%>
			<div class="div_img">
				<div class="div_img_farme">
					<a href="#"><img src="img/index/x/div1.jpg" /></a>
				</div>
			</div>
			<div class="product_div_head" id="section-2">
				<div class="pdh_first_last">
					<font size="5px">手机</font>
				</div>
				<div class="product_div_ul">
					<ul>
						<li><a href="ProductSearchServlet?keyWord=荣耀">荣耀</a></li>
						<li><a href="ProductSearchServlet?keyWord=HUAWEI P">HUAWEI P系列</a></li>
						<li><a href="ProductSearchServlet?keyWord=畅玩">畅玩</a></li>
						<li><a href="ProductSearchServlet?keyWord=HUAWEI Mate">HUAWEI Mate系列</a></li>
						<li><a href="ProductSearchServlet?keyWord=HUAWEI nova">HUAWEI nova系列</a></li>
						<li><a href="">HUAWEI 麦芒系列</a></li>
						<li><a href="">华为畅享系列</a></li>
						<li><a href="">合约机</a></li>
					</ul>
				</div>
				<div class="pdh_first_last">
					<a href="#" style="font-size:20px;">更多>&nbsp;</a>
				</div>
			</div>
			<div class="product_div">
				<div class="product_div_line">
					<c:set var="navPhonePros" value="${requestScope.navPhonePros }"></c:set>
					<c:forEach var="i" begin="0" end="3" step="1">
						<div class="thumbnail">
							<div class="c_imgs">
								<a href="ProductQueryServlet?pid=${navPhonePros.get(i).pid }"><img src="/upload/middle/${myfn:getImgName(navPhonePros.get(i).image) }" width="65%" height="65%" /></a><br />
							</div>
							<div class="c_names">${navPhonePros.get(i).pname }</div>
							<div class="c_special">赠送保护壳+流量卡</div>
							<div class="c_price">¥${navPhonePros.get(i).price.intValue() }</div>
						</div>
					</c:forEach>
				</div>

				<div class="product_div_line">
					<c:forEach var="i" begin="4" end="7" step="1">
						<div class="thumbnail">
							<div class="c_imgs">
								<a href="ProductQueryServlet?pid=${navPhonePros.get(i).pid }"><img src="/upload/middle/${myfn:getImgName(navPhonePros.get(i).image) }" width="65%" height="65%" /></a><br />
							</div>
							<div class="c_names">${navPhonePros.get(i).pname }</div>
							<div class="c_special">赠送保护壳+流量卡</div>
							<div class="c_price">¥${navPhonePros.get(i).price.intValue() }</div>
						</div>
					</c:forEach>
				</div>
			</div>

			<div class="product_div_head" id="section-3" style="margin-top: 20px;">
				<div class="pdh_first_last">
					<font size="5px">智能家居</font>
				</div>
				<div class="product_div_ul">
					<ul>
						<li><a href="">子母路由</a></li>
						<li><a href="">电视盒子</a></li>
						<li><a href="">路由器</a></li>
						<li><a href="">电力猫</a></li>
						<li><a href="">随行wifi</a></li>
						<li><a href="">HUAWEI HiLink生态产品</a></li>
					</ul>
				</div>
				<div class="pdh_first_last">
					<a href="#" style="font-size:20px;">更多>&nbsp;</a>
				</div>
			</div>
			<div class="product_div">
				<div class="product_div_line">
					<div class="product_div_img">
						<a href="#"><img src="img/index/x/x1.jpg" width="594px" height="346px"/></a>
					</div>
					<c:set var="navFurnPros" value="${requestScope.navFurnPros }"></c:set>
					<c:forEach var="i" begin="0" end="1" step="1">
						<div class="thumbnail">
							<c:set var="image" value="${myfn:getImgName(navFurnPros.get(i).image) }"></c:set>
							<div class="c_imgs">
								<a href="ProductQueryServlet?pid=${navFurnPros.get(i).pid }"><img src="/upload/middle/${myfn:getImgName(navFurnPros.get(i).image) }" width="65%" height="65%" /></a><br />
							</div>
							<div class="c_names">${navFurnPros.get(i).pname }</div>
							<div class="c_special">赠送保护壳+流量卡</div>
							<div class="c_price">¥${navFurnPros.get(i).price.intValue() }</div>
						</div>
					</c:forEach>
				</div>

				<div class="product_div_line">
					<c:forEach var="i" begin="2" end="5" step="1">
						<div class="thumbnail">
							<div class="c_imgs">
								<a href="ProductQueryServlet?pid=${navFurnPros.get(i).pid }"><img src="/upload/middle/${myfn:getImgName(navFurnPros.get(i).image) }" width="65%" height="65%" /></a><br />
							</div>
							<div class="c_names">${navFurnPros.get(i).pname }</div>
							<div class="c_special">赠送保护壳+流量卡</div>
							<div class="c_price">¥${navFurnPros.get(i).price.intValue() }</div>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
	
	<div id="footer">
		<div class="footer_content_frame">
			<div class="footer_top">
				<!-- <div class="row"> -->
					<div class="footer_top_head">
						<img src="img/index/footer/f1.png" alt="img" align="middle" width="50px" height="50px" />&nbsp;&nbsp;<span>百强企业 品质保证</span>
					</div>
					<div class="footer_top_head">
						<img src="img/index/footer/f2.png" alt="img" align="middle" width="50px" height="50px" />&nbsp;&nbsp;<span>7天退货 15天换货</span>
					</div>
					<div class="footer_top_head">
						<img src="img/index/footer/f3.png" alt="img" align="middle" width="50px" height="50px" />&nbsp;&nbsp;<span>48元起免运费</span>
					</div>
					<div class="footer_top_head">
						<img src="img/index/footer/f4.png" alt="img" align="middle" width="50px" height="50px" />&nbsp;&nbsp;<span>448家维修网点 全国联保</span>
					</div>
				<!-- </div> -->
			</div>
			<div class="footer_middle">
				<!-- <div class="row"> -->
					<div class="footer_middle_ul">
						<ul>
							<li style="font-size: 20px;color:#A8A8A8;"><b>—</b></li>
							<li>
								<h5>购物相关</h5>
							</li>
							<li><a href="">购物指南</a></li>
							<li><a href="">配送方式</a></li>
							<li><a href="">支付方式</a></li>
							<li><a href="">常见问题</a></li>
						</ul>
					</div>
					<div class="footer_middle_ul">
						<ul>
							<li style="font-size: 20px;color:#A8A8A8;"><b>—</b></li>
							<li>
								<h5>保修与退换货</h5>
							</li>
							<li><a href="">保修政策</a></li>
							<li><a href="">退换货政策</a></li>
							<li><a href="">退换货流程</a></li>
							<li><a href="">保修状态查询</a></li>
						</ul>
					</div>
					<div class="footer_middle_ul">
						<ul>
							<li style="font-size: 20px;color:#A8A8A8;"><b>—</b></li>
							<li>
								<h5>维修与技术支持</h5>
							</li>
							<li><a href="">售后网点</a></li>
							<li><a href="">预约维修</a></li>
							<li><a href="">手机寄修</a></li>
							<li><a href="">维修配件价格查询</a></li>
						</ul>
					</div>
					<div class="footer_middle_ul">
						<ul>
							<li style="font-size: 20px;color:#A8A8A8;"><b>—</b></li>
							<li>
								<h5>关于我们</h5>
							</li>
							<li><a href="">公司介绍</a></li>
							<li><a href="">华为商城简介</a></li>
							<li><a href="">华为线下门店</a></li>
							<li><a href="">荣耀线下门店</a></li>
							<li><a href="">诚征英才</a></li>
						</ul>
					</div>
					<div class="footer_middle_ul">
						<ul>
							<li style="font-size: 20px;color:#A8A8A8;"><b>—</b></li>
							<li>
								<h5>关于我们</h5>
							</li>
							<li><a href="">新浪微博</a></li>
							<li><a href="">腾讯微博</a></li>
							<li><a href="">花粉俱乐部</a></li>
						</ul>
					</div>
					<div class="footer_middle_ul">
						<ul>
							<li style="font-size: 20px;color:#A8A8A8;"><b>—</b></li>
							<li>
								<h5>友情链接</h5>
							</li>
							<li><a href="">华为官网</a></li>
							<li><a href="">华为商城</a></li>
							<li><a href="">荣耀官网</a></li>
							<li><a href="">花粉俱乐部</a></li>
							<li><a href="">莫塞尔商城</a></li>
						</ul>
					</div>
					<div class="special_ul">
						<ul>
							<li style="font-size: 20px;color:#A8A8A8;"><b>—</b></li>
							<li style="font-size: 25px;">400-088-6888</li>
							<li style="font-size: 10px;color:#666666;">24小时客服热线（仅收市话费）</li>
							<li class="special_ulli">
								<a style="color:red;" href=""><span class="glyphicon glyphicon-envelope">&nbsp;在线客服</span></a>
							</li>
							<li class="special_ulli" style="margin-top:7px;">
								<a style="color:red;" href=""><span class="glyphicon glyphicon-warning-sign">&nbsp;不良信息举报</span></a>
							</li>
						</ul>
					</div>
				<!-- </div> -->
			</div>
		</div>
	</div>
	<div class="legal_info">
		<div class="legal_content_frame">
			<div class="footer_bottom">
				<div class="footer_bottom_left">
					<div class="fbl_img">
						<img src="img/index/x/flogo.png" style="padding-top:47px;">
					</div>
					<div class="fbl_content">
						<p style="color:#A8A8A8;font-size: 10px;"><a>隐私政策</a>&nbsp;<a>服务协议</a> Copyright © 2012-2017 华为软件技术有限公司 版权所有 保留一切权利<br/>
						公安备案 苏公网安备32011402010009号 | 苏ICP备17040376号-6 | 增值电信业务经营许可证：苏B2-20130048号|<br/>
						网络文化经营许可证：苏网文[2015] 1599-026号</p>
					</div>
				</div>
				<div class="footer_bottom_right">
					<img src="img/index/footer/legal1.jpg" width="70px">&nbsp;
					<img src="img/index/footer/legal2.png" width="70px">&nbsp;
					<img src="img/index/footer/legal3.png" width="70px">&nbsp;
					<img src="img/index/footer/legal4.png" width="70px">&nbsp;
					<img src="img/index/footer/legal5.jpg" width="70px">
				</div>
			</div>
		</div>
	</div>
</body>
</html>