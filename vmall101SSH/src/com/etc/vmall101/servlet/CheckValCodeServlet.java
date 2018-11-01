package com.etc.vmall101.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CheckValCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CheckValCodeServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");

		// 获取out输出对象---需要输出内容时加此句
		PrintWriter out = response.getWriter();
		
		// 获取session对象---需要session对象时加此句
		HttpSession session = request.getSession();
		
		String VCImg = (String) session.getAttribute("valCodeInSession");
		String VCInput = request.getParameter("VCInput");
		if(VCImg.toLowerCase().equals(VCInput.toLowerCase())){
			out.print("yes");
		}else{
			out.print("no");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

