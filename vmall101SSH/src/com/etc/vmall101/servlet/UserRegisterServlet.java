package com.etc.vmall101.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.etc.vmall101.bean.User;
import com.etc.vmall101.service.UserService;
import com.etc.vmall101.service.impl.UserServiceImpl;

public class UserRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserRegisterServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");	

		//获取out输出对象---需要输出内容时加此句
		PrintWriter out = response.getWriter();	   	

		//获取session对象---需要session对象时加此句
		HttpSession session = request.getSession();	

	    //获取application对象
		ServletContext application = this.getServletContext();

		//设置字符编码
		request.setCharacterEncoding("utf-8"); 
		
		String uname = request.getParameter("username");
		String pwd = request.getParameter("pwd");
		String repwd = request.getParameter("repwd");
		String email = request.getParameter("email");
		if(uname==null ||pwd==null ||repwd==null ||email==null){
			return;
		}
		if(!pwd.equals(repwd)){
			return;
		}
		User user = new User();
		user.setUname(uname);
		user.setPassword(pwd);
		user.setIdentity("用户");
		user.setEmail(email);
		user.setSessionid(Long.toString(System.currentTimeMillis()));
		UserService us = new UserServiceImpl();
		if(us.register(user)){
			out.print("<script>alert('注册成功！请登录');location='login.jsp'</script>");
		}else{
			out.print("<script>alert('注册失败！请重试');location='login.jsp'</script>");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
