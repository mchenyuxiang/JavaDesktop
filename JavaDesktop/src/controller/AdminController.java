package controller;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.util.Properties;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import dao.AdminDao;
import util.FrameUtil;

public class AdminController {
	private final String PROP_FILE = "config/mysql.ini";
	private String driver;
	// url是数据库的服务地址
	private String url;
	private String user;
	private String pass;

	private JFrame jf = new JFrame("管理员界面");

	private JPanel jPanelLeft = new JPanel();
	private JPanel jPanelRight = new JPanel();

	private JButton saleButton = new JButton("添加销售");
	private JButton supplierButton = new JButton("添加经销商");
	private JButton shopButton = new JButton("添加店铺");
	private JButton departButton = new JButton("添加出发城市");
	private JButton destinationButton = new JButton("添加目的地");

	// 垂直摆放
	private Box verticalLeft = Box.createVerticalBox();
	private Box saleVerticalRight = Box.createVerticalBox();
	private Box supplierVerticalRight = Box.createVerticalBox();
	private Box shopVerticalRight = Box.createVerticalBox();
	private Box departVerticalRight = Box.createVerticalBox();
	private Box destinationVerticalRight = Box.createVerticalBox();
	int adminId;

	// 管理员添加数据dao方法
	private AdminDao adminDao = new AdminDao();

	public AdminController() {

	}

	public AdminController(int adminId) {
		this.adminId = adminId;
	}

	public void init() throws Exception {
		Properties connProp = new Properties();
		connProp.load(new FileInputStream(PROP_FILE));
		driver = connProp.getProperty("driver");
		url = connProp.getProperty("url");
		user = connProp.getProperty("user");
		pass = connProp.getProperty("pass");
		// 加载驱动
		Class.forName(driver);

		verticalLeft.add(saleButton);
		verticalLeft.add(supplierButton);
		verticalLeft.add(shopButton);
		verticalLeft.add(departButton);
		verticalLeft.add(destinationButton);

		JPanel jPanel1 = new JPanel();
		jPanel1.add(new JLabel("用户名："));
		final JTextField salerName = new JTextField(20);
		jPanel1.add(salerName);

		JPanel jPanel2 = new JPanel();
		jPanel2.add(new JLabel("电话："));
		final JTextField salerPhone = new JTextField(20);
		jPanel2.add(salerPhone);

		JPanel jPanel3 = new JPanel();
		jPanel3.add(new JLabel("座机："));
		final JTextField salerTel = new JTextField(20);
		jPanel3.add(salerTel);

		JPanel jPanel4 = new JPanel();
		jPanel4.add(new JLabel("QQ："));
		final JTextField salerQQ = new JTextField(20);
		jPanel4.add(salerQQ);

		JPanel jPanel5 = new JPanel();
		jPanel5.add(new JLabel("微信："));
		final JTextField salerWeiXin = new JTextField(20);
		jPanel5.add(salerWeiXin);

		JPanel jPanel7 = new JPanel();
		jPanel7.add(new JLabel("密码："));
		final JTextField salerPassword = new JTextField(20);
		jPanel7.add(salerPassword);

		JPanel jPanel6 = new JPanel();
		JButton jButton = new JButton("确认");
		jPanel6.add(jButton);

		saleVerticalRight.add(jPanel1);
		saleVerticalRight.add(jPanel2);
		saleVerticalRight.add(jPanel3);
		saleVerticalRight.add(jPanel4);
		saleVerticalRight.add(jPanel5);
		saleVerticalRight.add(jPanel7);
		saleVerticalRight.add(jPanel6);
		jf.add(saleVerticalRight, BorderLayout.CENTER);

		jButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (adminDao.insertSale(salerName.getText(),
						salerPhone.getText(), salerTel.getText(),
						salerQQ.getText(), salerWeiXin.getText(),
						salerPassword.getText(), url, user, pass)) {
					JOptionPane.showMessageDialog(jf, "添加销售成功");
				} else {
					JOptionPane.showMessageDialog(jf, "添加销售失败");
				}
			}
		});

		jf.add(verticalLeft, BorderLayout.WEST);
		jf.add(saleVerticalRight, BorderLayout.CENTER);

		FrameUtil.initFrame(jf, 500, 400);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);

		saleButton.addActionListener(new SaleInfo());
		supplierButton.addActionListener(new SupplierInfo());
		shopButton.addActionListener(new ShopInfo());
		departButton.addActionListener(new DepartInfo());
		destinationButton.addActionListener(new DesinationInfo());

	}

	// 销售添加资料界面
	public class SaleInfo implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			saleVerticalRight.setVisible(true);
			supplierVerticalRight.setVisible(false);
			supplierVerticalRight.removeAll();
			shopVerticalRight.setVisible(false);
			shopVerticalRight.removeAll();
			departVerticalRight.setVisible(false);
			departVerticalRight.removeAll();
			destinationVerticalRight.setVisible(false);
			destinationVerticalRight.removeAll();

			JPanel jPanel1 = new JPanel();
			jPanel1.add(new JLabel("用户名："));
			final JTextField salerName = new JTextField(20);
			jPanel1.add(salerName);

			JPanel jPanel2 = new JPanel();
			jPanel2.add(new JLabel("电话："));
			final JTextField salerPhone = new JTextField(20);
			jPanel2.add(salerPhone);

			JPanel jPanel3 = new JPanel();
			jPanel3.add(new JLabel("座机："));
			final JTextField salerTel = new JTextField(20);
			jPanel3.add(salerTel);

			JPanel jPanel4 = new JPanel();
			jPanel4.add(new JLabel("QQ："));
			final JTextField salerQQ = new JTextField(20);
			jPanel4.add(salerQQ);

			JPanel jPanel5 = new JPanel();
			jPanel5.add(new JLabel("微信："));
			final JTextField salerWeiXin = new JTextField(20);
			jPanel5.add(salerWeiXin);

			JPanel jPanel7 = new JPanel();
			jPanel7.add(new JLabel("密码："));
			final JTextField salerPassword = new JTextField(20);
			jPanel7.add(salerPassword);

			JPanel jPanel6 = new JPanel();
			JButton jButton = new JButton("确认");
			jPanel6.add(jButton);

			saleVerticalRight.add(jPanel1);
			saleVerticalRight.add(jPanel2);
			saleVerticalRight.add(jPanel3);
			saleVerticalRight.add(jPanel4);
			saleVerticalRight.add(jPanel5);
			saleVerticalRight.add(jPanel7);
			saleVerticalRight.add(jPanel6);
			jf.add(saleVerticalRight, BorderLayout.CENTER);

			jButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if (adminDao.insertSale(salerName.getText(),
							salerPhone.getText(), salerTel.getText(),
							salerQQ.getText(), salerWeiXin.getText(),
							salerPassword.getText(), url, user, pass)) {
						JOptionPane.showMessageDialog(jf, "添加销售成功");
					} else {
						JOptionPane.showMessageDialog(jf, "添加销售失败");
					}
				}
			});

		}
	}

	// 经销商添加资料界面
	public class SupplierInfo implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			supplierVerticalRight.setVisible(true);
			saleVerticalRight.setVisible(false);
			saleVerticalRight.removeAll();
			shopVerticalRight.setVisible(false);
			shopVerticalRight.removeAll();
			departVerticalRight.setVisible(false);
			departVerticalRight.removeAll();
			destinationVerticalRight.setVisible(false);
			destinationVerticalRight.removeAll();

			JPanel jPanel1 = new JPanel();
			jPanel1.add(new JLabel("名称："));
			final JTextField supplierName = new JTextField(20);
			jPanel1.add(supplierName);

			JPanel jPanel2 = new JPanel();
			jPanel2.add(new JLabel("地址："));
			final JTextField supplierAddress = new JTextField(20);
			jPanel2.add(supplierAddress);

			JPanel jPanel3 = new JPanel();
			jPanel3.add(new JLabel("联系人："));
			final JTextField supplierContact = new JTextField(20);
			jPanel3.add(supplierContact);

			JPanel jPanel4 = new JPanel();
			jPanel4.add(new JLabel("手机："));
			final JTextField supplierPhone = new JTextField(20);
			jPanel4.add(supplierPhone);

			JPanel jPanel5 = new JPanel();
			jPanel5.add(new JLabel("座机："));
			final JTextField supplierTel = new JTextField(20);
			jPanel5.add(supplierTel);

			JPanel jPanel7 = new JPanel();
			jPanel7.add(new JLabel("QQ："));
			final JTextField supplierQQ = new JTextField(20);
			jPanel7.add(supplierQQ);

			JPanel jPanel8 = new JPanel();
			jPanel8.add(new JLabel("微信："));
			final JTextField supplierWinXin = new JTextField(20);
			jPanel8.add(supplierWinXin);

			JPanel jPanel9 = new JPanel();
			jPanel9.add(new JLabel("密码："));
			final JTextField supplierPassword = new JTextField(20);
			jPanel9.add(supplierPassword);

			JPanel jPanel6 = new JPanel();
			JButton jButton = new JButton("确认");
			jPanel6.add(jButton);

			supplierVerticalRight.add(jPanel1);
			supplierVerticalRight.add(jPanel2);
			supplierVerticalRight.add(jPanel3);
			supplierVerticalRight.add(jPanel4);
			supplierVerticalRight.add(jPanel5);
			supplierVerticalRight.add(jPanel7);
			supplierVerticalRight.add(jPanel8);
			supplierVerticalRight.add(jPanel9);
			supplierVerticalRight.add(jPanel6);
			jf.add(supplierVerticalRight, BorderLayout.CENTER);

			jButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if (adminDao.insertSupplier(supplierName.getText(),
							supplierAddress.getText(),
							supplierContact.getText(), supplierPhone.getText(),
							supplierTel.getText(), supplierQQ.getText(),
							supplierWinXin.getText(),
							supplierPassword.getText(), url, user, pass)) {
						JOptionPane.showMessageDialog(jf, "添加经销商成功");
					} else {
						JOptionPane.showMessageDialog(jf, "添加经销商失败");
					}
				}
			});

		}

	}

	// 店铺添加资料界面
	public class ShopInfo implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			shopVerticalRight.setVisible(true);
			saleVerticalRight.setVisible(false);
			saleVerticalRight.removeAll();
			supplierVerticalRight.setVisible(false);
			supplierVerticalRight.removeAll();
			departVerticalRight.setVisible(false);
			departVerticalRight.removeAll();
			destinationVerticalRight.setVisible(false);
			destinationVerticalRight.removeAll();

			JPanel jPanel1 = new JPanel();
			jPanel1.add(new JLabel("店铺名称："));
			final JTextField shopName = new JTextField(20);
			jPanel1.add(shopName);

			JPanel jPanel2 = new JPanel();
			jPanel2.add(new JLabel("店铺信息："));
			final JTextArea shopInfo = new JTextArea(10, 20);
			jPanel2.add(shopInfo);

			JPanel jPanel6 = new JPanel();
			JButton jButton = new JButton("确认");
			jPanel6.add(jButton);

			shopVerticalRight.add(jPanel1);
			shopVerticalRight.add(jPanel2);
			shopVerticalRight.add(jPanel6);

			jf.add(shopVerticalRight, BorderLayout.CENTER);

			jButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if (adminDao.insertShop(shopName.getText(),
							shopInfo.getText(), url, user, pass)) {
						JOptionPane.showMessageDialog(jf, "添加门店成功");
					} else {
						JOptionPane.showMessageDialog(jf, "添加门店失败");
					}
				}
			});
		}

	}

	// 出发地添加资料界面
	public class DepartInfo implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			departVerticalRight.setVisible(true);
			saleVerticalRight.setVisible(false);
			saleVerticalRight.removeAll();
			supplierVerticalRight.setVisible(false);
			supplierVerticalRight.removeAll();
			shopVerticalRight.setVisible(false);
			shopVerticalRight.removeAll();
			destinationVerticalRight.setVisible(false);
			destinationVerticalRight.removeAll();

			JPanel jPanel1 = new JPanel();
			jPanel1.add(new JLabel("城市名称："));
			final JTextField departName = new JTextField(20);
			jPanel1.add(departName);

			JPanel jPanel2 = new JPanel();
			jPanel2.add(new JLabel("城市信息："));
			final JTextArea departInfo = new JTextArea(10, 20);
			jPanel2.add(departInfo);

			JPanel jPanel6 = new JPanel();
			JButton jButton = new JButton("确认");
			jPanel6.add(jButton);

			departVerticalRight.add(jPanel1);
			departVerticalRight.add(jPanel2);
			departVerticalRight.add(jPanel6);
			jf.add(departVerticalRight, BorderLayout.CENTER);

			jButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if (adminDao.insertDepart(departName.getText(),
							departInfo.getText(), url, user, pass)) {
						JOptionPane.showMessageDialog(jf, "添加出发地成功");
					} else {
						JOptionPane.showMessageDialog(jf, "添加出发地失败");
					}
				}
			});

		}

	}

	// 目的地添加资料界面
	public class DesinationInfo implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			destinationVerticalRight.setVisible(true);
			saleVerticalRight.setVisible(false);
			saleVerticalRight.removeAll();
			supplierVerticalRight.setVisible(false);
			supplierVerticalRight.removeAll();
			shopVerticalRight.setVisible(false);
			shopVerticalRight.removeAll();
			departVerticalRight.setVisible(false);
			departVerticalRight.removeAll();

			JPanel jPanel1 = new JPanel();
			jPanel1.add(new JLabel("目的地："));
			final JTextField destinationName = new JTextField(20);
			jPanel1.add(destinationName);

			JPanel jPanel2 = new JPanel();
			jPanel2.add(new JLabel("目的地信息："));
			final JTextArea destinationInfo = new JTextArea(10, 20);
			jPanel2.add(destinationInfo);

			JPanel jPanel6 = new JPanel();
			JButton jButton = new JButton("确认");
			jPanel6.add(jButton);

			destinationVerticalRight.add(jPanel1);
			destinationVerticalRight.add(jPanel2);
			destinationVerticalRight.add(jPanel6);
			jf.add(destinationVerticalRight, BorderLayout.CENTER);

			jButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if (adminDao.insertDestination(destinationName.getText(),
							destinationInfo.getText(), url, user, pass)) {
						JOptionPane.showMessageDialog(jf, "添加目的地成功");
					} else {
						JOptionPane.showMessageDialog(jf, "添加目的地失败");
					}
				}
			});

		}

	}

	// public static void main(String[] args) {
	// try {
	// new AdminController().init();
	// } catch (Exception e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
}
