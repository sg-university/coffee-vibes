package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
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

public class ProductManagementForm extends JFrame implements ActionListener, MouseListener {

	private JPanel PNLtop, PNLcenter, PNLbottom;
	private JTextField nameText, descText, priceText, stockText;
	private JLabel nameLBL, descLBL, priceLBL, stockLBL;
	private JButton insertBut, updateBut, deleteBut;
	private JTable TBLproduct;
	private DefaultTableModel dtm;

	public ProductManagementForm() {
		// TODO Auto-generated constructor stub

		addComp();
		initFrame();
	}

	public void addComp() {

		PNLtop = new JPanel();

		JLabel title = new JLabel("Product Management");
		PNLtop.add(title);

		JPanel PNLcenterTop = new JPanel();

		GridLayout layout = new GridLayout(5, 1, 4, 4);

		JPanel PNLcenterBottom = new JPanel(new GridLayout(5, 1, 4, 4));

		String header[] = { "ID", "Nama", "Description", "Price", "Stock" };

		dtm = new DefaultTableModel(header, 0);

		TBLproduct = new JTable(dtm);
		TBLproduct.setFillsViewportHeight(true);
		TBLproduct.addMouseListener(this);

		nameLBL = new JLabel("Nama Coffee: ");
		descLBL = new JLabel("Description Coffee: ");
		priceLBL = new JLabel("Price Coffee: ");
		stockLBL = new JLabel("Stock Coffee: ");

		nameText = new JTextField();
		descText = new JTextField();
		priceText = new JTextField();
		stockText = new JTextField();

		PNLcenter = new JPanel(new GridLayout(4, 1));
		PNLcenter.add(PNLcenterTop, BorderLayout.NORTH);
		PNLcenter.add(PNLcenterBottom, BorderLayout.SOUTH);

		PNLcenterTop.add(new JScrollPane(TBLproduct), BorderLayout.NORTH);

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

		PNLbottom = new JPanel();

		PNLbottom.add(insertBut);
		PNLbottom.add(updateBut);
		PNLbottom.add(deleteBut);

	}

	public void initFrame() {
		add(PNLtop, BorderLayout.NORTH);
		add(PNLcenter, BorderLayout.CENTER);
		add(PNLbottom, BorderLayout.SOUTH);

		setSize(800, 800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
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
