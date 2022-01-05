import controllers.AuthHandler;
import controllers.CartHandler;
import controllers.EmployeeHandler;
import controllers.PositionHandler;
import controllers.ProductHandler;
import controllers.VoucherHandler;
import models.CartItem;
import models.Employee;
import models.Position;
import models.Product;
import views.ProductManagementForm;
import views.TransactionManagementForm;

public class Program {

	public Program() {
//		AuthHandler.getInstance().viewLoginForm();
//		CartHandler.getInstance().viewCartManagementForm();
//		Product product = new Product();
		Product product = new Product();
		
		CartHandler.getInstance().getCart().add(new CartItem(10,product.getProduct(1), 2));
		CartHandler.getInstance().getCart().add(new CartItem(2, product.getProduct(2), 1));
		CartHandler.getInstance().getCart().add(new CartItem(3,product.getProduct(3), 3));
		Employee employee = new Employee();
		AuthHandler.getInstance().setEmployee(employee.getEmployee("username3"));
//		ProductHandler.getInstance().viewProductManagementForm();
//		VoucherHandler.getInstance().viewVoucherManagementForm();
		new TransactionManagementForm();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Program();
	}

}
