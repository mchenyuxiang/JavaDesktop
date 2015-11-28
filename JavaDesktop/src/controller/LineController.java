package controller;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import util.FrameUtil;
import dao.SelectDao;

public class LineController {
	private final String PROP_FILE = "config/mysql.ini";
	private String driver;
	// url是数据库的服务地址
	private String url;
	private String user;
	private String pass;

	private JFrame jf = new JFrame("线路添加");
	
	// 销售名称下拉框
	private JComboBox<String> salerNameComboBox = new JComboBox<String>();
	// 供应商名称下拉框
	private JComboBox<String> supplierNameComboBox = new JComboBox<String>();
	// 店铺名称下拉框
	private JComboBox<String> shopNameComboBox = new JComboBox<String>();
	// 目的地名称下拉框
	private JComboBox<String> destinationNameComboBox = new JComboBox<String>();
	// 出发城市下拉框
	private JComboBox<String> departNameComboBox = new JComboBox<String>();
	// 是否转机下拉框
	private JComboBox<String> flyComboBox = new JComboBox<String>();

	// 销售名称存放数组
	private ArrayList<String> salerNameArrayList = new ArrayList<>();
	// 供应商名称存放数组
	private ArrayList<String> supplierNameArrayList = new ArrayList<>();
	// 店铺名称存放数组
	private ArrayList<String> shopNameArrayList = new ArrayList<>();
	// 目的地名称存放数组
	private ArrayList<String> destinationNameArrayList = new ArrayList<>();
	// 出发城市存放数组
	private ArrayList<String> departNameArrayList = new ArrayList<>();

	// 查询dao对象
	private SelectDao selectDao = new SelectDao();

	// 垂直摆放
	private Box vertical = Box.createVerticalBox();

	public void init() throws Exception {
		// 数据库
		Properties connProp = new Properties();
		connProp.load(new FileInputStream(PROP_FILE));
		driver = connProp.getProperty("driver");
		url = connProp.getProperty("url");
		user = connProp.getProperty("user");
		pass = connProp.getProperty("pass");
		// 加载驱动
		Class.forName(driver);

		

		// 销售名称下拉框查询
		salerNameArrayList.addAll(selectDao.salerNameSelect(url, user, pass));
		for (String st : salerNameArrayList) {
			salerNameComboBox.addItem(st);
		}
		// 供应商名称下拉框查询
		supplierNameArrayList.addAll(selectDao.supplierNameSelect(url, user,
				pass));
		for (String st : supplierNameArrayList) {
			supplierNameComboBox.addItem(st);
		}
		// 店铺名称下拉框查询
		shopNameArrayList.addAll(selectDao.shopNameSelect(url, user, pass));
		for (String st : shopNameArrayList) {
			shopNameComboBox.addItem(st);
		}
		// 目的地名称下拉框查询
		destinationNameArrayList.addAll(selectDao.destinationNameselect(url,
				user, pass));
		for (String st : destinationNameArrayList) {
			destinationNameComboBox.addItem(st);
		}
		// 出发城市名称下拉框查询
		departNameArrayList.addAll(selectDao.departNameSelect(url, user, pass));
		for (String st : departNameArrayList) {
			departNameComboBox.addItem(st);
		}

		// 线路名称
		JPanel lineNamePane = new JPanel();
		lineNamePane.setBorder(new EmptyBorder(5, 5, 5, 5));
		lineNamePane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JLabel lineNameLabel = new JLabel("线路名称:");
		final JTextField lineNameTextField = new JTextField(20);
		lineNamePane.add(lineNameLabel);
		lineNamePane.add(lineNameTextField);

		// 销售名称
		JPanel salerNamePane = new JPanel();
		salerNamePane.setBorder(new EmptyBorder(5, 5, 5, 5));
		salerNamePane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JLabel salerNameLabel = new JLabel("销售名称:");
		salerNamePane.add(salerNameLabel);
		salerNamePane.add(salerNameComboBox);

		// 供应商名称
		JPanel supplierNamePane = new JPanel();
		supplierNamePane.setBorder(new EmptyBorder(5, 5, 5, 5));
		supplierNamePane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JLabel supplierNameLabel = new JLabel("供应商名称:");
		supplierNamePane.add(supplierNameLabel);
		supplierNamePane.add(supplierNameComboBox);

		// 店铺名称
		JPanel shopNamePane = new JPanel();
		shopNamePane.setBorder(new EmptyBorder(5, 5, 5, 5));
		shopNamePane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JLabel shopNameLabel = new JLabel("供应商名称:");
		shopNamePane.add(shopNameLabel);
		shopNamePane.add(shopNameComboBox);

		// 出发地名称
		JPanel departNamePane = new JPanel();
		departNamePane.setBorder(new EmptyBorder(5, 5, 5, 5));
		departNamePane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JLabel departNameLabel = new JLabel("出发地名称:");
		departNamePane.add(departNameLabel);
		departNamePane.add(departNameComboBox);

		// 目的地名称
		JPanel destinationNamePane = new JPanel();
		destinationNamePane.setBorder(new EmptyBorder(5, 5, 5, 5));
		destinationNamePane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JLabel destinationNameLabel = new JLabel("目的地名称:");
		destinationNamePane.add(destinationNameLabel);
		destinationNamePane.add(destinationNameComboBox);

		JPanel confPanel = new JPanel();
		JButton confButton = new JButton("确认添加");
		confPanel.add(confButton);

		JPanel jPanel2 = new JPanel();
		jPanel2.add(new JLabel("自费信息："));
		final JTextArea shopInfo = new JTextArea(10, 20);
		jPanel2.add(shopInfo);

		// 目的地名称
		JPanel flyPane = new JPanel();
		flyPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		flyPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JLabel flyLabel = new JLabel("是否转机:");
		flyComboBox.addItem("是");
		flyComboBox.addItem("否");
		flyPane.add(flyLabel);
		flyPane.add(flyComboBox);

		vertical.add(lineNamePane);
		vertical.add(salerNamePane);
		vertical.add(supplierNamePane);
		vertical.add(shopNamePane);
		vertical.add(departNamePane);
		vertical.add(destinationNamePane);
		vertical.add(jPanel2);
		vertical.add(flyPane);
		vertical.add(confPanel);

		jf.add(vertical);
		FrameUtil.initFrame(jf, 500, 800);
		
		//关闭当前窗口
		jf.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		jf.setVisible(true);

		confButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (selectDao.lineAdd(lineNameTextField.getText(),
						(String) salerNameComboBox.getSelectedItem(),
						(String) supplierNameComboBox.getSelectedItem(),
						(String) shopNameComboBox.getSelectedItem(),
						(String) destinationNameComboBox.getSelectedItem(),
						(String) departNameComboBox.getSelectedItem(),
						shopInfo.getText(), (String) flyComboBox.getSelectedItem(), url,
						user, pass)) {
					JOptionPane.showMessageDialog(jf, "添加线路成功");
				} else {
					JOptionPane.showMessageDialog(jf, "添加线路失败");
				}
			}
		});
	}

//	public static void main(String[] args) {
//		try {
//			new LineController().init();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
}
