package com.etc.vmall101.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.etc.vmall101.bean.OrderDetail;
import com.etc.vmall101.bean.Product;
import com.etc.vmall101.dao.ProductDAO;
import com.etc.vmall101.util.JDBCUtil;

public class ProductDAOImpl implements ProductDAO {
	private JDBCUtil jdbcUtil = JDBCUtil.getInstance();
	@Override
	public List<Product> findProduct(Product pro) {
		String sql = "",keyWord = "";
		ResultSet rs = null;
		List<Product> pros = new ArrayList<Product>();
		if(pro.getPname()!=null){
			keyWord = "%"+pro.getPname()+"%";
			sql="select * from product where pname like? limit 8";
			rs = jdbcUtil.execQuery(sql, keyWord);
		}else if(pro.getDetail()!=null){
			keyWord = "%"+pro.getDetail()+"%";
			sql="select * from product where detail like? limit 8";
			rs = jdbcUtil.execQuery(sql, keyWord);
		}else{
			sql="select * from product limit 8";
			rs = jdbcUtil.execQuery(sql);
		}
		try {
			while(rs.next()){
				Product product = new Product();
				product.setPid(rs.getInt(1));
				product.setKid(rs.getInt(2));
				product.setPname(rs.getString(3));
				product.setDetail(rs.getString(4));
				product.setPubdate(rs.getTimestamp(5));
				product.setPrice(rs.getInt(6));
				product.setImage(rs.getString(7));
				product.setColor(rs.getString(8));
				product.setStand(rs.getString(9));
				product.setInStock(rs.getInt(10));
				pros.add(product);
			}
			return pros;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			jdbcUtil.closeConnection();
		}
		return null;
	}
	@Override
	public List<Product> searchProduct(String keyWord,int jumpPros,int queryPros,String time,String hot,String price) {
		List<Product> pros = new ArrayList<Product>();
		String sql="";
		
		if(time==null && hot==null && "0".equals(price)){
			sql="select * from product where detail like? or pname like? limit ?,?";
		}else if(time!=null && hot==null && "0".equals(price)){
			sql="select * from product where detail like? or pname like? order by pubdate desc limit ?,?";
		}else if(time==null && hot!=null && "0".equals(price)){
			sql="select * from product where detail like? or pname like? order by instock asc limit ?,?";
		}else if(time==null && hot==null && "1".equals(price)){
			sql="select * from product where detail like? or pname like? order by price desc limit ?,?";
		}else if(time==null && hot==null && "2".equals(price)){
			sql="select * from product where detail like? or pname like? order by price asc limit ?,?";
		}
		ResultSet rs = jdbcUtil.execQuery(sql, "%"+keyWord+"%","%"+keyWord+"%",jumpPros,queryPros);
		try {
			while(rs.next()){
				Product product = new Product();
				product.setPid(rs.getInt(1));
				product.setKid(rs.getInt(2));
				product.setPname(rs.getString(3));
				product.setDetail(rs.getString(4));
				product.setPubdate(rs.getTimestamp(5));
				product.setPrice(rs.getInt(6));
				product.setImage(rs.getString(7));
				product.setColor(rs.getString(8));
				product.setStand(rs.getString(9));
				product.setInStock(rs.getInt(10));
				pros.add(product);
			}
			return pros;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			jdbcUtil.closeConnection();
		}
		return null;
	}
	@Override
	public int proCounts(String keyWord) {
		String sql = "select count(*) from product where detail like? or pname like?";
		ResultSet rs = jdbcUtil.execQuery(sql,"%"+keyWord+"%","%"+keyWord+"%");
		try {
			if(rs.next()){
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	/*@Override
	public List<Product> findSCProduct(int[] pids) {
		List<Product> pros = new ArrayList<Product>();
		String sql = "select * from product where pid in ?";
		ResultSet rs = jdbcUtil.execQuery(sql,pids);
		try {
			while(rs.next()){
				Product product = new Product();
				product.setPid(rs.getInt(1));
				product.setKid(rs.getInt(2));
				product.setPname(rs.getString(3));
				product.setDetail(rs.getString(4));
				product.setPubdate(rs.getTimestamp(5));
				product.setPrice(rs.getInt(6));
				product.setImage(rs.getString(7));
				product.setColor(rs.getString(8));
				product.setStand(rs.getString(9));
				product.setInStock(rs.getInt(10));
				pros.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			jdbcUtil.closeConnection();
		}
		return null;
	}*/
	
	@Override
	public List<Product> findHighSalesProduct() {
		List<Product> pros = new ArrayList<Product>();
		String sql="select * from product order by instock asc limit 8";
		ResultSet rs = jdbcUtil.execQuery(sql);
		try {
			while(rs.next()){
				Product product = new Product();
				product.setPid(rs.getInt(1));
				product.setKid(rs.getInt(2));
				product.setPname(rs.getString(3));
				product.setDetail(rs.getString(4));
				product.setPubdate(rs.getTimestamp(5));
				product.setPrice(rs.getInt(6));
				product.setImage(rs.getString(7));
				product.setColor(rs.getString(8));
				product.setStand(rs.getString(9));
				product.setInStock(rs.getInt(10));
				pros.add(product);
			}
			return pros;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			jdbcUtil.closeConnection();
		}
		return null;
	}
	@Override
	public List<Product> selectProductEasy(String productsel,
			String selectproduct) {
		List<Product> productList = new ArrayList<Product>();
		String sel ="";
		if("bypid".equals(productsel)){
			sel = "pid";
		}else if("bypname".equals(productsel)){
			sel = "pname";
		}
		String sql = "select * from product where "+sel+" = ?";
		ResultSet rs = jdbcUtil.execQuery(sql,selectproduct);
		try {
			while(rs.next()){
				int pid = rs.getInt("pid");
				int kid = rs.getInt("kid");
				String pname = rs.getString("pname");
				String detail = rs.getString("detail");
				Date pubdate = rs.getTimestamp("pubdate");
				double price = rs.getDouble("price");
				String image = rs.getString("image");
				String color = rs.getString("color");
				String stand = rs.getString("stand");
				int inStock = rs.getInt("inStock");
				
				Product product = new Product();
				product.setPid(pid);
				product.setKid(kid);
				product.setPname(pname);
				product.setDetail(detail);
				product.setDetail(detail);
				product.setPubdate(pubdate);
				product.setPrice(price);
				product.setImage(image);
				product.setColor(color);
				product.setStand(stand);
				product.setStand(stand);
				product.setInStock(inStock);
				
				productList.add(product);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			jdbcUtil.closeConnection();
		}
		return productList;
	}
	@Override
	public List<Product> selectAllProduct(int currentPage, int pageSize) {
		List<Product> productList = new ArrayList<Product>();
		String sql = "select * from product limit ?,?";
		ResultSet rs = jdbcUtil.execQuery(sql,(currentPage-1)*pageSize,pageSize);
		try {
			while(rs.next()){
				int pid = rs.getInt("pid");
				int kid = rs.getInt("kid");
				String pname = rs.getString("pname");
				String detail = rs.getString("detail");
				Date pubdate = rs.getTimestamp("pubdate");
				double price = rs.getDouble("price");
				String image = rs.getString("image");
				String color = rs.getString("color");
				String stand = rs.getString("stand");
				int inStock = rs.getInt("inStock");
				
				Product product = new Product();
				product.setPid(pid);
				product.setKid(kid);
				product.setPname(pname);
				product.setDetail(detail);
				product.setDetail(detail);
				product.setPubdate(pubdate);
				product.setPrice(price);
				product.setImage(image);
				product.setColor(color);
				product.setStand(stand);
				product.setStand(stand);
				product.setInStock(inStock);
				
				productList.add(product);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			jdbcUtil.closeConnection();
		}
		return productList;
	}
	@Override
	public List<Product> selectOneKindProduct(int kid) {
		List<Product> productList = new ArrayList<Product>();
		String sql = "select * from product where kid=?";
		ResultSet rs = jdbcUtil.execQuery(sql,kid);
		
		try {
			while(rs.next()){
				int pid = rs.getInt("pid");
				String pname = rs.getString("pname");
				String detail = rs.getString("detail");
				Date pubdate = rs.getTimestamp("pubdate");
				double price = rs.getDouble("price");
				String image = rs.getString("image");
				String color = rs.getString("color");
				String stand = rs.getString("stand");
				int inStock = rs.getInt("inStock");
				
				Product product = new Product();
				product.setPid(pid);
				product.setKid(kid);
				product.setPname(pname);
				product.setDetail(detail);
				product.setDetail(detail);
				product.setPubdate(pubdate);
				product.setPrice(price);
				product.setImage(image);
				product.setColor(color);
				product.setStand(stand);
				product.setStand(stand);
				product.setInStock(inStock);
				
				productList.add(product);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			jdbcUtil.closeConnection();
		}
		return productList;
	}
	@Override
	public boolean addProduct(Product product) {
		String sql = "insert into product values(null,?,?,?,?,?,?,?,?,0)";
		int i = jdbcUtil.execUpdate(sql, product.getKid(),product.getPname(),product.getDetail(),product.getPubdate(),product.getPrice(),product.getImage(),product.getColor(),product.getStand());
		if(i>0){
			return true;
		}
		return false;
	}
	@Override
	public boolean deleteProductBatch(String[] pids) {
		StringBuffer sb = new StringBuffer("delete from product where pid in (");
		for(int i=0;i<pids.length;i++){
			sb.append("?,");
		}
		sb.deleteCharAt(sb.length()-1).append(")");
		String sql = sb.toString();
		int i = jdbcUtil.execUpdate(sql, pids);
		jdbcUtil.closeConnection();
		return i>0;
	}
	@Override
	public List<Product> selectProductComplex(String kname, String color,
			String stand, int minPrice, int maxPrice, String brginTime,
			String endTime) {
		List<Product> productList = new ArrayList<Product>();
		String sql = null;
		ResultSet rs = null;
		if("".equals(brginTime) && "".equals(endTime)){
			sql = "select product.* from product,kind where product.kid = kind.kid and kname like ? and product.color like ? "
					+ "and product.stand like ? and price>=? and price<=?";
			rs = jdbcUtil.execQuery(sql, "%"+kname+"%","%"+color+"%","%"+stand+"%",minPrice,maxPrice);
		}else{
			sql = "select product.* from product,kind where product.kid = kind.kid and kname like ? and product.color like ? "
					+ "and product.stand like ? and price>=? and price<=? and pubdate>? and pubdate<?";
			rs = jdbcUtil.execQuery(sql, "%"+kname+"%","%"+color+"%","%"+stand+"%",minPrice,maxPrice,brginTime+" 00:00:00",endTime+" 23:59:59");
		}
		
		try {
			while(rs.next()){
				int pid = rs.getInt("pid");
				String pname = rs.getString("pname");
				int kid = rs.getInt("kid");
				String detail = rs.getString("detail");
				Date pubdate = rs.getTimestamp("pubdate");
				String colors = rs.getString("color");
				String stands = rs.getString("stand");
				double price = rs.getDouble("price");
				String image = rs.getString("image");
				int inStock = rs.getInt("inStock");
				
				Product product = new Product();
				product.setPid(pid);
				product.setKid(kid);
				product.setPname(pname);
				product.setDetail(detail);
				product.setPubdate(pubdate);
				product.setPrice(price);
				product.setImage(image);
				product.setColor(colors);
				product.setStand(stands);
				product.setInStock(inStock);
				
				productList.add(product);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return productList;
	}
	@Override
	public int findTotalCount() {
		String sql = "select count(*) from product";
		ResultSet rs = jdbcUtil.execQuery(sql);
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
	@Override
	public Product findProductMessage(int pid) {
		// TODO Auto-generated method stub
		JDBCUtil jdbcUtil=JDBCUtil.getInstance();
		String sql="select * from product where pid=?";
		ResultSet rs=jdbcUtil.execQuery(sql, pid);
		try {
			if(rs.next()){
				
				
				Product product = new Product();
				product.setPid(pid);
				product.setKid(rs.getInt("kid"));
				product.setPname(rs.getString("pname"));
				product.setDetail(rs.getString("detail"));
				product.setPubdate(rs.getTimestamp("pubdate"));
				product.setPrice(rs.getDouble("price"));
				product.setImage(rs.getString("image"));
				product.setColor(rs.getString("color"));
				product.setStand(rs.getString("stand"));
				product.setInStock(rs.getInt("inStock"));
				
				return product;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			jdbcUtil.closeConnection();
		}
		return null;
	}
	@Override
	public Product selectOneProduct(int pid) {
		
		String sql = "select * from product where pid = ?";
		
		ResultSet rs = jdbcUtil.execQuery(sql,pid);
		
		Product product = new Product();
		try {
			if(rs.next()){
				
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
				
			}
			return product;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			jdbcUtil.closeConnection();
		}
			
		return null;
	}
	
	//支付成功，减去购买数量
	@Override
	public boolean reduceProductInstock(int pid ,int inStock) {
		String sql = "update product set instock = instock - ? where pid = ?";
		return jdbcUtil.execUpdate(sql, pid,inStock) > 0;
	}
	

}
