package SwingStudy;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.util.*;
import java.io.*;
import java.sql.*;

public class LoginFrame {
	private final String PROP_FILE = "config/mysql.ini";
	private String driver;
	// url�����ݿ�ķ����ַ
	private String url;
	private String user;
	private String pass;
	// ��¼�����GUI���
	private JFrame jf = new JFrame("��¼");
	private JTextField userField = new JTextField(20);
	private JTextField passField = new JTextField(20);
	private JButton loginButton = new JButton("��¼");

	private JFrame jf1 = new JFrame("��¼");
	
	public void init() throws Exception {
		Properties connProp = new Properties();
		connProp.load(new FileInputStream(PROP_FILE));
		driver = connProp.getProperty("driver");
		url = connProp.getProperty("url");
		user = connProp.getProperty("user");
		pass = connProp.getProperty("pass");
		// ��������
		Class.forName(driver);
		// Ϊ��¼��ť����¼�������
		loginButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// ��¼�ɹ�����ʾ����¼�ɹ���
				if (validate(userField.getText(), passField.getText())) {
					JOptionPane.showMessageDialog(jf, "��¼�ɹ�");
					jf1.pack();
					jf1.setVisible(true);
				}
				// ������ʾ����¼ʧ�ܡ�
				else {
					JOptionPane.showMessageDialog(jf, "��¼ʧ��");
				}

			}
		});
		jf.add(userField, BorderLayout.NORTH);
		jf.add(passField);
		jf.add(loginButton, BorderLayout.SOUTH);
		jf.pack();
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
	}

	// private boolean validate(String userName, String userPass)
	// {
	// // ִ�в�ѯ��SQL���
	// String sql = "select * from jdbc_test "
	// + "where jdbc_name='" + userName
	// + "' and jdbc_desc='" + userPass + "'";
	// System.out.println(sql);
	// try(
	// Connection conn = DriverManager.getConnection(url , user ,pass);
	// Statement stmt = conn.createStatement();
	// ResultSet rs = stmt.executeQuery(sql))
	// {
	// // �����ѯ��ResultSet���г���һ���ļ�¼�����¼�ɹ�
	// if (rs.next())
	// {
	// return true;
	// }
	// }
	// catch(Exception e)
	// {
	// e.printStackTrace();
	// }
	// return false;
	// }

	private boolean validate(String userName, String userPass) {
		try (Connection conn = DriverManager.getConnection(url, user, pass);
				PreparedStatement pstmt = conn
						.prepareStatement("select * from student_table where student_name=? and java_teacher=?")) {
			pstmt.setString(1, userName);
			pstmt.setString(2, userPass);
			try (ResultSet rs = pstmt.executeQuery()) {
				// �����ѯ��ResultSet���г���һ���ļ�¼�����¼�ɹ�
				if (rs.next()) {
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static void main(String[] args) throws Exception {
		new LoginFrame().init();
	}
}
