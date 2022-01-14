package views;

import java.awt.BorderLayout;
import java.awt.Color;
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
import controllers.ProductHandler;
import controllers.TransactionHandler;
import models.CartItem;
import models.Product;

public class CartManagementForm extends JFrame implements ActionListener {
	private JTable table;
	private DefaultTableModel dtm;
	private JLabel labelQuantity, labelName, labelPrice, labelPriceVal, labelNameVal, labelProductID, labelProductVal;
	private JTextField fieldQuantity;
	private JScrollPane scrollPane;
	private JPanel panelBottom, panelInput, panelButton, panelTop;
	private JButton buttonUpdate, buttonDelete, buttonCheckout, buttonProduct;

	private void getTableData() {
		List<CartItem> carts = CartHandler.getInstance().getCart();
		Object[] header = { "ProductID", "ProductName", "Price", "Quantity" };
		dtm = new DefaultTableModel(header, 0);
		for (CartItem cart : carts) {
			Vector<Object> row = new Vector<Object>();

			row.add(cart.getProduct().getProductID());
			row.add(cart.getProduct().getName());
			row.add(cart.getProduct().getPrice());
			row.add(cart.getQuantity());
			dtm.addRow(row);
		}
		table.setModel(dtm);

	}

	private void initiate() {
		// TODO Auto-generated method stub
		Object[] header = { "CartID", "ProductID", "Name", "Quantity" };

//		add(menuBar);
		panelTop = new JPanel();
		panelTop.add(new JLabel("Cart"));
		dtm = new DefaultTableModel(header, 0);
		table = new JTable(dtm);
		getTableData();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				int row = table.getSelectedRow();

				Integer productID = (int) table.getValueAt(row, 0);
				String name = (String) table.getValueAt(row, 1);
				Integer price = (int) table.getValueAt(row, 2);
				Integer quantity = (int) table.getValueAt(row, 3);
				labelPriceVal.setText(price + "");
				labelProductVal.setText(productID + "");
				labelNameVal.setText(name);
				fieldQuantity.setText(quantity + "");
			}
		});
		scrollPane = new JScrollPane(table);
		panelBottom = new JPanel(new GridLayout(2, 1));
		panelBottom.add(scrollPane);

		panelInput = new JPanel(new GridBagLayout());
		GridBagConstraints cons = new GridBagConstraints();

		labelPrice = new JLabel("Price:");
		labelName = new JLabel("Coffee Name:");
		labelQuantity = new JLabel("Quantity:");
		labelProductID = new JLabel("ProductID:");
		labelProductVal = new JLabel("-");
		labelPriceVal = new JLabel("-");
		labelNameVal = new JLabel("-");

		fieldQuantity = new JTextField(5);
		cons.insets = new Insets(10, 10, 10, 10);

		cons.gridx = 0;
		cons.gridy = 0;
		panelInput.add(labelName, cons);
		cons.gridx = 1;
		cons.gridy = 0;
		panelInput.add(labelNameVal, cons);
		cons.gridx = 0;
		cons.gridy = 1;
		panelInput.add(labelProductID, cons);
		cons.gridx = 1;
		cons.gridy = 1;
		panelInput.add(labelProductVal, cons);
		cons.gridx = 0;
		cons.gridy = 2;
		panelInput.add(labelPrice, cons);
		cons.gridx = 1;
		cons.gridy = 2;
		panelInput.add(labelPriceVal, cons);
		cons.gridx = 0;
		cons.gridy = 3;
		panelInput.add(labelQuantity, cons);
		cons.gridx = 1;
		cons.gridy = 3;
		panelInput.add(fieldQuantity, cons);

		panelButton = new JPanel(new GridBagLayout());
		buttonProduct = new JButton("ProductForm");
		buttonProduct.addActionListener(this);
		buttonCheckout = new JButton("Checkout");
		buttonCheckout.addActionListener(this);
		buttonUpdate = new JButton("Update");
		buttonUpdate.addActionListener(this);
		buttonDelete = new JButton("Delete");
		buttonDelete.addActionListener(this);
		GridBagConstraints buttonCons = new GridBagConstraints();
		buttonCons.insets = new Insets(10, 10, 0, 0);
		buttonCons.gridx = 0;
		buttonCons.gridy = 0;
		panelButton.add(buttonUpdate, buttonCons);
		buttonCons.gridx = 1;
		buttonCons.gridy = 0;
		panelButton.add(buttonDelete, buttonCons);
		buttonCons.gridx = 2;
		buttonCons.gridy = 0;
		panelButton.add(buttonCheckout, buttonCons);
		buttonCons.gridx = 3;
		buttonCons.gridy = 0;
		panelButton.add(buttonProduct, buttonCons);
		cons.gridx = 1;
		cons.gridy = 4;
		panelInput.add(panelButton, cons);

		panelBottom.add(panelInput);

		add(panelTop, BorderLayout.NORTH);
		add(panelBottom, BorderLayout.CENTER);
		add(new JPanel(), BorderLayout.WEST);
		add(new JPanel(), BorderLayout.EAST);
	}

	public CartManagementForm() {
		// TODO Auto-generated constructor stub
		setLayout(new BorderLayout());
		initiate();
		setResizable(false);
		setSize(800, 600);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		setTitle("Cart Management Form");
	}

	public CartManagementForm(Product product) {
		// TODO Auto-generated constructor stub
		setLayout(new BorderLayout());
		initiate();

//		labelIDVal.setText(product);
		labelPriceVal.setText(product.getPrice() + "");
		labelProductVal.setText(product.getProductID() + "");
		labelNameVal.setText(product.getName());
//		fieldQuantity.setText(p+"");
		setResizable(false);
		setSize(800, 600);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		setTitle("Cart Management Form");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == buttonUpdate) {
			CartItem item = CartHandler.getInstance().addToCart(labelProductVal.getText(), fieldQuantity.getText());
			if (item != null) {
				getTableData();
				JOptionPane.showMessageDialog(this, CartHandler.getInstance().getStatusMessage());
				this.dispose();
			} else {
				JOptionPane.showMessageDialog(this, CartHandler.getInstance().getStatusMessage());
			}

			// update data
		} else if (e.getSource() == buttonDelete) {
			// delete data
			if (labelProductVal.getText().equals("-") == true) {
				JOptionPane.showMessageDialog(this, "You need to choose items by clicking the row in the table first!");
			} else {
				if (JOptionPane.showConfirmDialog(this, "Are you sure want to delete this cart?") == 0) {
					boolean isDelete = CartHandler.getInstance().removeProductFromCart(labelProductVal.getText());
					if (isDelete) {
						JOptionPane.showMessageDialog(this, "Delete Success!");
						labelPriceVal.setText("-");
						labelNameVal.setText("-");
						labelProductVal.setText("-");
						fieldQuantity.setText("");
						getTableData();
					}
				}

			}
		} else if (e.getSource() == buttonCheckout) {
			this.dispose();
			TransactionHandler.getInstance().viewTransactionCheckout();
		} else if (e.getSource() == buttonProduct) {
			this.dispose();
			ProductHandler.getInstance().viewProductManagementForm();
		}
	}

}
