package controllers;

import models.Employee;
import views.LoginForm;

public class AuthHandler {
	private static AuthHandler authHandler;
	private Employee employee;
	private String statusCode;
	private String statusMessage;

	public static synchronized AuthHandler getInstance() {
		if (authHandler == null)
			authHandler = new AuthHandler();

		return authHandler;
	}

	private AuthHandler() {
		employee = new Employee();
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public Boolean login(String username, String password) {
		Employee employeeModel = new Employee();
		employee = employeeModel.getEmployeeByCredentials(username, password);

		if (employee == null) {
			this.statusMessage = "Failed to login by employee credentials.";
			this.statusCode = "failed";
			return false;
		}

		this.statusMessage = "Succeed to login by employee credentials.";
		this.statusCode = "succeed";
		return true;
	}

	public void viewLoginForm() {
		new LoginForm();
	}
}
