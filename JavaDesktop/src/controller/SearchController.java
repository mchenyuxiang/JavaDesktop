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
	// url�����ݿ�ķ����ַ
	private String url;
	private String user;
	private String pass;

	// ��ʼ�����
	private JFrame jf = new JFrame("��ѯ��·");

	// ��ʼ������ѡ���ܰ�ť
	private JPanel initJPanel = new JPanel();

	private JTable jTable = new JTable();

	// ����װjTable
	private JScrollPane scrollPane;

	public void init() throws Exception {
		Properties connProp = new Properties();
		connProp.load(new FileInputStream(PROP_FILE));
		driver = connProp.getProperty("driver");
		url = connProp.getProperty("url");
		user = connProp.getProperty("user");
		pass = connProp.getProperty("pass");
		// ��������
		Class.forName(driver);
		JPanel initJPanel = new JPanel();
		initJPanel.add(new JLabel("��·���ƣ�"));
		final JTextField lineNameField = new JTextField(15);
		initJPanel.add(lineNameField);

		initJPanel.add(new JLabel("�������ƣ�"));
		final JTextField salerNameField = new JTextField(15);
		initJPanel.add(salerNameField);

		initJPanel.add(new JLabel("��Ӧ�����ƣ�"));
		final JTextField supplierNameField = new JTextField(15);
		initJPanel.add(supplierNameField);

		initJPanel.add(new JLabel("�������ƣ�"));
		final JTextField shopNameField = new JTextField(15);
		initJPanel.add(shopNameField);

		initJPanel.add(new JLabel("Ŀ�ĵ����ƣ�"));
		final JTextField destinationNameField = new JTextField(15);
		initJPanel.add(destinationNameField);

		initJPanel.add(new JLabel("�����������ƣ�"));
		final JTextField departNameField = new JTextField(15);
		initJPanel.add(departNameField);

		JButton searchButton = new JButton("��ѯ");
		initJPanel.add(searchButton);

		initJPanel.setPreferredSize(new Dimension(800, 100));// �ؼ�����,����JPanel�Ĵ�С
		jf.add(initJPanel, BorderLayout.NORTH);
		FrameUtil.initFrame(jf, 800, 700);
		// �رյ�ǰ����
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

				// ɾ��ԭ����JTable(JTableʹ��scrollPane����װ)
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

					// �����û������SQLִ�в�ѯ
					ResultSet rs = stmt.executeQuery(query);
					// ȡ��ResultSet��MetaData
					ResultSetMetaData rsmd = rs.getMetaData();
					Vector<String> columnNames = new Vector<>();
					Vector<Vector<String>> data = new Vector<>();
					columnNames.add("��·ID");
					columnNames.add("��·����");
					columnNames.add("��������");
					columnNames.add("��Ӧ������");
					columnNames.add("��������");
					columnNames.add("Ŀ�ĵ�");
					columnNames.add("������");
					columnNames.add("��ע");
					columnNames.add("�Ƿ�ת��");

					// ��ResultSet�����м�¼��ӵ�Vector��
					while (rs.next()) {
						Vector<String> v = new Vector<>();
						for (int i = 0; i < rsmd.getColumnCount(); i++) {
							v.add(rs.getString(i + 1));
						}
						data.add(v);
					}
					// �����µ�JTable
					final JTable table = new JTable(data, columnNames);
					scrollPane = new JScrollPane(table);
					// ����µ�Table
					jf.add(scrollPane);
					// ����������
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
