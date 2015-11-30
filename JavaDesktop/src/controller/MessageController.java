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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import dao.MessageDao;
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
	private JFrame salerJF = new JFrame("ѡ����Ա��");

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

	

	// ������Ա����box�б�
	private List<JCheckBox> salerCheckBox = new ArrayList<JCheckBox>();

	// ������Ա�����б�
	private List<String> infos = new ArrayList<String>();

	private Integer messageId = -1;
	private String messageEnd = "";

	private SelectDao selectDao = new SelectDao();
	private MessageDao messageDao = new MessageDao();
	
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
				final JTable table = new JTable(data, columnNames);
				scrollPane = new JScrollPane(table);
				// ����µ�Table
				jf.add(scrollPane);
				// ����������
				jf.validate();
				table.getSelectionModel().addListSelectionListener(
						new ListSelectionListener() {

							@Override
							public void valueChanged(ListSelectionEvent e) {
								// TODO Auto-generated method stub
								if (e.getValueIsAdjusting()) {// ��������
									int rowIndex = table.getSelectedRow();
									if (rowIndex != -1) {
										// System.out.println("����б�ѡ��"+rowIndex);
										// System.out.println(table.getModel().getValueAt(rowIndex,
										// 0));
										messageId = Integer
												.valueOf((String) table
														.getModel().getValueAt(
																rowIndex, 0));
										messageEnd = (String) table
												.getModel().getValueAt(
														rowIndex, 2);
										// System.out.println(messageId);
//										System.out.println(messageEnd);
									}
								}
							}
						});
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

	}

	//������Ϣ
	public class LineSendMessage implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// �����Ի���
			// JOptionPane.showConfirmDialog(null, "choose one", "choose one",
			// JOptionPane.YES_NO_CANCEL_OPTION);

			
			if (messageId.equals(-1)) {
				JOptionPane.showMessageDialog(salerJF, "��ѡ����Ҫ���͵���Ϣ");
			} else {
				
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
				salerJF.add(sendButton,BorderLayout.SOUTH);
				FrameUtil.initFrame(salerJF, 500, 800);
				// jf.pack();

				// �رյ�ǰ����
				salerJF.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				salerJF.setVisible(true);
			}
		}

	}

	//���������Ϣ��ť�¼�
	public class ButtonSendMessage implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			for (JCheckBox checkBox : salerCheckBox) {
				if (checkBox.isSelected()) {
					infos.add(checkBox.getText());
				}
			}
			
			String[] str = messageEnd.split(",");
			ArrayList<String> addMessageEnd = new ArrayList<>();
			for(String stAl:infos){
				int flag = 0;
				for(String ss:str){
					if(stAl.equals(ss)){
						flag = 1;
					}
				}
				if(flag == 0){
					addMessageEnd.add(stAl);
				}
			}
			for(String tt:addMessageEnd){
				messageEnd += "," + tt;
			}
//			System.out.println(messageEnd);
			if(messageDao.insertMessage(messageId, messageEnd, url, user, pass)){
				salerJF.dispose();
				JOptionPane.showMessageDialog(jf, "��Ϣ�ɹ�");
			}else{
				JOptionPane.showMessageDialog(jf, "��Ϣʧ��");
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
