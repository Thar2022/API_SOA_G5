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

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.ClassTableDAO;
import dao.UserDAO;
import model.ClassTable;
import model.User;

@Path("/tebleservices")
public class TableClassService {
	ClassTableDAO taDao = new ClassTableDAO();

	@GET
	@Path("/table")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<ClassTable> getTableClass() {
		return taDao.getAllClassTables();
	}
  

}