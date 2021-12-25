package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import connect.Connect;

public class Employee {
	private Integer employeeID;
	private Integer positionID;
	private String name;
	private String status;
	private Integer salary;
	private String username;
	private String password;
	private final String table = "employee";
	private Connect conn = Connect.getInstance();
	public Employee() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public Employee(Integer employeeID, Integer positionID, String name, String status, Integer salary, String username,
			String password) {
		super();
		this.employeeID = employeeID;
		this.positionID = positionID;
		this.name = name;
		this.status = status;
		this.salary = salary;
		this.username = username;
		this.password = password;
	}



	private Employee map(ResultSet rs) {
		try {
			int id = rs.getInt("id");
			int positionId = rs.getInt("position_id");
			String name = rs.getString("name");
			String status = rs.getString("status");
			int salary = rs.getInt("salary");
			String username = rs.getString("username");
			String password = rs.getString("password");
			return new Employee(id, positionId, name, status, salary, username, password);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	public Employee getEmployeeByLogin(String username,String password) {
		String query = String.format("SELECT * FROM %s WHERE username = ? AND password = ?", this.table);
		PreparedStatement pstmt = conn.prepareStatement(query);
		try {
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			pstmt.execute();
			ResultSet rs = pstmt.getResultSet();
			if(rs.next()) {
//				System.out.println("test");
				return map(rs);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public Employee insertEmployee() {
		
		return null;
	}

	public List<Employee> getAllEmployees(){
		
		return null;
	}
	
	public Employee getEmployee(String username) {
		
		return null;
	}
	
	public Employee updateEmployee() {
		
		return null;
	}
	
	public boolean fireEmployee(int id) {
		
		return false;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
