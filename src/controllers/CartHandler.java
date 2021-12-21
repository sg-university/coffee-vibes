package controllers;

import java.util.List;

import models.CartItem;
import models.Product;

public class CartHandler {
	private static CartHandler cartHandler = null;
	private List<CartItem> listItem;
	public static synchronized CartHandler getInstance() {
		if(cartHandler == null) {
			cartHandler = new CartHandler();
		}
		
		return cartHandler;
	}
	
	private CartHandler() {
		// TODO Auto-generated constructor stub
	}

	public CartItem addToCart(Integer productID, Integer quantity) {
		
		return null;
	}
	
	public void viewAddProductToCartForm() {
		
		
	}
	
	public void viewCheckOutForm() {
		
	}
	
	public List<CartItem> getCart(){
		
		return this.listItem;
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
