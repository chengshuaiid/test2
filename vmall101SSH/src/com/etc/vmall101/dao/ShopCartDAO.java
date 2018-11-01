package com.etc.vmall101.dao;

import java.util.List;

import com.etc.vmall101.bean.ShopCart;

public interface ShopCartDAO {
	List<ShopCart> findShopCart(int userId);
	//加入购物车
	boolean insertShopCart(int pid, int userId);
	//增加购物车的商品
	boolean addShopcart(ShopCart shopcart);
	
	//删除一个购物车商品 （通过shoId）
	boolean deleteOneShopid(int shoId);
	
	//修改 购物车的 商品数量
	boolean updateShopcartCount(int shoId,int count);
	
	//查询购物车所有商品
	List<ShopCart> selectAllShopcart(int userid);
	
	//查询单个购物车商品
	ShopCart selectOneShopcart(int shoId);
}
