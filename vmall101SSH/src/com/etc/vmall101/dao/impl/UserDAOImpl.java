package com.etc.vmall101.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.etc.vmall101.bean.User;
import com.etc.vmall101.dao.UserDAO;
import com.etc.vmall101.util.JDBCUtil;

public class UserDAOImpl implements UserDAO {
	private JDBCUtil jdbcUtil = JDBCUtil.getInstance();

	@Override
	public User findUser(User user) {
		String sql = "select * from user where uname=? and password=?";
		ResultSet rs = jdbcUtil.execQuery(sql, user.getUname(),user.getPassword());
		try {
			if(rs.next()){
				User u = new User();
				u.setUserId(rs.getInt(1));
				u.setUname(rs.getString(2));
				u.setPassword(rs.getString(3));
				u.setIdentity(rs.getString(4));
				u.setEmail(rs.getString(5));
				u.setSessionid(rs.getString(6));
				return u;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			jdbcUtil.closeConnection();
		}
		return null;
	}

	@Override
	public boolean registerUser(User user) {
		String sql = "insert into user values(null,?,?,?,?,?)";
		return jdbcUtil.execUpdate(sql, user.getUname(),user.getPassword(),user.getIdentity(),user.getEmail(),user.getSessionid())>0;
	}

	@Override
	public boolean updateUserSessionId(User user) {
		String sql = "update user set sessionid=? where userid=?";
		return jdbcUtil.execUpdate(sql,user.getSessionid(),user.getUserId())>0;
	}

	@Override
	public boolean findUserName(User user) {
		String sql = "select * from user where uname=?";
		ResultSet rs = jdbcUtil.execQuery(sql, user.getUname());
		try {
			if(rs.next()){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			jdbcUtil.closeConnection();
		}
		return false;
	}

	@Override
	public boolean findEmail(User user) {
		String sql = "select * from user where email=?";
		ResultSet rs = jdbcUtil.execQuery(sql, user.getEmail());
		try {
			if(rs.next()){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			jdbcUtil.closeConnection();
		}
		return false;
	}

	@Override
	public List<User> selectAllUser(int currentPage, int pageSize) {
		List<User> userList = new ArrayList<User>();
		
		String sql = "select * from user order by userid limit ?,?";
		ResultSet rs = jdbcUtil.execQuery(sql,(currentPage-1)*pageSize,pageSize);
		try {
			while(rs.next()){
				int userId = rs.getInt("userid");
				String uname = rs.getString("uname");
				String password = rs.getString("password");
				String identity = rs.getString("identity");
				String email = rs.getString("email");
				
				User user = new User();
				user.setUserId(userId);
				user.setUname(uname);
				user.setPassword(password);
				user.setIdentity(identity);
				user.setEmail(email);
				userList.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			jdbcUtil.closeConnection();
		}
		return userList;
	}

	@Override
	public List<User> selectUserEasy(String usersel, String selectuser) {
		List<User> userList = new ArrayList<User>();
		String sel ="";
		if("byuserid".equals(usersel)){
			sel = "userid";
		}else if("byuname".equals(usersel)){
			sel = "uname";
		}
		String sql = "select * from user where "+sel+" = ?";
		ResultSet rs = jdbcUtil.execQuery(sql,selectuser);
		try {
			while(rs.next()){
				int userId = rs.getInt("userid");
				String uname = rs.getString("uname");
				String password = rs.getString("password");
				String identity = rs.getString("identity");
				String email = rs.getString("email");
				
				User user = new User();
				user.setUserId(userId);
				user.setUname(uname);
				user.setPassword(password);
				user.setIdentity(identity);
				user.setEmail(email);
				userList.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			jdbcUtil.closeConnection();
		}
		return userList;
	}

	@Override
	public boolean deleteUserBatch(String[] userIds) {
		StringBuffer sb = new StringBuffer("delete from user where userid in (");
		for(int i=0;i<userIds.length;i++){
			sb.append("?,");
		}
		sb.deleteCharAt(sb.length()-1).append(")");
		String sql = sb.toString();
		int i = jdbcUtil.execUpdate(sql, userIds);
		jdbcUtil.closeConnection();
		return i>0;
	}

	@Override
	public List<User> selectUserComplex(String identiyty, String uname,
			String email) {
		List<User> userList = new ArrayList<User>();
		String sql = "select * from user where identity like ? and uname like ? and email like ?";
		ResultSet rs = jdbcUtil.execQuery(sql,"%"+identiyty+"%","%"+uname+"%","%"+email+"%");
		
		try {
			while(rs.next()){
				int userId = rs.getInt("userid");
				String unames = rs.getString("uname");
				String password = rs.getString("password");
				String identity = rs.getString("identity");
				String emails = rs.getString("email");
				
				User user = new User();
				user.setUserId(userId);
				user.setUname(unames);
				user.setPassword(password);
				user.setIdentity(identity);
				user.setEmail(emails);
				userList.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userList;
	}

	@Override
	public int findTotalCount() {
		String sql = "select count(*) from user";
		ResultSet rs = jdbcUtil.execQuery(sql);
		try {
			if(rs.next()){
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}

}
