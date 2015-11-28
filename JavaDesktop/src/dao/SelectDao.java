package dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

public class SelectDao {
//	private final static String PROP_FILE = "config/mysql.ini";
//	private static String driver;
//	// url是数据库的服务地址
//	private static String url;
//	private static String user;
//	private static String pass;

	// 查询销售名称
	public ArrayList<String> salerNameSelect(String url, String user, String pass) {
		ArrayList<String> salerName = new ArrayList<>();

		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			String query = "select salerName from tb_saler";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			
			while(rs.next()){
				String temp = rs.getString("salerName");
				salerName.add(temp);
			}
			
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return salerName;
	}
	
	// 查询供应商名称
	public ArrayList<String> supplierNameSelect(String url, String user, String pass) {
		ArrayList<String> supplierName = new ArrayList<>();
		
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			String query = "select supplierName from tb_supplier";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			
			while(rs.next()){
				String temp = rs.getString("supplierName");
				supplierName.add(temp);
			}
			
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return supplierName;
	}
	
	// 查询店铺名称
	public ArrayList<String> shopNameSelect(String url, String user, String pass) {
		ArrayList<String> shopName = new ArrayList<>();
		
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			String query = "select shopName from tb_shop";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			
			while(rs.next()){
				String temp = rs.getString("shopName");
				shopName.add(temp);
			}
			
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return shopName;
	}
	
	// 查询出发城市名称
	public ArrayList<String> departNameSelect(String url, String user, String pass) {
		ArrayList<String>  departName = new ArrayList<>();
		
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			String query = "select  departName from tb_depart";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			
			while(rs.next()){
				String temp = rs.getString("departName");
				 departName.add(temp);
			}
			
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return  departName;
	}
	
	// 查询目的地名称
	public ArrayList<String> destinationNameselect(String url, String user, String pass) {
		ArrayList<String> destinationName = new ArrayList<>();
		
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			String query = "select destinationName from tb_destination";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			
			while(rs.next()){
				String temp = rs.getString("destinationName");
				destinationName.add(temp);
			}
			
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return destinationName;
	}
	
	public boolean lineAdd(String lineName,String salerName,String supplierName,String shopName
			,String destinationName,String departName,String expenseInfo,String isThrough,String url, String user, String pass){
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			String query = "insert into tb_line (lineName,salerName,supplierName,shopName,destinationName,departName,expenseInfo,isThrough) "
					+ "values (?,?,?,?,?,?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, lineName);
			pstmt.setString(2, salerName);
			pstmt.setString(3, supplierName);
			pstmt.setString(4, shopName);
			pstmt.setString(5, destinationName);
			pstmt.setString(6, departName);
			pstmt.setString(7, expenseInfo);
			pstmt.setString(8, isThrough);

			if (pstmt.executeUpdate() > 0) {
				conn.close();
				return true;
			} else {
				conn.close();
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
//	public static void main(String[] args) throws Exception {
//		// 数据库
//		Properties connProp = new Properties();
//		connProp.load(new FileInputStream(PROP_FILE));
//		driver = connProp.getProperty("driver");
//		url = connProp.getProperty("url");
//		user = connProp.getProperty("user");
//		pass = connProp.getProperty("pass");
//		// 加载驱动
//		Class.forName(driver);
//		
//		new SelectDao().selectSalerName(url, user, pass);
//	}
}
