package com.etc.vmall101.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.etc.vmall101.bean.Comment;
import com.etc.vmall101.bean.User;
import com.etc.vmall101.dao.CommentDao;
import com.etc.vmall101.util.JDBCUtil;

public class CommentDaoImpl implements CommentDao {
	private JDBCUtil jdbcUtil = JDBCUtil.getInstance();
	@Override
	public List<Comment> selectAllComment(int currentPage,int pageSize) {
		// TODO Auto-generated method stub
		List<Comment> commentList = new ArrayList<Comment>();
		String sql = "select * from comment limit ?,?";
		ResultSet rs = jdbcUtil.execQuery(sql,(currentPage-1)*pageSize,pageSize);
		try {
			while(rs.next()){
				int comId = rs.getInt("comid");
				int pid = rs.getInt("pid");
				int userId = rs.getInt("userid");
				String content = rs.getString("content");
				Date pubTime = rs.getTimestamp("pubtime");
				int score = rs.getInt("score");
				
				Comment comment = new Comment();
				comment.setComId(comId);
				comment.setPid(pid);
				comment.setUserId(userId);
				comment.setContent(content);
				comment.setPubTime(pubTime);
				comment.setScore(score);
				
				commentList.add(comment);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			jdbcUtil.closeConnection();
		}
		return commentList;
	}

	@Override
	public List<Comment> selectCommentEasy(String commentsel,String selectcomment) {
		// TODO Auto-generated method stub
		List<Comment> commentList = new ArrayList<Comment>();
		String sel ="";
		if("bypid".equals(commentsel)){
			sel = "pid";
		}else if("bycomid".equals(commentsel)){
			sel = "comid";
		}
		String sql = "select * from comment where "+sel+" = ?";
		ResultSet rs = jdbcUtil.execQuery(sql,selectcomment);
		try {
			while(rs.next()){
				int comId = rs.getInt("comid");
				int pid = rs.getInt("pid");
				int userId = rs.getInt("userid");
				String content = rs.getString("content");
				Date pubTime = rs.getTimestamp("pubtime");
				int score = rs.getInt("score");
				
				Comment comment = new Comment();
				comment.setComId(comId);
				comment.setPid(pid);
				comment.setUserId(userId);
				comment.setContent(content);
				comment.setPubTime(pubTime);
				comment.setScore(score);
				
				commentList.add(comment);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			jdbcUtil.closeConnection();
		}
		return commentList;
	}

	@Override
	public boolean deleteComment(String[] comIds) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer("delete from comment where comid in (");
		for(int i=0;i<comIds.length;i++){
			sb.append("?,");
		}
		sb.deleteCharAt(sb.length()-1).append(")");
		String sql = sb.toString();
		int i = jdbcUtil.execUpdate(sql, comIds);
		jdbcUtil.closeConnection();
		return i>0;
	}

	@Override
	public List<Comment> selectCommentComplex(String pname, String uname, String brginTime, String endTime) {
		// TODO Auto-generated method stub
		List<Comment> commentList = new ArrayList<Comment>();
		String sql = null;
		ResultSet rs = null;
		System.out.println(pname);
		System.out.println(uname);
		System.out.println(brginTime);
		System.out.println(endTime);
		if("".equals(brginTime) && "".equals(endTime)){
			sql = "select comment.* from comment,product,user where comment.pid=product.pid and comment.userid=user.userid and pname like ? and uname like ?";
			rs = jdbcUtil.execQuery(sql, "%"+pname+"%","%"+uname+"%");
		}else{
			sql = "select comment.* from comment,product,user where comment.pid=product.pid and comment.userid=user.userid and pname like ? and uname like ? and pubtime>? and pubtime<?";
			rs = jdbcUtil.execQuery(sql, "%"+pname+"%","%"+uname+"%",brginTime+" 00:00:00",endTime+" 23:59:59");
			
		}
		try {
			while(rs.next()){
				int comId = rs.getInt("comid");
				int pid = rs.getInt("pid");
				int userId = rs.getInt("userid");
				String content = rs.getString("content");
				Date pubTime = rs.getTimestamp("pubtime");
				int score = rs.getInt("score");
				
				Comment comment = new Comment();
				comment.setComId(comId);
				comment.setPid(pid);
				comment.setUserId(userId);
				comment.setContent(content);
				comment.setPubTime(pubTime);
				comment.setScore(score);
				
				commentList.add(comment);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return commentList;
	}

	@Override
	public int findTotalCount() {
		// TODO Auto-generated method stub
		String sql = "select count(*) from comment";
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

	@Override
	public List<Comment> findCommentPage(int pid,int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		List<Comment> List = new ArrayList<Comment>();
		JDBCUtil jdbcUtil=JDBCUtil.getInstance();
		String sql="select comment.*,user.uname from comment,user where pid=? and comment.userid=user.userid limit ?,?";
		ResultSet rs=jdbcUtil.execQuery(sql, pid,(currentPage-1) * pageSize, pageSize);
		
		try {
			while(rs.next()){
				
				Comment comment=new Comment();
				User user=new User();
				comment.setComId(rs.getInt("comid"));
				comment.setPid(pid);
				comment.setUserId(rs.getInt("userid"));
				comment.setContent(rs.getString("content"));
				comment.setPubTime(rs.getTimestamp("pubtime"));
				comment.setScore(rs.getInt("score"));
				user.setUname(rs.getString("uname"));
				comment.setUser(user);
				List.add(comment);
				
			}
			return List;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			jdbcUtil.closeConnection();
		}
		return null;
	}

	

	@Override
	public int findTotalCount(int pid) {
		String sql = "select count(*) from comment where pid=?";
		JDBCUtil jdbcUtil = JDBCUtil.getInstance();
		ResultSet rs = jdbcUtil.execQuery(sql,pid);

		try {
			if(rs.next()){
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return -1;
	}

	@Override
	public boolean insertComment(Comment comment) {
		String sql = "insert into comment values(null, ?, ?, ?, ?, ?)";
		JDBCUtil jdbcUtil = JDBCUtil.getInstance();
		return jdbcUtil.execUpdate(sql, comment.getPid(),comment.getUserId(),comment.getContent(),comment.getPubTime(),comment.getScore()) > 0;
	}

}
