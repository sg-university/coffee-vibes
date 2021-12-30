package views;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controllers.CartHandler;
import controllers.VoucherHandler;
import models.CartItem;
import models.Voucher;

public class TransactionCheckoutForm extends JFrame{
	private JTable table;
	private DefaultTableModel dtm;
	private JLabel labelTotal;
	private JButton button;
	private JComboBox<String> comboVoucher;
	private JPanel panelTop,panelCenter,panelBot,panelVoucher,panelButton;
	private JScrollPane scrollTable;
	public TransactionCheckoutForm() {
		// TODO Auto-generated constructor stub
		init();
		setSize(800, 600);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		setTitle("Transaction Checkout Form");
		 
	}
	
	private void generateVoucher() {
		
		comboVoucher = new JComboBox<String>();
		List<Voucher> listVoucher = VoucherHandler.getInstance().getAllVouchers();
		comboVoucher.addItem("");
		for (Voucher voucher : listVoucher) {
			comboVoucher.addItem(voucher.getVoucherID()+"");
		}
	}
	
	private void loadTable() {
		Object[] columnNames = {"ProductID","ProductName","Price","Quantity"};
		dtm = new DefaultTableModel(columnNames,0);
		
		List<CartItem> carts = CartHandler.getInstance().getCart();
		for (CartItem cartItem : carts) {
			Vector<Object> row = new Vector<Object>();
			row.add(cartItem.getProduct().getProductID());
			row.add(cartItem.getProduct().getName());
			row.add(cartItem.getProduct().getPrice());
			row.add(cartItem.getQuantity());
			
			dtm.addColumn(row);
		}
		table = new JTable(dtm);
	}

	protected void init() {
		// TODO Auto-generated method stub
		
		loadTable();
		generateVoucher();
		panelTop = new JPanel();
		scrollTable = new JScrollPane(table);
		panelTop.add(new JLabel("Transaction Checkout"));
		panelCenter = new JPanel(new GridLayout(3,1));
		panelCenter.add(scrollTable);
		
		
		panelBot = new JPanel(new GridLayout(1,3));
		button = new JButton("Checkout");
		panelButton = new JPanel();
		panelButton.add(button);
		panelBot.add(panelButton);
		
		labelTotal = new JLabel("Total Price: ");
		labelTotal.setFont(new Font("Arial",Font.PLAIN,30));
		panelVoucher = new JPanel(new GridLayout(6,1));
		
		panelCenter.add(panelVoucher);
		panelVoucher.add(new JLabel("Input Voucher: "));
		panelVoucher.add(comboVoucher);
		panelVoucher.add(new JLabel(""));
		panelVoucher.add(new JLabel(""));
		panelVoucher.add(labelTotal);
		
		add(panelTop,BorderLayout.NORTH);
		add(panelCenter,BorderLayout.CENTER);
		add(new JPanel(),BorderLayout.WEST);
		add(new JPanel(),BorderLayout.EAST);
		add(panelBot,BorderLayout.SOUTH);
	}
	
	
	
	
	
}
