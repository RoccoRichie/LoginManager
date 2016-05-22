package com.ait.servlet.login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ait.login.ValidateUser;

/***
 * Servlet implementation
 * 
 * class HowyaHorse
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		RequestDispatcher rs;
		String name = request.getParameter("userName");
		String passWord = request.getParameter("password");
		System.out.println("doGet():: User Name entered: " + name);
		System.out.println("doGet():: Password entered: " + passWord);

		if (ValidateUser.checkUser(name, passWord)) {
			switch (ValidateUser.uRole) {
			case "System Administrator":
				rs = request.getRequestDispatcher("dashboard-Admin.html");
				rs.forward(request, response);
				break;
			case "Customer Service Representative":
				rs = request.getRequestDispatcher("dashboard-CSR.html");
				rs.forward(request, response);
				break;
			case "Support Engineer":
				rs = request.getRequestDispatcher("dashboard-SEng.html");
				rs.forward(request, response);
				break;
			case "Network Management Engineer":
				rs = request.getRequestDispatcher("dashboard-NEng.html");
				rs.forward(request, response);
				break;

			default:
				System.out.println("....No User Role....");
				break;
			}
		} else {
			out.println("Username or Password incorrect");
			rs = request.getRequestDispatcher("index.html");
			rs.include(request, response);
		}

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
