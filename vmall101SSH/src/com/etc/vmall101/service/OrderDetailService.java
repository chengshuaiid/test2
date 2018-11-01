package com.etc.vmall101.service;

import com.etc.vamll101.vo.OrderDetailPage;
import com.etc.vmall101.bean.OrderDetail;


public interface OrderDetailService {
	OrderDetailPage SelectAllOrderDetail(int olId,int currentPage,int pageSize);
	//查询单个 订单明细 包括总价（通过 订单编号）
	OrderDetail selectOrderDetail(String olId);
	
	//修改 订单明细里的商品数量（通过 订单编号）
	boolean updateOrderDetailCount(String olId);
	
	//增加 订单明细表 的内容
	boolean addOrderDetail(OrderDetail orderDetail);
}
