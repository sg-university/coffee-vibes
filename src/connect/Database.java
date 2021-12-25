package connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

	private final String username = "root";
	private final String password = "";
	private final String database = "coffee_vibes";
	private final String host = "localhost:3306";
	private final String url = String.format("jdbc:mysql://%s/%s", host, database);

	private Connection connection;
	private Statement statement;
	public static Database instance;

	private Database() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(url, username, password);
			statement = connection.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Failed to connect, please check again!");
		}
	}

	public static Database getInstance() {
		if (instance == null)
			instance = new Database();
		return instance;
	}

	public ResultSet executeQuery(String sql) {
		ResultSet resultSet = null;
		try {
			resultSet = this.statement.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultSet;
	}

	public PreparedStatement prepareStatement(String sql) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = this.connection.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return preparedStatement;
	}

	public Connection getConnection() {
		return this.connection;
	}
}
