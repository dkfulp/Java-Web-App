package com.cs330;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.*;
import com.google.gson.Gson;
import javax.naming.*;
import java.*;
import javax.*;

@Path("ws2")
public class ParkingServices {

	@Path("/parkingSpots/{lotId}")
	@GET
	@Produces("text/plain")
	public Response getSpots(@PathParam("lotId") String theId) throws NamingException,SQLException,ClassNotFoundException 	{
		
		//Get a reference to the ParkingFacade singleton object
		ParkingFacade pFacade = ParkingFacade.getInstance();
		
		//Call the ParkingFacade method getSpotsKimbel to get the spots in the lot
		ParkingSpot[] resultArray = pFacade.getSpots(theId);
		
		//Create a Json string representation of the array of spots
		if(resultArray != null){
			Gson theGsonObj = new Gson();
			String result = theGsonObj.toJson(resultArray);
			
			//Add the JSON string to the response message body
			ResponseBuilder rb = Response.ok(result, MediaType.TEXT_PLAIN);
			//Setting the HTTP status code to 200
			rb.status(200);
			//Create and return the Response object
			return rb.build();
		}//end if
		else {
			return Response.status(700).build();
		}
	}
	
	@Path("/openSpots/{lotId}")
	@GET
	@Produces("text/plain")
	public Response getOpenSpots(@PathParam("lotId") String theId) throws NamingException,SQLException,ClassNotFoundException 
	{
		
		//Get a reference to the ParkingFacade singleton object
		ParkingFacade pFacade = ParkingFacade.getInstance();
		
		//Call the ParkingFacade method getSpotsKimbel to get the spots in the lot
		ParkingSpot[] resultArray = pFacade.getOpenSpots(theId);
		
		//Create a Json string representation of the array of spots
		if(resultArray != null){
			Gson theGsonObj = new Gson();
			String result = theGsonObj.toJson(resultArray);
			
			//Add the JSON string to the response message body
			ResponseBuilder rb = Response.ok(result, MediaType.TEXT_PLAIN);
			//Setting the HTTP status code to 200
			rb.status(200);
			//Create and return the Response object
			return rb.build();
		}//end if
		else {
			return Response.status(700).build();
		}
	}
	
}