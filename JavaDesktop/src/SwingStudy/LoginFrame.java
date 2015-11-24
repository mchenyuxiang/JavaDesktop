package SwingStudy;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import util.FrameUtil;

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
	// ����һ����ѡ��ť����ʼ����ѡ��״̬
	private JRadioButton admin = new JRadioButton("����Ա", true);
	private JRadioButton sale = new JRadioButton("����", false);
	private JRadioButton supplier = new JRadioButton("������", false);
	private String saveValue = null;

	private JFrame jf1 = new JFrame("��¼");

	private ButtonGroup buttonGroup = new ButtonGroup();

	private RadioButtonListener radioButtonListener;

	private IsLoginIn isLoginIn = new IsLoginIn();

	private JPanel jPanel = new JPanel();

	public void init() throws Exception {
		Properties connProp = new Properties();
		connProp.load(new FileInputStream(PROP_FILE));
		driver = connProp.getProperty("driver");
		url = connProp.getProperty("url");
		user = connProp.getProperty("user");
		pass = connProp.getProperty("pass");
		// ��������
		Class.forName(driver);

		// ����ɫ������ť
		buttonGroup.add(admin);
		buttonGroup.add(sale);
		buttonGroup.add(supplier);
		jPanel.add(admin);
		jPanel.add(sale);
		jPanel.add(supplier);
		Box vertical = Box.createVerticalBox();
		vertical.add(userField);
		vertical.add(passField);
		vertical.add(jPanel);
		vertical.add(loginButton);

		jf.add(vertical);
		jf.pack();
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
		// FrameUtil.initFrame(jf, 300, 150);

		// Ϊ��¼��ť����¼�������
		radioButtonListener = new RadioButtonListener();
		admin.addActionListener(radioButtonListener);
		sale.addActionListener(radioButtonListener);
		supplier.addActionListener(radioButtonListener);
		System.out.println(radioButtonListener.saveValueTemp);
		saveValue = radioButtonListener.saveValueTemp;
		loginButton.addActionListener(isLoginIn);
	}

	// �ж�ѡ���ĸ���ɫ��¼
	public class RadioButtonListener implements ActionListener {
		String saveValueTemp = null;

		@Override
		public void actionPerformed(ActionEvent arg0) {
			JRadioButton temp = (JRadioButton) arg0.getSource();
			if (temp.isSelected()) {
				saveValueTemp = temp.getText();
				// System.out.println(temp.getText());
//				System.out.println(saveValueTemp);
			}

		}

	}

	// �ж��Ƿ���������¼
	public class IsLoginIn implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			System.out.println(saveValue);
			int flag = 0;
			if (saveValue.equals("����Ա")) {
				flag = 1;
			} else if (saveValue.equals("����")) {
				flag = 2;
			} else {
				flag = 3;
			}
			// ��¼�ɹ�����ʾ����¼�ɹ���
			if (validate(userField.getText(), passField.getText(), flag)) {
				JOptionPane.showMessageDialog(jf, saveValue + "��¼�ɹ�");
				// �رմ���
				jf.dispose();
				jf1.pack();
				jf1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				jf1.setVisible(true);
			}
			// ������ʾ����¼ʧ�ܡ�
			else {
				JOptionPane.showMessageDialog(jf, "��¼ʧ��");
			}

		}

	}

	private boolean validate(String userName, String userPass, int flag) {
		try (Connection conn = DriverManager.getConnection(url, user, pass);
				PreparedStatement pstmt = conn
						.prepareStatement("select * from tb_user where userLogin=? and userPwd=? and userRoleId=?")) {
			pstmt.setString(1, userName);
			pstmt.setString(2, userPass);
			pstmt.setInt(3, flag);
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
