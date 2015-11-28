package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDao {

	//�жϹ���Ա�Ƿ��ܵ�½
	public int adminValidate(String userName, String userPass, String url,String user,String pass) {
		try (Connection conn = DriverManager.getConnection(url, user, pass);
				PreparedStatement pstmt = conn
						.prepareStatement("select * from tb_admin where adminName=? and adminPassword=?")) {
			pstmt.setString(1, userName);
			pstmt.setString(2, userPass);
			try (ResultSet rs = pstmt.executeQuery()) {
				// �����ѯ��ResultSet���г���һ���ļ�¼�����¼�ɹ�
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
	
	//�ж�������Ա�Ƿ��ܵ�½
	public boolean salerValidate(String userName, String userPass, String url,String user,String pass) {
		try (Connection conn = DriverManager.getConnection(url, user, pass);
				PreparedStatement pstmt = conn
						.prepareStatement("select * from tb_saler where salerName=? and salerPassword=?")) {
			pstmt.setString(1, userName);
			pstmt.setString(2, userPass);
			try (ResultSet rs = pstmt.executeQuery()) {
				// �����ѯ��ResultSet���г���һ���ļ�¼�����¼�ɹ�
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
	
	//�жϾ������Ƿ��ܵ�½
	public boolean supplierValidate(String userName, String userPass, String url,String user,String pass) {
		try (Connection conn = DriverManager.getConnection(url, user, pass);
				PreparedStatement pstmt = conn
						.prepareStatement("select * from tb_supplier where supplierName=? and supplierPassword=?")) {
			pstmt.setString(1, userName);
			pstmt.setString(2, userPass);
			try (ResultSet rs = pstmt.executeQuery()) {
				// �����ѯ��ResultSet���г���һ���ļ�¼�����¼�ɹ�
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
}
