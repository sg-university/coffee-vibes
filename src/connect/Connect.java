package connect;

import java.sql.*;


public class Connect {
	
	private final String username="root";
	private final String password="";
	private final String database="coffee_vibes";
	private final String host="localhost:3306";
	private final String connection = String.format("jdbc:mysql://%s/%s",host,database);
	
	public ResultSet rs;
	public ResultSetMetaData rsm;
	
	private Connection con;
	private Statement stmt;
	private static Connect conn;
	
	
	
	
	private Connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(connection,username,password);
			stmt = con.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Failed to connect, please check again!");
			
		}
		
	}
	
	public static synchronized Connect getInstance() {
		if(conn == null) {
			conn = new Connect();
		}		
		return conn;
	}
	
	public ResultSet executeQuery(String query) {
		rs = null;
		try {
			rs = stmt.executeQuery(query);
			rsm = rs.getMetaData();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return rs;
	}
	
	public void executeUpdate(String query) {
		try {
			stmt.executeUpdate(query);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	public PreparedStatement prepareStatement(String query) {
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(query);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return ps;
	}
}
