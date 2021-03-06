package dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

public class TimeDao {
	 private final static String PROP_FILE = "config/mysql.ini";
	 private static String driver;
	 // url是数据库的服务地址
	 private static String url;
	 private static String user;
	 private static String pass;

	//查询已经发布公告，但是还没被自己查看的信息
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
	
	//若已经销售选择接受了线路则进行更新
	public boolean updateMessageStatus(String userName,String messageId,String url,String user,String pass){
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			String query = "UPDATE tb_message SET messageStatus = CONCAT(messageStatus,',"
					+ userName
					+ "') WHERE FIND_IN_SET('"
					+ userName
					+ "',messageEnd) AND FIND_IN_SET('"
					+ userName
					+ "',messageStatus) = 0 AND messageId = "
					+ messageId;
//			System.out.println(query);
			PreparedStatement pstmt = conn.prepareStatement(query);

			if (pstmt.executeUpdate() > 0) {
				conn.close();
				return true;
			} else {
				conn.close();
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
//	public static void main(String[] args) throws IOException, ClassNotFoundException {
//		Properties connProp = new Properties();
//		connProp.load(new FileInputStream(PROP_FILE));
//		driver = connProp.getProperty("driver");
//		url = connProp.getProperty("url");
//		user = connProp.getProperty("user");
//		pass = connProp.getProperty("pass");
//		// 加载驱动
//		Class.forName(driver);
//		
//		new TimeDao().noLookMessage("saler", url, user, pass);
//	}
}
