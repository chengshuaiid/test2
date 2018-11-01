package com.etc.vmall101.service.impl;

import java.util.List;

import com.etc.vamll101.vo.OrderDetailPage;
import com.etc.vmall101.bean.OrderDetail;
import com.etc.vmall101.dao.OrderDetailDao;
import com.etc.vmall101.dao.impl.OrderDetailDaoImpl;
import com.etc.vmall101.service.OrderDetailService;

public class OrderDetailServiceImpl implements OrderDetailService {
	OrderDetailDao odd = new OrderDetailDaoImpl();
	@Override
	public OrderDetailPage SelectAllOrderDetail(int olId,int currentPage,int pageSize) {
		// TODO Auto-generated method stub
		OrderDetailPage orderDetailPage = new OrderDetailPage();
		orderDetailPage.setOlId(olId);
		List<OrderDetail> orderdeList = null;
		orderdeList = odd.SelectAllOrderDetail(olId,currentPage,pageSize);
		orderDetailPage.setList(orderdeList);
		
		orderDetailPage.setPageSize(pageSize);
		orderDetailPage.setCurrentPage(currentPage);
		int totalCount = odd.findTotalCount(olId);
		orderDetailPage.setTotalCount(totalCount);
		int totalPage = (totalCount%pageSize==0)? totalCount/pageSize:totalCount/pageSize + 1;
		orderDetailPage.setTotalPage(totalPage);
		
		int pagrNumber = 5;
		int beginPage = currentPage - pagrNumber/2;
		int endPage = currentPage + pagrNumber/2;
		
		if(beginPage<1){
			beginPage = 1;
			endPage = pagrNumber;
		}
		if(endPage>totalPage){
			endPage = totalPage;
			beginPage = totalPage-pagrNumber+1;
		}
		if(totalPage<pagrNumber){
			beginPage = 1;
			endPage = totalPage;
		}
		orderDetailPage.setBeginPage(beginPage);
		orderDetailPage.setEndPage(endPage);
		return orderDetailPage;
	}
	//查询单个 订单明细 包括总价（通过 订单编号）
	@Override
	public OrderDetail selectOrderDetail(String olId) {
		
		return odd.selectOrderDetail(olId);
	}
	
	//修改 订单明细里的商品数量（通过 订单编号）
	@Override
	public boolean updateOrderDetailCount(String olId) {
		return odd.updateOrderDetailCount(olId);
	}
	
	
	//增加 订单明细表 的内容
	@Override
	public boolean addOrderDetail(OrderDetail orderDetail) {
		
		return odd.addOrderDetail(orderDetail);
	}

}
