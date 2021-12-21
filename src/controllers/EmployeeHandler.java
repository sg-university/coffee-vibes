package controllers;

import java.util.List;

import models.Employee;

public class EmployeeHandler {
	private static EmployeeHandler employeeHandler;
	
	public static synchronized EmployeeHandler getInstance() {
		if(employeeHandler == null)employeeHandler = new EmployeeHandler();
		
		return employeeHandler;
	}
	
	private EmployeeHandler() {
		// TODO Auto-generated constructor stub
	}
	
	public void viewEmployeeManagementForm() {
		// TODO Auto-generated method stub

	}
	
	public Employee insertEmployee(String name,String position, Integer salary, String username, String password) {
		
		
		return null;
	}
	
	public List<Employee> getAllEmployees(){
		
		return null;
	}
	
	public Employee getEmployee(String username) {
		
		return null;
	}
	
	public Employee updateEmployee(Integer employeeID,String name,Integer Salary,String username,String password) {
		
		return null;
	}
	
	public boolean deleteEmployee(Integer employeeID) {
		
		return false;
	}
	
	public boolean fireEmployee(Integer id) {
		
		return false;
	}
	
	
	
	
}
