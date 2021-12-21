package controllers;

import java.util.List;

import models.Product;

public class ProductHandler {
	private static ProductHandler productHandler = null;
	
	public static synchronized ProductHandler getInstance() {
		if(productHandler == null) {
			productHandler = new ProductHandler();
		}
		
		return productHandler;
	}
	public ProductHandler() {
		// TODO Auto-generated constructor stub
	}

	public void viewProductManagementForm() {
		// TODO Auto-generated method stub

	}
	
	public Product insertProduct(String name,String description,Integer Price, Integer stock) {
		
		return null;
	}
	
	public List<Product> getAllProducts(){
		
		return null;
	}
	
	public Product getProduct(Integer productID) {
		
		return null;
	}
	
	public Product updateProduct(Integer productID, String name, String description, Integer price) {
		
		return null;
	}
	
	public Product updateProductStock(Integer productID, Integer stock) {
		
		return null;
	}
	
	public boolean deleteProduct(Integer productID) {
		
		return false;
	}
	
}
