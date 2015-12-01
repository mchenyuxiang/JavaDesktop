package dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

public class TimeDao {
	 private final static String PROP_FILE = "config/mysql.ini";
	 private static String driver;
	 // url�����ݿ�ķ����ַ
	 private static String url;
	 private static String user;
	 private static String pass;

	//��ѯ�Ѿ��������棬���ǻ�û���Լ��鿴����Ϣ
	public ArrayList<String> noLookMessage(String userName,String url,String user,String pass){
		ArrayList<String> messageInfo = new ArrayList<>();
		Connection conn;
		String query = "select messageInfo from tb_message"
				+ " where FIND_IN_SET('"
				+ userName
				+ "',messageEnd) and "
				+ "FIND_IN_SET('"
				+ userName
				+ "',messageStatus) = 0";
//		System.out.println(query);
		Statement stmt;
		try {
			conn = DriverManager.getConnection(url, user, pass);
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String temp = rs.getString("messageInfo");
				messageInfo.add(temp);
			}
//			for(String tt: messageInfo){
//				System.out.println(tt);
//			}

			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return messageInfo;
	}
	
//	public static void main(String[] args) throws IOException, ClassNotFoundException {
//		Properties connProp = new Properties();
//		connProp.load(new FileInputStream(PROP_FILE));
//		driver = connProp.getProperty("driver");
//		url = connProp.getProperty("url");
//		user = connProp.getProperty("user");
//		pass = connProp.getProperty("pass");
//		// ��������
//		Class.forName(driver);
//		
//		new TimeDao().noLookMessage("saler", url, user, pass);
//	}
}
