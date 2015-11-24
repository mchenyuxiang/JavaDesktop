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
	// url是数据库的服务地址
	private String url;
	private String user;
	private String pass;
	// 登录界面的GUI组件
	private JFrame jf = new JFrame("登录");
	private JTextField userField = new JTextField(20);
	private JTextField passField = new JTextField(20);
	private JButton loginButton = new JButton("登录");
	// 定义一个单选按钮，初始处于选中状态
	private JRadioButton admin = new JRadioButton("管理员", true);
	private JRadioButton sale = new JRadioButton("销售", false);
	private JRadioButton supplier = new JRadioButton("经销商", false);
	private String saveValue = null;

	private JFrame jf1 = new JFrame("登录");

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
		// 加载驱动
		Class.forName(driver);

		// 将角色单击按钮
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

		// 为登录按钮添加事件监听器
		radioButtonListener = new RadioButtonListener();
		admin.addActionListener(radioButtonListener);
		sale.addActionListener(radioButtonListener);
		supplier.addActionListener(radioButtonListener);
		System.out.println(radioButtonListener.saveValueTemp);
		saveValue = radioButtonListener.saveValueTemp;
		loginButton.addActionListener(isLoginIn);
	}

	// 判断选择哪个角色登录
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

	// 判断是否能正常登录
	public class IsLoginIn implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			System.out.println(saveValue);
			int flag = 0;
			if (saveValue.equals("管理员")) {
				flag = 1;
			} else if (saveValue.equals("销售")) {
				flag = 2;
			} else {
				flag = 3;
			}
			// 登录成功则显示“登录成功”
			if (validate(userField.getText(), passField.getText(), flag)) {
				JOptionPane.showMessageDialog(jf, saveValue + "登录成功");
				// 关闭窗口
				jf.dispose();
				jf1.pack();
				jf1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				jf1.setVisible(true);
			}
			// 否则显示“登录失败”
			else {
				JOptionPane.showMessageDialog(jf, "登录失败");
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
				// 如果查询的ResultSet里有超过一条的记录，则登录成功
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
