package AWTStudy;

import java.awt.Button;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextField;

/**
 * 
 * @author yuxiang
 * 
 * Panel��AWT�е�һ������������������������ڡ�����������������е�������
 * Panel���������ص�:
 * 	1������Ϊ������ʢװ���������Ϊ��������ṩ�ռ䡣
 *  2�����ܵ������ڣ�������õ����������С�
 *  3��Ĭ��ʹ��FlowLayout��Ϊ�䲼�ֹ�������
 */

public class PanelTest {
	
	public static void main(String[] args) {
		Frame f = new Frame("���Դ���");
		// ����һ��Panel����
		Panel p = new Panel();
		// ��Panel����������������
		p.add(new TextField(20));
		p.add(new Button("������"));
		// ��Panel������ӵ�Frame������
		f.add(p);
		// ���ô��ڵĴ�С������
		f.setBounds(30, 30, 250, 200);
		// ��������ʾ����
		f.setVisible(true);
	}

}
