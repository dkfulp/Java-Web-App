package com.cs330;
import java.sql.*;
import javax.naming.*;
import java.util.*;
import java.*;
import javax.*;

public class ParkingFacade {
	private static ParkingFacade singleton;
	
	private ParkingDataAccess dao;
	
	private ParkingFacade () throws NamingException, SQLException {
		this.dao = ParkingDataAccess.getInstance();
	}
	
	public static ParkingFacade getInstance() throws NamingException, SQLException {
		
		if(singleton == null)
		{
			singleton = new ParkingFacade();
		}
		return singleton;
	}
	
	//Lot Reference Method Used to Determine Which Lot We Are Talking About//
	public static String determineLot(String lotId){
		String temp = "No lot";
		if(lotId.equalsIgnoreCase("kimbel") || lotId.equalsIgnoreCase("kimbellot")){
			temp = "kimbellot";
			return temp;
		}
		else if(lotId.equalsIgnoreCase("extended") || lotId.equalsIgnoreCase("extendedlot"))
		{
			temp = "extendedlot";
			return temp;
		}
		else if(lotId.equalsIgnoreCase("science") || lotId.equalsIgnoreCase("sciencelot")){
			temp = "sciencelot";
			return temp;
		}
		else
		{
			temp = "nulllot";
			return temp;
		}
	}
	
	//PARKING FACADE METHODS ARE BELOW:  IF YOU ARE GOING TO ALTER ANYTHING THEY ARE DOWN THERE//
	
	public ParkingSpot [] getSpots(String lotId) throws SQLException, ClassNotFoundException {
		//get the connection from the Data Access singleton object
		Connection con = dao.getConnection();
		
		//finding out which lot is being referred to//
		String lot = determineLot(lotId);
		
		//Execute the query
		Statement stmt = con.createStatement();
		String query = "SELECT * FROM " + lot;
		ResultSet rs = stmt.executeQuery(query);
		
		//Build the array of ParkingSpot objects
		ParkingSpot[] spotArray = new ParkingSpot[1000];
		int count = 0;
		while(rs.next()) {
			//create Parking spot objects and load them into the array here//
			String parkingLotId = rs.getString("parkingLotId");
			String parkingSpotId = rs.getString("parkingSpotId");
			int bool = rs.getInt("occupied");
			boolean occupied;
			if(bool == 0){
				occupied = false;
			}
			else{
				occupied = true;
			}
			int occupiedBy = rs.getInt("occupiedBy");
			String spaceType = rs.getString("spaceType");
			//put timer collector here//
			int spotTimeLimit = rs.getInt("spotTimeLimit");
			int boolTime= rs.getInt("overTime");
			boolean overTime;
			if(boolTime == 0){
				overTime = true;
			}
			else {
				overTime = false;
			}
			
			ParkingSpot spot = new ParkingSpot(parkingLotId,parkingSpotId,spaceType,spotTimeLimit);

			if(occupied == true){
				spot.setOccupied(true);
				spot.setOccupiedBy(occupiedBy);
			}
			if(occupied == false){
				spot.setOccupied(false);
				spot.setOccupiedBy(0);
			}
			if(overTime = true){
				spot.setOverTime(true);
			}
			if(overTime = false){
				spot.setOverTime(false);
			}
			
			spotArray[count] = spot;
			count++;
		}
		if(count > 0) {
			spotArray = Arrays.copyOf(spotArray, count);
			return spotArray;
		}
		else{
			return null;
		}
	}

	public ParkingSpot [] getOpenSpots(String lotId) throws SQLException, ClassNotFoundException {
		//get the connection from the Data Access singleton object
		Connection con = dao.getConnection();
		
		//finding out which lot is being referred to//
		String lot = determineLot(lotId);
		
		//Execute the query
		Statement stmt = con.createStatement();
		String query = "SELECT * FROM " + lot + " WHERE occupied IS NULL";
		ResultSet rs = stmt.executeQuery(query);
		
		//Build the array of ParkingSpot objects
		ParkingSpot[] spotArray = new ParkingSpot[1000];
		int count = 0;
		while(rs.next()) {
			//create Parking spot objects and load them into the array here//
			String parkingLotId = rs.getString("parkingLotId");
			String parkingSpotId = rs.getString("parkingSpotId");
			int bool = rs.getInt("occupied");
			boolean occupied;
			if(bool == 0){
				occupied = false;
			}
			else{
				occupied = true;
			}
			int occupiedBy = rs.getInt("occupiedBy");
			String spaceType = rs.getString("spaceType");
			//put timer collector here//
			int spotTimeLimit = rs.getInt("spotTimeLimit");
			int boolTime= rs.getInt("overTime");
			boolean overTime;
			if(boolTime == 0){
				overTime = true;
			}
			else {
				overTime = false;
			}
			
			ParkingSpot spot = new ParkingSpot(parkingLotId,parkingSpotId,spaceType,spotTimeLimit);

			if(occupied == true){
				spot.setOccupied(true);
				spot.setOccupiedBy(occupiedBy);
			}
			if(occupied == false){
				spot.setOccupied(false);
				spot.setOccupiedBy(0);
			}
			if(overTime = true){
				spot.setOverTime(true);
			}
			if(overTime = false){
				spot.setOverTime(false);
			}
			
			spotArray[count] = spot;
			count++;
		}
		if(count > 0) {
			spotArray = Arrays.copyOf(spotArray, count);
			return spotArray;
		}
		else{
			return null;
		}
	}
}