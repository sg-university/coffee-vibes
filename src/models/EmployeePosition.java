package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connect.Database;

public class EmployeePosition {

	private Integer employeePositionID;
	private String employeePositionName;
	private final String table = "employee_position";
	private Database db = Database.getInstance();

	public EmployeePosition() {
		// TODO Auto-generated constructor stub
	}

	public EmployeePosition(Integer employeePositionID, String employeePositionName) {
		super();
		this.employeePositionID = employeePositionID;
		this.employeePositionName = employeePositionName;
	}

	@Override
	public String toString() {
		return "EmployeePosition [employeePositionID=" + employeePositionID + ", employeePositionName="
				+ employeePositionName + "]";
	}

	private EmployeePosition map(ResultSet rs) {
		try {
			Integer id = rs.getInt("id");
			String name = rs.getString("name");
			EmployeePosition employeePosition = new EmployeePosition(id, name);
			return employeePosition;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<EmployeePosition> getAllEmployeePosition() {
		String sql = String.format("select * from %s", this.table);
		PreparedStatement ps = this.db.prepareStatement(sql);
		try {
			ps.execute();
			ResultSet rs = ps.getResultSet();
			List<EmployeePosition> employeePositionList = new ArrayList<EmployeePosition>();
			while (rs.next()) {
				EmployeePosition employeePosition = this.map(rs);
				employeePositionList.add(employeePosition);
			}
			return employeePositionList;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public EmployeePosition getEmployeePositionByEmployeeID(Integer employeeID) {
		String sql = String.format(
				"select employee_position.* from employee inner join employee_position on employee.position_id = employee_position.id where employee.id = ?");

		PreparedStatement ps = this.db.prepareStatement(sql);
		try {
			ps.setInt(1, employeeID);
			ps.execute();
			ResultSet rs = ps.getResultSet();
			while (rs.next()) {
				EmployeePosition employeePosition = this.map(rs);
				return employeePosition;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public Integer getEmployeePositionID() {
		return employeePositionID;
	}

	public void setEmployeePositionID(Integer employeePositionID) {
		this.employeePositionID = employeePositionID;
	}

	public String getEmployeePositionName() {
		return employeePositionName;
	}

	public void setEmployeePositionName(String employeePositionName) {
		this.employeePositionName = employeePositionName;
	}

}
