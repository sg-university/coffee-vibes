package views;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controllers.CartHandler;
import models.CartItem;
import models.Product;

public class CartManagementForm extends JFrame implements ActionListener{
	private JTable table;
	private DefaultTableModel dtm;
	private JLabel labelQuantity,labelName,labelID,labelIDVal,labelNameVal,labelProductID,labelProductVal;
	private JTextField fieldQuantity;
	private JScrollPane scrollPane;
	private JPanel panelBottom,panelInput,panelButton,panelTop;
	private JButton buttonUpdate,buttonDelete;
	private JMenuBar menuBar;
	private JMenu cart,product;
	
	private void getTableData() {
		List<CartItem> carts = CartHandler.getInstance().getCart();
		Object[] header = {"CartID","ProductID","Name","Quantity"};
		dtm = new DefaultTableModel(header,0);
		for (CartItem cart : carts) {
			Vector<Object> row = new Vector<Object>();
			
			row.add(cart.getId());
			row.add(cart.getProduct().getProductID());
			row.add(cart.getProduct().getName());
			row.add(cart.getQuantity());
			dtm.addRow(row);
		}
		table.setModel(dtm);
		
	}
	
	private void initiate() {
		// TODO Auto-generated method stub
		Object[] header = {"CartID","ProductID","Name","Quantity"};
		menuBar = new JMenuBar();
		cart = new JMenu("CartForm");
		product = new JMenu("ProductForm");
		product.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
				new ProductManagementForm();
			}
			
		});
		
		menuBar.add(cart);
		menuBar.add(product);
//		add(menuBar);
		panelTop = new JPanel(new BorderLayout());
		dtm = new DefaultTableModel(header,0);
		table = new JTable(dtm);
		getTableData();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				int row = table.getSelectedRow();
				Integer id = (int) table.getValueAt(row, 0);
				Integer productID = (int) table.getValueAt(row, 1);
				String name = (String) table.getValueAt(row, 2);
				Integer quantity = (int) table.getValueAt(row, 3);
				labelIDVal.setText(id+"");
				labelProductVal.setText(productID+"");
				labelNameVal.setText(name);
				fieldQuantity.setText(quantity+"");
			}
		});
		scrollPane = new JScrollPane(table);
		
		
		
		panelBottom = new JPanel(new BorderLayout());
		panelInput = new JPanel(new GridBagLayout());
		GridBagConstraints cons = new GridBagConstraints();
		
		
		
		
		labelID = new JLabel("Cart ID:");
		labelName = new JLabel("Item Name:");
		labelQuantity = new JLabel("Quantity:");
		labelProductID = new JLabel("ProductID:");
		labelProductVal = new JLabel("-");
		labelIDVal = new JLabel("-");
		labelNameVal = new JLabel("-");
		fieldQuantity = new JTextField(5);
		cons.insets = new Insets(10, 10, 10, 10);
		cons.gridx = 0;
		cons.gridy = 0;
		panelInput.add(labelID,cons);
		cons.gridx = 1;
		cons.gridy = 0;
		panelInput.add(labelIDVal,cons);
		cons.gridx = 0;
		cons.gridy = 1;
		panelInput.add(labelName,cons);
		cons.gridx = 1;
		cons.gridy = 1;
		panelInput.add(labelNameVal,cons);
		cons.gridx = 0;
		cons.gridy = 2;
		panelInput.add(labelProductID,cons);
		cons.gridx = 1;
		cons.gridy = 2;
		panelInput.add(labelProductVal,cons);
		cons.gridx = 0;
		cons.gridy = 3;
		panelInput.add(labelQuantity,cons);
		cons.gridx = 1;
		cons.gridy = 3;
		panelInput.add(fieldQuantity,cons);
		
		
		panelButton = new JPanel(new GridBagLayout());
		buttonUpdate = new JButton("Update");
		buttonUpdate.addActionListener(this);
		buttonDelete = new JButton("Delete");
		GridBagConstraints buttonCons = new GridBagConstraints();
		buttonCons.insets = new Insets(10,10,0,0);
		buttonCons.gridx = 0;
		buttonCons.gridy = 0;
		panelButton.add(buttonUpdate,buttonCons);
		buttonCons.gridx = 1;
		buttonCons.gridy = 0;
		panelButton.add(buttonDelete,buttonCons);
		cons.gridx = 1;
		cons.gridy = 4;
		panelInput.add(panelButton,cons);
		
		panelBottom.add(new JLabel("Update Cart"),BorderLayout.NORTH);
		panelBottom.add(panelInput,BorderLayout.CENTER);
		
		panelTop.add(menuBar,BorderLayout.NORTH);
		panelTop.add(scrollPane,BorderLayout.CENTER);
		add(panelTop);
		add(panelBottom);
	}
	
	
	public CartManagementForm() {
		// TODO Auto-generated constructor stub
		setLayout(new GridLayout(2,1));
		initiate();
		setResizable(false);
		setSize(800, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		setTitle("Cart Management Form");
	}
	
	public CartManagementForm(Product product) {
		// TODO Auto-generated constructor stub
		setLayout(new GridLayout(2,1));
		initiate();
		
//		labelIDVal.setText(product);
		labelProductVal.setText(product.getProductID()+"");
		labelNameVal.setText(product.getName());
//		fieldQuantity.setText(p+"");
		setResizable(false);
		setSize(800, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);;
		setLocationRelativeTo(null);
		setVisible(true);
		setTitle("Cart Management Form");
	}
	
	private void removeComponent() {
		this.removeAll();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == buttonUpdate) {
			
			
			CartItem item = CartHandler.getInstance().addToCart(labelProductVal.getText(), fieldQuantity.getText());
			if(item != null) {
				getTableData();
				JOptionPane.showMessageDialog(this, CartHandler.getInstance().getErrorMsg());
				this.dispose();
			}else {
				JOptionPane.showMessageDialog(this, CartHandler.getInstance().getErrorMsg());
			}
			
			//update data
		}else if(e.getSource() == buttonDelete) {
			//delete data
		}
	}

	

}
