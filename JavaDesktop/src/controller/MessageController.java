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
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import dao.SelectDao;
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

	private SelectDao selectDao = new SelectDao();

	// ������Ա����box�б�
	private List<JCheckBox> salerCheckBox = new ArrayList<JCheckBox>();

	// ������Ա�����б�
	private List<String> infos = new ArrayList<String>();

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
			// �����Ի���
			// JOptionPane.showConfirmDialog(null, "choose one", "choose one",
			// JOptionPane.YES_NO_CANCEL_OPTION);

			JFrame salerJF = new JFrame("ѡ����Ա��");

			JPanel contentPane = new JPanel(); // �����������

			ArrayList<String> salerName = new ArrayList<>();

			salerName.addAll(selectDao.salerNameSelect(url, user, pass));

			for (String str : salerName) {
				JCheckBox jc = new JCheckBox(str);
				contentPane.add(jc);
				salerCheckBox.add(jc);
			}

			JButton sendButton = new JButton("����");

			sendButton.addActionListener(new ButtonSendMessage());

			salerJF.add(contentPane, BorderLayout.CENTER);
			FrameUtil.initFrame(salerJF, 500, 800);
			// jf.pack();

			// �رյ�ǰ����
			salerJF.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			salerJF.setVisible(true);

		}

	}

	public class ButtonSendMessage implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			for (JCheckBox checkBox : salerCheckBox) {
				if (checkBox.isSelected()) {
					infos.add(checkBox.getText());
				}
			}
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
