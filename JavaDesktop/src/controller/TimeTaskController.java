package controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.JOptionPane;

import dao.TimeDao;

public class TimeTaskController {
	private final String PROP_FILE = "config/mysql.ini";
	private String driver;
	// url�����ݿ�ķ����ַ
	private String url;
	private String user;
	private String pass;

	private String userName;
	
	private TimeDao timeDao = new TimeDao();

	public TimeTaskController() {

	}

	public TimeTaskController(String userName) {
		this.userName = userName;
	}

	public void init() throws IOException, ClassNotFoundException {
		// ���ݿ�
		Properties connProp = new Properties();
		connProp.load(new FileInputStream(PROP_FILE));
		driver = connProp.getProperty("driver");
		url = connProp.getProperty("url");
		user = connProp.getProperty("user");
		pass = connProp.getProperty("pass");
		// ��������
		Class.forName(driver);
		
		ArrayList<String> messageInfo = new ArrayList<>();
		
		// run in a second
		final long timeInterval = 10000;
		Runnable runnable = new Runnable() {
			public void run() {
				while (true) {
					// ------- code for task to run
					ArrayList<String> messageInfo = new ArrayList<>();
					messageInfo.addAll(timeDao.noLookMessage(userName, url, user, pass));
					
					//������ϢID
					String messageId = "";
					for(String message:messageInfo){
//						 �����Ի���
						 int replaced = JOptionPane.showConfirmDialog(null, message, "�µ���·��Ϣ",
						 JOptionPane.YES_NO_OPTION);
						 String[] strTemp = message.split("��");
						 messageId = strTemp[0];

						 //ѡ���Ķ���. 0:YES;1:NO
						 if(replaced == 0){
							 timeDao.updateMessageStatus(userName, messageId, url, user, pass);
							 JOptionPane.showMessageDialog(null, "������·�ɹ�");
						 }
						 
						 
					}
					// ------- ends here
					try {
						Thread.sleep(timeInterval);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		Thread thread = new Thread(runnable);
		thread.start();
	}
	
	public static void main(String[] args) {
		try {
			new TimeTaskController().init();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
