package models;

import connect.Database;

public class TransactionItem {
	private Integer transactionID;
	private Integer productID;
	private Integer quantity;
	private final String table = "transactionitem";
	private Database db = Database.getInstance();
	public TransactionItem() {
		// TODO Auto-generated constructor stub
	}

	public Integer getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(Integer transactionID) {
		this.transactionID = transactionID;
	}

	public Integer getProductID() {
		return productID;
	}

	public void setProductID(Integer productID) {
		this.productID = productID;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}
