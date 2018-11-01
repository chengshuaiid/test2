package com.etc.vmall101.service.impl;

import java.util.List;

import com.etc.vamll101.vo.CommentPage;
import com.etc.vmall101.bean.Comment;
import com.etc.vmall101.dao.CommentDao;
import com.etc.vmall101.dao.impl.CommentDaoImpl;
import com.etc.vmall101.service.CommentService;

public class CommentServiceImpl implements CommentService {
	private CommentDao cd = new CommentDaoImpl();
	private CommentPage commentPage = new CommentPage();
	@Override
	public CommentPage selectAllComment(int currentPage,int pageSize) {
		// TODO Auto-generated method stub
		List<Comment> commentList = null;
		commentList = cd.selectAllComment(currentPage,pageSize);
		commentPage.setList(commentList);
		
		commentPage.setPageSize(pageSize);
		commentPage.setCurrentPage(currentPage);
		int totalCount = cd.findTotalCount();
		commentPage.setTotalCount(totalCount);
		int totalPage = (totalCount%pageSize==0)? totalCount/pageSize:totalCount/pageSize + 1;
		commentPage.setTotalPage(totalPage);
		
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
		commentPage.setBeginPage(beginPage);
		commentPage.setEndPage(endPage);
		return commentPage;
	}

	@Override
	public CommentPage selectCommentEasy(String commentsel, String selectcomment) {
		// TODO Auto-generated method stub
		List<Comment> commentList = null;
		commentList = cd.selectCommentEasy(commentsel, selectcomment);
		commentPage.setList(commentList);
		return commentPage;
	}

	@Override
	public boolean deleteComment(String[] comIds) {
		// TODO Auto-generated method stub
		boolean flag = false;
		flag = cd.deleteComment(comIds);
		return flag;
	}

	@Override
	public CommentPage selectCommentComplex(String pname, String uname, String brginTime, String endTime) {
		// TODO Auto-generated method stub
		List<Comment> commentList = null;
		commentList = cd.selectCommentComplex(pname, uname, brginTime, endTime);
		commentPage.setList(commentList);
		return commentPage;
	}
	@Override
	public CommentPage getCommentPage(int pid, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		CommentPage commentPage=new CommentPage();
		//分页大小
		commentPage.setPageSize(pageSize);
		
		//当前页码
		commentPage.setCurrentPage(currentPage);
		
		//总记录数
		int totalCount = cd.findTotalCount(pid);
		commentPage.setTotalCount(totalCount);
		
		//总页数
		int totalPage = (totalCount % pageSize==0) ?  totalCount / pageSize :  totalCount / pageSize + 1;
		commentPage.setTotalPage(totalPage);
		
		//数据列表
		List<Comment> list = cd.findCommentPage(pid, currentPage, pageSize);
		commentPage.setList(list);
		
		int pageNumber = 5;
		
		//计算起止页码
		int beginPage = currentPage - pageNumber / 2;
		int endPage = currentPage + pageNumber / 2;
		
		//调整起始页码
		if(beginPage < 1){
			beginPage = 1;
			endPage = pageNumber;
		}
		
		//调整结束页码
		if(endPage > totalPage){
			endPage = totalPage;
			beginPage = totalPage - pageNumber + 1;
		}
		
		if(totalPage < pageNumber){
			beginPage = 1;
			endPage = totalPage;
		}
		
		//设置起止页码
		commentPage.setBeginPage(beginPage);
		commentPage.setEndPage(endPage);
		
		return commentPage;
	}

	@Override
	public boolean addComment(Comment comment) {
		// TODO Auto-generated method stub
		if(cd.insertComment(comment)){   
				return true;
		}
		return false;
		
	}

}
