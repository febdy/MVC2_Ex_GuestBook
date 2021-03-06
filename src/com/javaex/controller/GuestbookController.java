package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.GuestBookDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.GuestVo;

@WebServlet("/gbc")
public class GuestbookController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		GuestBookDao dao;
		GuestVo vo;
		String url;
		String action = request.getParameter("a");
		
		System.out.println("\nHI! This is Servlet.");

		if ("list".equals(action)) {
			System.out.println("This is list.");
			
			dao = new GuestBookDao();
			List<GuestVo> gList = dao.getList();
			
			request.setAttribute("gList", gList);
			url = "/WEB-INF/list.jsp";
			WebUtil.forward(request, response, url);
			
		} else if ("add".equals(action)) {
			System.out.println("This is add.");
			
			request.setCharacterEncoding("UTF-8");

			String name = request.getParameter("name");
			String password = request.getParameter("pass");
			String content = request.getParameter("content");
			
			dao = new GuestBookDao();
			vo = new GuestVo(1, name, password, content, "");

			dao.insert(vo);

			url = "gbc?a=list";
			WebUtil.redirect(request, response, url);

		} else if ("deleteform".equals(action)) {
			System.out.println("This is deleteform.");
			
			int no = Integer.parseInt(request.getParameter("no"));
			request.setAttribute("no", no);
			url = "/WEB-INF/deleteform.jsp";
			WebUtil.forward(request, response, url);
			
		} else if("delete".equals(action)){
			System.out.println("This is delete.");
			
			int no = Integer.parseInt(request.getParameter("no"));
			String password = request.getParameter("password");
			
			dao = new GuestBookDao();
			dao.delete(no, password);
			
			url = "gbc?a=list";
			WebUtil.redirect(request, response, url);
			
		} else {
			System.out.println("a is wrong value.");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
