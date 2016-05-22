package com.ait.login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.ait.user.User;

@Stateless
@LocalBean
public class ValidateUser {

	@PersistenceContext
	private static EntityManager em;

	private static String url;
	private static String driver = null;
	public static String uRole = "";

	// I can't get checkUser2 method to work correctly with JPA so used
	// checkUser and JDBC under this method --> Need Help
	public static boolean checkUser2(String name, String password) {
		boolean userExists = false;
		User user = null;
		TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.userName = :id AND u.pWord = :pass",
				User.class);
		query.setParameter("id", name);
		query.setParameter("pass", password);
		try {
			user = query.getSingleResult();
			userExists = true;
			return userExists;
		} catch (NoResultException e) {
			System.out.println("CheckUser2:: .... ERROR getting the User");
		}
		if (userExists) {
			uRole = user.getUserRole();
			System.out.println("User Role assigned as:: " + uRole);
		} else {
			System.out.println("checkUser2():: .........User Does not Exist...........");
		}
		System.out.println(userExists);
		return userExists;
	}

	public static boolean checkUser(String userName, String pWord) {
		boolean userExists = false;
		try {

			// loading drivers for mysql
			Class.forName("com.mysql.jdbc.Driver");

			// creating connection with the database
			url = "jdbc:mysql://localhost:3306/UserLoginDB?user=root&password=admin";
			driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection connection = DriverManager.getConnection(url);

			PreparedStatement ps = connection
					.prepareStatement("select userRole from User where userName=? and pWord=?");
			ps.setString(1, userName);
			ps.setString(2, pWord);
			ResultSet rs = ps.executeQuery();
			userExists = rs.next();
			System.out.println(userExists);
			System.out.println(rs.getString("userRole"));

			if (userExists) {
				uRole = rs.getString("userRole");
				System.out.println("User Role assigned as:: " + uRole);
			} else {
				System.out.println(".........No Result Set...........");
			}

		} catch (NoResultException e) {
			System.err.println("...........NO USER MATCH...........");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(userExists);
		return userExists;
	}

}
