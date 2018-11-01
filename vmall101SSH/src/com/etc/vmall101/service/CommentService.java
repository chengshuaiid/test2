package com.etc.vmall101.service;

import com.etc.vamll101.vo.CommentPage;
import com.etc.vmall101.bean.Comment;


public interface CommentService {
	CommentPage selectAllComment(int currentPage,int pageSize);
	CommentPage selectCommentEasy(String commentsel,String selectcomment);
	boolean deleteComment(String[] comIds);
	CommentPage selectCommentComplex(String pname,String uname,String brginTime,String endTime);
	//获取评论分页
	CommentPage getCommentPage(int pid,int currentPage, int pageSize);
	
	//添加评论
	boolean addComment(Comment comment);
}
