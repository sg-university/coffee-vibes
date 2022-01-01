import controllers.AuthHandler;
import controllers.CartHandler;
import controllers.EmployeeHandler;
import controllers.PositionHandler;
import models.Employee;
import models.Position;

public class Program {

	public Program() {
//		AuthHandler.getInstance().viewLoginForm();
//		CartHandler.getInstance().viewCartManagementForm();
//		Product product = new Product();

//		CartHandler.getInstance().getCart().add(new CartItem(product.getProduct(1), 10, 2));
//		CartHandler.getInstance().getCart().add(new CartItem(product.getProduct(2), 2, 1));
//		CartHandler.getInstance().getCart().add(new CartItem(product.getProduct(3), 3, 3));
//		Employee employee = new Employee();
//		AuthHandler.getInstance().setEmployee(employee.getEmployee("username3"));
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
		System.out.println("======================");
		System.out.println("AuthHandler");
		AuthHandler authHandler = AuthHandler.getInstance();
		System.out.println("+login(String username, String password): Boolean");
		Boolean isLoggedin = authHandler.login("username1", "password1");
		System.out.println(isLoggedin);
		System.out.println(authHandler.getStatusMessage());
		System.out.println("======================");
		System.out.println("CartHandler");
		CartHandler cartHandler = CartHandler.getInstance();
		System.out.println("+addToCart(productID, quantity) : CartItem");
		cartHandler.addToCart("1", "1");
		System.out.println(cartHandler.getStatusMessage());
		cartHandler.addToCart("2", "-1");
		System.out.println(cartHandler.getStatusMessage());
		System.out.println("+getCart: List<CartItem>");
		cartHandler.getCart().parallelStream().forEach(x -> System.out.println(x));
		System.out.println(cartHandler.getStatusMessage());
		System.out.println("======================");
		System.out.println("EmployeeHandler");
		Employee employee = new Employee();
		EmployeeHandler employeeHandler = EmployeeHandler.getInstance();
		System.out.println("+insertEmployee(name, position, salary, username, password) : employee");
		employee = employeeHandler.insertEmployee("namet", "barista", 1000, "usernamet", "passwordt");
		System.out.println(employeeHandler.getStatusMessage());
		System.out.println(employee);
		System.out.println("+getEmployee(username) : employee");
		employee = employeeHandler.getEmployee("usernamet");
		System.out.println(employee);
		System.out.println(employeeHandler.getStatusMessage());
		System.out.println("+updateEmployee(employeeID, name, position, salary, username, password) : employee");
		employee = employeeHandler.updateEmployee(employee.getEmployeeID(), "namett", "barista", 1001, "usernamett",
				"passwordtt");
		System.out.println(employee);
		System.out.println(employeeHandler.getStatusMessage());
		System.out.println("+deleteEmployee(employeeID) : boolean");
		Boolean isEmployeeDeleted = employeeHandler.deleteEmployee(employee.getEmployeeID());
		System.out.println(isEmployeeDeleted);
		System.out.println(employeeHandler.getStatusMessage());
		System.out.println("+getAllEmployees() : list<employee>");
		employeeHandler.getAllEmployees().parallelStream().forEach(x -> System.out.println(x));
		System.out.println(employeeHandler.getStatusMessage());
		System.out.println("======================");
		System.out.println("PositionHandler");
		Position position = new Position();
		PositionHandler positionHandler = PositionHandler.getInstance();
		System.out.println("+getAllPositions() : list<position>");
		positionHandler.getAllPositions().parallelStream().forEach(x -> System.out.println(x));
		System.out.println(positionHandler.getStatusMessage());
		System.out.println("+getPosition(positionID) : position");
		position = positionHandler.getPosition(1);
		System.out.println(position);
		System.out.println(positionHandler.getStatusMessage());
		System.out.println("");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Program();
	}

}
