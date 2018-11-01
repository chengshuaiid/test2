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

import com.etc.vmall101.bean.Product;
import com.etc.vmall101.service.ProductService;
import com.etc.vmall101.service.impl.ProductServiceImpl;

public class IndexProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public IndexProductServlet() {
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
		
		Product pro = new Product();
		ProductService proSer = new ProductServiceImpl();
		
		
		pro.setDetail("手机");
		List<Product> navPhonePros =  proSer.QueryProduct(pro);
		
		pro.setDetail("笔记本");
		List<Product> navComputerPros =  proSer.QueryProduct(pro);
		
		pro.setDetail("穿戴");
		List<Product> navWearPros =  proSer.QueryProduct(pro);
		
		pro.setDetail("智能家居");
		List<Product> navFurnPros =  proSer.QueryProduct(pro);
		
		pro.setDetail("通用配件");
		List<Product> navUniversalPros =  proSer.QueryProduct(pro);
		
		pro.setDetail("专属配件");
		List<Product> navExclusivePros =  proSer.QueryProduct(pro);
		
		List<Product> highSalesProduct =  proSer.findHighSalesProduct();
		
		request.setAttribute("navPhonePros", navPhonePros);
		request.setAttribute("navComputerPros", navComputerPros);
		request.setAttribute("navWearPros", navWearPros);
		request.setAttribute("navFurnPros", navFurnPros);
		request.setAttribute("navUniversalPros", navUniversalPros);
		request.setAttribute("navExclusivePros", navExclusivePros);
		request.setAttribute("highSalesProduct", highSalesProduct);
		
		request.getRequestDispatcher("index.jsp").forward(request,response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
