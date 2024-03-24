package controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

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
import javax.ws.rs.core.Response.ResponseBuilder;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.json.JSONArray;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.ReserveDAO;
import dao.UserDAO;
import model.Reserve;
import model.User;

@Path("/services")
public class UserService {
	UserDAO userDao = new UserDAO();
	ReserveDAO reserv = new ReserveDAO();
	
	
	@GET
	@Path("/users")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<User> getUsers() {
		return userDao.getAllUsers();
	}
	
	
	@GET
	@Path("/users/{param}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<User> getUserByName(@PathParam("param") String name) {

		return userDao.getUserByName(name);

	}
	
	@POST
	@Path("/users")
	@Produces(MediaType.APPLICATION_JSON)
	public  Response addUsers(User user)  throws IOException {
		System.out.println(user.getName());
		boolean checkStatus = userDao.addUser(user);
		if (checkStatus == true)
			return Response.status(201).entity(" create successfully").build();
		else
			return Response.status(201).entity(" create fail").build();
	}
	
	
	@DELETE
	@Path("/users/{id}") 
	public Response deleteCustomer(@PathParam("id") int userId) throws IOException { 
		//System.out.println("asasas");
		boolean i = userDao.deleteUser(userId);
		if (i == true)
			return Response.status(200).entity(" update successfully").build();
		else
			return Response.status(404).entity(" update fail because cus_id not found.").build();
	}
	
	
	
	@PUT
	@Path("/users/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateCustomer(@PathParam("id") int userId, User user) throws IOException {
		System.out.println(user.getName());
		boolean i = userDao.updateUser(userId, user);
		if (i == true)
			return Response.status(200).entity(" update successfully").build();
		else
			return Response.status(404).entity(" update fail because cus_id not found.").build();
	}
		
	

	
	@GET
	@Path("/classTable")
//	@Produces(MediaType.APPLICATION_JSON)
	public String getClassTable() {
		return userDao.getClassTable();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	


	
	
	

	

	

}