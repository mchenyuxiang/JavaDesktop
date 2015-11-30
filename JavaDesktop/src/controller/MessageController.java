package controller;

import java.awt.BorderLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import util.FrameUtil;

public class MessageController {
	private final String PROP_FILE = "config/mysql.ini";
	private String driver;
	// url�����ݿ�ķ����ַ
	private String url;
	private String user;
	private String pass;

	// ��ʼ�����
	private JFrame jf = new JFrame("��Ϣ����");

	// �鿴�Ѿ�������Ϣ��ť
	private JButton alreadySend = new JButton("�鿴�Ѿ�������Ϣ");

	// ����·������Ϣ
	private JButton lineSend = new JButton("��     ·     ��     ��  ");

	// ������������
	private JButton otherSend = new JButton("��     ��     ��     ��  ");

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

		initJPanel.add(alreadySend);
		initJPanel.add(lineSend);
		initJPanel.add(otherSend);

		jf.add(initJPanel, BorderLayout.NORTH);
		FrameUtil.initFrame(jf, 500, 800);
		// jf.pack();

		// �رյ�ǰ����
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);

		alreadySend.addActionListener(new AlreadySendMessage());
		lineSend.addActionListener(new LineSendMessage());
		otherSend.addActionListener(new OtherSendMessage());
	}

	// ��ʾ������Ϣ
	public class AlreadySendMessage implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// ɾ��ԭ����JTable(JTableʹ��scrollPane����װ)
			if (scrollPane != null) {
				jf.remove(scrollPane);
			}
			try {
				Connection conn = DriverManager.getConnection(url, user, pass);
				Statement stmt = conn.createStatement();
				// �����û������SQLִ�в�ѯ
				ResultSet rs = stmt
						.executeQuery("select messageId,messageStart,messageEnd,messageStatus,messageInfo from tb_message");
				// ȡ��ResultSet��MetaData
				ResultSetMetaData rsmd = rs.getMetaData();
				Vector<String> columnNames = new Vector<>();
				Vector<Vector<String>> data = new Vector<>();
				// ��ResultSet������������ӵ�Vector��
				// for (int i = 0; i < rsmd.getColumnCount(); i++) {
				// columnNames.add(rsmd.getColumnName(i + 1));
				// }
				columnNames.add("��ϢID");
				columnNames.add("��Ϣ������");
				columnNames.add("��Ϣ������");
				columnNames.add("��Ϣ�Ѿ�������״̬");
				columnNames.add("��Ϣ��Ϣ");

				// ��ResultSet�����м�¼��ӵ�Vector��
				while (rs.next()) {
					Vector<String> v = new Vector<>();
					for (int i = 0; i < rsmd.getColumnCount(); i++) {
						v.add(rs.getString(i + 1));
					}
					data.add(v);
				}
				// �����µ�JTable
				JTable table = new JTable(data, columnNames);
				scrollPane = new JScrollPane(table);
				// ����µ�Table
				jf.add(scrollPane);
				// ����������
				jf.validate();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

	}

	public class LineSendMessage implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

		}

	}

	public class OtherSendMessage implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

		}

	}

	public static void main(String[] args) {
		try {
			new MessageController().init();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
