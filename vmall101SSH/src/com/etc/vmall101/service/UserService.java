package com.etc.vmall101.service;

import com.etc.vamll101.vo.UserPage;
import com.etc.vmall101.bean.User;

public interface UserService {
	User login(User user);
	boolean register(User user);
	boolean updateSesionId(User user);
	boolean registerName(User user);
	boolean registerEmail(User user);
	UserPage selectAllUser(int currentPage,int pageSize);
	UserPage selectUserEasy(String usersel,String selectuser);
	boolean deleteUserBatch(String[] userIds);
	UserPage selectUserComplex(String identiyty,String uname,String email);
}
