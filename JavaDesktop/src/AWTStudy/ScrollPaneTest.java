package AWTStudy;

import java.awt.Button;
import java.awt.Frame;
import java.awt.ScrollPane;
import java.awt.TextField;

/**
 * 
 * @author yuxiang
 * ScrollPane ��һ��������������������Ҳ���ܶ������ڣ����뱻��ӵ����������С��ص����£�
 * 	1������Ϊ������ʢװ��������������ռ�ÿռ����ʱ��ScrollPane�Զ���������������ȻҲ����ͨ��ָ���ض��Ĺ��������
 * ��ָ��Ĭ�Ͼ��й�������
 * 	2�����ܵ������ڣ�������õ����������С�
 * 	3��Ĭ��ʹ��BorderLayout��Ϊ�䲼�ֹ�������ScrollPaneͨ������ʢװ��������������ͨ��������ı�ScrollPane�Ĳ���
 * ��������
 */

public class ScrollPaneTest {

	public static void main(String[] args) {
		Frame f = new Frame("���Դ���");
		// ����һ��ScrollPane������ָ�����Ǿ��й�����
		ScrollPane sp = new ScrollPane(ScrollPane.SCROLLBARS_ALWAYS);
		// ��ScrollPane����������������
		sp.add(new TextField(20));
		sp.add(new Button("������"));
		// ��ScrollPane������ӵ�Frame������
		f.add(sp);
		// ���ô��ڴ�С��λ�á��ɼ�
		f.setBounds(30, 30, 250, 200);
		f.setVisible(true);
	}
}
