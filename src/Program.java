import controllers.AuthHandler;
import controllers.EmployeeHandler;
import models.Employee;
import models.Product;

public class Program {

	public Program() {
//		AuthHandler.getInstance().viewLoginForm();
//		CartHandler.getInstance().viewCartManagementForm();
//		Product product = new Product();

//		CartHandler.getInstance().getCart().add(new CartItem(product.getProduct(1), 10, 2));
//		CartHandler.getInstance().getCart().add(new CartItem(product.getProduct(2), 2, 1));
//		CartHandler.getInstance().getCart().add(new CartItem(product.getProduct(3), 3, 3));
//		Employee employee = new Employee();
//		AuthHandler.getInstance().setEmployee(employee.getEmployee("username1"));
//		Transaction ts = new Transaction();
//		Transaction temp = ts.insertTransaction(AuthHandler.getInstance().getEmployee().getEmployeeID(), 10000);
//		
//		if(temp != null) {
//			System.out.println("Transaction ID: "+ temp.getTransactionID());
//			System.out.println("Transaction employeeID: "+ temp.getEmployeeID());
//			System.out.println("Transaction total: "+temp.getTotalPrice());
//		}else {
//			System.out.println("Transaction Null");
//		}
//		new ProductManagementForm();
//		new TransactionCheckoutForm();
//		new EmployeeManagementForm();
//		ProductHandler.getInstance().viewProductManagementForm();

		System.out.println("TEST");
		System.out.println("Employee");
		Employee employee = new Employee();
		EmployeeHandler employeeHandler = EmployeeHandler.getInstance();
		System.out.println("+insertEmployee(name, position, salary, username, password) : employee");
		employee = employeeHandler.insertEmployee("namet", "barista", 1000, "usernamet", "passwordt");
		System.out.println(employeeHandler.getStatusMessage());
		System.out.println(employee);
		System.out.println("+getEmployee(username) : employee");
		employee = employeeHandler.getEmployee("usernamet");
		System.out.println(employeeHandler.getStatusMessage());
		System.out.println(employee);
		System.out.println("+updateEmployee(employeeID, name, position, salary, username, password) : employee");
		employee = employeeHandler.updateEmployee(employee.getEmployeeID(), "namett", "barista", 1001, "usernamett",
				"passwordtt");
		System.out.println(employeeHandler.getStatusMessage());
		System.out.println(employee);
		System.out.println("+deleteEmployee(employeeID) : boolean");
		Boolean result = employeeHandler.deleteEmployee(employee.getEmployeeID());
		System.out.println(result);
		System.out.println("+getAllEmployees() : list<employee>");
		employeeHandler.getAllEmployees().parallelStream().forEach(x -> System.out.println(x));
		System.out.println(employeeHandler.getStatusMessage());
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Program();
	}

}
