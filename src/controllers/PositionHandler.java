package controllers;

import java.util.List;

import models.Position;

public class PositionHandler {
	private static PositionHandler positionHandler = null;
	private String statusCode;
	private String statusMessage;

	public static synchronized PositionHandler getInstance() {
		if (positionHandler == null)
			positionHandler = new PositionHandler();

		return positionHandler;
	}

	private PositionHandler() {
	}

	public List<Position> getAllPositions() {
		Position position = new Position();
		List<Position> positionList = position.getAllPosition();

		if (positionList == null) {
			this.statusCode = "failed";
			this.statusMessage = "Failed to get all position.";
			return null;
		}

		this.statusCode = "success";
		this.statusMessage = "Succeed to get all position.";
		return positionList;
	}

	public Position getPosition(Integer positionID) {
		Position position = new Position();
		position = position.getPosition(positionID);

		if (position == null) {
			this.statusCode = "failed";
			this.statusMessage = "Failed to get all position.";
			return null;
		}

		this.statusCode = "success";
		this.statusMessage = "Succeed to get all position.";

		return position;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

}
