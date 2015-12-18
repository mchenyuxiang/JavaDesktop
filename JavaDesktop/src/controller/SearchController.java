package controller;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Properties;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import util.FrameUtil;

public class SearchController {
	private final String PROP_FILE = "config/mysql.ini";
	private String driver;
	// url是数据库的服务地址
	private String url;
	private String user;
	private String pass;

	// 初始化面板
	private JFrame jf = new JFrame("查询线路");

	// 初始化三个选择功能按钮
	private JPanel initJPanel = new JPanel();

	private JTable jTable = new JTable();

	// 容器装jTable
	private JScrollPane scrollPane;

	public void init() throws Exception {
		Properties connProp = new Properties();
		connProp.load(new FileInputStream(PROP_FILE));
		driver = connProp.getProperty("driver");
		url = connProp.getProperty("url");
		user = connProp.getProperty("user");
		pass = connProp.getProperty("pass");
		// 加载驱动
		Class.forName(driver);
		JPanel initJPanel = new JPanel();
		initJPanel.add(new JLabel("线路名称："));
		final JTextField lineNameField = new JTextField(15);
		initJPanel.add(lineNameField);

		initJPanel.add(new JLabel("销售名称："));
		final JTextField salerNameField = new JTextField(15);
		initJPanel.add(salerNameField);

		initJPanel.add(new JLabel("供应商名称："));
		final JTextField supplierNameField = new JTextField(15);
		initJPanel.add(supplierNameField);

		initJPanel.add(new JLabel("店铺名称："));
		final JTextField shopNameField = new JTextField(15);
		initJPanel.add(shopNameField);

		initJPanel.add(new JLabel("目的地名称："));
		final JTextField destinationNameField = new JTextField(15);
		initJPanel.add(destinationNameField);

		initJPanel.add(new JLabel("出发城市名称："));
		final JTextField departNameField = new JTextField(15);
		initJPanel.add(departNameField);

		JButton searchButton = new JButton("查询");
		initJPanel.add(searchButton);

		initJPanel.setPreferredSize(new Dimension(800, 100));// 关键代码,设置JPanel的大小
		jf.add(initJPanel, BorderLayout.NORTH);
		FrameUtil.initFrame(jf, 800, 700);
		// 关闭当前窗口
		jf.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		jf.setVisible(true);

//		System.out.println(lineNameField.getText());
		searchButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
//				System.out.println(lineNameField.getText() + ";"
//						+ salerNameField.getText() + ";" + supplierNameField.getText()
//						+ ";" + shopNameField.getText() + ";" + destinationNameField.getText()
//						+ ";" + departNameField.getText());

				// 删除原来的JTable(JTable使用scrollPane来包装)
				if (scrollPane != null) {
					jf.remove(scrollPane);
				}
				try {
					Connection conn = DriverManager.getConnection(url, user,
							pass);
					Statement stmt = conn.createStatement();

					String query = "select lineId,lineName,salerName,supplierName,shopName,destinationName"
							+ ",departName,expenseInfo,isThrough"
							+ " from tb_line" + " where 1=1 ";

					if (!lineNameField.getText().equals("")) {
						query += " and lineName like '%" + lineNameField.getText() + "%' ";
					}
					if (!salerNameField.getText().equals("")) {
						query += " and salerName like '%" + salerNameField.getText() + "%' ";
					}
					if (!supplierNameField.getText().equals("")) {
						query += " and supplierName like '%" + supplierNameField.getText()
								+ "%' ";
					}
					if (!shopNameField.getText().equals("")) {
						query += " and shopName like '%" + shopNameField.getText() + "%' ";
					}
					if (!destinationNameField.getText().equals("")) {
						query += " and destinationName like '%"
								+ destinationNameField.getText() + "%' ";
					}
					if (!departNameField.getText().equals("")) {
						query += " and departName like '%" + departNameField.getText() + "%' ";
					}

//					System.out.println(query);

					// 根据用户输入的SQL执行查询
					ResultSet rs = stmt.executeQuery(query);
					// 取出ResultSet的MetaData
					ResultSetMetaData rsmd = rs.getMetaData();
					Vector<String> columnNames = new Vector<>();
					Vector<Vector<String>> data = new Vector<>();
					columnNames.add("线路ID");
					columnNames.add("线路名称");
					columnNames.add("销售名称");
					columnNames.add("供应商名称");
					columnNames.add("店铺名称");
					columnNames.add("目的地");
					columnNames.add("出发地");
					columnNames.add("备注");
					columnNames.add("是否转机");

					// 把ResultSet的所有记录添加到Vector里
					while (rs.next()) {
						Vector<String> v = new Vector<>();
						for (int i = 0; i < rsmd.getColumnCount(); i++) {
							v.add(rs.getString(i + 1));
						}
						data.add(v);
					}
					// 创建新的JTable
					final JTable table = new JTable(data, columnNames);
					scrollPane = new JScrollPane(table);
					// 添加新的Table
					jf.add(scrollPane);
					// 更新主窗口
					jf.validate();

				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});

	}

	public static void main(String[] args) {
		try {
			new SearchController().init();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
