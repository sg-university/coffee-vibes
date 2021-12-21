package models;

import connect.Connect;

public class CartItem {
	private Product product;
	private Integer quantity;
	private final String table = "cartitem";
	private Connect conn = Connect.getInstance();
	public CartItem() {
		// TODO Auto-generated constructor stub
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
