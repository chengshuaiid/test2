package com.etc.vmall101.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.connector.Request;

import com.etc.vmall101.bean.Kind;
import com.etc.vmall101.dao.ProductKindDao;
import com.etc.vmall101.util.JDBCUtil;

public class ProductKindDaoImpl implements ProductKindDao {
	private JDBCUtil jdbcUtil = JDBCUtil.getInstance();
	@Override
	public List<Kind> selectProductKindEasy(String kindsel, String selectKind) {
		// TODO Auto-generated method stub
		List<Kind> kindList = new ArrayList<Kind>();
		String sel ="";
		if("bykid".equals(kindsel)){
			sel = "kid";
		}else if("bykname".equals(kindsel)){
			sel = "kname";
		}
		String sql = "select * from kind where "+sel+" = ?";
		ResultSet rs = jdbcUtil.execQuery(sql,selectKind);
		try {
			while(rs.next()){
				Kind kind = new Kind();
				//从结果集中提取数据
				int kid = rs.getInt("kid");
				String kname = rs.getString("kname");
				String color = rs.getString("color");
				String stand = rs.getString("stand");
				//把数据放入对象
				kind.setKid(kid);
				kind.setKname(kname);
				kind.setColor(color);
				kind.setStand(stand);
				//把对象放入集合
				kindList.add(kind);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			jdbcUtil.closeConnection();
		}
		return kindList;
	}

	@Override
	public List<Kind> selectAllProductKind(int currentPage,int pageSize) {
		// TODO Auto-generated method stub
		List<Kind> kindList = new ArrayList<Kind>();
		String sql = "select * from kind limit ?,?";
		ResultSet rs = jdbcUtil.execQuery(sql,(currentPage-1)*pageSize,pageSize);
		try {
			while(rs.next()){
				Kind kind = new Kind();
				//从结果集中提取数据
				int kid = rs.getInt("kid");
				String kname = rs.getString("kname");
				String color = rs.getString("color");
				String stand = rs.getString("stand");
				//把数据放入对象
				kind.setKid(kid);
				kind.setKname(kname);
				kind.setColor(color);
				kind.setStand(stand);
				//把对象放入集合
				kindList.add(kind);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			jdbcUtil.closeConnection();
		}
		return kindList;
	}

	@Override
	public boolean addProductKind(String color,String stand,String kname) {
		// TODO Auto-generated method stub
		String sql = "insert into kind(kname,color,stand) value(?,?,?)";
		int i = jdbcUtil.execUpdate(sql, kname,color,stand);
		if(i>0){
			return true;
		}
		jdbcUtil.closeConnection();
		return false;
	}

	@Override
	public String selectOneKindColor(String kid) {
		// TODO Auto-generated method stub
		String color = null;
		String sql = "select color from kind where kid=?";
		ResultSet rs = jdbcUtil.execQuery(sql, kid);
		try {
			while(rs.next()){
				color = rs.getString("color");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			jdbcUtil.closeConnection();
		}
		return color;
	}

	@Override
	public String selectOneKindStand(String kid) {
		// TODO Auto-generated method stub
		String stand = null;
		String sql = "select stand from kind where kid=?";
		ResultSet rs = jdbcUtil.execQuery(sql, kid);
		try {
			while(rs.next()){
				stand = rs.getString("stand");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			jdbcUtil.closeConnection();
		}
		return stand;
	}

	@Override
	public int findTotalCount() {
		// TODO Auto-generated method stub
		String sql = "select count(*) from kind";
		ResultSet rs = jdbcUtil.execQuery(sql);
		try {
			if(rs.next()){
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			jdbcUtil.closeConnection();
		}
		return -1;
	}

	@Override
	public List<Kind> getAllProductKind() {
		// TODO Auto-generated method stub
		List<Kind> kindList = new ArrayList<Kind>();
		String sql = "select * from kind";
		ResultSet rs = jdbcUtil.execQuery(sql);
		try {
			while(rs.next()){
				Kind kind = new Kind();
				//从结果集中提取数据
				int kid = rs.getInt("kid");
				String kname = rs.getString("kname");
				String color = rs.getString("color");
				String stand = rs.getString("stand");
				//把数据放入对象
				kind.setKid(kid);
				kind.setKname(kname);
				kind.setColor(color);
				kind.setStand(stand);
				//把对象放入集合
				kindList.add(kind);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			jdbcUtil.closeConnection();
		}
		return kindList;
	}
	@Override
	public Kind findProductKind(int kid) {
		// TODO Auto-generated method stub
		
		JDBCUtil jdbcUtil=JDBCUtil.getInstance();
		String sql="select * from kind where kid=?";
		ResultSet rs = jdbcUtil.execQuery(sql,kid);
		try {
			if(rs.next()){
				Kind kind=new Kind();
				kind.setKid(kid);
				kind.setKname(rs.getString("kname"));
				kind.setColor(rs.getString("color"));
				kind.setStand(rs.getString("stand"));
				return kind;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			jdbcUtil.closeConnection();
		}
		return null;
	}

}
