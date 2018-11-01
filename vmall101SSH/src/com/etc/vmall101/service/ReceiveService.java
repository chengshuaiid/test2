package com.etc.vmall101.service;

import java.util.List;

import com.etc.vmall101.bean.Receive;

public interface ReceiveService {
	
	//查询收件人单个信息表(通过userid,rname)
	Receive selectOneReceive(int userid,String rname);
	
	//查询收件人所有信息表(通过userid)
	List<Receive> selectAllReceive(int userid);
	
	//增加收货地址信息
	boolean addOneReceive(Receive receive);
	
	//填充orderlist的rid
}
