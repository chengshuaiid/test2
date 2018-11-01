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
import com.etc.vmall101.util.CookieUtil;

public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserLoginServlet() {
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
		String remenber = request.getParameter("remenber");
		if(uname==null || pwd==null){
			out.println("<script>alert('还未登录！请登录');location='login.jsp'</script>");
			return;
		}
		User user = new User();
		user.setUname(uname);
		user.setPassword(pwd);
		UserService us = new UserServiceImpl();
		if(us.login(user)!=null){
			User u = us.login(user);
			if(remenber==null){
				session.setMaxInactiveInterval(900);
			}
			session.setAttribute("user", u);
			if("用户".equals(u.getIdentity())){
				response.sendRedirect("ShowCartShowServlet");
			}else{
				out.print("<script>alert('管理员');location='login.jsp'</script>");
			}
		}else{
			out.println("<script>alert('用户名或密码错误，请重新输入');location='login.jsp'</script>");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
