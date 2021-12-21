package controllers;

import java.util.List;

import models.Transaction;

public class TransactionHandler {
	private static TransactionHandler transactionHandler = null;
	
	
	public static synchronized TransactionHandler getInstance() {
		if(transactionHandler == null) {
			transactionHandler = new TransactionHandler();
		}
		
		return transactionHandler;
	}
	
	
	
	private TransactionHandler() {
		// TODO Auto-generated constructor stub
	}
	
	
	public List<Transaction> getAllTransactions(){
		
		return null;
	}
	
	public Transaction getTransactionDetail(Integer transactionID) {
		
		return null;
	}
	
	public Transaction insertTransaction(Integer voucherID,Integer employeeID, Integer totalPayment) {
		
		return null;
	}
	
	public void viewTransactionManagement() {
		// TODO Auto-generated method stub

	}
}
