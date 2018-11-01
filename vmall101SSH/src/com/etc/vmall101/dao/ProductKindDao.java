package com.etc.vmall101.dao;

import java.util.List;

import com.etc.vmall101.bean.Kind;

public interface ProductKindDao {
	List<Kind> selectProductKindEasy(String kindsel, String selectKind);
	List<Kind> selectAllProductKind(int currentPage,int pageSize);
	List<Kind> getAllProductKind();
	boolean addProductKind(String color,String stand,String kname);
	String selectOneKindColor(String kid);
	String selectOneKindStand(String kid);
	int findTotalCount();
	Kind findProductKind(int kid);
}
