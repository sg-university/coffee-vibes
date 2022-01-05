package controllers;

import java.util.List;
import java.util.stream.Collectors;

import models.Voucher;
import utils.Validate;
import views.VoucherManagementForm;

public class VoucherHandler {
	private static VoucherHandler voucherHandler = null;
	private String statusMessage;
	private String statusCode;

	public static synchronized VoucherHandler getInstance() {
		if (voucherHandler == null) {
			voucherHandler = new VoucherHandler();
		}

		return voucherHandler;
	}

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

	private VoucherHandler() {
	}

	public void viewVoucherManagementForm() {
		new VoucherManagementForm();
	}

	public List<Voucher> getAllVouchers() {
		Voucher voucher = new Voucher();
		List<Voucher> voucherList = voucher.getAllVouchers();

		if (voucherList == null) {
			this.statusCode = "failed";
			this.statusMessage = "Failed to get all voucher.";
		}

		this.statusCode = "succeed";
		this.statusMessage = "Succeed to get all voucher.";

		return voucherList;
	}

	

	public Voucher insertVoucher(String discount) {
		if (discount.isEmpty()) {
			this.statusCode = "failed";
			this.statusMessage = "Discount Field cannot be empty";
			return null;
		}

		if (!Validate.isInteger(discount)) {
			this.statusCode = "failed";
			this.statusMessage = "Discount must be Integer!";
			return null;
		}

		Voucher voucher = new Voucher(null, Integer.parseInt(discount), "active");
		Voucher insertedVoucher = voucher.insert();

		if (insertedVoucher == null) {
			this.statusCode = "failed";
			this.statusMessage = "Failed to insert voucher.";
			return insertedVoucher;
		}

		this.statusCode = "succeed";
		this.statusMessage = "Succeed to insert voucher.";
		return insertedVoucher;
	}

	public Boolean deleteVoucher(String voucherID) {
		if(voucherID.isEmpty()) {
			this.statusCode = "failed";
			this.statusMessage = "Please choose the voucher by clicking the rows in order to delete the voucher!";
			return false;
		}
		if(!Validate.isInteger(voucherID)) {
			this.statusCode = "failed";
			this.statusMessage ="Voucher ID is not Integer!";
			return false;
		}
		
		Voucher voucher = this.getVoucher(Integer.parseInt(voucherID));

		if (voucher == null) {
			this.statusCode = "failed";
			this.statusMessage = "Failed to get voucher by voucher id.";
			return false;
		}

		if (voucher.getStatus().equals("inactive")) {
			this.statusCode = "failed";
			this.statusMessage = "Failed to delete voucher by voucher id because the status is inactive.";
			return false;
		}

		Boolean result = voucher.delete(Integer.parseInt(voucherID));

		if (result == false) {
			this.statusCode = "failed";
			this.statusMessage = "Failed to delete voucher by voucher id.";
			return false;
		}

		this.statusCode = "succeed";
		this.statusMessage = "Succeed to delete voucher by voucher id.";
		return true;
	}

	public Voucher getVoucher(Integer voucherID) {
		Voucher voucher = new Voucher();
		Voucher gotVoucher = voucher.getVoucher(voucherID);

		if (gotVoucher == null) {
			this.statusCode = "failed";
			this.statusMessage = "Failed to get one voucher by voucher id.";
			return null;
		}

		this.statusCode = "succeed";
		this.statusMessage = "Succeed to get one voucher by voucher id.";
		return gotVoucher;
	}

}
