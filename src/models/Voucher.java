package models;

import java.util.List;

import connect.Database;

public class Voucher {
	private Integer voucherID;
	private Integer discount;
	private String status;
	private final String table = "voucher";
	private Database db = Database.getInstance();
	
	public Voucher() {
		// TODO Auto-generated constructor stub
	}

	public Voucher generateVoucher() {
		
		return null;
	}
	
	public List<Voucher> getAllVouchers(){
		
		
		return null;
	}
	
	public Voucher getVoucher(Integer voucherID) {
		
		return null;
	}
	
	public boolean deleteVoucher(Integer voucherID) {
		
		return false;
	}
	
	
	public Integer getVoucherID() {
		return voucherID;
	}

	public void setVoucherID(Integer voucherID) {
		this.voucherID = voucherID;
	}

	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
