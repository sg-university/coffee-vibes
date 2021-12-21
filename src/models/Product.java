package models;

import java.util.List;

import connect.Connect;

public class Product {
	private Integer productID;
	private String name;
	private String description;
	private Integer price;
	private Integer stock;
	private final String table = "product";
	private Connect conn = Connect.getInstance();
	public Product() {
		// TODO Auto-generated constructor stub
	}
	
	public Product insertNewProduct() {
		
		return null;
	}
	
	public List<Product> getAllProduct(){
		
		return null;
	}
	
	public Product getProduct(int productID) {
		
		return null;
	}
	
	public Product updateProduct() {
		
		return null;
	}
	
	public boolean deleteProduct() {
		
		return false;
	}
	
	
	public Integer getProductID() {
		return productID;
	}
	public void setProductID(Integer productID) {
		this.productID = productID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}

	
	
}
