package controllers;

import java.util.List;

import models.Voucher;

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

	public Voucher insertVoucher(Integer discount) {
		Voucher voucher = new Voucher(null, discount, "active");
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

	public Boolean deleteVoucher(Integer voucherID) {
		Voucher voucher = new Voucher();
		Boolean result = voucher.delete(voucherID);

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
