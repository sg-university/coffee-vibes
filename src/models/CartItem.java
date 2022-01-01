package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

	@Override
	public String toString() {
		return "CartItem [id=" + id + ", product=" + product + ", quantity=" + quantity + "]";
	}

	public CartItem(Integer id, Product product, Integer quantity) {
		super();
		this.id = id;
		this.product = product;
		this.quantity = quantity;
	}

	private CartItem map(ResultSet rs) {
		try {
			Integer id = rs.getInt("id");
			Integer productId = rs.getInt("product_id");
			Integer quantity = rs.getInt("quantity");
			Product product = ProductHandler.getInstance().getProduct(productId);
			CartItem cartItem = new CartItem(id, product, quantity);
			return cartItem;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public List<CartItem> getAllCartItems() {
		String query = String.format("SELECT * FROM %s", this.table);
		try {
			PreparedStatement ps = db.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			List<CartItem> cartItemList = new ArrayList<CartItem>();
			while (rs.next()) {
				CartItem cartItem = map(rs);
				cartItemList.add(cartItem);
			}
			return cartItemList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Boolean updateCartItem(Integer id, Integer quantity) {
		String query = String.format("UPDATE %s SET quantity = ? WHERE product_id = ?", this.table);
		try {
			PreparedStatement ps = db.prepareStatement(query);
			ps.setInt(1, quantity);
			ps.setInt(2, id);
			if (ps.executeUpdate() != 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public CartItem insertNewCartItem() {
		String query = String.format("INSERT INTO %s (product_id,quantity) VALUES (?, ?)", this.table);
		try {
			PreparedStatement ps = db.prepareStatement(query);
			ps.setInt(1, this.getProduct().getProductID());
			ps.setInt(2, this.quantity);
			ps.execute();
			if (ps.getUpdateCount() != 0) {
				return this;
			}
		} catch (SQLException e) {
			e.printStackTrace();
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
