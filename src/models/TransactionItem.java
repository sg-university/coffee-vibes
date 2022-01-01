package models;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import connect.Database;

public class TransactionItem {
	private Integer transactionID;
	private Integer productID;
	private Integer quantity;
	private final String table = "transaction_item";
	private Database db = Database.getInstance();
	public TransactionItem() {
		// TODO Auto-generated constructor stub
	}
	
	

	public TransactionItem(Integer transactionID, Integer productID, Integer quantity) {
		super();
		this.transactionID = transactionID;
		this.productID = productID;
		this.quantity = quantity;
	}

	public boolean insertToDB() {
		String query = String.format("INSERT INTO %s (transaction_id,product_id,quantity) VALUES(?, ?, ?)", this.table);
		PreparedStatement ps = db.prepareStatement(query);
		try {
			ps.setInt(1, this.transactionID);
			ps.setInt(2, this.productID);
			ps.setInt(3, this.quantity);
			
			return ps.executeUpdate()==1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
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
