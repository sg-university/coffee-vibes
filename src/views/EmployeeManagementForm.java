package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controllers.AuthHandler;
import controllers.EmployeeHandler;
import models.Employee;

public class EmployeeManagementForm extends JFrame implements ActionListener, MouseListener {

	private JPanel PNLtop, PNLcenter, PNLbottom;
	private JTextField idText,nameText, statusText, salaryText, usernameText, passText;
	private JLabel idLBL,nameLBL, statusLBL, salaryLBL, usernameLBL, passLBL;
	private JButton insertBut, updateBut, deleteBut;
	private JTable TBLEmployee;
	private DefaultTableModel dtm;

	public EmployeeManagementForm() {
		// TODO Auto-generated constructor stub
		
		addComp();
		initTable();
		initFrame();
	}
	
	
	private void initTable() {
		String header[] = { "ID", "Position ID", "Name", "Status", "Salary", "Username", "Password" };
		dtm = new DefaultTableModel(header, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		List<Employee> listEmp = EmployeeHandler.getInstance().getAllEmployees();
		for (Employee employee : listEmp) {
			Vector<Object> row = new Vector<Object>();
			row.add(employee.getEmployeeID());
			row.add(employee.getPositionID());
			row.add(employee.getName());
			row.add(employee.getStatus());
			row.add(employee.getSalary());
			row.add(employee.getUsername());
			row.add(employee.getPassword());
			dtm.addRow(row);
		}
		
		TBLEmployee.setModel(dtm);
	}
	
	public void addComp() {

		PNLtop = new JPanel();

		JLabel title = new JLabel("Employee Management");
		PNLtop.add(title);



		GridLayout layout = new GridLayout(5, 1, 4, 4);

		JPanel PNLcenterBottom = new JPanel(new GridLayout(6, 1, 4, 4));

		String header[] = { "ID", "Position ID", "Name", "Status", "Salary", "Username", "Password" };

		dtm = new DefaultTableModel(header, 0);

		TBLEmployee = new JTable(dtm);
		TBLEmployee.setFillsViewportHeight(true);
		TBLEmployee.addMouseListener(this);

		nameLBL = new JLabel("Employee Name: ");
		statusLBL = new JLabel("Employee Status: ");
		salaryLBL = new JLabel("Employee Salary: ");
		usernameLBL = new JLabel("Employee Username: ");
		passLBL = new JLabel("Employee Password: ");
		idLBL = new JLabel("Employee ID: ");

		nameText = new JTextField();
		statusText = new JTextField();
		salaryText = new JTextField();
		usernameText = new JTextField();
		passText = new JTextField();
		idText = new JTextField();

		PNLcenter = new JPanel(new GridLayout(3, 1));
		PNLcenter.add(new JScrollPane(TBLEmployee));
		PNLcenter.add(PNLcenterBottom);
		
		PNLcenterBottom.add(idLBL);
		PNLcenterBottom.add(idText);
		
		PNLcenterBottom.add(nameLBL);
		PNLcenterBottom.add(nameText);

		PNLcenterBottom.add(statusLBL);
		PNLcenterBottom.add(statusText);

		PNLcenterBottom.add(salaryLBL);
		PNLcenterBottom.add(salaryText);

		PNLcenterBottom.add(usernameLBL);
		PNLcenterBottom.add(usernameText);

		PNLcenterBottom.add(passLBL);
		PNLcenterBottom.add(passText);

		insertBut = new JButton("Insert");
		insertBut.addActionListener(this);

		updateBut = new JButton("update");
		updateBut.setBackground(Color.MAGENTA);
		updateBut.addActionListener(this);

		deleteBut = new JButton("fire");
		deleteBut.setBackground(Color.MAGENTA);
		deleteBut.addActionListener(this);

		PNLbottom = new JPanel();
		Employee emp = AuthHandler.getInstance().getEmployee();
		idText.setEditable(false);
		if(emp.getPosition().equals("manager")) {
			PNLbottom.add(insertBut);
			PNLbottom.add(updateBut);
			deleteBut = new JButton("Delete");
			deleteBut.setBackground(Color.MAGENTA);
			deleteBut.addActionListener(this);
			PNLbottom.add(deleteBut);
		}else if(emp.getPosition().equals("human resource")) {
			
			nameText.setEditable(false);
			statusText.setEditable(false);
			salaryText.setEditable(false);
			usernameText.setEditable(false);
			passText.setEditable(false);
			PNLbottom.add(deleteBut);
		}
		
		
		

	}

	public void initFrame() {
		add(PNLtop, BorderLayout.NORTH);
		add(PNLcenter, BorderLayout.CENTER);
		add(PNLbottom, BorderLayout.SOUTH);
		add(new JPanel(),BorderLayout.WEST);
		add(new JPanel(),BorderLayout.EAST);
		setSize(800, 600);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);

		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

		if (arg0.getSource() == insertBut) {
			System.out.println("keklik insert button");
		} else if (arg0.getSource() == updateBut) {
			System.out.println("keklik update button");
		} else if (arg0.getSource() == deleteBut) {
			if(JOptionPane.showConfirmDialog(this, "Are you sure want to delete this employee?") == 0) {
				Boolean isDelete = EmployeeHandler.getInstance().deleteEmployee(idText.getText().toString());
				JOptionPane.showMessageDialog(this, EmployeeHandler.getInstance().getStatusMessage());
				if(isDelete) {
					initTable();
				}
			}		
		}

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		int row = TBLEmployee.getSelectedRow();
		int id = (int) TBLEmployee.getValueAt(row,0);
		//"ID", "Position ID", "Name", "Status", "Salary", "Username", "Password"
//private JTextField nameText, statusText, salaryText, usernameText, passText;
		Integer posID = (int) TBLEmployee.getValueAt(row,1);
		String name = (String) TBLEmployee.getValueAt(row, 2);
		String status = (String) TBLEmployee.getValueAt(row, 3);
		int salary = (int) TBLEmployee.getValueAt(row, 4);
		String username = (String) TBLEmployee.getValueAt(row, 5);
		String password = (String) TBLEmployee.getValueAt(row, 6);
	
		idText.setText(id+"");
		nameText.setText(name);
		statusText.setText(status);
		salaryText.setText(salary+"");
		usernameText.setText(username);
		passText.setText(password);
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
