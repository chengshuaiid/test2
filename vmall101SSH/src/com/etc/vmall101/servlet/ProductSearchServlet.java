package com.etc.vmall101.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.etc.vamll101.vo.ProsPages;
import com.etc.vmall101.service.ProductService;
import com.etc.vmall101.service.impl.ProductServiceImpl;

public class ProductSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ProductSearchServlet() {
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
		
		String keyWord = request.getParameter("keyWord");
		String time = request.getParameter("time");
		String hot = request.getParameter("hot");
		String price = "0";
		if(request.getParameter("price")!=null){
			price = request.getParameter("price");
		}
		if(keyWord==null){
			keyWord="";
		}
		if("GET".equals(request.getMethod())){
			keyWord = new String(keyWord.getBytes("iso8859-1"),"utf8");
		}
		int pageNum = Integer.parseInt(this.getInitParameter("pageNum"));
		int pages=1;
		if(request.getParameter("pages")!=null){
			pages = Integer.parseInt(request.getParameter("pages"));
		}
		
		ProductService proSer = new ProductServiceImpl();
		ProsPages prosPages = proSer.clickProduct(keyWord,pageNum,pages,time,hot,price);
		request.setAttribute("prosPages", prosPages);
		request.setAttribute("keyWord", keyWord);
		request.setAttribute("price", price);
		request.getRequestDispatcher("search.jsp").forward(request,response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
