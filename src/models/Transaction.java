package models;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import connect.Database;

public class Transaction {
	private Integer transactionID;
	private Date purchaseDate;
	private Integer voucherID;
	private Integer employeeID;
	private Integer totalPrice;
	private List<TransactionItem> listTransactionItem;
	private final String table = "transaction";
	private Database db = Database.getInstance();

	public Transaction() {
		this.listTransactionItem = new ArrayList<TransactionItem>();
	}

	public Transaction(Integer transactionID, Date purchaseDate, Integer voucherID, Integer employeeID,
			Integer totalPrice) {
		super();
		this.transactionID = transactionID;
		this.purchaseDate = purchaseDate;
		this.voucherID = voucherID;
		this.employeeID = employeeID;
		this.totalPrice = totalPrice;
	}

	private Transaction map(ResultSet rs) {
		try {
			Integer id = rs.getInt(1);
			Date date = rs.getDate(2);
			Integer vouchId = rs.getInt(3);
			Integer empId = rs.getInt(4);
			Integer totalPrice = rs.getInt(5);
			return new Transaction(id, date, vouchId, empId, totalPrice);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Integer getLastInsertId() {
		String sql = String.format("SELECT last_insert_id()", this.table);
		PreparedStatement ps = db.prepareStatement(sql);
		try {
			ps = this.db.prepareStatement(sql);
			ps.execute();
			ResultSet rs = ps.getResultSet();
			while (rs.next()) {
				Integer id = rs.getInt(1);
				this.transactionID = id;
				return id;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Transaction insert() {
		String sql = String.format(
				"INSERT INTO %s (purchase_date, voucher_id, employee_id, total_price) VALUES(?, ?, ?, ?)", this.table);
		String sql2 = String.format("SELECT last_insert_id()", this.table);
		PreparedStatement ps = db.prepareStatement(sql);
		try {
			ps.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
			if (this.voucherID == null) {
				ps.setNull(2, Types.NULL);
			} else {
				ps.setInt(2, this.voucherID);
			}
			ps.setInt(3, this.employeeID);
			ps.setInt(4, this.totalPrice);
			ps.execute();
			if (ps.getUpdateCount() != 0) {
				ps = this.db.prepareStatement(sql2);
				ps.execute();
				ResultSet rs = ps.getResultSet();
				while (rs.next()) {
					Integer transactionId = rs.getInt(1);
					this.transactionID = transactionId;
					return this;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Transaction> getAllTransactions() {
		String sql = String.format("SELECT * from %s", this.table);
		try {
			PreparedStatement ps = db.prepareStatement(sql);
			ps.execute();
			ResultSet rs = ps.getResultSet();
			List<Transaction> transactionList = new ArrayList<Transaction>();
			while (rs.next()) {
				Transaction transaction = this.map(rs);
				transactionList.add(transaction);
			}
			return transactionList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Transaction getTransactionDetail(Integer transactionID) {
		String sql = String.format("SELECT * from %s where id=?", this.table);
		try {
			PreparedStatement ps = db.prepareStatement(sql);
			ps.setInt(1, transactionID);
			ps.execute();
			ResultSet rs = ps.getResultSet();
			while (rs.next()) {
				Transaction transaction = this.map(rs);
				return transaction;
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

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public Integer getVoucherID() {
		return voucherID;
	}

	public void setVoucherID(Integer voucherID) {
		this.voucherID = voucherID;
	}

	public Integer getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(Integer employeeID) {
		this.employeeID = employeeID;
	}

	public Integer getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}

	public List<TransactionItem> getListTransactionItem() {
		return listTransactionItem;
	}

	public void setListTransactionItem(List<TransactionItem> listTransactionItem) {
		this.listTransactionItem = listTransactionItem;
	}

}
