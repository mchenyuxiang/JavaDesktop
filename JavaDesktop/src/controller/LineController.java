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
	// url�����ݿ�ķ����ַ
	private String url;
	private String user;
	private String pass;

	//��������������
	private JComboBox<String> salerNameComboBox = new JComboBox<String>();
	//��Ӧ������������
	private JComboBox<String> supplierNameComboBox = new JComboBox<String>();
	//��������������
	private JComboBox<String> shopNameComboBox = new JComboBox<String>();
	//Ŀ�ĵ�����������
	private JComboBox<String> destinationNameComboBox = new JComboBox<String>();
	//��������������
	private JComboBox<String> departNameComboBox = new JComboBox<String>();
	
	
	//�������ƴ������
	private ArrayList<String> salerNameArrayList = new ArrayList<>();
	//��Ӧ�����ƴ������
	private ArrayList<String> supplierNameArrayList = new ArrayList<>();
	//�������ƴ������
	private ArrayList<String> shopNameArrayList = new ArrayList<>();
	//Ŀ�ĵ����ƴ������
	private ArrayList<String> destinationNameArrayList = new ArrayList<>();
	//�������д������
	private ArrayList<String> departNameArrayList = new ArrayList<>();

	
	//��ѯdao����
	private SelectDao selectDao = new SelectDao();

	public void init() throws Exception {
		// ���ݿ�
		Properties connProp = new Properties();
		connProp.load(new FileInputStream(PROP_FILE));
		driver = connProp.getProperty("driver");
		url = connProp.getProperty("url");
		user = connProp.getProperty("user");
		pass = connProp.getProperty("pass");
		// ��������
		Class.forName(driver);

		JFrame jf = new JFrame("��·���");

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
