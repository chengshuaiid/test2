package com.etc.vmall101.service;


import java.util.List;

import com.etc.vmall101.bean.ShopCart;

public interface ShopCartService {
	List<ShopCart> ShopCarts(int userId);
	//添加
	boolean addShopCart(ShopCart shopCart);
	//增加购物车的商品
	boolean addShopcart(ShopCart shopcart);
	
	//删除一个购物车商品 （通过shoId）
	boolean deleteOneShopid(int shoId);
	
	//修改 购物车的 商品数量
	boolean updateShopcartCount(int shoId,int count);
	
	//查询购物车所有商品
	List<ShopCart> selectAllShopcart(int userid);
}
