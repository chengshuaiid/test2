package com.etc.vmall101.bean;

import java.util.Date;

public class ProductQuestion {
	private int pqid;
	private int pid;
	private int userId;
	private String qname;
	private String answer;
	private Date questTime;
	
	public int getPqid() {
		return pqid;
	}
	public void setPqid(int pqid) {
		this.pqid = pqid;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getQname() {
		return qname;
	}
	public void setQname(String qname) {
		this.qname = qname;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public Date getQuestTime() {
		return questTime;
	}
	public void setQuestTime(Date questTime) {
		this.questTime = questTime;
	}
}
