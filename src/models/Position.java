package models;

import java.util.List;

import connect.Connect;

public class Position {
	private Integer positionID;
	private String name;
	private final String table = "position";
	private Connect conn = Connect.getInstance();
	public Position() {
		// TODO Auto-generated constructor stub
	}
	
	public List<Position> getAllPositions(){
		
		return null;
	}
	
	public Position getPosition() {
		
		
		return null;
	}
	
	
	public Integer getPositionID() {
		return positionID;
	}

	public void setPositionID(Integer positionID) {
		this.positionID = positionID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	
}
