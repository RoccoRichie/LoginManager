package com.ait.user;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlRootElement;

@Path("/user")
@Stateless
@LocalBean
public class UserWebServices {

	@EJB
	private UserDAO userDAO = new UserDAO();

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response findAllUsers() {
		System.out.println("Get all Users");
		List<User> users = userDAO.getAllUsers();
		System.out.println("....got users....");
		System.out.println(users.size());
		return Response.status(200).entity(users).build();
	}

	@POST
	@Path("/login/")
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response findUserNamesPasswords(LoginDetails details) throws MalformedURLException {
		System.out.println("findUserNamesPasswords: " + details.getUsername() + " " + details.getPassword());
		URL userUrl = userDAO.successfulLoginUrl(details.getUsername(), details.getPassword());
		return Response.status(200).entity(userUrl).build();
	}

	@GET
	@Path("/find/{query}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response findUserByName(@PathParam("query") String query) {
		System.out.println("findUserByName: " + query);
		List<User> users = userDAO.getUsersByName(query);
		return Response.status(200).entity(users).build();
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/{empId}")
	public Response findUserById(@PathParam("empId") int empId) {
		User user = userDAO.getUserById(empId);
		return Response.status(200).entity(user).build();

	}

	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	public Response saveUser(User user) {
		userDAO.save(user);
		return Response.status(200).entity(user).build();
	}

	@PUT
	@Consumes("application/json")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response updateUser(User user) {
		userDAO.update(user);
		return Response.status(200).entity(user).build();
	}

	@DELETE
	@Path("/{empId}")
	public Response deleteUser(@PathParam("empId") int empId) {
		userDAO.delete(empId);
		return Response.status(204).build();
	}

}

@XmlRootElement
class LoginDetails {
	private String username;
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
