package controllers;

import models.Employee;
import views.LoginForm;

public class AuthHandler {
	private static AuthHandler authHandler;
	private Employee employee;
	private String errorMsg;
	public static synchronized AuthHandler getInstance() {
		if(authHandler == null)authHandler = new AuthHandler();
		
		return authHandler;
	}
	
	private AuthHandler() {
		// TODO Auto-generated constructor stub
		employee = new Employee();
	}
	
	
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public boolean login(String username,String password) {
		employee = employee.getEmployeeByLogin(username, password);
		
		if(employee != null) {
			return true;
		}else {
			employee = new Employee();
			errorMsg = "Invalid Employee!";
		}
		
		return false;
	}
	public void viewLoginForm() {
		new LoginForm();
	}
}
