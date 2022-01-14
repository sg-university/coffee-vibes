package views;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controllers.EmployeeHandler;
import controllers.TransactionHandler;
import models.Employee;
import models.Transaction;

public class TransactionManagementForm extends JFrame implements ActionListener, MouseListener {
	private JTable tableTransaction;
	private JPanel panelUp, panelBot, panelCenter;
	private DefaultTableModel dtm;
	private JScrollPane scrollPane;
	private JButton btnToEmployee;

	public TransactionManagementForm() {
		// TODO Auto-generated constructor stub
		this.setLayout(new BorderLayout());
		init();

		setSize(800, 600);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);

		setVisible(true);
		setTitle("Transaction Management Form");
	}

	private void initTable() {
		Object[] header = { "ID", "TransactionDate", "VoucherId", "Employee Name", "Total Price" };
		dtm = new DefaultTableModel(header, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};

		List<Transaction> listTransaction = TransactionHandler.getInstance().getAllTransactions();
		for (Transaction transaction : listTransaction) {
			Vector<Object> row = new Vector<Object>();
			row.add(transaction.getTransactionID());
			row.add(transaction.getPurchaseDate().toString());
			row.add(transaction.getVoucherID());
			Employee emp = EmployeeHandler.getInstance().getEmployeeById(transaction.getEmployeeID());
			row.add(emp.getName());
			row.add(transaction.getTotalPrice());
			dtm.addRow(row);

		}

		tableTransaction.setModel(dtm);

	}

	private void init() {
		// TODO Auto-generated method stub
//		p.setLayout(new BorderLayout());
		Object[] header = { "ID", "TransactionDate", "VoucherId", "Employee Name", "Total Price" };
		dtm = new DefaultTableModel(header, 0);

		tableTransaction = new JTable(dtm);

		tableTransaction.addMouseListener(this);

		scrollPane = new JScrollPane(tableTransaction);
		panelCenter = new JPanel(new BorderLayout());
		panelCenter.add(scrollPane);
		initTable();
		JLabel label = new JLabel("Transaction Table");
		panelUp = new JPanel();
		panelUp.add(label);

		panelBot = new JPanel();
		btnToEmployee = new JButton("Employee Form >>");
		btnToEmployee.addActionListener(this);

		panelBot.add(btnToEmployee);
		this.add(panelUp, BorderLayout.NORTH);
		this.add(panelCenter, BorderLayout.CENTER);
		this.add(panelBot, BorderLayout.SOUTH);
		this.add(new JPanel(), BorderLayout.WEST);
		this.add(new JPanel(), BorderLayout.EAST);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == btnToEmployee) {
			EmployeeHandler.getInstance().viewEmployeeManagementForm();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = tableTransaction.getSelectedRow();
		Integer id = (int) tableTransaction.getValueAt(row, 0);
		TransactionHandler.getInstance().viewTransactionDetail(id);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
