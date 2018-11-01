package com.etc.vmall101.service.impl;

import java.util.List;

import com.etc.vamll101.vo.OrderlistPage;
import com.etc.vmall101.bean.Orderlist;
import com.etc.vmall101.dao.OrderlistDao;
import com.etc.vmall101.dao.impl.OrderlistDaoImpl;
import com.etc.vmall101.service.OrderlistService;

public class OrderlistServiceImpl implements OrderlistService {
	private OrderlistDao old = new OrderlistDaoImpl();
	private OrderlistPage orderlistPage = new OrderlistPage();
	@Override
	public OrderlistPage SelectAllOrderList(int currentPage,int pageSize) {
		// TODO Auto-generated method stub
		List<Orderlist> orderList = null;
		orderList = old.SelectAllOrderList(currentPage,pageSize);
		orderlistPage.setList(orderList);
		orderlistPage.setPageSize(pageSize);
		orderlistPage.setCurrentPage(currentPage);
		int totalCount = old.findTotalCount();
		orderlistPage.setTotalCount(totalCount);
		int totalPage = (totalCount%pageSize==0)? totalCount/pageSize:totalCount/pageSize + 1;
		orderlistPage.setTotalPage(totalPage);
		
		
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
		orderlistPage.setBeginPage(beginPage);
		orderlistPage.setEndPage(endPage);
		return orderlistPage;
	}

	@Override
	public OrderlistPage selectOrderListEasy(String ordersel, String selectorder) {
		// TODO Auto-generated method stub
		List<Orderlist> orderList = null;
		orderList = old.selectOrderListEasy(ordersel, selectorder);
		orderlistPage.setList(orderList);
		return orderlistPage;
	}

	@Override
	public boolean deleteOrderlistBatch(String[] olIds) {
		// TODO Auto-generated method stub
		boolean flag = false;
		flag = old.deleteOrderlistBatch(olIds);
		return flag;
	}

	@Override
	public OrderlistPage selectOrderListComplex(String uname, String payid, String status, String brginTime,
			String endTime) {
		// TODO Auto-generated method stub
		List<Orderlist> orderList = null;
		orderList = old.selectOrderListComplex(uname, payid, status, brginTime, endTime);
		orderlistPage.setList(orderList);
		return orderlistPage;
	}
	//增加订单列表
	@Override
	public boolean addOrderlist(Orderlist orderlist) {
		
		return old.addOrderlist(orderlist);
	}

}
