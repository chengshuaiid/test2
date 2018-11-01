package com.etc.vmall101.service;

import com.etc.vamll101.vo.OrderlistPage;
import com.etc.vmall101.bean.Orderlist;


public interface OrderlistService {
	OrderlistPage SelectAllOrderList(int currentPage,int pageSize);
	OrderlistPage selectOrderListEasy(String ordersel,String selectorder);
	boolean deleteOrderlistBatch(String[] olIds);
	OrderlistPage selectOrderListComplex(String uname,String payid,String status,String brginTime,String endTime);
	//增加订单列表
	boolean addOrderlist(Orderlist orderlist);
}
