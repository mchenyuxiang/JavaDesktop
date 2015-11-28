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
	// url�����ݿ�ķ����ַ
	private String url;
	private String user;
	private String pass;

	private JFrame jf = new JFrame("��·���");
	
	// ��������������
	private JComboBox<String> salerNameComboBox = new JComboBox<String>();
	// ��Ӧ������������
	private JComboBox<String> supplierNameComboBox = new JComboBox<String>();
	// ��������������
	private JComboBox<String> shopNameComboBox = new JComboBox<String>();
	// Ŀ�ĵ�����������
	private JComboBox<String> destinationNameComboBox = new JComboBox<String>();
	// ��������������
	private JComboBox<String> departNameComboBox = new JComboBox<String>();
	// �Ƿ�ת��������
	private JComboBox<String> flyComboBox = new JComboBox<String>();

	// �������ƴ������
	private ArrayList<String> salerNameArrayList = new ArrayList<>();
	// ��Ӧ�����ƴ������
	private ArrayList<String> supplierNameArrayList = new ArrayList<>();
	// �������ƴ������
	private ArrayList<String> shopNameArrayList = new ArrayList<>();
	// Ŀ�ĵ����ƴ������
	private ArrayList<String> destinationNameArrayList = new ArrayList<>();
	// �������д������
	private ArrayList<String> departNameArrayList = new ArrayList<>();

	// ��ѯdao����
	private SelectDao selectDao = new SelectDao();

	// ��ֱ�ڷ�
	private Box vertical = Box.createVerticalBox();

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

		

		// ���������������ѯ
		salerNameArrayList.addAll(selectDao.salerNameSelect(url, user, pass));
		for (String st : salerNameArrayList) {
			salerNameComboBox.addItem(st);
		}
		// ��Ӧ�������������ѯ
		supplierNameArrayList.addAll(selectDao.supplierNameSelect(url, user,
				pass));
		for (String st : supplierNameArrayList) {
			supplierNameComboBox.addItem(st);
		}
		// ���������������ѯ
		shopNameArrayList.addAll(selectDao.shopNameSelect(url, user, pass));
		for (String st : shopNameArrayList) {
			shopNameComboBox.addItem(st);
		}
		// Ŀ�ĵ������������ѯ
		destinationNameArrayList.addAll(selectDao.destinationNameselect(url,
				user, pass));
		for (String st : destinationNameArrayList) {
			destinationNameComboBox.addItem(st);
		}
		// �������������������ѯ
		departNameArrayList.addAll(selectDao.departNameSelect(url, user, pass));
		for (String st : departNameArrayList) {
			departNameComboBox.addItem(st);
		}

		// ��·����
		JPanel lineNamePane = new JPanel();
		lineNamePane.setBorder(new EmptyBorder(5, 5, 5, 5));
		lineNamePane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JLabel lineNameLabel = new JLabel("��·����:");
		final JTextField lineNameTextField = new JTextField(20);
		lineNamePane.add(lineNameLabel);
		lineNamePane.add(lineNameTextField);

		// ��������
		JPanel salerNamePane = new JPanel();
		salerNamePane.setBorder(new EmptyBorder(5, 5, 5, 5));
		salerNamePane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JLabel salerNameLabel = new JLabel("��������:");
		salerNamePane.add(salerNameLabel);
		salerNamePane.add(salerNameComboBox);

		// ��Ӧ������
		JPanel supplierNamePane = new JPanel();
		supplierNamePane.setBorder(new EmptyBorder(5, 5, 5, 5));
		supplierNamePane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JLabel supplierNameLabel = new JLabel("��Ӧ������:");
		supplierNamePane.add(supplierNameLabel);
		supplierNamePane.add(supplierNameComboBox);

		// ��������
		JPanel shopNamePane = new JPanel();
		shopNamePane.setBorder(new EmptyBorder(5, 5, 5, 5));
		shopNamePane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JLabel shopNameLabel = new JLabel("��Ӧ������:");
		shopNamePane.add(shopNameLabel);
		shopNamePane.add(shopNameComboBox);

		// ����������
		JPanel departNamePane = new JPanel();
		departNamePane.setBorder(new EmptyBorder(5, 5, 5, 5));
		departNamePane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JLabel departNameLabel = new JLabel("����������:");
		departNamePane.add(departNameLabel);
		departNamePane.add(departNameComboBox);

		// Ŀ�ĵ�����
		JPanel destinationNamePane = new JPanel();
		destinationNamePane.setBorder(new EmptyBorder(5, 5, 5, 5));
		destinationNamePane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JLabel destinationNameLabel = new JLabel("Ŀ�ĵ�����:");
		destinationNamePane.add(destinationNameLabel);
		destinationNamePane.add(destinationNameComboBox);

		JPanel confPanel = new JPanel();
		JButton confButton = new JButton("ȷ�����");
		confPanel.add(confButton);

		JPanel jPanel2 = new JPanel();
		jPanel2.add(new JLabel("�Է���Ϣ��"));
		final JTextArea shopInfo = new JTextArea(10, 20);
		jPanel2.add(shopInfo);

		// Ŀ�ĵ�����
		JPanel flyPane = new JPanel();
		flyPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		flyPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JLabel flyLabel = new JLabel("�Ƿ�ת��:");
		flyComboBox.addItem("��");
		flyComboBox.addItem("��");
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
		
		//�رյ�ǰ����
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
					JOptionPane.showMessageDialog(jf, "�����·�ɹ�");
				} else {
					JOptionPane.showMessageDialog(jf, "�����·ʧ��");
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
