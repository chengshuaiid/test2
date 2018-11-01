package com.etc.vmall101.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.etc.vmall101.bean.Orderlist;
import com.etc.vmall101.dao.OrderlistDao;
import com.etc.vmall101.util.JDBCUtil;

public class OrderlistDaoImpl implements OrderlistDao {
	private JDBCUtil jdbcUtil = JDBCUtil.getInstance();
	@Override
	public List<Orderlist> SelectAllOrderList(int currentPage,int pageSize) {
		// TODO Auto-generated method stub
		List<Orderlist> orderList = new ArrayList<Orderlist>();
		String sql = "select * from orderlist limit ?,?";
		ResultSet rs = jdbcUtil.execQuery(sql,(currentPage-1)*pageSize,pageSize);
		try {
			while(rs.next()){
				String olId = rs.getString("olid");
				int rid = rs.getInt("rid");
				int userId = rs.getInt("userid");
				int payId = rs.getInt("payid");
				int status = rs.getInt("status");
				Date orderTime = rs.getTimestamp("ordertime");
				
				Orderlist orderl = new Orderlist();
				orderl.setOlId(olId);
				orderl.setRid(rid);
				orderl.setUserId(userId);
				orderl.setPayId(payId);
				orderl.setStatus(status);
				orderl.setOrderTime(orderTime);
				
				orderList.add(orderl);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			jdbcUtil.closeConnection();
		}
		return orderList;
	}

	@Override
	public List<Orderlist> selectOrderListEasy(String ordersel, String selectorder) {
		// TODO Auto-generated method stub
		List<Orderlist> orderList = new ArrayList<Orderlist>();
		String sel ="";
		if("byolId".equals(ordersel)){
			sel = "olid";
		}else if("byuserid".equals(ordersel)){
			sel = "userid";
		}
		String sql = "select * from orderlist where "+sel+" = ?";
		ResultSet rs = jdbcUtil.execQuery(sql,selectorder);
		try {
			while(rs.next()){
				String olId = rs.getString("olid");
				int rid = rs.getInt("rid");
				int userId = rs.getInt("userid");
				int payId = rs.getInt("payid");
				int status = rs.getInt("status");
				Date orderTime = rs.getTimestamp("ordertime");
				
				Orderlist orderl = new Orderlist();
				orderl.setOlId(olId);
				orderl.setRid(rid);
				orderl.setUserId(userId);
				orderl.setPayId(payId);
				orderl.setStatus(status);
				orderl.setOrderTime(orderTime);
				
				orderList.add(orderl);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			jdbcUtil.closeConnection();
		}
		return orderList;
	}

	@Override
	public boolean deleteOrderlistBatch(String[] olIds) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer("delete from orderlist where olid in (");
		for(int i=0;i<olIds.length;i++){
			sb.append("?,");
		}
		sb.deleteCharAt(sb.length()-1).append(")");
		String sql = sb.toString();
		int i = jdbcUtil.execUpdate(sql, olIds);
		jdbcUtil.closeConnection();
		return i>0;
	}

	@Override
	public List<Orderlist> selectOrderListComplex(String uname, String payid, String status, String brginTime,
			String endTime) {
		// TODO Auto-generated method stub
		List<Orderlist> orderList = new ArrayList<Orderlist>();
		String sql = null;
		ResultSet rs = null;
		if("".equals(brginTime) && "".equals(endTime)){
			sql = "select orderlist.* from orderlist,user where orderlist.userid = user.userid and uname like ?"
					+ " and payid like ? and status like ?";
			rs = jdbcUtil.execQuery(sql, "%"+uname+"%","%"+payid+"%","%"+status+"%");
		}else{
			sql = "select orderlist.* from orderlist,user where orderlist.userid = user.userid and uname like ?"
					+ " and payid like ? and status like ? and ordertime>? and ordertime<?";
			rs = jdbcUtil.execQuery(sql, "%"+uname+"%","%"+payid+"%","%"+status+"%",brginTime+" 00:00:00",endTime+" 23:59:59");
		}
		try {
			while(rs.next()){
				String olId = rs.getString("olid");
				int rid = rs.getInt("rid");
				int payId = rs.getInt("payid");
				int statusi =  rs.getInt("status");
				int userId = rs.getInt("userid");
				Date orderTime = rs.getTimestamp("ordertime");
				
				Orderlist orderl = new Orderlist();
				orderl.setOlId(olId);
				orderl.setRid(rid);
				orderl.setUserId(userId);
				orderl.setPayId(payId);
				orderl.setStatus(statusi);
				orderl.setOrderTime(orderTime);
				
				orderList.add(orderl);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			jdbcUtil.closeConnection();
		}
		return orderList;
	}

	@Override
	public int findTotalCount() {
		// TODO Auto-generated method stub
		String sql = "select count(*) from orderlist";
		ResultSet rs = jdbcUtil.execQuery(sql);
		try {
			if(rs.next()){
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		}
		return -1;
	}
	//增加订单列表
	@Override
	public boolean addOrderlist(Orderlist orderlist) {
		String sql = "insert into orderlist values(null,?,?,?,?,?)";
		return jdbcUtil.execUpdate(sql,orderlist.getRid(),
				orderlist.getUserId(),orderlist.getPayId(),
				orderlist.getStatus(),orderlist.getOrderTime())>0;
	}

}
