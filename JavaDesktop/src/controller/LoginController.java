package controller;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import dao.LoginDao;
import ActionListener.RadioButtonListener;
import util.FrameUtil;

import java.util.*;
import java.io.*;
import java.sql.*;

public class LoginController {
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
	private JRadioButton admin = new JRadioButton("����Ա");
	private JRadioButton sale = new JRadioButton("����");
	private JRadioButton supplier = new JRadioButton("������");
	private String saveValue = null;

	private JFrame jf1 = new JFrame("��¼");

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

		// Ϊ��¼��ť�����¼�������
		radioButtonListener = new RadioButtonListener();
		admin.addActionListener(radioButtonListener);
		sale.addActionListener(radioButtonListener);
		supplier.addActionListener(radioButtonListener);
		loginButton.addActionListener(isLoginIn);
	}


	// �ж��Ƿ���������¼
	public class IsLoginIn implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			saveValue = radioButtonListener.getSaveValueTemp();
			if (saveValue.equals("����Ա")) {
				// ��¼�ɹ�����ʾ����¼�ɹ���
				if (login.adminValidate(userField.getText(), passField.getText(),url,user,pass)) {
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
			} else if (saveValue.equals("����")) {
				// ��¼�ɹ�����ʾ����¼�ɹ���
				if (login.salerValidate(userField.getText(), passField.getText(),url,user,pass)) {
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
			} else {
				// ��¼�ɹ�����ʾ����¼�ɹ���
				if (login.supplierValidate(userField.getText(), passField.getText(),url,user,pass)) {
					JOptionPane.showMessageDialog(jf, saveValue + "��¼�ɹ�");
					// �رմ���
					jf.dispose();
					jf1.pack();
					jf1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					jf1.setVisible(true);
				}
				// ������ʾ����¼ʧ�ܡ�
				else {
					JOptionPane.showMessageDialog(jf, "��¼ʧ�������û���������ϵ����Ա");
				}
			}
			

		}

	}

	public static void main(String[] args) throws Exception {
		new LoginController().init();
	}
}