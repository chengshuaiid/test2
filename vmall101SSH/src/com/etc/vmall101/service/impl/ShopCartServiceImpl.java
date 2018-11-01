package com.etc.vmall101.service.impl;

import java.util.List;

import com.etc.vmall101.bean.ShopCart;
import com.etc.vmall101.dao.ShopCartDAO;
import com.etc.vmall101.dao.impl.ShopCartDAOImpl;
import com.etc.vmall101.service.ShopCartService;

public class ShopCartServiceImpl implements ShopCartService {
	private ShopCartDAO shopCartDAO = new ShopCartDAOImpl();

	@Override
	public List<ShopCart> ShopCarts(int userId) {
		return shopCartDAO.findShopCart(userId);
	}

	@Override
	public boolean addShopCart(ShopCart shopCart) {

		return shopCartDAO.insertShopCart(shopCart.getPid(), shopCart.getUserId());
	}
	//增加购物车的商品
	@Override
	public boolean addShopcart(ShopCart shopcart){
		if(shopCartDAO.selectOneShopcart(shopcart.getShoId())!=null){
			
			shopCartDAO.updateShopcartCount(shopcart.getShoId(), shopcart.getCount());
		}
			return shopCartDAO.addShopcart(shopcart);
	}
	
	//删除一个购物车商品 （通过shoId）
	@Override
	public boolean deleteOneShopid(int shoId) {
		
		return shopCartDAO.deleteOneShopid(shoId);
	}
	
	//修改 购物车的 商品数量
	@Override
	public boolean updateShopcartCount(int shoId, int count) {

		return shopCartDAO.updateShopcartCount(shoId, count);
	}
	
	//查询购物车所有商品
	@Override
	public List<ShopCart> selectAllShopcart(int userid) {
		
		return shopCartDAO.selectAllShopcart(userid);
	}
}
