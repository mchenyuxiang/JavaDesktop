package AWTStudy;

import java.awt.Frame;

/**
 * 
 * @author yuxiang
 *
 * ͨ��Frame������һ������
 */

public class FrameTest {

	public static void main(String[] args) {
		Frame f = new Frame("���Դ���");
		// ���ô��ڵĴ�С��λ��
		f.setBounds(30, 30, 250, 200);
		//��������ʾ����
		f.setVisible(true);
	}
}
