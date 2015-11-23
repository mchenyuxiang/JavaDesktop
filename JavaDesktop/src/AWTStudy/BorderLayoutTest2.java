package AWTStudy;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextField;

public class BorderLayoutTest2 {

	public static void main(String[] args) {
		Frame f = new Frame("���Դ���");
		f.setLayout(new BorderLayout(30, 5));
		f.add(new Button("��"), BorderLayout.NORTH);
		f.add(new Button("��"), BorderLayout.SOUTH);
		
		//����һ��Panel����
		Panel p = new Panel();
		p.add(new TextField(20));
		p.add(new Button("������"));
		
		f.add(p);
		f.add(new Button("��"), BorderLayout.EAST);
		
		f.pack();
		f.setVisible(true);
	}
}
