import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/***************************************************
 * ParkingSimulationConsole.java - This java class is
 * meant to test the parking web application that we 
 * currently have.  It implements a sleeper thread to
 * slow it down to a real time speed during a class 
 * switch time.
 * 
 *
 * @author: Dakota Fulp
 * CCU Parking Application
 * Course: CSCI 490
 * Email: dkfulp@g.coastal.edu
 * Date: 11/15/2016
 * @version: 1.0
 ***************************************************/
public class ParkingSimulationConsole 
{
	//ATTRIBUTES ----------------------------------//
	
	private static String[] kimbelSpots;
	
	private String[] extendedSpots;
	
	private String[] scienceSpots;
	
	//MAIN FUNCTION -------------------------------//
	public static void main(String[] args) throws SQLException,ClassNotFoundException 
	{
		//1. First we make the connection to the database
		//This will allow us to change whether a spot has
		//been occupied or not.
		String connectStr = "jdbc:mysql://localhost:3306/csci490";
		String username = "root";
		String password = "csci330pass";
		
		String driver = "com.mysql.jdbc.Driver";
		
		Connection con = DriverManager.getConnection(connectStr,username,password);
		Statement stmt = con.createStatement();
		
		//2. Now we load the arrays with all of their spots.
		//We will do this using sql commands to save time.
		kimbelSpots = new String[144];
		String sql = "SELECT parkingSpotId FROM kimbellot";
		
		ResultSet rs = stmt.executeQuery(sql);
		int count = 0;
		while(rs.next()){
			kimbelSpots[count] = rs.getString("parkingSpotId");
			System.out.println(kimbelSpots[count]);
			count++;
		}
		//3, Set up timer and start looping through to change
		//which spots are occupied or not.
		int timer = 10000;
		
		while(timer >= 0)
		{
			int randSpot = (int)(Math.random() * 143);
			
			String spotId = kimbelSpots[randSpot];
			
			sql = "SELECT occupied FROM kimbellot WHERE parkingSpotId = '"+spotId+"';";
			
			rs = stmt.executeQuery(sql);
			boolean occupied;
			while(rs.next()){
				int occu = rs.getInt("occupied");
				System.out.println("Time: " + timer + " " + occu);
				if(occu == 1){
					occupied = true;
				}
				else{
					occupied = false;
				}
			}
			
			if(occupied = false){
				sql = "UPDATE kimbellot SET occupied = 1 WHERE parkingSpotId = '"+spotId+"';";
				stmt.executeUpdate(sql);
				System.out.println("Changed to 1");
			}
			if(occupied = true){
				sql = "UPDATE kimbellot SET occupied = 0 WHERE parkingSpotId = '"+spotId+"';";
				stmt.executeUpdate(sql);
				System.out.println("Changed to 0");
			}
			
			try{
				Thread.sleep(1000);
			}
			catch(InterruptedException ie)
			{
				System.out.println("Interrupted Exception should not have occured. ");
			}
		timer --;
		}
	}
	
	public static String[] addKimbelSpots(String[] spotArray)
	{
		int count = 0;
		String spot;
		
		while(count < 16)
		{
			spot = "A"+(count+1);
			spotArray[count]=spot;
			count++;
		}
		while(count<38){
			spot = "B"+(count-16+1);
			spotArray[count]=spot;
			count++;
		}
		while(count<60){
			spot = "C"+(count-38+1);
			spotArray[count]=spot;
			count++;
		}
		while(count<79){
			spot = "D"+(count-60+1);
			spotArray[count]=spot;
			count++;
		}
		while(count<98){
			spot = "E"+(count-79+1);
			spotArray[count]=spot;
			count++;
		}
		while(count<117){
			spot = "F"+(count-98+1);
			spotArray[count]=spot;
			count++;
		}
		while(count<134){
			spot = "G"+(count-117+1);
			spotArray[count]=spot;
			count++;
		}
		while(count<144){
			spot = "H"+(count-134+1);
			spotArray[count]=spot;
			count++;
		}
		return spotArray;
		
	}

}
