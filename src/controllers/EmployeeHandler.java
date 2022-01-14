package controllers;

import java.util.List;

import models.Employee;
import models.Position;
import utils.Validate;
import views.EmployeeManagementForm;

public class EmployeeHandler {
	private static EmployeeHandler employeeHandler = null;
	private String statusCode;
	private String statusMessage;

	public static synchronized EmployeeHandler getInstance() {
		if (employeeHandler == null)
			employeeHandler = new EmployeeHandler();

		return employeeHandler;
	}

	public Employee getEmployeeById(Integer id) {

		Employee emp = new Employee();
		Employee temp = emp.getEmployeeByID(id);
		if (temp == null) {
			this.statusCode = "failed";
			this.statusMessage = "There is no such employee exist";
			return null;
		}
		return temp;

	}

	private EmployeeHandler() {
		// TODO Auto-generated constructor stub
	}

	public void viewEmployeeManagementForm() {
		// TODO Auto-generated method stub
		new EmployeeManagementForm();
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

	public Employee insertEmployee(String name, String position, String salary, String username, String password) {
		Position positionModel = new Position();
		List<Employee> employeeList = this.getAllEmployees();
		List<Position> positionList = positionModel.getAllPosition();

		Boolean isPositionAvailable = positionList.parallelStream().anyMatch(x -> x.gePositionName().equals(position));
		if (!isPositionAvailable) {
			this.statusCode = "failed";
			this.statusMessage = "Employee position must be available.";
			return null;
		}

		Boolean isSalaryInteger = Validate.isInteger(salary);
		if (!isSalaryInteger) {
			this.statusCode = "failed";
			this.statusMessage = "Employee salary must be integer.";
			return null;
		}

		Boolean isSalaryHigherThanOne = Integer.parseInt(salary) >= 1;
		if (!isSalaryHigherThanOne) {
			this.statusCode = "failed";
			this.statusMessage = "Employee salary must be higher than or equal to 1.";
			return null;
		}

		Boolean isUsernameUnique = !employeeList.parallelStream().anyMatch(x -> x.getUsername().equals(username));
		if (!isUsernameUnique) {
			this.statusCode = "failed";
			this.statusMessage = "Employee username must be unique.";
			return null;
		}

		Integer positionID = positionList.parallelStream().filter(x -> x.gePositionName().equals(position)).findAny()
				.get().getPositionID();
		Employee employee = new Employee(null, positionID, name, Integer.parseInt(salary), username, password);
		Employee insertedEmployee = employee.insertEmployee();
		if (insertedEmployee == null) {
			this.statusCode = "failed";
			this.statusMessage = "Failed to insert employee.";
			return null;
		}

		this.statusCode = "succeed";
		this.statusMessage = "Succeed to insert employee.";

		return insertedEmployee;
	}

	public List<Employee> getAllEmployees() {
		Employee employee = new Employee();
		List<Employee> employeeList = employee.getAllEmployees();

		if (employeeList == null) {
			this.statusCode = "failed";
			this.statusMessage = "Failed to get all employee.";
			return null;
		}

		this.statusCode = "succeed";
		this.statusMessage = "Succeed to get all employee.";

		return employeeList;
	}

	public Employee getEmployee(String username) {
		Employee employee = new Employee();
		Employee gotEmploye = employee.getEmployee(username);

		if (gotEmploye == null) {
			this.statusCode = "failed";
			this.statusMessage = "Failed to get one employee by username.";
			return null;
		}

		this.statusCode = "succeed";
		this.statusMessage = "Succeed to get one employee by username.";

		return gotEmploye;
	}

	public Employee updateEmployee(String employeeID, String name, String position, String salary, String username,
			String password) {
		Position positionModel = new Position();
		List<Employee> employeeList = this.getAllEmployees();
		List<Position> positionList = positionModel.getAllPosition();

		Boolean isIdInteger = Validate.isInteger(employeeID);
		if (!isIdInteger) {
			this.statusCode = "failed";
			this.statusMessage = "Employee id must be integer.";
			return null;
		}

		Boolean isPositionAvailable = positionList.parallelStream().anyMatch(x -> x.gePositionName().equals(position));
		if (!isPositionAvailable) {
			this.statusCode = "failed";
			this.statusMessage = "Employee position must be available.";
			return null;
		}

		Boolean isSalaryInteger = Validate.isInteger(salary);
		if (!isSalaryInteger) {
			this.statusCode = "failed";
			this.statusMessage = "Employee salary must be integer.";
			return null;
		}

		Boolean isSalaryHigherThanOne = Integer.parseInt(salary) >= 1;
		if (!isSalaryHigherThanOne) {
			this.statusCode = "failed";
			this.statusMessage = "Employee salary must be higher than or equal to 1.";
			return null;
		}

		Boolean isUsernameUnique = !employeeList.parallelStream().anyMatch(x -> x.getUsername().equals(username));
		if (!isUsernameUnique) {
			this.statusCode = "failed";
			this.statusMessage = "Employee username must be unique.";
			return null;
		}

		Integer positionID = positionList.parallelStream().filter(x -> x.gePositionName().equals(position)).findAny()
				.get().getPositionID();
		Employee employee = new Employee(Integer.parseInt(employeeID), positionID, name, Integer.parseInt(salary),
				username, password);
		Employee updatedEmployee = employee.updateEmployee();
		if (updatedEmployee == null) {
			this.statusCode = "failed";
			this.statusMessage = "Failed to update one employee by employee id.";
			return null;
		}

		this.statusCode = "succeed";
		this.statusMessage = "Succeed to update one employee by employee id.";

		return updatedEmployee;
	}

	public Boolean deleteEmployee(String employeeID) {
		if (employeeID.isEmpty() == true) {
			this.statusCode = "failed";
			this.statusMessage = "You must choose employee by click the row in the table!";
			return false;
		}
		Employee employee = new Employee();
		Boolean deletedEmployee = employee.fireEmployee(Integer.parseInt(employeeID));

		if (deletedEmployee == false) {
			this.statusCode = "failed";
			this.statusMessage = "Failed to delete one employee by employee id.";
		} else {
			this.statusCode = "succeed";
			this.statusMessage = "Succeed to delete one employee by employee id.";
		}

		return deletedEmployee;
	}

	public Boolean fireEmployee(Integer employeeID) {
		Employee employee = new Employee();
		Boolean deletedEmploye = employee.fireEmployee(employeeID);

		if (deletedEmploye == false) {
			this.statusCode = "failed";
			this.statusMessage = "Failed to fire one employee by employee id.";
			return false;
		}

		this.statusCode = "succeed";
		this.statusMessage = "Succeed to fire one employee by employee id.";
		return true;
	}

}
