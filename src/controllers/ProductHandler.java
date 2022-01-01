package controllers;

import java.util.List;

import models.Product;
import utils.Validate;
import views.ProductManagementForm;

public class ProductHandler {
	private static ProductHandler productHandler = null;
	private Product product;
	private String statusMessage;
	private String statusCode;

	public static synchronized ProductHandler getInstance() {
		if (productHandler == null) {
			productHandler = new ProductHandler();
		}

		return productHandler;
	}

	private ProductHandler() {
		product = new Product();
	}

	public void viewProductManagementForm() {
		new ProductManagementForm();
	}

	public Product insertProduct(String name, String description, String price, String stock) {
		if (name.isEmpty()) {
			this.statusCode = "failed";
			this.statusMessage = "Name cannot be empty!";
			return null;
		}

		if (description.isEmpty()) {
			this.statusCode = "failed";
			this.statusMessage = "Description cannot be empty!";
			return null;
		}

		if (!Validate.isInteger(stock)) {
			this.statusCode = "failed";
			this.statusMessage = "Stock must be integer!";
			return null;
		}

		Boolean isStockMoreThanOrEqualToZero = Integer.parseInt(stock) >= 0;
		if (!isStockMoreThanOrEqualToZero) {
			this.statusCode = "failed";
			this.statusMessage = "Stock must be more than or equal to zero!";
			return null;
		}

		if (!Validate.isInteger(price)) {
			this.statusCode = "failed";
			this.statusMessage = "Price must be Integer!";
			return null;
		}

		product = new Product(name, description, Integer.parseInt(price), Integer.parseInt(stock));
		Product temp = product.insertNewProduct();

		if (temp == null) {
			this.statusCode = "failed";
			this.statusMessage = "Failed to insert product.";
			return null;
		}

		this.statusCode = "succeed";
		this.statusMessage = "Succeed to insert product.";

		return temp;
	}

	public List<Product> getAllProducts() {
		List<Product> temp = product.getAllProduct();
		if (temp == null) {
			this.statusCode = "failed";
			this.statusMessage = "Failed to get all product.";
			return null;
		}

		this.statusCode = "succeed";
		this.statusMessage = "Succeed to get all product.";
		return temp;
	}

	public Product getProduct(Integer productID) {
		product = new Product();
		product = product.getProduct(productID);
		if (product == null) {
			this.statusCode = "failed";
			this.statusMessage = "Failed to get product by product id because product not found";
		}
		this.statusCode = "succeed";
		this.statusMessage = "Succeed to get product by product id.";
		return product;
	}

	public Product updateProduct(Integer productID, String name, String description, String price) {
		if (name.isEmpty()) {
			this.statusCode = "failed";
			this.statusMessage = "Name cannot be empty!";
			return null;
		}

		if (description.isEmpty()) {
			this.statusCode = "failed";
			this.statusMessage = "Description cannot be empty!";
			return null;
		}

		if (!Validate.isInteger(price)) {
			this.statusCode = "failed";
			this.statusMessage = "Price must be Integer!";
			return null;
		}

		Product product = this.getProduct(productID);
		product.setName(name);
		product.setDescription(description);
		product.setPrice(Integer.parseInt(price));
		product = product.updateProduct();

		if (product == null) {
			this.statusCode = "failed";
			this.statusMessage = "Failed to update product by product id.";
			return null;
		}

		this.statusMessage = "Succeed to update product by product id.";
		return product;
	}

	public Product updateProductStock(Integer productID, String stock) {
		Product product = this.getProduct(productID);

		if (!Validate.isInteger(stock)) {
			this.statusCode = "failed";
			this.statusMessage = "Stock must be integer!";
			return null;
		}

		Boolean isStockMoreThanOrEqualToZero = Integer.parseInt(stock) >= 0;
		if (!isStockMoreThanOrEqualToZero) {
			this.statusCode = "failed";
			this.statusMessage = "Stock must be more than or equal to zero!";
			return null;
		}

		product.setStock(Integer.parseInt(stock));
		product = product.updateProduct();

		if (product == null) {
			this.statusCode = "failed ";
			this.statusMessage = "Failed to update product stock by product id.";
			return null;
		}

		this.statusCode = "succeed";
		this.statusMessage = "Succeed to update product stock by product id.";
		return product;
	}

	public Boolean deleteProduct(Integer productID) {
		Product product = this.getProduct(productID);

		Boolean result = product.deleteProduct();

		if (result == null) {
			this.statusCode = "failed";
			this.statusMessage = "Failed to delete product by product id.";
		}

		this.statusCode = "succeed";
		this.statusMessage = "Succeed to delete product by product id.";

		return result;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

}
