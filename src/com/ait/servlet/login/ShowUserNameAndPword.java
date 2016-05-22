package com.ait.servlet.login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/***
 * Servlet implementation
 * 
 * class HowyaHorse
 */
@WebServlet("/ShowUserNameAndPword")
public class ShowUserNameAndPword extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head><title>Login Info</title></head>");
		out.println("<body>" + "<h1>Get Login Info:-)</h1>" + "<p>User Name: " + request.getParameter("userName")
				+ "</p>" + "<p>Password: " + request.getParameter("password") + "</p></body></html>");
		out.flush();
		out.close();
		System.out.println("User Name entered: " + request.getParameter("userName"));
		System.out.println("Password entered: " + request.getParameter("password"));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
