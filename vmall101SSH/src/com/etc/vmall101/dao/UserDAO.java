package com.etc.vmall101.dao;

import java.util.List;

import com.etc.vmall101.bean.User;

public interface UserDAO {
	User findUser(User user);
	boolean registerUser(User user);
	boolean updateUserSessionId(User user);
	boolean findUserName(User user);
	boolean findEmail(User user);
	List<User> selectAllUser(int currentPage,int pageSize);
	List<User> selectUserEasy(String usersel,String selectuser);
	boolean deleteUserBatch(String[] userIds);
	List<User> selectUserComplex(String identiyty,String uname,String email);
	int findTotalCount();
}
