package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Format;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import connect.Database;
import utils.Rand;

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
		Integer discount = Rand.range(1, 100);
		Voucher voucher = new Voucher(null, discount, "active");
		return voucher.insert();
	}

	public Voucher(Integer voucherID, Integer discount, String status) {
		super();
		this.voucherID = voucherID;
		this.discount = discount;
		this.status = status;
	}

	private Voucher map(ResultSet rs) {
		try {
			Integer id = rs.getInt("id");
			String status = rs.getString("status");
			Integer discount = rs.getInt("discount");
			return new Voucher(id, discount, status);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Voucher> getAllVouchers() {
		String sql = String.format("SELECT * FROM %s WHERE status='active'", this.table);
		try {
			ResultSet rs = db.executeQuery(sql);
			List<Voucher> listVoucher = new ArrayList<Voucher>();
			while (rs.next()) {
				Voucher voucher = this.map(rs);
				listVoucher.add(voucher);
			}
			return listVoucher;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Voucher getVoucher(Integer voucherID) {
		String query = String.format("SELECT * FROM %s WHERE id = ?", this.table);
		PreparedStatement ps = db.prepareStatement(query);
		try {
			ps.setInt(1, voucherID);
			ps.execute();
			ResultSet rs = ps.getResultSet();
			while (rs.next()) {
				Voucher voucher = this.map(rs);
				return voucher;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Voucher insert() {
		String query = String.format("insert into %s(discount, status) values (?, ?)", this.table);
		PreparedStatement ps = db.prepareStatement(query);
		try {
			ps.setInt(1, this.discount);
			ps.setString(2, this.status);
			if (ps.executeUpdate() != 0) {
				return this;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean delete(Integer voucherID) {
		String sql = String.format("delete from %s where WHERE id=?", this.table);
		PreparedStatement ps = db.prepareStatement(sql);
		try {
			ps.setInt(1, voucherID);
			if (ps.executeUpdate() != 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean update() {
		String query = String.format("UPDATE %s SET discount=?, status=? WHERE id=?", this.table);
		PreparedStatement ps = db.prepareStatement(query);
		try {
			ps.setInt(1, this.discount);
			ps.setString(2, this.status);
			ps.setInt(3, this.voucherID);
			if (ps.executeUpdate() != 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
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
