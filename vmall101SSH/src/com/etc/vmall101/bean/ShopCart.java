package com.etc.vmall101.bean;

import java.util.Date;

public class ShopCart {
	private int shoId;
	private int userId;
	private int pid;
	private int count;
	private Product product;
	private Date inputTime;
	
	public int getShoId() {
		return shoId;
	}
	public void setShoId(int shoId) {
		this.shoId = shoId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Date getInputTime() {
		return inputTime;
	}
	public void setInputTime(Date inputTime) {
		this.inputTime = inputTime;
	}
}