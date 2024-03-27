package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import dao.ReserveDAO;
import dao.UserDAO;
import model.Reserve;
import model.User;

@Path("/services")
public class ReserveService {
	ReserveDAO reserv = new ReserveDAO();
	
	@GET
	@Path("/reserves")
	@Produces(MediaType.APPLICATION_JSON)
	public String getUsers() {
		return reserv.getAllReserves();
	}

	@DELETE
	@Path("/reserves/{id}") 
	public Response deleteCustomer(@PathParam("id") int userId) throws IOException { 
		boolean i = reserv.deleteReserve(userId);
		if (i == true)
			return Response.status(200).entity(" update successfully").build();
		else
			return Response.status(404).entity(" update fail because cus_id not found.").build();
	}
	@POST
	@Path("/reserves")
	@Produces(MediaType.APPLICATION_JSON)
	public  Response addUsers(Reserve user)  throws IOException {
		//System.out.println(user.getName());
		boolean checkStatus = reserv.addReserve(user);
		if (checkStatus == true)
			return Response.status(201).entity(" create successfully").build();
		else
			return Response.status(201).entity(" create fail").build();
	}
	
	
}
