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
		if(quantity.equals("") == true) {
			errorMsg = "Quantity field must be fill!";
			return null;
		}
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
				cartItem.setQuantity(qty);
				errorMsg = "Item already in cart, Update Success!";

				return cartItem;
			}
		}
		
		CartItem cart = new CartItem(item, qty, id);
//		if(cart.insertNewCartItem() == null) {
//			errorMsg = "Insert Failed!";
//			return null;
//		}
		listItem.add(cart);
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
//		listItem = cartItem.getAllCartItems();
		if(listItem != null) {
			return listItem;
		}else {
			listItem = new Vector<CartItem>();
			errorMsg = "There is no CartItem";
		}
		return listItem;
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
	
	public boolean removeProductFromCart(String productID) {
		int id = Integer.parseInt(productID);

		
		for(int i = 0; i < listItem.size();i++) {
			if(listItem.get(i).getId() == id) {
				listItem.remove(i);
				errorMsg = "Delete Success!";
				return true;
			}
		}
		errorMsg = "Delete Failed, Item was not found!";
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
