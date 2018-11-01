package com.etc.vmall101.dao;

import java.util.List;

import com.etc.vmall101.bean.OrderDetail;

public interface OrderDetailDao {
	List<OrderDetail> SelectAllOrderDetail(int olId,int currentPage,int pageSize);
	int findTotalCount(int olId);
	//查询单个 订单明细 包括总价（通过 订单编号）
	OrderDetail selectOrderDetail(String olId);
	
	//修改 订单明细里的商品数量（通过 订单编号）
	boolean updateOrderDetailCount(String olId);
	
	//增加 订单明细表 的内容
	boolean addOrderDetail(OrderDetail orderDetail);
}
