package models;

import java.util.List;
import java.util.Vector;

import connect.Connect;

public class Employee {
	private Integer employeeID;
	private Integer positionID;
	private String name;
	private String status;
	private Integer salary;
	private String username;
	private String password;
	private final String table = "employee";
	private Connect conn = Connect.getInstance();
	public Employee() {
		// TODO Auto-generated constructor stub
	}
	

	public Employee insertEmployee() {
		
		return null;
	}

	public List<Employee> getAllEmployees(){
		
		return null;
	}
	
	public Employee getEmployee(String username) {
		
		return null;
	}
	
	public Employee updateEmployee() {
		
		return null;
	}
	
	public boolean fireEmployee(int id) {
		
		return false;
	}
	
	
	public Integer getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(Integer employeeID) {
		this.employeeID = employeeID;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getSalary() {
		return salary;
	}

	public void setSalary(Integer salary) {
		this.salary = salary;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	
	
	
}
