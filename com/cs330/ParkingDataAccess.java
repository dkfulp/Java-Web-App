package com.cs330;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;
import java.util.*;
import java.*;
import javax.*;

public class ParkingDataAccess {
	private static ParkingDataAccess singleton;
	
	private DataSource dataSource;
	
	private ParkingDataAccess() throws NamingException, SQLException {
		Context initContext = new InitialContext();
		Context envContext = (Context) initContext.lookup("java:/comp/env");
		this.dataSource = (DataSource) envContext.lookup("jdbc/csci490");
		//Connection con = dataSource.getConnection();
	}
	
	public static ParkingDataAccess getInstance() throws NamingException, SQLException {
		if(singleton == null){
			singleton = new ParkingDataAccess();
		}
		
		return singleton;
	}
	
	public Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}
}