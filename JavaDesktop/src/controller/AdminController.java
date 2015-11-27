package controller;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AdminController {

	private JFrame jf = new JFrame("����Ա����");

	private JPanel jPanelLeft = new JPanel();
	private JPanel jPanelRight = new JPanel();

	private JButton saleButton = new JButton("�������");
	private JButton supplierButton = new JButton("��Ӿ�����");
	private JButton shopButton = new JButton("��ӵ���");
	private JButton departButton = new JButton("��ӳ�������");
	private JButton destinationButton = new JButton("���Ŀ�ĵ�");

	// ��ֱ�ڷ�
	private Box verticalLeft = Box.createVerticalBox();
	private Box saleVerticalRight = Box.createVerticalBox();
	private Box supplierVerticalRight = Box.createVerticalBox();
	private Box shopVerticalRight = Box.createVerticalBox();
	private Box departVerticalRight = Box.createVerticalBox();
	private Box destinationVerticalRight = Box.createVerticalBox();

	public void init() {
		verticalLeft.add(saleButton);
		verticalLeft.add(supplierButton);
		verticalLeft.add(shopButton);
		verticalLeft.add(departButton);
		verticalLeft.add(destinationButton);

		JPanel jPanel1 = new JPanel();
		jPanel1.add(new JLabel("�û�����"));
		jPanel1.add(new JTextField(20));
		JPanel jPanel2 = new JPanel();
		jPanel2.add(new JLabel("������"));
		jPanel2.add(new JTextField(20));
		JPanel jPanel3 = new JPanel();
		jPanel3.add(new JLabel("������"));
		jPanel3.add(new JTextField(20));
		JPanel jPanel4 = new JPanel();
		jPanel4.add(new JLabel("������"));
		jPanel4.add(new JTextField(20));
		JPanel jPanel5 = new JPanel();
		jPanel5.add(new JLabel("������"));
		jPanel5.add(new JTextField(20));
		JPanel jPanel6 = new JPanel();
		jPanel6.add(new JButton("ȷ��"));

		saleVerticalRight.add(jPanel1);
		saleVerticalRight.add(jPanel2);
		saleVerticalRight.add(jPanel3);
		saleVerticalRight.add(jPanel4);
		saleVerticalRight.add(jPanel5);
		saleVerticalRight.add(jPanel6);

		jf.add(verticalLeft, BorderLayout.WEST);
		jf.add(saleVerticalRight, BorderLayout.CENTER);

		jf.pack();
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);

		saleButton.addActionListener(new SaleInfo());
		supplierButton.addActionListener(new SupplierInfo());

	}

	// ����������Ͻ���
	public class SaleInfo implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			saleVerticalRight.setVisible(true);
			supplierVerticalRight.setVisible(false);

			JPanel jPanel1 = new JPanel();
			jPanel1.add(new JLabel("�û�����"));
			jPanel1.add(new JTextField(20));
			JPanel jPanel2 = new JPanel();
			jPanel2.add(new JLabel("������"));
			jPanel2.add(new JTextField(20));
			JPanel jPanel3 = new JPanel();
			jPanel3.add(new JLabel("������"));
			jPanel3.add(new JTextField(20));
			JPanel jPanel4 = new JPanel();
			jPanel4.add(new JLabel("������"));
			jPanel4.add(new JTextField(20));
			JPanel jPanel5 = new JPanel();
			jPanel5.add(new JLabel("������"));
			jPanel5.add(new JTextField(20));
			JPanel jPanel6 = new JPanel();
			jPanel6.add(new JButton("ȷ��"));

			saleVerticalRight.add(jPanel1);
			saleVerticalRight.add(jPanel2);
			saleVerticalRight.add(jPanel3);
			saleVerticalRight.add(jPanel4);
			saleVerticalRight.add(jPanel5);
			saleVerticalRight.add(jPanel6);
			jf.add(saleVerticalRight, BorderLayout.CENTER);

		}

	}

	// ������������Ͻ���
	public class SupplierInfo implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			saleVerticalRight.setVisible(false);
			supplierVerticalRight.setVisible(true);

			JPanel jPanel1 = new JPanel();
			jPanel1.add(new JLabel("�û�����"));
			jPanel1.add(new JTextField(20));
			JPanel jPanel2 = new JPanel();
			jPanel2.add(new JLabel("���룺"));
			jPanel2.add(new JTextField(20));
			JPanel jPanel3 = new JPanel();
			jPanel3.add(new JLabel("������"));
			jPanel3.add(new JTextField(20));
			JPanel jPanel4 = new JPanel();
			jPanel4.add(new JLabel("������"));
			jPanel4.add(new JTextField(20));
			JPanel jPanel5 = new JPanel();
			jPanel5.add(new JLabel("������"));
			jPanel5.add(new JTextField(20));
			JPanel jPanel6 = new JPanel();
			jPanel6.add(new JButton("ȷ��"));

			supplierVerticalRight.add(jPanel1);
			supplierVerticalRight.add(jPanel2);
			supplierVerticalRight.add(jPanel3);
			supplierVerticalRight.add(jPanel4);
			supplierVerticalRight.add(jPanel5);
			supplierVerticalRight.add(jPanel6);
			jf.add(supplierVerticalRight, BorderLayout.CENTER);

		}

	}

	public static void main(String[] args) {
		new AdminController().init();
	}
}
