package com.etc.vmall101.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.etc.vmall101.bean.ShopCart;
import com.etc.vmall101.bean.User;
import com.etc.vmall101.service.ProductService;
import com.etc.vmall101.service.ShopCartService;
import com.etc.vmall101.service.impl.ProductServiceImpl;
import com.etc.vmall101.service.impl.ShopCartServiceImpl;

public class ShowCartShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ShowCartShowServlet() {
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
		
		if(session.getAttribute("user")!=null){
			User u = (User) session.getAttribute("user");
			ShopCartService shopSer = new ShopCartServiceImpl();
			List<ShopCart> scs = shopSer.ShopCarts(u.getUserId());
			session.setAttribute("shopCartList", scs);
			response.sendRedirect("IndexProductServlet");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
