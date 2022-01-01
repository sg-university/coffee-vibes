package models;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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
		// TODO Auto-generated constructor stub
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
			if(rs.next()) {
				int id = rs.getInt(1);
				Date date = rs.getDate(2);
				int vouchId = rs.getInt(3);
				int empId = rs.getInt(4);
				int totalPrice = rs.getInt(5);
				return new Transaction(id, date, vouchId, empId, totalPrice);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public int getLastInsertId() {
		String query = String.format("SELECT last_insert_id()", this.table);
		PreparedStatement ps = db.prepareStatement(query);
		try {
			ps = this.db.prepareStatement(query);
		    ps.execute();
		    ResultSet rs = ps.getResultSet();
		    while (rs.next()) {
		        Integer id = rs.getInt(1);
		        this.transactionID = id;
		        
		        return id;
		    }
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	public Transaction insertTransaction(int voucherID,int employeeID,int totalPayment) {
//		Date date = new Date(new java.util.Date());
		String query = String.format("INSERT INTO %s (purchase_date,voucher_id,employee_id,total_price) VALUES(?, ?, ?, ?)", this.table);
		String query2 = String.format("SELECT * FROM %s WHERE id=?", this.table);
		PreparedStatement ps = db.prepareStatement(query);
		try {
			ps.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
			ps.setInt(2, voucherID);
			ps.setInt(3, employeeID);
			ps.setInt(4, totalPayment);
			ps.execute();
			if (ps.getUpdateCount() != 0) {
			    int id = getLastInsertId();
			    if(id != 0) {
			    	ps = db.prepareStatement(query2);
			    	ps.setInt(1, id);
			    	ps.execute();
			    	return map(ps.getResultSet());
			    }
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return null;
	}
	
	public List<Transaction> getAllTransactions(){
		
		return null;
	}
	
	public Transaction getTransactionDetail(int transactionID) {
		//get transaction by ID
		String query = String.format("SELECT * FROM %s WHERE id=?", this.table);
		
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
