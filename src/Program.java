import connect.Connect;
import controllers.AuthHandler;
import controllers.CartHandler;
import views.CartManagementForm;
import views.LoginForm;

public class Program {

	public Program() {
//		AuthHandler.getInstance().viewLoginForm();
		CartHandler.getInstance().viewCartManagementForm();
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Program();
	}

}
