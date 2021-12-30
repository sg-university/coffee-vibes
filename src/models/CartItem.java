package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import connect.Database;
import controllers.ProductHandler;

public class CartItem {
	private Integer id;
	private Product product;
	private Integer quantity;
	private final String table = "cart_item";
	private Database db = Database.getInstance();

	public CartItem() {
		// TODO Auto-generated constructor stub
	}

	private CartItem map(ResultSet rs) {
		try {
			Integer id = rs.getInt("id");
			Integer productId = rs.getInt("product_id");
			Integer quantity = rs.getInt("quantity");
			Product productTemp = ProductHandler.getInstance().getProduct(productId);
			CartItem cartItem = new CartItem(productTemp, quantity, id);
			return cartItem;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public List<CartItem> getAllCartItems() {
		String query = String.format("SELECT * FROM %s", this.table);
		ResultSet rs = db.executeQuery(query);
		Vector<CartItem> listCart = new Vector<CartItem>();
		try {
			while (rs.next()) {
				CartItem cartItem = map(rs);
				listCart.add(cartItem);
			}
			return listCart;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public CartItem(Product product, Integer quantity, Integer id) {
		super();
		this.product = product;
		this.quantity = quantity;
		this.id = id;
	}
	

	
	public boolean updateCartItem(int id,int quantity) {
		this.quantity=quantity;
		String query = String.format("UPDATE %s SET quantity = ? WHERE product_id = ?", this.table);
		PreparedStatement ps = db.prepareStatement(query);
		try {
			ps.setInt(1, quantity);
			ps.setInt(2, id);
			System.out.println("quantity");
			return ps.executeUpdate()==1;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
		return false;
	}
	
	public CartItem insertNewCartItem() {
		String query = String.format("INSERT INTO %s (product_id,quantity) VALUES (?, ?)", this.table);
		PreparedStatement ps = db.prepareStatement(query);
		try {
			ps.setInt(1, this.getProduct().getProductID());
			ps.setInt(2, this.quantity);
			ps.execute();
			if (ps.getUpdateCount() != 0) {
				return this;
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
		return null;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}
