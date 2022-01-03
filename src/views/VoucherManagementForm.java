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
import controllers.ProductHandler;
import controllers.VoucherHandler;
import models.Employee;
import models.Product;
import models.Voucher;

public class VoucherManagementForm extends JFrame implements ActionListener,ItemListener,MouseListener{

	private JPanel PNLtop, PNLcenter, PNLbottom;
	private JTextField discountText, idText;
	private JLabel discountLBL, idLBL;
	private JButton insertBut, updateBut, deleteBut, addCartBut, goToCartBut,productBut;
	private JTable TBLproduct;
	private DefaultTableModel dtm;
	private List<Voucher> vouchers;
	private JComboBox<String> nameCombo;
	private String itemChoice;

	public VoucherManagementForm() {
		// TODO Auto-generated constructor stub

		addComp();
		initFrame();
	}

	public void addComp() {

		PNLtop = new JPanel();

		JLabel title = new JLabel("Voucher Management");
		PNLtop.add(title);

		GridLayout layout = new GridLayout(6, 2, 4, 4);

		JPanel PNLcenterBottom = new JPanel(layout);

		Object[] header = { "ID", "Status","Discount(%)" };

		dtm = new DefaultTableModel(header, 0);

		TBLproduct = new JTable(dtm);
		
		TBLproduct.setFillsViewportHeight(true);
		TBLproduct.addMouseListener(this);

		idLBL = new JLabel("Voucher ID: ");
		discountLBL = new JLabel("Discount: ");
		discountText = new JTextField();

		idText = new JTextField();
		idText.setEditable(false);
		PNLcenter = new JPanel(new GridLayout(2, 1));
		PNLcenter.add(new JScrollPane(TBLproduct));
		PNLcenter.add(PNLcenterBottom);

		PNLcenterBottom.add(idLBL);
		PNLcenterBottom.add(idText);
		
		PNLcenterBottom.add(discountLBL);
		PNLcenterBottom.add(discountText);



		insertBut = new JButton("Insert");
		insertBut.addActionListener(this);

		updateBut = new JButton("Update");
		updateBut.setBackground(Color.MAGENTA);
		updateBut.addActionListener(this);

		deleteBut = new JButton("Delete");
		deleteBut.setBackground(Color.MAGENTA);
		deleteBut.addActionListener(this);
		
		productBut = new JButton("Product Form >>");
		productBut.addActionListener(this);
		PNLbottom = new JPanel();
		Employee employee = AuthHandler.getInstance().getEmployee();
		if (employee != null) {
			if (employee.getPosition().equals("product admin")) {
				PNLbottom.add(insertBut);
				PNLbottom.add(updateBut);
				PNLbottom.add(deleteBut);
				PNLbottom.add(productBut);
			}
		}

	}

	private void initTable() {
		Object[] header = { "ID", "Status","Discount(%)" };
		vouchers = VoucherHandler.getInstance().getAllVouchers();

		dtm = new DefaultTableModel(header, 0);
		for (Voucher voucher : vouchers) {
			Vector<Object> row = new Vector<Object>();

			row.add(voucher.getVoucherID());
			row.add(voucher.getStatus());
			row.add(voucher.getDiscount());
			dtm.addRow(row);
		}
		TBLproduct.setModel(dtm);

	}

	public void initFrame() {

		add(PNLtop, BorderLayout.NORTH);
		add(PNLcenter, BorderLayout.CENTER);
		add(PNLbottom, BorderLayout.SOUTH);
		add(new JPanel(), BorderLayout.WEST);
		add(new JPanel(), BorderLayout.EAST);
		initTable();
		setSize(800, 600);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		setTitle("Voucher Management Form");
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

		if (arg0.getSource() == insertBut) {
			if (JOptionPane.showConfirmDialog(this, "Do you want to Insert?") == 0) {
				Voucher v = VoucherHandler.getInstance().insertVoucher(discountText.getText());
				JOptionPane.showMessageDialog(this, VoucherHandler.getInstance().getStatusMessage());
				if(v != null) {
					initTable();
				}
			}

		} else if (arg0.getSource() == updateBut) {
			if(JOptionPane.showConfirmDialog(this, "Do you want to update this product?") == 0) {
				
			}
		} else if (arg0.getSource() == deleteBut) {
			if(JOptionPane.showConfirmDialog(this, "Are you sure want to delete this product?") == 0) {
				
			}
		}

	}
	private void resetField() {
		idText.setText("");

	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

		int row = TBLproduct.getSelectedRow();
		Integer id = (int) TBLproduct.getValueAt(row, 0);

		Integer discount = (int) TBLproduct.getValueAt(row, 2);
		
		idText.setText(id+"");
		discountText.setText(discount+"");
		
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
