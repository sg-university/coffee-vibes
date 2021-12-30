package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class EmployeeManagementForm extends JFrame implements ActionListener, MouseListener {

	private JPanel PNLtop, PNLcenter, PNLbottom;
	private JTextField nameText, statusText, salaryText, usernameText, passText;
	private JLabel nameLBL, statusLBL, salaryLBL, usernameLBL, passLBL;
	private JButton insertBut, updateBut, deleteBut;
	private JTable TBLproduct;
	private DefaultTableModel dtm;

	public EmployeeManagementForm() {
		// TODO Auto-generated constructor stub

		addComp();
		initFrame();
	}

	public void addComp() {

		PNLtop = new JPanel();

		JLabel title = new JLabel("Employee Management");
		PNLtop.add(title);



		GridLayout layout = new GridLayout(5, 1, 4, 4);

		JPanel PNLcenterBottom = new JPanel(new GridLayout(5, 1, 4, 4));

		String header[] = { "ID", "Position ID", "Name", "Status", "Salary", "Username", "Password" };

		dtm = new DefaultTableModel(header, 0);

		TBLproduct = new JTable(dtm);
		TBLproduct.setFillsViewportHeight(true);
		TBLproduct.addMouseListener(this);

		nameLBL = new JLabel("Employee Name: ");
		statusLBL = new JLabel("Employee Status: ");
		salaryLBL = new JLabel("Employee Salary: ");
		usernameLBL = new JLabel("Employee Username: ");
		passLBL = new JLabel("Employee Password: ");

		nameText = new JTextField();
		statusText = new JTextField();
		salaryText = new JTextField();
		usernameText = new JTextField();
		passText = new JTextField();

		PNLcenter = new JPanel(new GridLayout(4, 1));
		PNLcenter.add(new JScrollPane(TBLproduct));
		PNLcenter.add(PNLcenterBottom);

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

		PNLbottom.add(insertBut);
		PNLbottom.add(updateBut);
		PNLbottom.add(deleteBut);

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
			System.out.println("keklik delete button");
		}

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

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
