package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
import controllers.CartHandler;
import controllers.EmployeeHandler;
import controllers.ProductHandler;
import models.CartItem;
import models.Employee;
import models.Product;

public class ProductManagementForm extends JFrame implements ActionListener, MouseListener,ItemListener {

	private JPanel PNLtop, PNLcenter, PNLbottom;
	private JTextField nameText, descText, priceText, stockText,idText;
	private JLabel nameLBL, descLBL, priceLBL, stockLBL,idLBL;
	private JButton insertBut, updateBut, deleteBut,addCartBut,goToCartBut;
	private JTable TBLproduct;
	private DefaultTableModel dtm;
	private List<Product> products;
	private JComboBox<String> nameCombo;
	private String itemChoice;
	public ProductManagementForm() {
		// TODO Auto-generated constructor stub

		addComp();
		initFrame();
	}

	public void addComp() {
		
		PNLtop = new JPanel();

		JLabel title = new JLabel("Welcome "+AuthHandler.getInstance().getEmployee().getName());
		PNLtop.add(title);



		GridLayout layout = new GridLayout(6, 2, 4, 4);

		JPanel PNLcenterBottom = new JPanel(layout);

		String header[] = { "ID", "Nama", "Description", "Price", "Stock" };

		dtm = new DefaultTableModel(header, 0);

		TBLproduct = new JTable(dtm);
		TBLproduct.setFillsViewportHeight(true);
		TBLproduct.addMouseListener(this);


		idLBL = new JLabel("Coffee ID: ");
		nameLBL = new JLabel("Nama Coffee: ");
		descLBL = new JLabel("Description Coffee: ");
		priceLBL = new JLabel("Price Coffee: ");
		stockLBL = new JLabel("Stock Coffee: ");

		
		nameText = new JTextField();
		descText = new JTextField();
		priceText = new JTextField();
		stockText = new JTextField();
		idText = new JTextField();
		
		PNLcenter = new JPanel(new GridLayout(3, 1));
		PNLcenter.add(new JScrollPane(TBLproduct));
		PNLcenter.add(PNLcenterBottom);

		PNLcenterBottom.add(idLBL);
		PNLcenterBottom.add(idText);
		
		PNLcenterBottom.add(nameLBL);
		PNLcenterBottom.add(nameText);

		PNLcenterBottom.add(descLBL);
		PNLcenterBottom.add(descText);

		PNLcenterBottom.add(priceLBL);
		PNLcenterBottom.add(priceText);

		PNLcenterBottom.add(stockLBL);
		PNLcenterBottom.add(stockText);

		insertBut = new JButton("Insert");
		insertBut.addActionListener(this);

		updateBut = new JButton("update");
		updateBut.setBackground(Color.MAGENTA);
		updateBut.addActionListener(this);

		deleteBut = new JButton("delete");
		deleteBut.setBackground(Color.MAGENTA);
		deleteBut.addActionListener(this);
		
		addCartBut = new JButton("Add To Cart");
		addCartBut.addActionListener(this);
		
		
		PNLbottom = new JPanel();
		Employee employee = AuthHandler.getInstance().getEmployee();
		if(employee != null) {
			if(employee.getPosition().equals("product admin")) {
				PNLbottom.add(insertBut);
				PNLbottom.add(updateBut);
				PNLbottom.add(deleteBut);
			}else if(employee.getPosition().equals("barista")){
				nameText.setEditable(false);
				idText.setEditable(false);
				descText.setEditable(false);
				priceText.setEditable(false);
				stockText.setEditable(false);
				initTable();
				PNLcenterBottom.add(new JLabel("Choose Coffee ID To Adding To Cart:"));
				PNLcenterBottom.add(nameCombo);
				goToCartBut = new JButton("Go To Cart >>");
				goToCartBut.addActionListener(this);
				
				PNLbottom.add(addCartBut);
				PNLbottom.add(goToCartBut);
			}
		}
		
		
	}
	
	private void initTable() {
		Object[] header = {"ID","Name","Description","Price","Stock"};
		products = ProductHandler.getInstance().getAllProducts();
		nameCombo = new JComboBox<String>();
		nameCombo.addItemListener(this);
		nameCombo.addActionListener(this);

		dtm = new DefaultTableModel(header,0);
		for (Product product:products) {
			Vector<Object> row = new Vector<Object>();
			
			row.add(product.getProductID());
			row.add(product.getName());
			row.add(product.getDescription());
			row.add(product.getPrice());
			row.add(product.getStock());
			nameCombo.addItem(product.getProductID()+"");
			dtm.addRow(row);
		}
		TBLproduct.setModel(dtm);
		
	}
	
	public void initFrame() {
		
		add(PNLtop, BorderLayout.NORTH);
		add(PNLcenter, BorderLayout.CENTER);
		add(PNLbottom, BorderLayout.SOUTH);
		add(new JPanel(),BorderLayout.WEST);
		add(new JPanel(),BorderLayout.EAST);
		initTable();
		setSize(800, 600);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		setTitle("Product Management Form");
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
		}else if(arg0.getSource() == addCartBut) {
			int productID = Integer.parseInt(itemChoice);
			Product product = ProductHandler.getInstance().getProduct(productID);
			CartHandler.getInstance().viewCartManagementForm(product);

		}else if(arg0.getSource() == goToCartBut) {
			this.dispose();
			CartHandler.getInstance().viewCartManagementForm();
		}

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
		int row = TBLproduct.getSelectedRow();
		Integer id = (int) TBLproduct.getValueAt(row, 0);
		
		String name = (String) TBLproduct.getValueAt(row, 1);
		String des = (String) TBLproduct.getValueAt(row, 2);
		Integer price = (int) TBLproduct.getValueAt(row, 3);
		Integer stock = (int) TBLproduct.getValueAt(row, 4);
		idText.setText(id+"");
		nameText.setText(name);
		descText.setText(des);
		priceText.setText(price+"");
		stockText.setText(stock+"");
		
//		nameCombo.setSelectedItem(nameCombo.getItemAt(row));
//		nameCombo.setSelectedIndex(row);
		itemChoice = id + "";
//		System.out.println(nameCombo.getSelectedItem());
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
		itemChoice = e.getItem().toString();
	}

	

	

}
