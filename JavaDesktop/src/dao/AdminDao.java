package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class AdminDao {

	// 添加销售
	public boolean insertSale(String salerName, String salerPhone,
			String salerTel, String salerQQ, String salerWeiXin,
			String salerPassword, String url, String user, String pass) {
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			String query = "insert into tb_saler (salerName,salerPhone,salerTel,salerQQ,salerWeiXin,salerPassword) "
					+ " values (?,?,?,?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, salerName);
			pstmt.setString(2, salerPhone);
			pstmt.setString(3, salerTel);
			pstmt.setString(4, salerQQ);
			pstmt.setString(5, salerWeiXin);
			pstmt.setString(6, salerPassword);

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

	// 添加经销商
	public boolean insertSupplier(String supplierName, String supplierAddress,
			String supplierContact, String supplierPhone, String supplierTel,
			String supplierQQ, String supplierWinXin, String supplierPassword,String supplierSale,
			String url, String user, String pass) {
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			String query = "insert into tb_supplier (supplierName,supplierAddress,supplierContact,supplierPhone"
					+ ",supplierTel,supplierQQ,supplierWinXin,supplierPassword,supplierSaler) "
					+ " values (?,?,?,?,?,?,?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, supplierName);
			pstmt.setString(2, supplierAddress);
			pstmt.setString(3, supplierContact);
			pstmt.setString(4, supplierPhone);
			pstmt.setString(5, supplierTel);
			pstmt.setString(6, supplierQQ);
			pstmt.setString(7, supplierWinXin);
			pstmt.setString(8, supplierPassword);
			pstmt.setString(9, supplierSale);

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

	// 添加店铺
	public boolean insertShop(String shopName, String shopInfo, String url,
			String user, String pass) {
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			String query = "insert into tb_shop (shopName,shopInfo) "
					+ " values (?,?)";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, shopName);
			pstmt.setString(2, shopInfo);

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
	// 添加出发地
	public boolean insertDepart(String departName, String departInfo, String url,
			String user, String pass) {
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			String query = "insert into tb_depart (departName,departInfo) "
					+ " values (?,?)";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, departName);
			pstmt.setString(2, departInfo);
			
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
	// 添加目的地
	public boolean insertDestination(String destinationName, String destinationInfo, String url,
			String user, String pass) {
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			String query = "insert into tb_destination (destinationName,destinationInfo) "
					+ " values (?,?)";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, destinationName);
			pstmt.setString(2, destinationInfo);
			
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
}
