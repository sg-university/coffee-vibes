package views;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
import javax.swing.table.DefaultTableModel;

import controllers.AuthHandler;
import controllers.CartHandler;
import controllers.TransactionHandler;
import controllers.VoucherHandler;
import models.CartItem;
import models.Transaction;
import models.Voucher;

public class TransactionCheckoutForm extends JFrame implements ActionListener,ItemListener{
	private JTable table;
	private DefaultTableModel dtm;
	private JLabel labelTotal;
	private JButton button;
	private JComboBox<String> comboVoucher;
	private int totalPrice;
	private JPanel panelTop,panelCenter,panelBot,panelVoucher,panelButton;
	private JScrollPane scrollTable;
	private String voucherID;
	public TransactionCheckoutForm() {
		// TODO Auto-generated constructor stub
		voucherID = "";
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
		comboVoucher.addItemListener(this);
		for (Voucher voucher : listVoucher) {
			comboVoucher.addItem(voucher.getVoucherID()+"");
		}
	}
	
	private void loadTable() {
		Object[] columnNames = {"ProductID","ProductName","Price","Quantity"};
		dtm = new DefaultTableModel(columnNames,0);
		totalPrice = 0;
		List<CartItem> carts = CartHandler.getInstance().getCart();
		for (CartItem cartItem : carts) {
			Vector<Object> row = new Vector<Object>();
			row.add(cartItem.getProduct().getProductID());
			row.add(cartItem.getProduct().getName());
			row.add(cartItem.getProduct().getPrice());
			row.add(cartItem.getQuantity());
			totalPrice+=(cartItem.getQuantity()*cartItem.getProduct().getPrice());
			dtm.addRow(row);
		}
		table.setModel(dtm);
	}

	protected void init() {
		// TODO Auto-generated method stub
		table = new JTable();
		generateVoucher();
		panelTop = new JPanel();
		scrollTable = new JScrollPane(table);
		panelTop.add(new JLabel("Transaction Checkout"));
		panelCenter = new JPanel(new GridLayout(3,1));
		panelCenter.add(scrollTable);
		
		panelBot = new JPanel(new GridLayout(1,3));
		button = new JButton("Checkout");
		button.addActionListener(this);
		panelButton = new JPanel();
		panelButton.add(button);
		panelBot.add(panelButton);
		loadTable();
		labelTotal = new JLabel("Total Price: "+totalPrice);
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == button) {
			Transaction transaction = TransactionHandler.getInstance().insertTransaction(voucherID, AuthHandler.getInstance().getEmployee().getEmployeeID(), totalPrice);
			JOptionPane.showMessageDialog(this, TransactionHandler.getInstance().getErrorMsg());
			loadTable();
			this.dispose();
			CartHandler.getInstance().viewCartManagementForm();
			
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		voucherID = e.getItem().toString();
	}
	
	
	
	
	
}
