package controllers;

import java.util.ArrayList;
import java.util.List;

import models.CartItem;
import models.Product;
import utils.Validate;
import views.CartManagementForm;

public class CartHandler {
	private static CartHandler cartHandler = null;
	private List<CartItem> cartItemList;
	private String statusMessage;
	private String statusCode;

	public static synchronized CartHandler getInstance() {
		if (cartHandler == null) {
			cartHandler = new CartHandler();
		}
		return cartHandler;
	}

	private CartHandler() {
		this.cartItemList = new ArrayList<CartItem>();
	}

	public List<CartItem> getListItem() {
		return cartItemList;
	}

	public void setListItem(List<CartItem> listItem) {
		this.cartItemList = listItem;
	}

	public CartItem addToCart(String productID, String quantity) {
		if (quantity.isEmpty()) {
			this.statusMessage = "Quantity field must be fill!";
			this.statusCode = "failed";
			return null;
		}

		if (!Validate.isInteger(productID)) {
			this.statusMessage = "ProductId must be Integer!";
			this.statusCode = "failed";
			return null;
		}

		if (!Validate.isInteger(quantity)) {
			this.statusMessage = "Quantity must be Integer!";
			this.statusCode = "failed";
			return null;
		}

		ProductHandler productHandler = ProductHandler.getInstance();
		Product product = productHandler.getProduct(Integer.parseInt(productID));
		if (product == null) {
			this.statusMessage = "Product is must be available!";
			this.statusCode = "failed";
			return null;
		}

		if (!Validate.range(Integer.parseInt(quantity), 1, product.getStock())) {
			this.statusMessage = "Quantity must be in 1 - " + product.getStock();
			this.statusCode = "failed";
			return null;
		}

		for (CartItem cartItem : cartItemList) {
			if (cartItem.getProduct().getProductID() == Integer.parseInt(productID)) {
				cartItem.setQuantity(Integer.parseInt(quantity));
				this.statusMessage = "Item already in cart, update success!";
				return cartItem;
			}
		}

		CartItem cart = new CartItem(cartItemList.size(), product, Integer.parseInt(quantity));
//		if(cart.insertNewCartItem() == null) {
//			errorMsg = "Insert Failed!";
//			return null;
//		}
		cartItemList.add(cart);
		this.statusMessage = "Succeed to add cart item.";
		this.statusCode = "succeed";
		return cart;
	}

	public void viewCartManagementForm() {
		new CartManagementForm();
	}

	public void viewCartManagementForm(Product product) {
		new CartManagementForm(product);
	}

	public void viewAddProductToCartForm() {

	}

	public void viewCheckOutForm() {

	}

	public List<CartItem> getCart() {
		if (this.cartItemList == null) {
			this.statusCode = "failed";
			this.statusMessage = "Failed to get cart.";
		}

		if (this.cartItemList.isEmpty()) {
			this.statusMessage = "There is no cart item.";
		}

		this.statusMessage = "Succeed to get the cart.";
		this.statusCode = "success";
		return cartItemList;
	}

	public Product getProduct(Integer productID) {
		ProductHandler productHandler = ProductHandler.getInstance();
		Product product = productHandler.getProduct(productID);
		if (product == null) {
			this.statusMessage = "Failed to get product by product id.";
			this.statusCode = "failed";
			return null;
		}
		this.statusMessage = "Succeed to get product by product id.";
		this.statusCode = "succeed";
		return product;
	}

	public Product updateProductStock(Integer productID, Integer stock) {

		return null;
	}

	public CartItem updateCartProductQuantity(Integer productID, Integer quantity) {

		return null;
	}

	public Boolean removeProductFromCart(String productID) {
		Integer id = Integer.parseInt(productID);

		for (int i = 0; i < cartItemList.size(); i++) {
			if (cartItemList.get(i).getProduct().getProductID() == id) {
				cartItemList.remove(i);
				this.statusMessage = "Succeed to remove product from cart by product id.";
				this.statusCode = "succeed";
				return true;
			}
		}
		this.statusMessage = "Failed to remove product from cart by product id because the item was not found.";
		this.statusCode = "failed";
		return false;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String errorMsg) {
		this.statusMessage = errorMsg;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public void clearCart() {
		this.cartItemList.clear();
	}

}
