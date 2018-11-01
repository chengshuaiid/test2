package com.etc.vmall101.service.impl;

import java.util.List;

import com.etc.vamll101.vo.ProductPage;
import com.etc.vamll101.vo.ProsPages;
import com.etc.vmall101.bean.Product;
import com.etc.vmall101.dao.ProductDAO;
import com.etc.vmall101.dao.impl.ProductDAOImpl;
import com.etc.vmall101.service.ProductService;

public class ProductServiceImpl implements ProductService {
	private ProductPage productPage = new ProductPage();
	private ProductDAO proDAO = new ProductDAOImpl();
	@Override
	public List<Product> QueryProduct(Product pro){
		return proDAO.findProduct(pro);
	}
	@Override
	public ProsPages clickProduct(String keyWord,int pageNum,int pages,String time,String hot,String price) {
		int prosNum = proDAO.proCounts(keyWord);//总共条记录
		int allPages = prosNum%pageNum==0?prosNum/pageNum:prosNum/pageNum+1;//页数
		int jumpPros = (pages-1)*pageNum;
		ProsPages prosPage = new ProsPages();
		prosPage.setPageNum(pageNum);
		prosPage.setProsNum(prosNum);
		prosPage.setPages(pages);
		prosPage.setAllPages(allPages);
		
		int showPages = 5;
		int beginPage = pages-showPages/2;
		int endPage = pages+showPages/2;
		if(allPages<showPages){
			beginPage = 1;
			endPage=allPages;
		}
		if(beginPage<1){
			beginPage=1;
			endPage=showPages;
		}
		if(endPage>allPages){
			beginPage = endPage-showPages;
			endPage = allPages;
		}
		
		prosPage.setBeginPages(beginPage);
		prosPage.setEndPages(endPage);
		prosPage.setProsList(proDAO.searchProduct(keyWord, jumpPros, pageNum,time,hot,price));
		return prosPage;
	}
	/*@Override
	public List<Product> findSCProduct(int[] pids) {
		return proDAO.findSCProduct(pids);
	}*/
	@Override
	public List<Product> findHighSalesProduct() {
		return proDAO.findHighSalesProduct();
	}
	
	@Override
	public ProductPage selectProductEasy(String productsel, String selectproduct) {
		// TODO Auto-generated method stub
		List<Product> productList = null;
		productList = proDAO.selectProductEasy(productsel, selectproduct);
		productPage.setList(productList);
		return productPage;
	}

	@Override
	public ProductPage selectAllProduct(int currentPage,int pageSize) {
		// TODO Auto-generated method stub
		List<Product> productList = null;
		
		productList = proDAO.selectAllProduct(currentPage,pageSize);
		productPage.setList(productList);
		
		productPage.setPageSize(pageSize);
		productPage.setCurrentPage(currentPage);
		int totalCount = proDAO.findTotalCount();
		productPage.setTotalCount(totalCount);
		int totalPage = (totalCount%pageSize==0)? totalCount/pageSize:totalCount/pageSize + 1;
		productPage.setTotalPage(totalPage);
		
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
		productPage.setBeginPage(beginPage);
		productPage.setEndPage(endPage);
		return productPage;
	}

	@Override
	public ProductPage selectOneKindProduct(int kid) {
		// TODO Auto-generated method stub
		List<Product> productList = null;
		productList = proDAO.selectOneKindProduct(kid);
		productPage.setList(productList);
		return productPage;
	}

	@Override
	public boolean addProduct(Product product) {
		// TODO Auto-generated method stub
		boolean flag = false;
		flag = proDAO.addProduct(product);
		return flag;
	}

	@Override
	public boolean deleteProductBatch(String[] pids) {
		// TODO Auto-generated method stub
		boolean flag = false;
		flag = proDAO.deleteProductBatch(pids);
		return flag;
	}

	@Override
	public ProductPage selectProductComplex(String kname, String color, String stand, int minPrice, int maxPrice,
			String brginTime, String endTime) {
		// TODO Auto-generated method stub
		List<Product> productList = null;
		productList = proDAO.selectProductComplex(kname, color, stand, minPrice, maxPrice, brginTime, endTime);
		productPage.setList(productList);
		return productPage;
	}
	@Override
	public Product getProductMessage(int pid) {
		// TODO Auto-generated method stub
		return proDAO.findProductMessage(pid);
	}
	//通过pid查询单个商品信息
	@Override
	public Product selectOneProduct(int pid) {
		
		return proDAO.selectOneProduct(pid);
	}
	
	//支付成功，减去购买数量
	@Override
	public boolean reduceProductInstock(int pid,int count) {
		if(proDAO.selectOneProduct(pid).getInStock()>count){
			return proDAO.reduceProductInstock(pid,count);
		}
		return false;
	}


}
