package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import connect.Connect;
import controllers.ProductHandler;

public class CartItem {
	private Integer id;
	private Product product;
	private Integer quantity;
	private final String table = "cart_item";
	private Connect conn = Connect.getInstance();
	public CartItem() {
		// TODO Auto-generated constructor stub
	}
	
	private CartItem map(ResultSet rs) {
		try {
			int id = rs.getInt("id");
			int productId = rs.getInt("product_id");
			int quantity = rs.getInt("quantity");
			Product productTemp = ProductHandler.getInstance().getProduct(productId);
			CartItem cartItem = new CartItem(productTemp, quantity,id);
			return cartItem;
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return null;
	}
	
	public List<CartItem> getAllCartItems(){
		String query = String.format("SELECT * FROM %s", this.table);
		ResultSet rs = conn.executeQuery(query);
		Vector<CartItem> listCart = new Vector<CartItem>();
		try {
			while(rs.next()) {
				
				CartItem cartItem = map(rs);
				listCart.add(cartItem);
			}
			return listCart;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	public CartItem(Product product, Integer quantity,Integer id) {
		super();
		this.product = product;
		this.quantity = quantity;
		this.id = id;
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
