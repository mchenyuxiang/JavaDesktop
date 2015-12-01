package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginDao {

	// 判断管理员是否能登陆
	public int adminValidate(String userName, String userPass, String url,
			String user, String pass) {
		try (Connection conn = DriverManager.getConnection(url, user, pass);
				PreparedStatement pstmt = conn
						.prepareStatement("select * from tb_admin where adminName=? and adminPassword=?")) {
			pstmt.setString(1, userName);
			pstmt.setString(2, userPass);
			try (ResultSet rs = pstmt.executeQuery()) {
				// 如果查询的ResultSet里有超过一条的记录，则登录成功
				if (rs.next()) {
					int adminId = rs.getInt("adminId");
					conn.close();
					return adminId;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	// 判断销售人员是否能登陆
	public int salerValidate(String userName, String userPass, String url,
			String user, String pass) {
		try (Connection conn = DriverManager.getConnection(url, user, pass);
				PreparedStatement pstmt = conn
						.prepareStatement("select * from tb_saler where salerName=? and salerPassword=?")) {
			pstmt.setString(1, userName);
			pstmt.setString(2, userPass);
			try (ResultSet rs = pstmt.executeQuery()) {
				// 如果查询的ResultSet里有超过一条的记录，则登录成功
				if (rs.next()) {
					int salerId = rs.getInt("salerId");
					conn.close();
					return salerId;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	// 判断经销商是否能登陆
	public boolean supplierValidate(String userName, String userPass,
			String url, String user, String pass) {
		try (Connection conn = DriverManager.getConnection(url, user, pass);
				PreparedStatement pstmt = conn
						.prepareStatement("select * from tb_supplier where supplierName=? and supplierPassword=?")) {
			pstmt.setString(1, userName);
			pstmt.setString(2, userPass);
			try (ResultSet rs = pstmt.executeQuery()) {
				// 如果查询的ResultSet里有超过一条的记录，则登录成功
				if (rs.next()) {
					conn.close();
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	// 根据用户id得到用户名 flag:0、管理员；1、销售
	public String getAdminName(int userId, int flag, String url,
			String user, String pass) {
		String userName = "";
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			String query = "select salerName from tb_saler";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			if (flag == 0) {
				PreparedStatement pstmt = conn
						.prepareStatement("select adminName from tb_admin where adminId = ?");
				pstmt.setInt(1, userId);
			} else if (flag == 1) {
				PreparedStatement pstmt = conn
						.prepareStatement("select salerName from tb_saler where salerId = ?");
				pstmt.setInt(1, userId);
			}

			
			
				// 如果查询的ResultSet里有超过一条的记录，则登录成功
				if (rs.next()) {
					String temp = "";
					if (flag == 0) {
						temp = rs.getString("adminName");
					} else if (flag == 1) {
						temp = rs.getString("adminName");
					}
					userName = temp;
					conn.close();
					return userName;
				}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userName;
	}
}
