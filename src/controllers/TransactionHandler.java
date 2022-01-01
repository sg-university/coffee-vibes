package controllers;

import java.util.List;
import java.util.Vector;

import models.CartItem;
import models.Product;
import models.Transaction;
import models.TransactionItem;
import models.Voucher;
import views.TransactionCheckoutForm;

public class TransactionHandler {
	private static TransactionHandler transactionHandler = null;
	private Transaction transaction;
	private String errorMsg;
	
	public String getErrorMsg() {
		return errorMsg;
	}



	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}



	public static synchronized TransactionHandler getInstance() {
		if(transactionHandler == null) {
			transactionHandler = new TransactionHandler();
		}
		
		return transactionHandler;
	}
	
	
	
	private TransactionHandler() {
		transaction = new Transaction();
		// TODO Auto-generated constructor stub
	}
	
	
	public List<Transaction> getAllTransactions(){
		
		return null;
	}
	
	public Transaction getTransactionDetail(Integer transactionID) {
		
		return null;
	}
	
	public Transaction insertTransaction(String voucherID,Integer employeeID, Integer totalPayment) {
		int vouchID = 0;
		int empID = employeeID;
		int total = 0;
		Voucher vouch = new Voucher();
		boolean useVouch = false;
		if(!voucherID.equals("")) {
			try {
				vouchID = Integer.parseInt(voucherID);
				
				vouch = VoucherHandler.getInstance().getVoucher(vouchID);
				System.out.println(vouchID);
				if(vouch == null) {
					errorMsg = "Voucher is not found!";
					return null;
				}
				
				boolean isUpdate = VoucherHandler.getInstance().deleteVoucher(vouchID);
				System.out.println(vouchID);
				if(!isUpdate) {
					errorMsg = "Can't Use the Voucher!";
					return null;
				}
				useVouch = true;
				
			} catch (Exception e) {
				// TODO: handle exception
				errorMsg = "Voucher ID can't be parsed to Integer!";
				return null;
			}
		}
		
		Transaction temp = new Transaction();
		if(useVouch) {
			total = totalPayment - (totalPayment * vouch.getDiscount()/100);
			temp = transaction.insertTransaction(vouchID, empID, total);
		}else {
			total = totalPayment;
			temp = transaction.insertTransaction(empID, total);
			//voucher id = 0 berarti tidak ada voucher;
		}
		
		
		
		
		List<CartItem> listCart = CartHandler.getInstance().getCart();
		List<TransactionItem> listTransDetail = new Vector<TransactionItem>();
		for (CartItem cartItem : listCart) {
			int tempStock = cartItem.getProduct().getStock()-cartItem.getQuantity();
			ProductHandler.getInstance().updateProductStock(cartItem.getProduct().getProductID(), tempStock);
			TransactionItem item = new TransactionItem(temp.getTransactionID(),cartItem.getProduct().getProductID(),cartItem.getQuantity());
			item.insertToDB();
		}
		
		CartHandler.getInstance().clearCart();
//		temp.setListTransactionItem(new Vector<TransactionItem>());
		errorMsg = "Checkout Success!";
		return temp;
	}
	public void viewTransactionCheckout() {
		new TransactionCheckoutForm();
	}
	public void viewTransactionManagement() {
		// TODO Auto-generated method stub

	}
}
