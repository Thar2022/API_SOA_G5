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
import dao.ClassExcerciseDAO;
import dao.ReserveDAO;
import dao.UserDAO;
import model.ClassExercise;
import model.User;


@Path("/services")


public class HomePageService {
	ClassExcerciseDAO classExcerciseDao = new ClassExcerciseDAO();
	ReserveDAO reserv = new ReserveDAO();
	
	
	@GET
	@Path("/homepage")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<ClassExercise> getClassExercises() {
		return classExcerciseDao.getAllClassExercises();
	}
//	@GET
//    @Path("/download")
//    @Produces("image/png")
//    public Response getCompanyLogo() throws IOException {
//        String filePath = "C:/Users/Asus/Desktop/soaaa/soa_project_05/public/assets/images/cena.png";
//        File file = new File(filePath);
//
//        ResponseBuilder response = Response.ok((Object) file);
//
//        response.header("Content-Disposition", "attachment; filename=cena.png");
//
//        return response.build();
//    }
	
	
//	@GET
//	@Path("/classex/{param}")
//	@Produces(MediaType.APPLICATION_JSON)
//	public ArrayList<User> getUserByName(@PathParam("param") String name) {
//
//		return classExerciseDao.getUserByName(name);
//
//	}
	
	@POST
	@Path("/homepage")
	@Produces(MediaType.APPLICATION_JSON)
	public  Response addUsers(ClassExercise classExercise)  throws IOException {
		System.out.println(classExercise.getName());
		boolean checkStatus = classExcerciseDao.addClassExercise(classExercise);
		if (checkStatus == true)
			return Response.status(201).entity(" create successfully").build();
		else
			return Response.status(201).entity(" create fail").build();
	}
	
	
	@DELETE
	@Path("/classex/{id}") 
	public Response deleteCustomer(@PathParam("id") int classExerciseId) throws IOException { 
		//System.out.println("asasas");
		boolean i = classExcerciseDao.deleteClassExercise(classExerciseId);
		if (i == true)
			return Response.status(200).entity(" update successfully").build();
		else
			return Response.status(404).entity(" update fail because cus_id not found.").build();
	}
	
	
	
	@PUT
	@Path("/classex/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateCustomer(@PathParam("id") int classExerciseId, ClassExercise classExercise) throws IOException {
		System.out.println(classExercise.getName());
		boolean i = classExcerciseDao.updateClassExercise(classExerciseId, classExercise);
		if (i == true)
			return Response.status(200).entity(" update successfully").build();
		else
			return Response.status(404).entity(" update fail because cus_id not found.").build();
	}
		
	

	
//	@GET
//	@Path("/classTable")
////	@Produces(MediaType.APPLICATION_JSON)
//	public String getClassTable() {
//		return userDao.getClassTable();
//	}
//	@GET
//	@Path("/classEX")
////	@Produces(MediaType.APPLICATION_JSON)
//	public String getClassEX() {
//		return classExerciseDao.getClassEx();
//	}

}
