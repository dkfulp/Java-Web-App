package com.cs330;

public class ParkingSpot {
	
	private String parkingLotID;
	
	private String parkingSpotID;
	
	private boolean occupied;
	
	private int occupiedBy;
	
	private String spaceType;
	
	//some timer attribute will go here
	
	private int spotTimeLimit;
	
	private boolean overTime;
	
	public ParkingSpot(String lId, String sId, String sType, int tLimit){
		setParkingLotID(lId);
		setParkingSpotID(sId);
		occupied = false;
		occupiedBy = 0;
		setSpaceType(sType);
		//Some timer imitialization here
		setSpotTimeLimit(tLimit);
		overTime = false;
	}
	
	public String getParkingLotID(){
		return parkingLotID;
	}
	
	public String getParkingSpotID(){
		return parkingSpotID;
	}
	
	public boolean getOccupied(){
		return occupied;
	}
	
	public int getOccupiedBy(){
		return occupiedBy;
	}
	
	public String getSpaceType(){
		return spaceType;
	}
	
	//put a get time elapsed function here 
	
	public int getSpotTimeLimit(){
		return spotTimeLimit;
	}
	
	public boolean getOverTime(){
		return overTime;
	}
	
	private void setParkingLotID(String id){
		if(id != null){
			parkingLotID = id;
		}
		else
		{
			System.out.println("Please enter valid id");
			parkingLotID = "No ID yet";
		}
	}
	
	private void setParkingSpotID(String id){
		if(id != null){
			parkingSpotID = id;
		}
		else
		{
			System.out.println("Please enter valid id");
			parkingSpotID = "No ID yet";
		}
	}	
	
	public void setOccupied(boolean val){
		occupied = val;
	}
	
	public void setOccupiedBy(int uID){
		if(uID < 0)
		{
			System.out.println("Please enter a valid ID");
			occupiedBy = 0;
		}
		else
		{
			occupiedBy = uID;
		}
	}
	
	private void setSpaceType(String type){
		spaceType = type;
	}
	
	public void setSpotTimeLimit(int timeMax){
		if(timeMax < 0)
		{
			System.out.println("Please enter a valid time");
			spotTimeLimit = 0;
		}
		else
		{
			spotTimeLimit = timeMax;
		}
	}
	
	public void setOverTime(boolean val){
		overTime = val;
	}
}