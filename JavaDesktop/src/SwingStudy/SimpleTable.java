package SwingStudy;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class SimpleTable {
	JFrame jf = new JFrame("�򵥱��");
	JTable table;
	//�����ά������Ϊ�������
	Object[][] tableData = 
		{
			new Object[]{"������",29,"Ů"},
			new Object[]{"�ո�����",56,"��"},
			new Object[]{"���",35,"��"},
			new Object[]{"Ū��",18,"Ů"},
			new Object[]{"��ͷ",2,"��"}
		};
	//����һά������Ϊ�б���
	Object[] columnTitle = {"����","����","�Ա�"};
	public void init(){
		//�Զ�ά�����һά����������һ��JTable����
		table = new JTable(tableData, columnTitle);
		//��JTable�������JScrollPane��
		//������JScrollPane���ڴ�������ʾ
		jf.add(new JScrollPane(table));
		jf.pack();
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
	}
	
	public static void main(String[] args) {
		new SimpleTable().init();
	}
}
