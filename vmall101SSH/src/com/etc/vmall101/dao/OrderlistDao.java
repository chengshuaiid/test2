package com.etc.vmall101.dao;

import java.util.List;

import com.etc.vmall101.bean.Orderlist;

public interface OrderlistDao {
	List<Orderlist> SelectAllOrderList(int currentPage,int pageSize);
	List<Orderlist> selectOrderListEasy(String ordersel,String selectorder);
	boolean deleteOrderlistBatch(String[] olIds);
	List<Orderlist> selectOrderListComplex(String uname,String payid,String status,String brginTime,String endTime);
	int findTotalCount();
	//增加订单列表
	boolean addOrderlist(Orderlist orderlist);
}
