package controllers;

import java.util.List;

import models.Position;

public class PositionHandler {
	private static PositionHandler positionHandler = null;
	private Position position;
	public static synchronized PositionHandler getInstance() {
		if(positionHandler == null) positionHandler = new PositionHandler();
		
		return positionHandler; 
	}
	
	private PositionHandler() {
		// TODO Auto-generated constructor stub
	}

	public List<Position> getAllPositions(){
		
		return null;
	}
	
	public Position getPosition(Integer positionID) {
		
		return null;
	}
	
	
	
}
