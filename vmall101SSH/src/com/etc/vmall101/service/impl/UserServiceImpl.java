package com.etc.vmall101.service.impl;

import java.util.List;

import com.etc.vamll101.vo.UserPage;
import com.etc.vmall101.bean.User;
import com.etc.vmall101.dao.UserDAO;
import com.etc.vmall101.dao.impl.UserDAOImpl;
import com.etc.vmall101.service.UserService;

public class UserServiceImpl implements UserService {
	private UserDAO userDAO = new UserDAOImpl();
	private UserPage userPage = new UserPage();

	@Override
	public User login(User user) {
		return userDAO.findUser(user);
	}

	@Override
	public boolean register(User user) {
		return userDAO.registerUser(user);
	}

	@Override
	public boolean updateSesionId(User user) {
		return userDAO.updateUserSessionId(user);
	}

	@Override
	public boolean registerName(User user) {
		return userDAO.findUserName(user);
	}

	@Override
	public boolean registerEmail(User user) {
		return userDAO.findEmail(user);
	}
	@Override
	public UserPage selectAllUser(int currentPage,int pageSize) {
		// TODO Auto-generated method stub
		List<User> userList = null;
		userList = userDAO.selectAllUser(currentPage,pageSize);
		userPage.setList(userList);
		
		userPage.setPageSize(pageSize);
		userPage.setCurrentPage(currentPage);
		int totalCount = userDAO.findTotalCount();
		userPage.setTotalCount(totalCount);
		int totalPage = (totalCount%pageSize==0)? totalCount/pageSize:totalCount/pageSize + 1;
		userPage.setTotalPage(totalPage);
		
		int pagrNumber = 5;
		int beginPage = currentPage - pagrNumber/2;
		int endPage = currentPage + pagrNumber/2;
		
		if(beginPage<1){
			beginPage = 1;
			endPage = pagrNumber;
		}
		if(endPage>totalPage){
			endPage = totalPage;
			beginPage = totalPage-pagrNumber+1;
		}
		if(totalPage<pagrNumber){
			beginPage = 1;
			endPage = totalPage;
		}
		userPage.setBeginPage(beginPage);
		userPage.setEndPage(endPage);
		return userPage;
	}

	@Override
	public UserPage selectUserEasy(String usersel, String selectuser) {
		// TODO Auto-generated method stub
		List<User> userList = null;
		userList = userDAO.selectUserEasy(usersel, selectuser);
		userPage.setList(userList);
		return userPage;
	}

	@Override
	public boolean deleteUserBatch(String[] userIds) {
		// TODO Auto-generated method stub
		boolean flag = false;
		flag = userDAO.deleteUserBatch(userIds);
		return flag;
	}

	@Override
	public UserPage selectUserComplex(String identiyty, String uname, String email) {
		// TODO Auto-generated method stub
		List<User> userList = null;
		userList = userDAO.selectUserComplex(identiyty, uname, email);
		userPage.setList(userList);
		return userPage;
	}

}
