package com.etc.vmall101.dao;

import java.util.List;

import com.etc.vmall101.bean.Receive;

public interface ReceiveDAO {
	
	//查询收件人单个信息表(通过userid,rname)
	Receive selectOneReceive(int userid,String rname);
	
	//查询收件人单个信息表（通过rid）
	Receive selectOneReceive(int rid);
	
	//查询收件人所有信息表(通过userid)
	List<Receive> selectAllReceive(int userid);
	
	//增加收货地址信息
	boolean addOneReceive(Receive receive);
}
