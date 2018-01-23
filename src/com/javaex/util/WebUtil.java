package com.javaex.util;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebUtil {
	
	static public void forward(HttpServletRequest request, 
							   HttpServletResponse response, 
							   String url) throws ServletException, IOException {

		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
		
	}
	
	static public void redirect(HttpServletRequest request, 
								HttpServletResponse response, 
								String url) throws IOException {
		
		response.sendRedirect(url);
		
	}
	
}
