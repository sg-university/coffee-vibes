package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import connect.*;

public class Employee {
	private Integer employeeID;
	private Integer positionID;
	private String name;
	private Integer salary;
	private String status;
	private String username;
	private String password;
	private final String table = "employee";
	private Database db = Database.getInstance();

	public Employee() {
		// TODO Auto-generated constructor stub
	}

	public Employee(Integer employeeID, Integer positionID, String name, Integer salary, String username,
			String password,String status) {
		super();
		this.employeeID = employeeID;
		this.positionID = positionID;
		this.name = name;
		this.salary = salary;
		this.username = username;
		this.password = password;
		this.status = status;
	}
	public Employee(Integer employeeID, Integer positionID, String name, Integer salary, String username,
			String password) {
		super();
		this.employeeID = employeeID;
		this.positionID = positionID;
		this.name = name;
		this.salary = salary;
		this.username = username;
		this.password = password;
		this.status = "-";
	}

	@Override
	public String toString() {
		return "Employee [employeeID=" + employeeID + ", positionID=" + positionID + ", name=" + name + ", salary="
				+ salary + ", username=" + username + ", password=" + password + "]";
	}

	private Employee map(ResultSet rs) {
		try {
			Integer id = rs.getInt("id");
			Integer positionId = rs.getInt("position_id");
			String name = rs.getString("name");
			Integer salary = rs.getInt("salary");
			String username = rs.getString("username");
			String password = rs.getString("password");
			String stat = rs.getString("status");
			Employee employee = new Employee(id, positionId, name, salary, username, password,stat);
			return employee;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Employee getEmployeeByID(int id) {
		String sql = String.format("SELECT * FROM %s WHERE id=? ", this.table);
		PreparedStatement ps = this.db.prepareStatement(sql);
		try {
			ps.setInt(1, id);
			ps.execute();
			ResultSet rs = ps.getResultSet();
			if(rs.next()) {
				return map(rs);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	public Employee getEmployeeByCredentials(String username, String password) {
		String sql = String.format("select * from %s where username = ? AND password = ? AND status!='not available'", this.table);
		PreparedStatement ps = this.db.prepareStatement(sql);
		try {
			ps.setString(1, username);
			ps.setString(2, password);
			ps.execute();
			ResultSet rs = ps.getResultSet();
			while (rs.next()) {
				return map(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public Employee insertEmployee() {
		String sql = String.format(
				"insert into %s(position_id, name, salary, username, password) values (?, ?, ?, ?, ?)", this.table);
		String sql2 = "select last_insert_id()";
		PreparedStatement ps = this.db.prepareStatement(sql);
		try {
			ps.setInt(1, this.positionID);
			ps.setString(2, this.name);
			ps.setInt(3, this.salary);
			ps.setString(4, this.username);
			ps.setString(5, this.password);
			ps.execute();
			if (ps.getUpdateCount() != 0) {
				ps = this.db.prepareStatement(sql2);
				ps.execute();
				ResultSet rs = ps.getResultSet();
				while (rs.next()) {
					Integer id = rs.getInt(1);
					this.setEmployeeID(id);
					return this;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public List<Employee> getAllEmployees() {
		String sql = String.format("select * from %s WHERE status!='not available'", this.table);
		PreparedStatement ps = this.db.prepareStatement(sql);
		try {
			ps.execute();
			ResultSet rs = ps.getResultSet();
			List<Employee> employeeList = new ArrayList<Employee>();
			while (rs.next()) {
				Employee employee = this.map(rs);
				employeeList.add(employee);
			}
			return employeeList;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public Employee getEmployee(String username) {
		String sql = String.format("select * from %s where username=?", this.table);
		PreparedStatement ps = this.db.prepareStatement(sql);
		try {
			ps.setString(1, username);
			ps.execute();
			ResultSet rs = ps.getResultSet();
			while (rs.next()) {
				Employee employee = this.map(rs);
				return employee;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public Employee updateEmployee() {
		String sql = String.format(
				"UPDATE %s SET position_id = ?, name = ?, salary = ?, username = ?, password = ? WHERE id = ?",
				this.table);
		PreparedStatement ps = this.db.prepareStatement(sql);
		try {
			ps.setInt(6, this.employeeID);
			ps.setInt(1, this.positionID);
			ps.setString(2, this.name);
			ps.setInt(3, this.salary);
			ps.setString(4, this.username);
			ps.setString(5, this.password);
			ps.execute();
			if (ps.getUpdateCount() != 0) {
				return this;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public boolean fireEmployee(Integer employeeID) {
		String sql = String.format("update %s SET status='not available' WHERE id = ?", this.table);
		PreparedStatement ps = this.db.prepareStatement(sql);
		try {
			ps.setInt(1, employeeID);
			ps.execute();
			if (ps.getUpdateCount() != 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public String getPosition() {
		Position position = new Position();
		Position gotPosition = position.getPositionByEmployeeID(this.employeeID);
		return gotPosition.gePositionName();
	}
	
	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(Integer employeeID) {
		this.employeeID = employeeID;
	}

	public Integer getPositionID() {
		return positionID;
	}

	public void setPositionID(Integer positionID) {
		this.positionID = positionID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSalary() {
		return salary;
	}

	public void setSalary(Integer salary) {
		this.salary = salary;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
