package AWTStudy;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;

/**
 * 
 * @author yuxiang
 *
 * FlowLayout���ֹ������У��������ˮһ����ĳ���������������ϰ����߽磩���ۻأ���ͷ��ʼ���С�
 * ��Ĭ������£�FlowLayout���ֹ�������������������������������߽�ͻ��ۻ���һ�����¿�ʼ��
 * 
 * f.peak()��peak()������window�����ṩ��һ���������÷������ڽ����ڵ�������Ѵ�С��
 */

public class FlowLayoutTest {

	public static void main(String[] args) {
		Frame f = new Frame("���Դ���");
		// ����Frame����ʹ��FlowLayout���ֹ�����
		f.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 5));
		// �򴰿������10����ť
		for(int i=0; i<10; i++){
			f.add(new Button("��ť" + i));
		}
		// ���ô���Ϊ��Ѵ�С������ʾ
		f.pack();
		f.setVisible(true);
	}
}
