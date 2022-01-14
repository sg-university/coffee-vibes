package models;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

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
			Integer productId = rs.getInt(3);
			Integer quantity = rs.getInt(4);
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

	public List<TransactionItem> getByTransactionId(Integer transactionId) {
		String sql = String.format("SELECT * FROM %s WHERE transaction_id=?", this.table);

		try {
			PreparedStatement ps = db.prepareStatement(sql);
			ps.setInt(1, transactionId);
			ps.execute();
			ResultSet rs = ps.getResultSet();
			Vector<TransactionItem> listItem = new Vector<TransactionItem>();
			while (rs.next()) {
				TransactionItem transactionItem = this.map(rs);
				listItem.add(transactionItem);
			}
			return listItem;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return new Vector<TransactionItem>();
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
