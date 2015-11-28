package dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

public class SelectDao {
//	private final static String PROP_FILE = "config/mysql.ini";
//	private static String driver;
//	// url�����ݿ�ķ����ַ
//	private static String url;
//	private static String user;
//	private static String pass;

	// ��ѯ��������
	public ArrayList<String> selectSalerName(String url, String user, String pass) {
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
	
	// ��ѯ��Ӧ������
	public ArrayList<String> selectSupplierName(String url, String user, String pass) {
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
	
//	public static void main(String[] args) throws Exception {
//		// ���ݿ�
//		Properties connProp = new Properties();
//		connProp.load(new FileInputStream(PROP_FILE));
//		driver = connProp.getProperty("driver");
//		url = connProp.getProperty("url");
//		user = connProp.getProperty("user");
//		pass = connProp.getProperty("pass");
//		// ��������
//		Class.forName(driver);
//		
//		new SelectDao().selectSalerName(url, user, pass);
//	}
}
