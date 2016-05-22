package com.ait.servlet.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UserLoginFromDB")
public class UserLoginFromDB extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private String url;
	private String driver = null;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			url = "jdbc:mysql://localhost:3306/UserLoginDB?user=root&password=admin";
			driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection connection = DriverManager.getConnection(url);

			String sql = "SELECT * FROM User ORDER BY empid";
			Statement statement = connection.createStatement();
			ResultSet resultset = statement.executeQuery(sql);
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<table><tr><td>EmployeeID</td><td>User Name</td><td>Password</td><td>User Role</td></tr>");
			while (resultset.next()) {
				out.println("<tr><td>" + resultset.getString("empId") + "</td><td>" + resultset.getString("userName")
						+ "</td><td>" + resultset.getString("pWord") + "</td><td>" + resultset.getString("userRole")
						+ "</td></tr>");
			}
			out.println("</table>");
			// Clean-up environment
			resultset.close();
			statement.close();
			connection.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		}
	} // end try

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
