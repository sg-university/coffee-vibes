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
	
	public Product insertProduct(String name,String description,String price, String stock) {
		int tempPrice = 0;
		int tempStock = 0;
		
		
		if(name.equals("")) {
			errorMsg = "Name cannot be empty!";
			return null;
		}
		
		if(description.equals("")) {
			errorMsg = "Description cannot be empty!";
			return null;
		}
		
		try {
			tempStock = Integer.parseInt(stock);
		} catch (Exception e) {
			// TODO: handle exception
			errorMsg = "Stock must be integer!";
			return null;
		}
		try {
			tempPrice = Integer.parseInt(price);
		} catch (Exception e) {
			// TODO: handle exception
			errorMsg = "Price must be Integer!";
			return null;
		}
		product = new Product(name, description, tempPrice, tempStock);
		Product temp = product.insertNewProduct();
		
		if(temp == null) {
			errorMsg = "Insert Failed!";
			return null;
		}
		
		return temp;
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
