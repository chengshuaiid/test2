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
<title>商品搜索-华为商城</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="css/search.css" />
<link rel="stylesheet" type="text/css" href="css/alltopbottom.css" />
<script type="text/javascript" src="js/jquery-1.11.3.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/search.js"></script>
</head>

<body style="overflow-x: hidden">
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
					<li class="dropdown" id="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"> 
						<span class="glyphicon glyphicon-shopping-cart"></span>购物车<b class="caret"></b></a>
						<ul class="dropdown-menu" id="dropdown-menu" style="left:-360px;">
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
										<input type="submit" value="结算" class="stop_shopcart" id="clear_shopcart"/>
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
	
	<div class="container" id="header" style="position: relative;box-shadow: 0 4px 4px 0 rgba(0,0,0,0.06);">
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
	<c:if test="${not empty requestScope.keyWord}">
		<div class="search_pmt">
			<div class="search_pmt_frame">
				<font size="2px" color="#666666">搜索<font color="red">&nbsp;[${requestScope.keyWord }]&nbsp;</font>共找到${requestScope.prosPages.prosNum }件商品</font>
			</div>
		</div>
	</c:if>
 	<div class="container" id="allshops">
		<div class="contents" id="content" style="min-width: 1200px;">
			<div class="product_div_head">
				<div class="pdh_first_last" style="padding-left:20px;">
					<font size="2px" color="#A4A4A4">排序:</font>
				</div>
				<div class="product_div_ul">
					<ul>
						<li><a href="ProductSearchServlet?keyWord=${requestScope.keyWord }" id="default_order">综合</a></li>
						<li><a href="ProductSearchServlet?keyWord=${requestScope.keyWord }&time=1" id="time_order">最新</a></li>
						<li><a href="ProductSearchServlet?keyWord=${requestScope.keyWord }&hot=1" id="hot_order">热度</a></li>
						<li><a href="ProductSearchServlet?keyWord=${requestScope.keyWord }&price=1" id="price_order"<%--  onclick="priceOrder(${requestScope.price },${requestScope.keyWord })" --%>>价格<span class="glyphicon glyphicon-arrow-down"></span></a></li>
						<li><a href="ProductSearchServlet?keyWord=${requestScope.keyWord }&price=2" id="price_order"<%--  onclick="priceOrder(${requestScope.price },${requestScope.keyWord })" --%>>价格<span class="glyphicon glyphicon-arrow-up"></span></a></li>
					</ul> 
				</div>
			</div>
			<div class="product_div">
				<c:set var="prosList" value="${requestScope.prosPages.prosList }"></c:set>
				<c:if test="${prosList.size()<=5 }">
					<div class="product_div_line">
						<c:forEach items="${prosList }" var="clickPro">
							<div class="thumbnail">
								<div class="c_imgs">
									<a href="ProductQueryServlet?pid=${clickPro.pid }"><img src="/upload/middle/${myfn:getImgName(clickPro.image) }"
										width="90%" height="90%" /></a><br />
								</div>
								<div class="c_names">${clickPro.pname }</div>
								<div class="c_price">¥${clickPro.price.intValue() }</div>
							</div>
						</c:forEach>
					</div>
				</c:if>
				
				<c:if test="${prosList.size()>5 && prosList.size()<=10}">
					<div class="product_div_line">
						<c:forEach var="i" begin="0" end="4" step="1">
							<div class="thumbnail">
								<div class="c_imgs">
									<a href="ProductQueryServlet?pid=${prosList.get(i).pid }"><img src="/upload/middle/${myfn:getImgName(prosList.get(i).image) }"
										width="90%" height="90%" /></a><br />
								</div>
								<div class="c_names">${prosList.get(i).pname }</div>
								<div class="c_price">¥${prosList.get(i).price.intValue() }</div>
							</div>
						</c:forEach>
					</div>
					<div class="product_div_line">
						<c:forEach var="i" begin="5" end="${prosList.size()-1 }" step="1">
							<div class="thumbnail">
								<div class="c_imgs">
									<a href="ProductQueryServlet?pid=${prosList.get(i).pid }"><img src="/upload/middle/${myfn:getImgName(prosList.get(i).image) }"
										width="90%" height="90%" /></a><br />
								</div>
								<div class="c_names">${prosList.get(i).pname }</div>
								<div class="c_price">¥${prosList.get(i).price.intValue() }</div>
							</div>
						</c:forEach>
					</div>
				</c:if>
				
				<c:if test="${prosList.size()>10 && prosList.size()<=15}">
					<div class="product_div_line">
						<c:forEach var="i" begin="0" end="4" step="1">
							<div class="thumbnail">
								<div class="c_imgs">
									<a href="ProductQueryServlet?pid=${prosList.get(i).pid }"><img src="/upload/middle/${myfn:getImgName(prosList.get(i).image) }"
										width="90%" height="90%" /></a><br />
								</div>
								<div class="c_names">${prosList.get(i).pname }</div>
								<div class="c_price">¥${prosList.get(i).price.intValue() }</div>
							</div>
						</c:forEach>
					</div>
					<div class="product_div_line">
						<c:forEach var="i" begin="5" end="9" step="1">
							<div class="thumbnail">
								<div class="c_imgs">
									<a href="ProductQueryServlet?pid=${prosList.get(i).pid }"><img src="/upload/middle/${myfn:getImgName(prosList.get(i).image) }"
										width="90%" height="90%" /></a><br />
								</div>
								<div class="c_names">${prosList.get(i).pname }</div>
								<div class="c_price">¥${prosList.get(i).price.intValue() }</div>
							</div>
						</c:forEach>
					</div>
					<div class="product_div_line">
						<c:forEach var="i" begin="10" end="${prosList.size()-1 }" step="1">
							<div class="thumbnail">
								<div class="c_imgs">
									<a href="ProductQueryServlet?pid=${prosList.get(i).pid }"><img src="/upload/middle/${myfn:getImgName(prosList.get(i).image) }"
										width="90%" height="90%" /></a><br />
								</div>
								<div class="c_names">${prosList.get(i).pname }</div>
								<div class="c_price">¥${prosList.get(i).price.intValue() }</div>
							</div>
						</c:forEach>
					</div>
				</c:if>
				
				<c:if test="${prosList.size()>15 && prosList.size()<=20}">
					<div class="product_div_line">
						<c:forEach var="i" begin="0" end="4" step="1">
							<div class="thumbnail">
								<div class="c_imgs">
									<a href="ProductQueryServlet?pid=${prosList.get(i).pid }"><img src="/upload/middle/${myfn:getImgName(prosList.get(i).image) }"
										width="90%" height="90%" /></a><br />
								</div>
								<div class="c_names">${prosList.get(i).pname }</div>
								<div class="c_price">¥${prosList.get(i).price.intValue() }</div>
							</div>
						</c:forEach>
					</div>
					<div class="product_div_line">
						<c:forEach var="i" begin="5" end="9" step="1">
							<div class="thumbnail">
								<div class="c_imgs">
									<a href="ProductQueryServlet?pid=${prosList.get(i).pid }"><img src="/upload/middle/${myfn:getImgName(prosList.get(i).image) }"
										width="90%" height="90%" /></a><br />
								</div>
								<div class="c_names">${prosList.get(i).pname }</div>
								<div class="c_price">¥${prosList.get(i).price.intValue() }</div>
							</div>
						</c:forEach>
					</div>
					<div class="product_div_line">
						<c:forEach var="i" begin="10" end="14" step="1">
							<div class="thumbnail">
								<div class="c_imgs">
									<a href="ProductQueryServlet?pid=${prosList.get(i).pid }"><img src="/upload/middle/${myfn:getImgName(prosList.get(i).image) }"
										width="90%" height="90%" /></a><br />
								</div>
								<div class="c_names">${prosList.get(i).pname }</div>
								<div class="c_price">¥${prosList.get(i).price.intValue() }</div>
							</div>
						</c:forEach>
					</div>					
					<div class="product_div_line">
						<c:forEach var="i" begin="15" end="${prosList.size()-1 }" step="1">
							<div class="thumbnail">
								<div class="c_imgs">
									<a href="ProductQueryServlet?pid=${prosList.get(i).pid }"><img src="/upload/middle/${myfn:getImgName(prosList.get(i).image) }"
										width="90%" height="90%" /></a><br />
								</div>
								<div class="c_names">${prosList.get(i).pname }</div>
								<div class="c_price">¥${prosList.get(i).price.intValue() }</div>
							</div>
						</c:forEach>
					</div>
				</c:if>
			</div>
			<div class="product_pagin">
				<div class="product_pagin_frame">
					<ul>
						<c:if test="${requestScope.prosPages.pages!=1 }">
							<li class="ppf_button"><a href="ProductSearchServlet?pages=1&keyWord=${requestScope.keyWord }">|<</a></li>
							<li class="ppf_button"><a href="ProductSearchServlet?pages=${requestScope.prosPages.pages-1 }&keyWord=${requestScope.keyWord }"><</a></li>
						</c:if>
						<c:if test="${requestScope.prosPages.pages==1 }">
							<li class="ppf_button">|<</li>
							<li class="ppf_button"><</li>
						</c:if>
						<c:forEach var="i" begin="${requestScope.prosPages.beginPages }" end="${requestScope.prosPages.endPages }" step="1">
							<c:if test="${i!=requestScope.prosPages.pages}">
								<li class="ppf_href"><a href="ProductSearchServlet?pages=${i}&keyWord=${requestScope.keyWord }">${i}</a></li>
							</c:if>
							<c:if test="${i==requestScope.prosPages.pages}">
								<li class="ppf_href" style="color:red;">${i}</li>
							</c:if>
						</c:forEach>
						
						<c:if test="${requestScope.prosPages.pages!=requestScope.prosPages.allPages }">
							<li class="ppf_button"><a href="ProductSearchServlet?pages=${requestScope.prosPages.pages+1 }&keyWord=${requestScope.keyWord }">></a></li>
							<li class="ppf_button"><a href="ProductSearchServlet?pages=${requestScope.prosPages.allPages }&keyWord=${requestScope.keyWord }">>|</a></li>
						</c:if>
						<c:if test="${requestScope.prosPages.pages==requestScope.prosPages.allPages }">
							<li class="ppf_button">></li>
							<li class="ppf_button">>|</li>
						</c:if>
					</ul>
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