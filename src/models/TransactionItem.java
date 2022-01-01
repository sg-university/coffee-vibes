package models;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

	private TransactionItem map(ResultSet rs) {
		try {
			Integer transactionId = rs.getInt(1);
			Integer productId = rs.getInt(2);
			Integer quantity = rs.getInt(3);
			return new TransactionItem(transactionId, productId, quantity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Boolean insert() {
		String query = String.format("INSERT INTO %s (transaction_id,product_id,quantity) VALUES(?, ?, ?)", this.table);
		PreparedStatement ps = db.prepareStatement(query);
		try {
			ps.setInt(1, this.transactionID);
			ps.setInt(2, this.productID);
			ps.setInt(3, this.quantity);
			return ps.executeUpdate() != 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public TransactionItem getByTransactionId(Integer trasnsactionId) {
		String sql = String.format("SELECT * FROM %s WHERE id=?", this.table);

		try {
			PreparedStatement ps = db.prepareStatement(sql);
			ps.setInt(1, transactionID);
			ps.execute();
			ResultSet rs = ps.getResultSet();
			while (rs.next()) {
				TransactionItem transactionItem = this.map(rs);
				return transactionItem;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
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
