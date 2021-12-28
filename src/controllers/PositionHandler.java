package controllers;

import java.util.List;

import models.EmployeePosition;

public class PositionHandler {
	private static PositionHandler positionHandler = null;
	private EmployeePosition position;
	public static synchronized PositionHandler getInstance() {
		if(positionHandler == null) positionHandler = new PositionHandler();
		
		return positionHandler; 
	}
	
	private PositionHandler() {
		// TODO Auto-generated constructor stub
	}

	public List<EmployeePosition> getAllPositions(){
		
		return null;
	}
	
	public EmployeePosition getPosition(Integer positionID) {
		
		return null;
	}
	
	
	
}
