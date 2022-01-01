package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Format;
import java.util.List;
import java.util.Vector;

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
	
	public Voucher(Integer voucherID, Integer discount, String status) {
		super();
		this.voucherID = voucherID;
		this.discount = discount;
		this.status = status;
	}

	private Voucher map(ResultSet rs) {
		
		try {
			int id = rs.getInt("id");
			String status = rs.getString("status");
			int discount = rs.getInt("discount");
			return new Voucher(id, discount, status);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public List<Voucher> getAllVouchers(){
		String query = String.format("SELECT * FROM %s WHERE status='active'", this.table);
		ResultSet rs = db.executeQuery(query);
		Vector<Voucher> listVoucher = new Vector<Voucher>();
		try {
			while(rs.next()) {
				listVoucher.add(map(rs));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listVoucher;
	}
	
	public Voucher getVoucher(Integer voucherID) {
		String query = String.format("SELECT * FROM %s WHERE id = ?", this.table);
		PreparedStatement ps = db.prepareStatement(query);
		Voucher vouch = null;
		try {
			ps.setInt(1, voucherID);
			ps.execute();
			if(ps.getUpdateCount() != 0) {
				ResultSet rs = ps.getResultSet();
				if(rs.next()) {
					vouch = map(rs);
				}
				
			}
			
//			return ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vouch;
	}
	
	public boolean deleteVoucher(Integer voucherID) {
		String query = String.format("UPDATE %s SET status=? WHERE id=?",this.table);
		PreparedStatement ps = db.prepareStatement(query);
		try {
			ps.setString(1, "inactive");
			ps.setInt(2, voucherID);
			return ps.executeUpdate() == 1;
			
		} catch (Exception e) {
			// TODO: handle exception
		}
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
