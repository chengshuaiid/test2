package com.etc.vmall101.service.impl;

import java.util.List;

import com.etc.vmall101.bean.Receive;
import com.etc.vmall101.dao.ReceiveDAO;
import com.etc.vmall101.dao.impl.ReceiveDAOImpl;
import com.etc.vmall101.service.ReceiveService;

public class ReceiveServiceImpl implements ReceiveService{
	private ReceiveDAO receiveImple = new ReceiveDAOImpl();
	
	//查询收件人单个信息表(通过userid,rname)
	@Override
	public Receive selectOneReceive(int userid, String rname) {
		
		return receiveImple.selectOneReceive(userid, rname);
	}
	
	//查询收件人所有信息表(通过userid)
	@Override
	public List<Receive> selectAllReceive(int userid) {
		
		return receiveImple.selectAllReceive(userid);
	}
	
	//增加收货地址信息
	@Override
	public boolean addOneReceive(Receive receive) {
		if(receiveImple.selectOneReceive(receive.getRid())!=null){
			return false;
		}
		return receiveImple.addOneReceive(receive);
	}
	
}
