package test;

import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Point;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.JTable;

import java.awt.Rectangle;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Manager extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JButton jButton = null;

	private JButton jButton1 = null;

	private JButton jButton2 = null;

	private JTable jTable = null;

	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setLocation(new Point(20, 0));
			jButton.setText("�޸���Ϣ");
			jButton.setSize(new Dimension(100, 30));
		}
		return jButton;
	}

	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setLocation(new Point(140, 0));
			jButton1.setText("ɾ����Ϣ");
			jButton1.setSize(new Dimension(100, 30));
		}
		return jButton1;
	}

	private JButton getJButton2() {
		if (jButton2 == null) {
			jButton2 = new JButton();
			jButton2.setLocation(new Point(260, 0));
			jButton2.setText("��ʾȫ��");
			jButton2.setSize(new Dimension(100, 30));
		}
		return jButton2;
	}

	// private void Debark() {
	// Connection conn=null;
	// ResultSet rs=null;
	// String sql=null;
	// conn=new Connection();
	// sql= "select * from message where =";
	// rs=conn.executeQuery(sql);
	//
	// try {
	// if (rs.next())
	// {
	//
	// }
	// else
	// JOptionPane.showMessageDialog(null,"�޶�Ӧ��Ϣ");
	// } catch (HeadlessException e) {
	// e.printStackTrace();
	// } catch (SQLException e) {
	// e.printStackTrace();
	// }
	// }
	//
	private JTable getJTable() {
		if (jTable == null) {
			jTable = new JTable();
			jTable.setBounds(new Rectangle(17, 97, 341, 82));
		}
		return jTable;
	}

	public static void main(String[] args) {
		// TODO �Զ����ɷ������
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Manager thisClass = new Manager();
				thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				thisClass.setVisible(true);
			}
		});
	}

	public Manager() {
		super();
		initialize();
	}

	private void initialize() {
		this.setSize(400, 300);
		this.setContentPane(getJContentPane());
		this.setTitle("����Աϵͳ");
	}

	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJButton(), null);
			jContentPane.add(getJButton1(), null);
			jContentPane.add(getJButton2(), null);
			jContentPane.add(getJTable(), null);
		}
		return jContentPane;
	}
}
