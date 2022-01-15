package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
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
import controllers.PositionHandler;
import controllers.VoucherHandler;
import models.Employee;
import models.Position;
import models.Voucher;

public class EmployeeManagementForm extends JFrame implements ActionListener, MouseListener, ItemListener {

	private JPanel PNLtop, PNLcenter, PNLbottom;
	private JTextField idText, nameText, statusText, salaryText, usernameText, passText;
	private JLabel idLBL, nameLBL, positionLBL, statusLBL, salaryLBL, usernameLBL, passLBL;
	private JButton insertBut, updateBut, deleteBut;
	private JTable TBLEmployee;
	private DefaultTableModel dtm;

	private String selectedPositionName;

	private JComboBox<String> comboPosition;

	List<Position> listPosition = PositionHandler.getInstance().getAllPositions();

	public EmployeeManagementForm() {
		// TODO Auto-generated constructor stub

		generatePosition();
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

		JPanel PNLcenterBottom = new JPanel(new GridLayout(7, 1, 4, 4));

		String header[] = { "ID", "Position ID", "Name", "Status", "Salary", "Username", "Password" };

		dtm = new DefaultTableModel(header, 0);

		TBLEmployee = new JTable(dtm);
		TBLEmployee.setFillsViewportHeight(true);
		TBLEmployee.addMouseListener(this);

		nameLBL = new JLabel("Employee Name: ");
		positionLBL = new JLabel("Employee Position: ");
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

		PNLcenterBottom.add(positionLBL);
		PNLcenterBottom.add(comboPosition);

		PNLcenterBottom.add(statusLBL);
		PNLcenterBottom.add(statusText);

		PNLcenterBottom.add(salaryLBL);
		PNLcenterBottom.add(salaryText);

		PNLcenterBottom.add(usernameLBL);
		PNLcenterBottom.add(usernameText);

		PNLcenterBottom.add(passLBL);
		PNLcenterBottom.add(passText);

		PNLbottom = new JPanel();
		idText.setEditable(false);
		Employee emp = AuthHandler.getInstance().getEmployee();
		if (emp.getPosition().equals("manager")) {
			deleteBut = new JButton("Fire");
			deleteBut.setBackground(Color.MAGENTA);
			deleteBut.addActionListener(this);
			nameText.setEditable(false);
			statusText.setEditable(false);
			salaryText.setEditable(false);
			usernameText.setEditable(false);
			passText.setEditable(false);
			PNLbottom.add(deleteBut);
		} else if (emp.getPosition().equals("human resource")) {
			insertBut = new JButton("Insert");
			insertBut.addActionListener(this);
			updateBut = new JButton("Update");
			updateBut.setBackground(Color.MAGENTA);
			updateBut.addActionListener(this);
			deleteBut = new JButton("Delete");
			deleteBut.setBackground(Color.MAGENTA);
			deleteBut.addActionListener(this);
			PNLbottom.add(deleteBut);
			PNLbottom.add(insertBut);
			PNLbottom.add(updateBut);
		}

	}

	private void generatePosition() {
		comboPosition = new JComboBox<String>();
		comboPosition.addItem("");
		comboPosition.addItemListener(this);
		for (Position position : listPosition) {
			comboPosition.addItem(position.getPositionName());
		}
	}

	private void resetField() {
		nameText.setText("");
		statusText.setText("");
		salaryText.setText("");
		usernameText.setText("");
		passText.setText("");
		idText.setText("");
		comboPosition.setSelectedItem("");
	}

	public void initFrame() {
		add(PNLtop, BorderLayout.NORTH);
		add(PNLcenter, BorderLayout.CENTER);
		add(PNLbottom, BorderLayout.SOUTH);
		add(new JPanel(), BorderLayout.WEST);
		add(new JPanel(), BorderLayout.EAST);
		setSize(800, 600);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);

		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

		if (arg0.getSource() == insertBut) {
			if (JOptionPane.showConfirmDialog(this, "Do you want to Insert?") == 0) {
				Employee p = EmployeeHandler.getInstance().insertEmployee(nameText.getText(), selectedPositionName,
						salaryText.getText(), usernameText.getText(), passText.getText());
				if (p == null) {
					JOptionPane.showMessageDialog(this, EmployeeHandler.getInstance().getStatusMessage());
				} else {
					JOptionPane.showMessageDialog(this, "Insert Employee Success!");
					initTable();
					resetField();
				}
			}
		} else if (arg0.getSource() == updateBut) {
			System.out.println("keklik update button");
			if (JOptionPane.showConfirmDialog(this, "Do you want to update this employee?") == 0) {
				Employee p = EmployeeHandler.getInstance().updateEmployee(idText.getText(), nameText.getText(),
						selectedPositionName, salaryText.getText(), usernameText.getText(), passText.getText());
				JOptionPane.showMessageDialog(this, EmployeeHandler.getInstance().getStatusMessage());
				if (p != null) {
					initTable();
					resetField();
				}
			}
		} else if (arg0.getSource() == deleteBut) {
			if (JOptionPane.showConfirmDialog(this, "Are you sure want to delete this employee?") == 0) {
				Boolean isDelete = EmployeeHandler.getInstance().deleteEmployee(idText.getText().toString());
				JOptionPane.showMessageDialog(this, EmployeeHandler.getInstance().getStatusMessage());
				if (isDelete) {
					initTable();
					resetField();
				}
			}
		}

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		int row = TBLEmployee.getSelectedRow();
		int id = (int) TBLEmployee.getValueAt(row, 0);
		// "ID", "Position ID", "Name", "Status", "Salary", "Username", "Password"
//private JTextField nameText, statusText, salaryText, usernameText, passText;
		Integer posID = (int) TBLEmployee.getValueAt(row, 1);
		String name = (String) TBLEmployee.getValueAt(row, 2);
		String status = (String) TBLEmployee.getValueAt(row, 3);
		int salary = (int) TBLEmployee.getValueAt(row, 4);
		String username = (String) TBLEmployee.getValueAt(row, 5);
		String password = (String) TBLEmployee.getValueAt(row, 6);

		idText.setText(id + "");
		nameText.setText(name);
		statusText.setText(status);
		salaryText.setText(salary + "");
		usernameText.setText(username);
		passText.setText(password);

		Position selectedPosition = PositionHandler.getInstance().getPosition(posID);

		selectedPositionName = selectedPosition.getPositionName();
		comboPosition.setSelectedItem(selectedPositionName);
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

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		selectedPositionName = e.getItem().toString();
	}

}
