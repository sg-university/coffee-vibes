package controllers;

import java.util.List;

import models.Employee;
import models.EmployeePosition;

public class EmployeeHandler {
	private static EmployeeHandler employeeHandler;
	private String statusCode;
	private String statusMessage;

	public static synchronized EmployeeHandler getInstance() {
		if (employeeHandler == null)
			employeeHandler = new EmployeeHandler();

		return employeeHandler;
	}

	private EmployeeHandler() {
		// TODO Auto-generated constructor stub
	}

	public void viewEmployeeManagementForm() {
		// TODO Auto-generated method stub

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

	public Employee insertEmployee(String name, String position, Integer salary, String username, String password) {
		EmployeePosition employeePosition = new EmployeePosition();
		List<Employee> employeeList = this.getAllEmployees();
		List<EmployeePosition> employeePositionList = employeePosition.getAllEmployeePosition();

		Boolean isPositionAvailable = employeePositionList.parallelStream()
				.anyMatch(x -> x.getEmployeePositionName().equals(position));
		if (!isPositionAvailable) {
			this.statusCode = "error";
			this.statusMessage = "Employee position must be available";
			return null;
		}

		Boolean isUsernameUnique = !employeeList.parallelStream().anyMatch(x -> x.getUsername().equals(username));
		if (!isUsernameUnique) {
			this.statusCode = "error";
			this.statusMessage = "Employee username must be unique";
			return null;
		}

		Integer employeePositionID = employeePositionList.parallelStream()
				.filter(x -> x.getEmployeePositionName().equals(position)).findAny().get().getEmployeePositionID();
		Employee employee = new Employee(salary, employeePositionID, name, position, salary, username, password);
		Employee createdEmployee = employee.insertEmployee();
		if (createdEmployee == null) {
			this.statusCode = "error";
			this.statusMessage = "Failed to insert employee";
			return null;
		}

		this.statusCode = "success";
		this.statusMessage = "Succeed to insert employee";

		return createdEmployee;
	}

	public List<Employee> getAllEmployees() {
		Employee employee = new Employee();
		List<Employee> employeeList = employee.getAllEmployees();

		if (employeeList != null) {
			this.statusCode = "success";
			this.statusMessage = "Succeed to get all employee";
		} else {
			this.statusCode = "error";
			this.statusMessage = "Failed to get all employee";
		}

		return employeeList;
	}

	public Employee getEmployee(String username) {
		Employee employee = new Employee();
		Employee gotEmploye = employee.getEmployee(username);

		if (gotEmploye != null) {
			this.statusCode = "success";
			this.statusMessage = "Succeed to get one employee by username";
		} else {
			this.statusCode = "error";
			this.statusMessage = "Failed to get one employee by username";
		}

		return gotEmploye;
	}

	public Employee updateEmployee(Integer employeeID, String name, String position, Integer salary, String username,
			String password) {
		EmployeePosition employeePosition = new EmployeePosition();
		List<Employee> employeeList = this.getAllEmployees();
		List<EmployeePosition> employeePositionList = employeePosition.getAllEmployeePosition();

		Boolean isPositionAvailable = employeePositionList.parallelStream()
				.anyMatch(x -> x.getEmployeePositionName().equals(position));
		if (!isPositionAvailable) {
			this.statusCode = "error";
			this.statusMessage = "Employee position must be available";
			return null;
		}

		Boolean isUsernameUnique = !employeeList.parallelStream().anyMatch(x -> x.getUsername().equals(username));
		if (!isUsernameUnique) {
			this.statusCode = "error";
			this.statusMessage = "Employee username must be unique";
			return null;
		}

		Integer employeePositionID = employeePositionList.parallelStream()
				.filter(x -> x.getEmployeePositionName().equals(position)).findAny().get().getEmployeePositionID();
		Employee employee = new Employee(employeeID, employeePositionID, name, position, salary, username, password);
		Employee updatedEmployee = employee.updateEmployee();
		if (updatedEmployee == null) {
			this.statusCode = "error";
			this.statusMessage = "Failed to update one employee by employee id";
			return null;
		}

		this.statusCode = "success";
		this.statusMessage = "Succeed to update one employee by employee id";

		return updatedEmployee;
	}

	public boolean deleteEmployee(Integer employeeID) {
		return this.fireEmployee(employeeID);
	}

	public boolean fireEmployee(Integer employeeID) {
		Employee employee = new Employee();
		Boolean deletedEmploye = employee.fireEmployee(employeeID);

		if (deletedEmploye == true) {
			this.statusCode = "success";
			this.statusMessage = "Succeed to delete one employee by employee id";
		} else {
			this.statusCode = "error";
			this.statusMessage = "Failed to delete one employee by employee id";
		}

		return deletedEmploye;
	}

}
