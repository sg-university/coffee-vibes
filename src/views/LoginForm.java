package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.Spring;
import javax.swing.SpringLayout;

import controllers.AuthHandler;

public class LoginForm extends JFrame implements ActionListener{
	private JPanel panelTitle,panelForm,panelButton;
	private JLabel labelTitle,labelUsername,labelPassword,errorMsg;
	private JButton buttonLogin,buttonRegister;
	private JTextField fieldUsername;
	private JPasswordField fieldPassword;
	private AuthHandler authHandler = AuthHandler.getInstance();
	
	
	
	private void initiate() {
		// TODO Auto-generated method stub
		panelTitle = new JPanel();
		labelTitle = new JLabel("Login");
		labelTitle.setFont(new Font("Calibri",Font.PLAIN,40));
		panelTitle.add(labelTitle);
		
		labelUsername = new JLabel("Username");
		labelPassword = new JLabel("Password");
		fieldPassword = new JPasswordField(15);

		fieldUsername = new JTextField(15);
		buttonLogin = new JButton("Login");
		buttonLogin.addActionListener(this);
		buttonRegister = new JButton("Register");
		buttonRegister.addActionListener(this);
		panelForm = new JPanel(new GridBagLayout());
		GridBagConstraints cons = new GridBagConstraints();
		
		cons.gridx = 0;
		cons.gridy = 0;
		cons.insets = new Insets(30,20,0,0);
		panelForm.add(labelUsername, cons);
		cons.gridx = 1;
		cons.gridy = 0;
		panelForm.add(fieldUsername,cons);
		cons.gridx = 0;
		cons.gridy = 1;
		panelForm.add(labelPassword,cons);
		cons.gridx = 1;
		cons.gridy = 1;
		panelForm.add(fieldPassword,cons);
		
		
		
		panelButton = new JPanel(new GridBagLayout());
		GridBagConstraints buttonCons = new GridBagConstraints();
		buttonCons.gridx = 0;
		buttonCons.gridy = 0;
		buttonCons.insets = new Insets(0,10,10,0);
		panelButton.add(buttonLogin,buttonCons);
		buttonCons.gridx = 1;
		buttonCons.gridy = 0;
	
		panelButton.add(buttonRegister,buttonCons);
		
		cons.gridx = 1;
		cons.gridy = 2;
		panelForm.add(panelButton,cons);
		
		add(panelTitle,BorderLayout.NORTH);
		add(panelForm,BorderLayout.CENTER);
	}
	
	public LoginForm() {
		// TODO Auto-generated constructor stub
		
		setLayout(new BorderLayout());
		initiate();
		setResizable(false);
		setSize(400,300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		setTitle("Login Employee");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == buttonLogin) {
			System.out.println("Login");
			String username = fieldUsername.getText();
			String password = fieldPassword.getText();
			boolean isLogin = authHandler.login(username, password);
			if(isLogin) {
				JOptionPane.showMessageDialog(this, "Login Success");
			}else {
				JOptionPane.showMessageDialog(this, AuthHandler.getInstance().getErrorMsg());
			}
		}else if(e.getSource() == buttonRegister) {
			// logic buat pencet tombol register
			System.out.println("Register");
		}
	}
	
	
	
}