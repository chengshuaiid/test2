package com.etc.vmall101.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.etc.vmall101.bean.Product;
import com.etc.vmall101.bean.ShopCart;
import com.etc.vmall101.dao.ShopCartDAO;
import com.etc.vmall101.util.JDBCUtil;

public class ShopCartDAOImpl implements ShopCartDAO {
	private JDBCUtil jdbcUtil = JDBCUtil.getInstance();
	@Override
	public List<ShopCart> findShopCart(int userId) {
		List<ShopCart> scs = new ArrayList<ShopCart>();
		String sql = "select * from shopcart,product where shopcart.pid = product.pid and userid=?";
		ResultSet rs = jdbcUtil.execQuery(sql,userId);
		try {
			while(rs.next()){
				ShopCart shopcart = new ShopCart();
				shopcart.setShoId(rs.getInt(1));
				shopcart.setUserId(rs.getInt(2));
				shopcart.setPid(rs.getInt(3));
				shopcart.setCount(rs.getInt(4));
				shopcart.setInputTime(rs.getTimestamp(5));
				Product product = new Product();
				product.setPid(rs.getInt(6));
				product.setKid(rs.getInt(7));
				product.setPname(rs.getString(8));
				product.setDetail(rs.getString(9));
				product.setPubdate(rs.getTimestamp(10));
				product.setPrice(rs.getDouble(11));
				product.setImage(rs.getString(12));
				product.setColor(rs.getString(13));
				product.setStand(rs.getString(14));
				product.setInStock(rs.getInt(15));
				shopcart.setProduct(product);
				scs.add(shopcart);
			}
			return scs;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			jdbcUtil.closeConnection();
		}
		return null;
	}
	@Override
	public boolean insertShopCart(int pid, int userId) {
		// TODO Auto-generated method stub
		JDBCUtil jdbcUtil=JDBCUtil.getInstance();
		String sql="insert into shopcart values(null,?,?,1,?)";
		return jdbcUtil.execUpdate(sql, pid,userId,new Date()) > 0;
	}
	//增加购物车的商品
	@Override
	public boolean addShopcart(ShopCart shopcart) {
		String sql = "insert into shopcart values(null,?,?,?,?)";
		return jdbcUtil.execUpdate(sql, shopcart.getUserId(),shopcart.getShoId(),shopcart.getPid(),shopcart.getCount(),shopcart.getInputTime())>0;
	}
	
	//删除一个购物车商品 （通过shoId）
	@Override
	public boolean deleteOneShopid(int shoId) {
		
		String sql = "delete from shopcart where shoid = ?";

		return jdbcUtil.execUpdate(sql, shoId) > 0;
		
	}
	
	//修改 购物车的 商品数量
	@Override
	public boolean updateShopcartCount(int shoId,int count) {
		String sql = "update shopcart set count = count + ? where shoid = ?";

		return jdbcUtil.execUpdate(sql, count, shoId) > 0;
	}
	
	//查询购物车所有商品
	@Override
	public List<ShopCart> selectAllShopcart(int userid) {
		String sql="select*from shopcart,product where shopcart.pid = product.pid and userid=?";
		ResultSet rs = jdbcUtil.execQuery(sql,userid);
		
		List<ShopCart> list = new ArrayList<ShopCart>();
		
		try {
			while(rs.next()){
				ShopCart shopCart=new ShopCart();
				
				shopCart.setShoId(rs.getInt("shoid"));
				shopCart.setUserId(rs.getInt("userid"));
				shopCart.setPid(rs.getInt("pid"));
				shopCart.setCount(rs.getInt("count"));
				shopCart.setInputTime(rs.getDate("inputtime"));
				
				
				Product product=new Product();
				
				product.setPid(rs.getInt("pid"));
				product.setKid(rs.getInt("kid"));
				product.setPname(rs.getString("pname"));
				product.setDetail(rs.getString("detail"));
				product.setPubdate(rs.getDate("pubdate"));
				product.setPrice(rs.getInt("price"));
				product.setImage(rs.getString("image"));
				product.setColor(rs.getString("color"));
				product.setStand(rs.getString("stand"));
				product.setInStock(rs.getInt("instock"));
				
				shopCart.setProduct(product);
				list.add(shopCart);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			jdbcUtil.closeConnection();
		}
		return null;
	}
	
	//查询单个购物车商品
	@Override
	public ShopCart selectOneShopcart(int shoId) {
		
		String sql = "select * from shopcart where shoid = ?";
		
		ResultSet rs = jdbcUtil.execQuery(sql,shoId);
		
		ShopCart shopcart = new ShopCart();
		
		try {
			if(rs.next()){
				
				shopcart.setShoId(rs.getInt("shoid"));
				shopcart.setUserId(rs.getInt("userid"));
				shopcart.setPid(rs.getInt("pid"));
				shopcart.setCount(rs.getInt("count"));
				shopcart.setInputTime(rs.getDate("inputtime"));
			}
			return shopcart;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			jdbcUtil.closeConnection();
		}
		return null;
	}

}
