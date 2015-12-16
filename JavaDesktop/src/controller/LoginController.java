package controller;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import dao.LoginDao;
import ActionListener.RadioButtonListener;
import util.FrameUtil;

import java.util.*;
import java.io.*;
import java.sql.*;

public class LoginController {
	private final String PROP_FILE = "config/mysql.ini";
	private String driver;
	// url是数据库的服务地址
	private String url;
	private String user;
	private String pass;
	// 登录界面的GUI组件
	private JFrame jf = new JFrame("登录");
	private JTextField userField = new JTextField(20);
	private JPasswordField passField = new JPasswordField(20);
	private JButton loginButton = new JButton("登录");
	// 定义一个单选按钮，初始处于选中状态
	private JRadioButton admin = new JRadioButton("管理员");
	private JRadioButton sale = new JRadioButton("销售");
	private JRadioButton supplier = new JRadioButton("经销商");
	private String saveValue = null;

	private JFrame jf1 = new JFrame("登录");

	private ButtonGroup buttonGroup = new ButtonGroup();

	private RadioButtonListener radioButtonListener;

	private IsLoginIn isLoginIn = new IsLoginIn();

	private JPanel jPanel = new JPanel();
	
	private LoginDao login = new LoginDao();

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
//		buttonGroup.add(supplier);
		jPanel.add(admin);
		jPanel.add(sale);
//		jPanel.add(supplier);
		Box vertical = Box.createVerticalBox();
		vertical.add(userField);
		vertical.add(passField);
		vertical.add(jPanel);
		vertical.add(loginButton);

		jf.add(vertical);
//		jf.pack();
		 FrameUtil.initFrame(jf, 300, 200);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
		

		// 为登录按钮添加事件监听器
		radioButtonListener = new RadioButtonListener();
		admin.addActionListener(radioButtonListener);
		sale.addActionListener(radioButtonListener);
		supplier.addActionListener(radioButtonListener);
		loginButton.addActionListener(isLoginIn);
	}


	// 判断是否能正常登录
	public class IsLoginIn implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			saveValue = radioButtonListener.getSaveValueTemp();
			if (saveValue.equals("管理员")) {
				// 登录成功则显示“登录成功”
				int adminId = -1;
				String userName = "";
				adminId = login.adminValidate(userField.getText(), passField.getText(),url,user,pass);
				if (adminId != -1) {
					
					JOptionPane.showMessageDialog(jf, saveValue + "登录成功");
					userName = login.getAdminName(adminId, 0, url, user, pass);
//					System.out.println(userName);
					try {
						new TimeTaskController(userName).init();
					} catch (ClassNotFoundException e2) {
						e2.printStackTrace();
					} catch (IOException e2) {
						e2.printStackTrace();
					}
					// 关闭窗口
					jf.dispose();
					try {
						new AdminController(userName,0).init();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				// 否则显示“登录失败”
				else {
					JOptionPane.showMessageDialog(jf, "登录失败");
				}
			} else if (saveValue.equals("销售")) {
				// 登录成功则显示“登录成功”
				int salerId = -1;
				String userName = "";
				salerId = login.salerValidate(userField.getText(), passField.getText(),url,user,pass);
				if (salerId != -1) {
					JOptionPane.showMessageDialog(jf, saveValue + "登录成功");
					userName = login.getAdminName(salerId, 1, url, user, pass);
					System.out.println(userName);
					try {
						new TimeTaskController(userName).init();
					} catch (ClassNotFoundException e2) {
						e2.printStackTrace();
					} catch (IOException e2) {
						e2.printStackTrace();
					}
					// 关闭窗口
					jf.dispose();
					try {
						new AdminController(userName,1).init();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				// 否则显示“登录失败”
				else {
					JOptionPane.showMessageDialog(jf, "登录失败");
				}
			} else {
				// 登录成功则显示“登录成功”
				if (login.supplierValidate(userField.getText(), passField.getText(),url,user,pass)) {
					JOptionPane.showMessageDialog(jf, saveValue + "登录成功");
					// 关闭窗口
					jf.dispose();
					jf1.pack();
					jf1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					jf1.setVisible(true);
				}
				// 否则显示“登录失败”
				else {
					JOptionPane.showMessageDialog(jf, "登录失败请检查用户名或者联系管理员");
				}
			}
			

		}

	}

	public static void main(String[] args) throws Exception {
		try
	    {
	        BeautyEyeLNFHelper.launchBeautyEyeLNF();
//	        System.out.println(BeautyEyeLNFHelper.isSurportedTranslucency());
	        BeautyEyeLNFHelper.translucencyAtFrameInactive=false;
	        UIManager.put("RootPane.setupButtonVisible", false);
	    }
	    catch(Exception e)
	    {
	        //TODO exception
	    }
		new LoginController().init();
	}
}
