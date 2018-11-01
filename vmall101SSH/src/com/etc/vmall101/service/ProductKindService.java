package com.etc.vmall101.service;

import java.util.List;

import com.etc.vamll101.vo.ProductKindPage;
import com.etc.vmall101.bean.Kind;


public interface ProductKindService {
	ProductKindPage selectProductKindEasy(String kindsel,String selectKind);
	ProductKindPage selectAllProductKind(int currentPage,int pageSize);
	List<Kind> getAllProductKind();
	boolean addProductKind(String color,String stand,String kname);
	String selectOneKindColor(String kname);
	String selectOneKindStand(String kname);
	Kind getProductKind(int kid);
}
