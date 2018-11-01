package com.etc.vamll101.vo;

import java.util.List;

public abstract class BasePages<T> {
	private int pageNum;
	private int prosNum;
	private int pages;
	private int allPages;
	private List<T> prosList;
	private int beginPages;
	private int endPages;
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getProsNum() {
		return prosNum;
	}
	public void setProsNum(int prosNum) {
		this.prosNum = prosNum;
	}
	public int getPages() {
		return pages;
	}
	public void setPages(int pages) {
		this.pages = pages;
	}
	public int getAllPages() {
		return allPages;
	}
	public void setAllPages(int allPages) {
		this.allPages = allPages;
	}
	public List<T> getProsList() {
		return prosList;
	}
	public void setProsList(List<T> prosList) {
		this.prosList = prosList;
	}
	public int getBeginPages() {
		return beginPages;
	}
	public void setBeginPages(int beginPages) {
		this.beginPages = beginPages;
	}
	public int getEndPages() {
		return endPages;
	}
	public void setEndPages(int endPages) {
		this.endPages = endPages;
	}
	
}
