package com.etc.vmall101.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.etc.vmall101.bean.User;
import com.etc.vmall101.service.UserService;
import com.etc.vmall101.service.impl.UserServiceImpl;

public class CheckUsernameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CheckUsernameServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");

		// 获取out输出对象---需要输出内容时加此句
		PrintWriter out = response.getWriter();
		
		String username = request.getParameter("username");
		username = new String(username.getBytes("iso8859-1"),"utf8");
		
		UserService us = new UserServiceImpl();
		User user = new User();
		user.setUname(username);
		if(!us.registerName(user)){
			out.print("yes");
		}else{
			out.print("no");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
