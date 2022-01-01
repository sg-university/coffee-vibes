package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import connect.Database;

public class Product {
	private Integer productID;
	private String name;
	private String description;
	private Integer price;
	private Integer stock;
	private final String table = "product";
	private Database db = Database.getInstance();
	public Product() {
		// TODO Auto-generated constructor stub
	}
	
	public Product(Integer productID, String name, String description, Integer price, Integer stock) {
		super();
		this.productID = productID;
		this.name = name;
		this.description = description;
		this.price = price;
		this.stock = stock;
	}

	public Product insertNewProduct() {
		
		return null;
	}
	
	public List<Product> getAllProduct(){
		String query = String.format("SELECT * FROM %s", this.table);
		
		ResultSet rs = db.executeQuery(query);
		Vector<Product> products = new Vector<Product>();
		try {
			while(rs.next()) {
				products.add(map(rs));
			}
			return products;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	private Product map(ResultSet rs) {
		try {
			int id = rs.getInt("id");
			String name = rs.getString("name");
			String description = rs.getString("description");
			int price =rs.getInt("price");
			int stock = rs.getInt("stock");
			return new Product(id, name, description, price, stock);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	public Product getProduct(int productID) {
		String query = String.format("SELECT * FROM %s WHERE id = ?", this.table);
		PreparedStatement pstmt = db.prepareStatement(query);
		try {
			pstmt.setInt(1, productID);
			pstmt.execute();
			ResultSet rs = pstmt.getResultSet();
			if(rs.next()) {
				return map(rs);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	public Product updateProduct() {
		String query = String.format("UPDATE %s SET name = ?,description = ?,price = ?,stock = ? WHERE id = ?",this.table);
		PreparedStatement ps = db.prepareStatement(query);
		try {
			ps.setString(1,this.name);
			ps.setString(2, this.description);
			ps.setInt(3, this.price);
			ps.setInt(4, this.stock);
			ps.setInt(5, this.productID);
			ps.execute();
			return this;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean deleteProduct() {
		
		return false;
	}
	
	
	public Integer getProductID() {
		return productID;
	}
	public void setProductID(Integer productID) {
		this.productID = productID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}

	
	
}
