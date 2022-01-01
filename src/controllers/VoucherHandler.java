package controllers;

import java.util.List;

import models.Voucher;

public class VoucherHandler {
	private static VoucherHandler voucherHandler = null;
	private Voucher voucher;
	public static synchronized VoucherHandler getInstance() {
		if(voucherHandler == null) {
			voucherHandler = new VoucherHandler();
		}
		
		return voucherHandler;
	}
	private VoucherHandler() {
		// TODO Auto-generated constructor stub
		voucher = new Voucher();
	}

	public void viewVoucherManagementForm() {
		
	}
	
	public List<Voucher> getAllVouchers(){
		voucher = new Voucher();
		List<Voucher> listVoucher = voucher.getAllVouchers();
		
		return listVoucher;
	}

	public Voucher insertVoucher(Integer discount) {
		
		return null;
	}
	
	public boolean deleteVoucher(Integer voucherID) {
		boolean isDelete = voucher.deleteVoucher(voucherID);
		
		return isDelete;
	}
	
	public Voucher getVoucher(Integer voucherID) {
		Voucher temp = voucher.getVoucher(voucherID);
		if(temp.getStatus().equals("active")) {
			return temp;
		}
		return null;
	}

}
