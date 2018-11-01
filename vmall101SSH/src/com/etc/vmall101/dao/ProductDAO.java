package com.etc.vmall101.dao;

import java.util.List;

import com.etc.vmall101.bean.OrderDetail;
import com.etc.vmall101.bean.Product;

public interface ProductDAO {
	List<Product> findProduct(Product pro);
	List<Product> searchProduct(String keyWord,int jumpNews,int queryNews,String time,String hot,String price);
	int proCounts(String keyWord);
	List<Product> findHighSalesProduct();
	List<Product> selectProductEasy(String productsel,String selectproduct);
	List<Product> selectAllProduct(int currentPage,int pageSize);
	List<Product> selectOneKindProduct(int kid);
	boolean addProduct(Product product);
	boolean deleteProductBatch(String[] pids);
	List<Product> selectProductComplex(String kname,String color,String stand,int minPrice,int maxPrice,String brginTime,String endTime);
	int findTotalCount();
	//商品信息查询
	Product findProductMessage(int pid);
	//通过pid查询单个商品信息
	Product selectOneProduct(int pid);
	
	//支付成功，减去购买数量
	boolean reduceProductInstock(int pid,int inStock);
}
