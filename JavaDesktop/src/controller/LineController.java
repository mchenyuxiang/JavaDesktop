package controller;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.JComboBox;
import javax.swing.JFrame;

import util.FrameUtil;
import dao.SelectDao;

public class LineController {
	private final String PROP_FILE = "config/mysql.ini";
	private String driver;
	// url是数据库的服务地址
	private String url;
	private String user;
	private String pass;

	//销售名称下拉框
	private JComboBox<String> salerNameComboBox = new JComboBox<String>();
	//供应商名称下拉框
	private JComboBox<String> supplierNameComboBox = new JComboBox<String>();
	//店铺名称下拉框
	private JComboBox<String> shopNameComboBox = new JComboBox<String>();
	//目的地名称下拉框
	private JComboBox<String> destinationNameComboBox = new JComboBox<String>();
	//出发城市下拉框
	private JComboBox<String> departNameComboBox = new JComboBox<String>();
	
	
	//销售名称存放数组
	private ArrayList<String> salerNameArrayList = new ArrayList<>();
	//供应商名称存放数组
	private ArrayList<String> supplierNameArrayList = new ArrayList<>();
	//店铺名称存放数组
	private ArrayList<String> shopNameArrayList = new ArrayList<>();
	//目的地名称存放数组
	private ArrayList<String> destinationNameArrayList = new ArrayList<>();
	//出发城市存放数组
	private ArrayList<String> departNameArrayList = new ArrayList<>();

	
	//查询dao对象
	private SelectDao selectDao = new SelectDao();

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

		JFrame jf = new JFrame("线路添加");

		salerNameArrayList.addAll(selectDao.selectSalerName(url, user, pass));
		for (String st : salerNameArrayList){
			salerNameComboBox.addItem(st);
		}

		jf.add(salerNameComboBox);
		FrameUtil.initFrame(jf, 500, 400);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
	}

	public static void main(String[] args) {
		try {
			new LineController().init();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
