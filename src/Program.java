import controllers.AuthHandler;
import controllers.CartHandler;
import controllers.EmployeeHandler;
import controllers.ProductHandler;
import models.CartItem;
import models.Employee;
import models.Product;
import models.Transaction;
import views.EmployeeManagementForm;
import views.ProductManagementForm;
import views.TransactionCheckoutForm;

public class Program {

	public Program() {
//		AuthHandler.getInstance().viewLoginForm();
//		CartHandler.getInstance().viewCartManagementForm();
		Product product = new Product();
		
		CartHandler.getInstance().getCart().add(new CartItem(product.getProduct(1), 10, 2));
		CartHandler.getInstance().getCart().add(new CartItem(product.getProduct(2), 2, 1));
		CartHandler.getInstance().getCart().add(new CartItem(product.getProduct(3), 3, 3));
		Employee employee = new Employee();
		AuthHandler.getInstance().setEmployee(employee.getEmployee("username1"));
		Transaction ts = new Transaction();
		Transaction temp = ts.insertTransaction(1, AuthHandler.getInstance().getEmployee().getEmployeeID(), 10000);
		
//		new ProductManagementForm();
//		new TransactionCheckoutForm();
//		new EmployeeManagementForm();
//		ProductHandler.getInstance().viewProductManagementForm();
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Program();
	}

}
