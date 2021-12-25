import controllers.EmployeeHandler;
import models.Employee;

public class Program {

	public Program() {
//		AuthHandler.getInstance().viewLoginForm();
//		CartHandler.getInstance().viewCartManagementForm();
		EmployeeHandler employeeHandler = EmployeeHandler.getInstance();
		employeeHandler.getAllEmployees().forEach(x -> System.out.println(x.toString()));
		System.out.println();

		Employee gotEmployee = employeeHandler.getEmployee("username1");
		System.out.println(gotEmployee);
		System.out.println();
		
		gotEmployee.setUsername("username11");
		gotEmployee.updateEmployee();
		employeeHandler.getAllEmployees().forEach(x -> System.out.println(x.toString()));
		System.out.println();

		employeeHandler.deleteEmployee(gotEmployee.getEmployeeID());
		employeeHandler.getAllEmployees().forEach(x -> System.out.println(x.toString()));
		System.out.println();

		employeeHandler.insertEmployee(gotEmployee.getName(), "barista", gotEmployee.getSalary(), "username1",
				gotEmployee.getPassword());
		employeeHandler.getAllEmployees().forEach(x -> System.out.println(x.toString()));
		System.out.println();
		

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Program();
	}

}
