package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connect.Database;

public class Position {

	private Integer positionID;
	private String positionName;
	private final String table = "employee_position";
	private Database db = Database.getInstance();

	public Position() {
		// TODO Auto-generated constructor stub
	}

	public Position(Integer positionID, String positionName) {
		super();
		this.positionID = positionID;
		this.positionName = positionName;
	}

	public String getPositionName() {
		return positionName;
	}

	@Override
	public String toString() {
		return "Position [positionID=" + positionID + ", positionName=" + positionName + "]";
	}

	private Position map(ResultSet rs) {
		try {
			Integer id = rs.getInt("id");
			String name = rs.getString("name");
			Position position = new Position(id, name);
			return position;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Position getPosition(Integer positionID) {
		String sql = String.format("select * from %s where id=?", this.table);
		PreparedStatement ps = this.db.prepareStatement(sql);
		try {
			ps.setInt(1, positionID);
			ps.execute();
			ResultSet rs = ps.getResultSet();
			while (rs.next()) {
				return this.map(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public List<Position> getAllPosition() {
		String sql = String.format("select * from %s", this.table);
		PreparedStatement ps = this.db.prepareStatement(sql);
		try {
			ps.execute();
			ResultSet rs = ps.getResultSet();
			List<Position> positionList = new ArrayList<Position>();
			while (rs.next()) {
				Position position = this.map(rs);
				positionList.add(position);
			}
			return positionList;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public Position getPositionByEmployeeID(Integer employeeID) {
		String sql = String.format(
				"select employee_position.* from employee inner join employee_position on employee.position_id = employee_position.id where employee.id = ?");

		PreparedStatement ps = this.db.prepareStatement(sql);
		try {
			ps.setInt(1, employeeID);
			ps.execute();
			ResultSet rs = ps.getResultSet();
			while (rs.next()) {
				Position position = this.map(rs);
				return position;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public Integer getPositionID() {
		return positionID;
	}

	public void setPositionID(Integer positionID) {
		this.positionID = positionID;
	}

	public String gePositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

}
