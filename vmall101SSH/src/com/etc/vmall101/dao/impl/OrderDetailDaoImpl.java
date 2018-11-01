package com.etc.vmall101.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.etc.vmall101.bean.OrderDetail;
import com.etc.vmall101.dao.OrderDetailDao;
import com.etc.vmall101.util.JDBCUtil;

public class OrderDetailDaoImpl implements OrderDetailDao {
	private JDBCUtil jdbcUtil = JDBCUtil.getInstance();
	@Override
	public List<OrderDetail> SelectAllOrderDetail(int olId,int currentPage,int pageSize) {
		// TODO Auto-generated method stub
		List<OrderDetail> orderdeList = new ArrayList<OrderDetail>();
		String sql = "select * from orderdetail where olid=? limit ?,?";
		ResultSet rs = jdbcUtil.execQuery(sql,olId,(currentPage-1)*pageSize,pageSize);
		try {
			while(rs.next()){
				int odId = rs.getInt("odid");
				int pid = rs.getInt("pid");
				int count = rs.getInt("count");
				double sum = rs.getDouble("sum");
				
				OrderDetail orderDetail = new OrderDetail();
				orderDetail.setOdId(odId);
				orderDetail.setPid(pid);
				orderDetail.setCount(count);
				orderDetail.setSum(sum);
				orderdeList.add(orderDetail);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			jdbcUtil.closeConnection();
		}
		return orderdeList;
	}
	@Override
	public int findTotalCount(int olId) {
		// TODO Auto-generated method stub
		String sql = "select count(*) from orderdetail where olid =?";
		ResultSet rs = jdbcUtil.execQuery(sql,olId);
		try {
			if(rs.next()){
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	//查询单个 订单明细 包括总价（通过 订单编号）
		@Override
		public OrderDetail selectOrderDetail(String olId) {
			
			String sql = "select * from orderdetail where olid = ?";
			
			ResultSet rs = jdbcUtil.execQuery(sql,olId);
			
			OrderDetail orderDetail = new OrderDetail();
			
			try {
				if(rs.next()){
					orderDetail.setOdId(rs.getInt("odid"));
					orderDetail.setPid(rs.getInt("pid"));
					orderDetail.setCount(rs.getInt("count"));
					orderDetail.setSum(rs.getInt("sum"));
				}
				return orderDetail;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				jdbcUtil.closeConnection();
			}
			
			return null;
		}
		
		//修改 订单明细里的商品数量（通过 订单编号）
		@Override
		public boolean updateOrderDetailCount(String olId) {
			
			String sql = "update orderdetail set count = count + ? where olid = ?";
			
			return jdbcUtil.execUpdate(sql, olId) > 0;
		}
		
		//增加 订单明细表 的内容
		@Override
		public boolean addOrderDetail(OrderDetail orderDetail) {
			String sql = "insert into shopcart values(null,?,?,?,?)";
			return jdbcUtil.execUpdate(sql, orderDetail) > 0;
		}

}
