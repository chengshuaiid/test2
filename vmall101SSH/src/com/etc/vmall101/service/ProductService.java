package com.etc.vmall101.service;

import java.util.List;

import com.etc.vamll101.vo.ProductPage;
import com.etc.vamll101.vo.ProsPages;
import com.etc.vmall101.bean.Product;

public interface ProductService {
	List<Product> QueryProduct(Product pro);
	ProsPages clickProduct(String keyWord,int pageNum,int pages,String time,String hot,String price);
	List<Product> findHighSalesProduct();
	ProductPage selectProductEasy(String productsel,String selectproduct);
	ProductPage selectAllProduct(int currentPage,int pageSize);
	ProductPage selectOneKindProduct(int kid);
	boolean addProduct(Product product);
	boolean deleteProductBatch(String[] pids);
	ProductPage selectProductComplex(String kname,String color,String stand,int minPrice,int maxPrice,String brginTime,String endTime);
	Product getProductMessage(int pid);
	//通过pid查询单个商品信息
	Product selectOneProduct(int pid);
		
	//支付成功，减去购买数量
	boolean reduceProductInstock(int pid,int count);
}
