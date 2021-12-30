package controllers;

import java.util.List;

import models.Voucher;

public class VoucherHandler {
	private static VoucherHandler voucherHandler = null;
	
	public static synchronized VoucherHandler getInstance() {
		if(voucherHandler == null) {
			voucherHandler = new VoucherHandler();
		}
		
		return voucherHandler;
	}
	private VoucherHandler() {
		// TODO Auto-generated constructor stub
	}

	public void viewVoucherManagementForm() {
		
	}
	
	public List<Voucher> getAllVouchers(){
		Voucher voucher = new Voucher();
		List<Voucher> listVoucher = voucher.getAllVouchers();
		
		return listVoucher;
	}

	public Voucher insertVoucher(Integer discount) {
		
		return null;
	}
	
	public boolean deleteVoucher(Integer voucherID) {
		
		return false;
	}
	
	public Voucher getVoucher(Integer voucherID) {
		
		return null;
	}

}
