import connect.Connect;
import controllers.AuthHandler;
import views.LoginForm;

public class Program {

	public Program() {

		AuthHandler.getInstance().viewLoginForm();
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Program();
	}

}
