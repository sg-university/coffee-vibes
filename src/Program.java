import controllers.AuthHandler;
import controllers.CartHandler;
import controllers.EmployeeHandler;
import controllers.ProductHandler;
import models.Employee;
import views.EmployeeManagementForm;
import views.ProductManagementForm;

public class Program {

	public Program() {
//		AuthHandler.getInstance().viewLoginForm();
//		CartHandler.getInstance().viewCartManagementForm();
		Employee employee = new Employee();
		AuthHandler.getInstance().setEmployee(employee.getEmployee("username1"));
		new ProductManagementForm();
//		new EmployeeManagementForm();
//		ProductHandler.getInstance().viewProductManagementForm();
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Program();
	}

}
