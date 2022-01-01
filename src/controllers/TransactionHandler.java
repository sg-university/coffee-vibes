package controllers;

import java.util.List;

import models.CartItem;
import models.Transaction;
import models.TransactionItem;
import models.Voucher;
import utils.Validate;
import views.TransactionCheckoutForm;

public class TransactionHandler {
	private static TransactionHandler transactionHandler = null;
	private String statusMessage;
	private String statusCode;

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public static synchronized TransactionHandler getInstance() {
		if (transactionHandler == null) {
			transactionHandler = new TransactionHandler();
		}

		return transactionHandler;
	}

	private TransactionHandler() {
	}

	public List<Transaction> getAllTransactions() {
		Transaction transaction = new Transaction();
		List<Transaction> transactionList = transaction.getAllTransactions();

		if (transactionList == null) {
			this.statusCode = "failed";
			this.statusMessage = "Failed to get all transaction.";
			return null;
		}

		this.statusCode = "succeed";
		this.statusMessage = "Succeed to get all transaction.";

		return transactionList;
	}

	public Transaction getTransactionDetail(Integer transactionID) {
		Transaction transaction = new Transaction();
		transaction = transaction.getTransactionDetail(transactionID);

		if (transaction == null) {
			this.statusCode = "failed";
			this.statusMessage = "Failed to get transaction detail.";
			return null;
		}

		this.statusCode = "succeed";
		this.statusMessage = "Succeed to get transaction detail.";

		return transaction;
	}

	public Transaction insertTransaction(String voucherID, Integer employeeID, Integer totalPayment) {
		Boolean isVoucherIdFilled = !voucherID.isEmpty();
		Boolean isUsingVoucher = isVoucherIdFilled;
		Voucher voucher = null;

		if (isUsingVoucher) {
			Boolean isVoucherIdInteger = Validate.isInteger(voucherID);
			if (!isVoucherIdInteger) {
				this.statusCode = "failed";
				this.statusMessage = "Voucher id must be integer.";
				return null;
			}

			VoucherHandler voucherHandler = VoucherHandler.getInstance();
			voucher = voucherHandler.getVoucher(Integer.parseInt(voucherID));
			if (voucher == null) {
				statusMessage = "Voucher is not found!";
				return null;
			}

			Boolean isVoucherActive = voucher.getStatus().equals("active");
			if (!isVoucherActive) {
				this.statusCode = "failed";
				this.statusMessage = "Voucher must be active.";
				return null;
			}

			voucher.setStatus("inactive");
			Boolean isVoucherUpdated = voucher.update();
			if (!isVoucherUpdated) {
				statusMessage = "Failed to update the voucher to inactive!";
				return null;
			}
		}

		Integer totalPrice = 0;
		Transaction transaction = new Transaction();
		if (isUsingVoucher) {
			totalPrice = totalPayment - (totalPayment * voucher.getDiscount() / 100);
			transaction.setVoucherID(Integer.parseInt(voucherID));
			transaction.setEmployeeID(employeeID);
			transaction.setTotalPrice(totalPrice);
		} else {
			totalPrice = totalPayment;
			transaction.setEmployeeID(employeeID);
			transaction.setTotalPrice(totalPrice);
		}

		transaction.insert();

		CartHandler cartHandler = CartHandler.getInstance();
		List<CartItem> cartItemList = cartHandler.getCart();
		ProductHandler productHandler = ProductHandler.getInstance();
		for (CartItem cartItem : cartItemList) {
			Integer stock = cartItem.getProduct().getStock() - cartItem.getQuantity();
			cartItem.setQuantity(stock);
			productHandler.updateProductStock(cartItem.getProduct().getProductID(), String.valueOf(stock));

			if (productHandler.getStatusCode().equals("failed")) {
				this.setStatusCode(productHandler.getStatusCode());
				this.setStatusMessage(productHandler.getStatusMessage());
				return null;
			}

			TransactionItem transactionItem = new TransactionItem(transaction.getTransactionID(),
					cartItem.getProduct().getProductID(), cartItem.getQuantity());
			transactionItem.insert();
		}

		cartHandler.clearCart();
		this.statusMessage = "Checkout success!";
		this.statusCode = "succeed";
		return transaction;
	}

	public void viewTransactionCheckout() {
		new TransactionCheckoutForm();
	}

	public void viewTransactionManagement() {
		// TODO Auto-generated method stub

	}
}
