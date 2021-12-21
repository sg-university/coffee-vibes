package models;

import java.util.Date;
import java.util.List;

import connect.Connect;

public class Transaction {
	private Integer transactionID;
	private Date purchaseDate;
	private Integer voucherID;
	private Integer employeeID;
	private Integer totalPrice;
	private List<TransactionItem> listTransactionItem;
	private final String table = "transaction";
	private Connect conn = Connect.getInstance();
	public Transaction() {
		// TODO Auto-generated constructor stub
	}
	
	public Transaction insertTransaction() {
		
		return null;
	}
	
	public List<Transaction> getAllTransactions(){
		
		return null;
	}
	
	public Transaction getTransactionDetail(int transactionID) {
		//get transaction by ID
		
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
