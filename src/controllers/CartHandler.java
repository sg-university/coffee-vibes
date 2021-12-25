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

	public CartItem addToCart(Integer productID, Integer quantity) {
		
		return null;
	}
	public void viewCartManagementForm() {
		new CartManagementForm();
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
	
	public void clearCart() {
		
	}
	
}
