package com.etc.vmall101.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.etc.vmall101.bean.OrderDetail;
import com.etc.vmall101.bean.Receive;
import com.etc.vmall101.dao.ReceiveDAO;
import com.etc.vmall101.util.JDBCUtil;

public class ReceiveDAOImpl implements ReceiveDAO{
	
	private JDBCUtil jdbcUtil = JDBCUtil.getInstance();
	
	//查询收件人信息表(通过userid,rname)
	@Override
	public Receive selectOneReceive(int userid, String rname) {
		String sql = "select * from receive where userid=? and rname=?";
		ResultSet rs = jdbcUtil.execQuery(sql, userid, rname);
		
		Receive receive = new Receive();
		try {
			if(rs.next()){
				
				receive.setRid(rs.getInt("rid"));
				receive.setUserId(rs.getInt("userid"));
				receive.setRname(rs.getString("rname"));
				receive.setAddress(rs.getString("address"));
				receive.setTel(rs.getString("tel"));
				receive.setZip(rs.getString("zip"));
				
			}
			return receive;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			jdbcUtil.closeConnection();
		}
		
		return null;
	}
	
	//查询收件人单个信息表（通过rid）
	@Override
	public Receive selectOneReceive(int rid) {
		String sql = "select * from receive where rid=?";
		ResultSet rs = jdbcUtil.execQuery(sql, rid);
		
		Receive receive = new Receive();
		try {
			if(rs.next()){
				
				receive.setRid(rs.getInt("rid"));
				receive.setUserId(rs.getInt("userid"));
				receive.setRname(rs.getString("rname"));
				receive.setAddress(rs.getString("address"));
				receive.setTel(rs.getString("tel"));
				receive.setZip(rs.getString("zip"));
				
			}
			return receive;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			jdbcUtil.closeConnection();
		}
		
		return null;
	}
	
	//查询收件人所有信息表(通过userid)
	@Override
	public List<Receive> selectAllReceive(int userid) {
		
		String sql = "select * from receive where userid=?";
		ResultSet rs = jdbcUtil.execQuery(sql, userid);
		
		List<Receive> list = new ArrayList<Receive>();
		
		try {
			while(rs.next()){
				Receive receive = new Receive();
				
				receive.setRid(rs.getInt("rid"));
				receive.setUserId(rs.getInt("userid"));
				receive.setRname(rs.getString("rname"));
				receive.setAddress(rs.getString("address"));
				receive.setTel(rs.getString("tel"));
				receive.setZip(rs.getString("zip"));
				
				list.add(receive);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			jdbcUtil.closeConnection();
		}
		
		return null;
	}	
	
	//增加收货地址信息
	@Override
	public boolean addOneReceive(Receive receive) {
		String sql = "insert into receive values(null,?,?,?,?,?)";
		return jdbcUtil.execUpdate(sql,receive.getUserId(),
				receive.getRname(),receive.getAddress(),
				receive.getTel(),receive.getZip())>0;
	}

}
