package controllers;

import java.util.List;
import java.util.Vector;

import models.CartItem;
import models.Product;
import views.CartManagementForm;

public class CartHandler {
	private static CartHandler cartHandler = null;
	private List<CartItem> listItem;
	private CartItem cartItem;
	private String errorMsg;
	public static synchronized CartHandler getInstance() {
		if(cartHandler == null) {
			cartHandler = new CartHandler();
		}
		
		return cartHandler;
	}
	
	private CartHandler() {
		// TODO Auto-generated constructor stub
		listItem = new Vector<CartItem>();
		cartItem = new CartItem();
	}

	public CartItem addToCart(String productID, String quantity) {
		int id = 0;
		int qty = 0;
		try {
			int temp = Integer.parseInt(productID);
			id = temp;
			
		} catch (Exception e) {
			// TODO: handle exception
			errorMsg = "ProductID must be Integer!";
			return null;
		}
		
		try {
			int temp = Integer.parseInt(quantity);
			qty = temp;
		} catch (Exception e) {
			errorMsg = "Quantity must be Integer!";
			return null;
			// TODO: handle exception
		}
		
		Product product = new Product();
		Product item = product.getProduct(id);
		if(item == null) {
			errorMsg = "There is no Product Available!";
			return null;
		}
		
		if(qty < 1 || qty > item.getStock()) {
			errorMsg = "Quantity must be in 1 - " + item.getStock();
			return null;
		}
		
		for (CartItem cartItem : listItem) {
			if(cartItem.getProduct().getProductID() == id) {
				
				if(cartItem.updateCartItem(id, qty)) {
					errorMsg = "Item already in cart, Update Success!";
					getCart();
					return cartItem;
				}
				
				errorMsg = "Item already in cart, Update Failed!";
				return cartItem;
			}
		}
		
		CartItem cart = new CartItem(item, qty, id);
		if(cart.insertNewCartItem() == null) {
			errorMsg = "Insert Failed!";
			return null;
		}
		errorMsg = "Insert Success!";
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
	
	public List<CartItem> getCart(){
		listItem = cartItem.getAllCartItems();
		if(listItem != null) {
			return listItem;
		}else {
			listItem = new Vector<CartItem>();
			errorMsg = "There is no CartItem";
		}
		return null;
	}
	
	public Product getProduct(Integer productID) {
		
		return null;
	}
	
	public Product updateProductStock(Integer productID,Integer stock) {
		
		return null;
	}
	
	public CartItem updateCartProductQuantity(Integer productID,Integer quantity) {
		
		return null;
	}
	
	public boolean removeProductFromCart(Integer productID) {
		
		return false;
	}
	
	
	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public void clearCart() {
		
	}
	
}
