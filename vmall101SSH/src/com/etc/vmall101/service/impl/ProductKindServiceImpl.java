package com.etc.vmall101.service.impl;

import java.util.List;

import com.etc.vamll101.vo.ProductKindPage;
import com.etc.vmall101.bean.Kind;
import com.etc.vmall101.dao.ProductKindDao;
import com.etc.vmall101.dao.impl.ProductKindDaoImpl;
import com.etc.vmall101.service.ProductKindService;

public class ProductKindServiceImpl implements ProductKindService {
	private ProductKindDao pkd = new ProductKindDaoImpl();
	private ProductKindPage productKindPage = new ProductKindPage();
	@Override
	public ProductKindPage selectProductKindEasy(String kindsel, String selectKind) {
		// TODO Auto-generated method stub
		List<Kind> kindList = null;
		kindList = pkd.selectProductKindEasy(kindsel, selectKind);
		productKindPage.setList(kindList);
		return productKindPage;
	}
	@Override
	public ProductKindPage selectAllProductKind(int currentPage,int pageSize) {
		// TODO Auto-generated method stub
		List<Kind> kindList = null;
		kindList = pkd.selectAllProductKind(currentPage,pageSize);
		productKindPage.setList(kindList);

		productKindPage.setPageSize(pageSize);
		productKindPage.setCurrentPage(currentPage);
		int totalCount = pkd.findTotalCount();
		productKindPage.setTotalCount(totalCount);
		int totalPage = (totalCount%pageSize==0)? totalCount/pageSize:totalCount/pageSize + 1;
		productKindPage.setTotalPage(totalPage);
		
		int pagrNumber = 5;
		int beginPage = currentPage - pagrNumber/2;
		int endPage = currentPage + pagrNumber/2;
		
		if(beginPage<1){
			beginPage = 1;
			endPage = pagrNumber;
		}
		if(endPage>totalPage){
			endPage = totalPage;
			beginPage = totalPage-pagrNumber+1;
		}
		if(totalPage<pagrNumber){
			beginPage = 1;
			endPage = totalPage;
		}
		productKindPage.setBeginPage(beginPage);
		productKindPage.setEndPage(endPage);
		return productKindPage;
	}
	@Override
	public boolean addProductKind(String color, String stand, String kname) {
		// TODO Auto-generated method stub
		boolean flag = pkd.addProductKind(color, stand, kname);
		return flag;
	}
	@Override
	public String selectOneKindColor(String kname) {
		// TODO Auto-generated method stub
		String color;
		color = pkd.selectOneKindColor(kname);
		return color;
	}
	@Override
	public String selectOneKindStand(String kname) {
		// TODO Auto-generated method stub
		String stand;
		stand = pkd.selectOneKindStand(kname);
		return stand;
	}
	@Override
	public List<Kind> getAllProductKind() {
		// TODO Auto-generated method stub
		List<Kind> kindList = null;
		kindList = pkd.getAllProductKind();
		return kindList;
	}
	@Override
	public Kind getProductKind(int kid) {
		// TODO Auto-generated method stub
		return pkd.findProductKind(kid);
	}

}
