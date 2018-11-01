package com.etc.vmall101.dao;

import java.util.List;

import com.etc.vmall101.bean.Comment;

public interface CommentDao {
	List<Comment> selectAllComment(int currentPage,int pageSize);
	List<Comment> selectCommentEasy(String commentsel,String selectcomment);
	boolean deleteComment(String[] comIds);
	List<Comment> selectCommentComplex(String pname,String uname,String brginTime,String endTime);
	int findTotalCount();
	List<Comment> findCommentPage(int pid,int currentPage, int pageSize);
	 //查找全部的新闻记录总数
	 int findTotalCount(int pid);
	 //插入评论
	 boolean insertComment(Comment comment);
}
