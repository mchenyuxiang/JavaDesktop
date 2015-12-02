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
	// url是数据库的服务地址
	private String url;
	private String user;
	private String pass;

	// 初始化面板
	private JFrame jf = new JFrame("消息发布");
	private JFrame salerJF = new JFrame("选择发送员工");

	// 查看未发布消息线路按钮
	private JButton noLineSend = new JButton("尚未发布公告线路");

	// 查看已经发布消息按钮
	private JButton alreadySend = new JButton("查看已经发布消息");

	// 对线路发布消息
	private JButton lineSend = new JButton("发 送  线   路   公   告  ");

	// 发布其他公告
	private JButton otherSend = new JButton("其     他     公     告  ");

	// 初始化三个选择功能按钮
	private JPanel initJPanel = new JPanel();

	private JTable jTable = new JTable();

	// 容器装jTable
	private JScrollPane scrollPane;

	// 发送人员名单box列表
	private List<JCheckBox> salerCheckBox = new ArrayList<JCheckBox>();

	// 发送人员名单列表
	private List<String> infos = new ArrayList<String>();

	private Integer messageId = -1;
	private String messageEnd = "";
	private Integer lineId = -1;
	private String messageInfo = "";

	private SelectDao selectDao = new SelectDao();
	private MessageDao messageDao = new MessageDao();

	private int userId;
	private String userName;

	public MessageController() {

	}

	public MessageController(String userName) {
		this.userName = userName;
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

		initJPanel.add(noLineSend);
		initJPanel.add(alreadySend);
		initJPanel.add(lineSend);
		initJPanel.add(otherSend);

		jf.add(initJPanel, BorderLayout.NORTH);
		FrameUtil.initFrame(jf, 800, 800);
		// jf.pack();

		// 关闭当前窗口
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);

		noLineSend.addActionListener(new NoLineSend());
		alreadySend.addActionListener(new AlreadySendMessage());
		lineSend.addActionListener(new LineSendMessage());
		otherSend.addActionListener(new OtherSendMessage());
	}

	// 查看还未发布公告线路
	public class NoLineSend implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// 删除原来的JTable(JTable使用scrollPane来包装)
			if (scrollPane != null) {
				jf.remove(scrollPane);
			}
			try {
				Connection conn = DriverManager.getConnection(url, user, pass);
				Statement stmt = conn.createStatement();
				// 根据用户输入的SQL执行查询
				ResultSet rs = stmt
						.executeQuery("select lineId,lineName,salerName,supplierName,shopName,destinationName"
								+ ",departName,expenseInfo,isThrough"
								+ " from tb_line"
								+ " where isMessage = 0 ");
				// 取出ResultSet的MetaData
				ResultSetMetaData rsmd = rs.getMetaData();
				Vector<String> columnNames = new Vector<>();
				Vector<Vector<String>> data = new Vector<>();
				// 把ResultSet的所有列名添加到Vector里
				// for (int i = 0; i < rsmd.getColumnCount(); i++) {
				// columnNames.add(rsmd.getColumnName(i + 1));
				// }
				columnNames.add("线路ID");
				columnNames.add("线路名称");
				columnNames.add("销售名称");
				columnNames.add("供应商名称");
				columnNames.add("店铺名称");
				columnNames.add("目的地");
				columnNames.add("出发地");
				columnNames.add("自费信息");
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
				table.getSelectionModel().addListSelectionListener(
						new ListSelectionListener() {

							@Override
							public void valueChanged(ListSelectionEvent e) {
								// TODO Auto-generated method stub
								if (e.getValueIsAdjusting()) {// 连续操作
									int rowIndex = table.getSelectedRow();
									if (rowIndex != -1) {
										// System.out.println("表格行被选中"+rowIndex);
										// System.out.println(table.getModel().getValueAt(rowIndex,
										// 0));
										lineId = Integer.valueOf((String) table
												.getModel().getValueAt(
														rowIndex, 0));
										messageInfo += (String) table.getModel().getValueAt(rowIndex, 0) + "。线路名称："
												+ (String) table
														.getModel()
														.getValueAt(rowIndex, 1)
												+ "。出发城市： "
												+ (String) table
														.getModel()
														.getValueAt(rowIndex, 6)
												+ "。目的地："
												+ (String) table
														.getModel()
														.getValueAt(rowIndex, 5)
												+ "。自费信息："
												+ (String) table
														.getModel()
														.getValueAt(rowIndex, 7)
												+ "。是否转机："
												+ (String) table
														.getModel()
														.getValueAt(rowIndex, 8);
										// System.out.println(messageId);
										// System.out.println(messageInfo);
									}
								}
							}
						});
			} catch (Exception e1) {
				e1.printStackTrace();
			}

		}

	}

	// 显示所有消息
	public class AlreadySendMessage implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// 删除原来的JTable(JTable使用scrollPane来包装)
			if (scrollPane != null) {
				jf.remove(scrollPane);
			}
			try {
				Connection conn = DriverManager.getConnection(url, user, pass);
				Statement stmt = conn.createStatement();
				// 根据用户输入的SQL执行查询
				ResultSet rs = stmt
						.executeQuery("select messageId,messageStart,messageEnd,messageStatus,messageInfo from tb_message");
				// 取出ResultSet的MetaData
				ResultSetMetaData rsmd = rs.getMetaData();
				Vector<String> columnNames = new Vector<>();
				Vector<Vector<String>> data = new Vector<>();
				// 把ResultSet的所有列名添加到Vector里
				// for (int i = 0; i < rsmd.getColumnCount(); i++) {
				// columnNames.add(rsmd.getColumnName(i + 1));
				// }
				columnNames.add("消息ID");
				columnNames.add("消息发送者");
				columnNames.add("消息接收者");
				columnNames.add("消息已经被接受状态");
				columnNames.add("消息信息");

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
				table.getSelectionModel().addListSelectionListener(
						new ListSelectionListener() {

							@Override
							public void valueChanged(ListSelectionEvent e) {
								// TODO Auto-generated method stub
								if (e.getValueIsAdjusting()) {// 连续操作
									int rowIndex = table.getSelectedRow();
									if (rowIndex != -1) {
										// System.out.println("表格行被选中"+rowIndex);
										// System.out.println(table.getModel().getValueAt(rowIndex,
										// 0));
										messageId = Integer
												.valueOf((String) table
														.getModel().getValueAt(
																rowIndex, 0));
										messageEnd = (String) table.getModel()
												.getValueAt(rowIndex, 2);
										// System.out.println(messageId);
										// System.out.println(messageEnd);
									}
								}
							}
						});
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

	}

	// 发送消息
	public class LineSendMessage implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// 弹出对话框
			// JOptionPane.showConfirmDialog(null, "choose one", "choose one",
			// JOptionPane.YES_NO_CANCEL_OPTION);

			if (messageId.equals(-1) && lineId.equals(-1)) {
				JOptionPane.showMessageDialog(salerJF, "请选择需要发送的信息");
			} else {

				JPanel contentPane = new JPanel(); // 创建内容面板

				ArrayList<String> salerName = new ArrayList<>();

				salerName.addAll(selectDao.salerNameSelect(url, user, pass));

				for (String str : salerName) {
					JCheckBox jc = new JCheckBox(str, false);
					contentPane.add(jc);
					salerCheckBox.add(jc);
				}

				JButton sendButton = new JButton("发送");

				sendButton.addActionListener(new ButtonSendMessage());

				salerJF.add(contentPane, BorderLayout.CENTER);
				salerJF.add(sendButton, BorderLayout.SOUTH);
				FrameUtil.initFrame(salerJF, 500, 800);
				// jf.pack();

				// 关闭当前窗口
				salerJF.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);;;
				salerJF.setVisible(true);
			}
		}

	}

	// 点击发送消息按钮事件
	public class ButtonSendMessage implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			for (JCheckBox checkBox : salerCheckBox) {
				if (checkBox.isSelected()) {
					infos.add(checkBox.getText());
				}
			}

			if (lineId == -1 && infos.size() > 0) { // lineid==-1表示是更新信息
				String[] str = messageEnd.split(",");
				ArrayList<String> addMessageEnd = new ArrayList<>();
				for (String stAl : infos) {
					int flag = 0;
					for (String ss : str) {
						if (stAl.equals(ss)) {
							flag = 1;
						}
					}
					if (flag == 0) {
						addMessageEnd.add(stAl);
					}
				}
				for (String tt : addMessageEnd) {
					messageEnd += "," + tt;
				}
				// System.out.println(messageEnd);
				if (messageDao.updateMessage(messageId, messageEnd, url, user,
						pass)) {
					salerJF.dispose();
					JOptionPane.showMessageDialog(jf, "信息发送成功");
				} else {
					JOptionPane.showMessageDialog(jf, "信息发送失败");
				}
			} else if(messageId == -1 && infos.size() > 0){
				String insertMessageEnd = infos.get(0);
				for(int i=1; i < infos.size(); i++){
					String tt = infos.get(i);
					insertMessageEnd += ","+tt;
				}
				if(messageDao.insertMessage(lineId,userName, insertMessageEnd, messageInfo, url, user, pass)){
					salerJF.dispose();
					JOptionPane.showMessageDialog(jf, "信息发送成功");
				} else {
					JOptionPane.showMessageDialog(jf, "信息发送失败");
				}
			}

		}

	}

	public class OtherSendMessage implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

		}

	}

	public static void main(String[] args) {
		try {
			new MessageController("admin").init();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
