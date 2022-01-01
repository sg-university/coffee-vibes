package controllers;

import java.util.List;
import java.util.Vector;

import models.Product;
import views.ProductManagementForm;

public class ProductHandler {
	private static ProductHandler productHandler = null;
	private Product product;
	private String errorMsg;
	public static synchronized ProductHandler getInstance() {
		if(productHandler == null) {
			productHandler = new ProductHandler();
		}
		
		return productHandler;
	}
	private ProductHandler() {
		// TODO Auto-generated constructor stub
		product = new Product();
	}

	public void viewProductManagementForm() {
		// TODO Auto-generated method stub
		new ProductManagementForm();
	}
	
	public Product insertProduct(String name,String description,Integer Price, Integer stock) {
		
		return null;
	}
	
	public List<Product> getAllProducts(){
		List<Product> temp = product.getAllProduct();
		if(temp == null) {
			errorMsg = "There is no product!";
			return new Vector<Product>();
		}
		return temp;
	}
	
	public Product getProduct(Integer productID) {
		product = new Product();
		product = product.getProduct(productID);
		if(product != null) {
			return product;
		}else {
			product = new Product();
			errorMsg = "Product Not Found";
		}
		return null;
	}
	
	public Product updateProduct(Integer productID, String name, String description, Integer price) {
		
		return null;
	}
	
	public Product updateProductStock(Integer productID, Integer stock) {
		product = new Product();
		product = product.getProduct(productID);
		
		if(product != null) {
			product.setStock(stock);
			product.updateProduct();
			return product;
		}
		
		return null;
	}
	
	public boolean deleteProduct(Integer productID) {
		
		return false;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
}
