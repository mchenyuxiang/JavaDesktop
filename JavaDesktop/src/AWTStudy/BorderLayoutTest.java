package AWTStudy;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;

public class BorderLayoutTest {

	public static void main(String[] args) {
		Frame f = new Frame("���Դ���");
		// ����Frame����ʹ��BorderLayout���ֹ�����
		f.setLayout(new BorderLayout(30, 5));
		f.add(new Button("��"), BorderLayout.NORTH);
		f.add(new Button("��"), BorderLayout.SOUTH);
		// Ĭ����ӵ��м�������
		f.add(new Button("��"));
		f.add(new Button("��"), BorderLayout.EAST);
		f.add(new Button("��"), BorderLayout.WEST);
		
		f.pack();
		f.setVisible(true);
	}
}
