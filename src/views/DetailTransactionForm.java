package views;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import controllers.ProductHandler;
import controllers.TransactionHandler;
import models.Employee;
import models.Product;
import models.Transaction;
import models.TransactionItem;

public class DetailTransactionForm extends JFrame{
	private JTable tableTransaction;
	private JPanel panelUp,panelBot,panelCenter;
	private DefaultTableModel dtm;
	private JScrollPane scrollPane;
	private Integer idTransaction;
	public DetailTransactionForm() {
		// TODO Auto-generated constructor stub
		this.setLayout(new BorderLayout());
		init();
		
		setSize(800, 400);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		
		
		setVisible(true);
		setTitle("Detail Transaction Form");
	}
	
	public DetailTransactionForm(int id) {
		// TODO Auto-generated constructor stub
		idTransaction = id;
		this.setLayout(new BorderLayout());
		init();
		
		setSize(800, 400);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		
		
		setVisible(true);
		setTitle("Detail Transaction Form");
	}
	

	private void initTable() {
		Object[] header = {"Detail ID","Product Name","Price","Quantity"};
		dtm = new DefaultTableModel(header, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		
		Transaction transaction = TransactionHandler.getInstance().getTransactionDetail(idTransaction);
		for (TransactionItem item : transaction.getListTransactionItem()) {
			Vector<Object> row = new Vector<Object>();
			Product product = ProductHandler.getInstance().getProduct(item.getProductID());
			
			row.add(item.getTransactionID());
			row.add(product.getName());
//			row.add(product.getProductID());
			row.add(product.getPrice());
			row.add(item.getQuantity());
			dtm.addRow(row);

		}
//		
		tableTransaction.setModel(dtm);
		
	}
	private void init() {
		// TODO Auto-generated method stub
//		p.setLayout(new BorderLayout());
		Object[] header = {"ID","Product Name","Price","Quantity"};
		dtm = new DefaultTableModel(header, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		
		tableTransaction = new JTable(dtm);
		
		
		scrollPane = new JScrollPane(tableTransaction);
		panelCenter = new JPanel(new BorderLayout());
		panelCenter.add(scrollPane);
		initTable();
		JLabel label = new JLabel("Transaction Table");
		panelUp = new JPanel();
		panelUp.add(label);
		
		panelBot = new JPanel();
		this.add(panelUp,BorderLayout.NORTH);
		this.add(panelCenter,BorderLayout.CENTER);
		this.add(panelBot,BorderLayout.SOUTH);
		this.add(new JPanel(),BorderLayout.WEST);
		this.add(new JPanel(),BorderLayout.EAST);
	}


}
