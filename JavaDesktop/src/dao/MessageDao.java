package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class MessageDao {

	//更新发送信息
	public boolean updateMessage(Integer messageId ,String messageEnd,String url, String user, String pass){
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			String query = "UPDATE  `tb_message` SET messageEnd = ? WHERE messageId = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, messageEnd);
			pstmt.setInt(2, messageId);


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
	
	//添加发送信息
	public boolean insertMessage(int lineId,String messageStart,String messageEnd,String messageInfo,String url, String user, String pass){
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			String query = "insert into  `tb_message` (messageStart,messageEnd,messageStatus,messageInfo)"
					+ " values(?,?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, messageStart);
			pstmt.setString(2, messageEnd);
			pstmt.setString(3, "还没人看");
			pstmt.setString(4, messageInfo);
			
			
			if (pstmt.executeUpdate() > 0) {
				String updateLine = "update tb_line set isMessage = 1 where lineId = ?";
				pstmt = conn.prepareStatement(updateLine);
				pstmt.setInt(1, lineId);
				pstmt.executeUpdate();
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
