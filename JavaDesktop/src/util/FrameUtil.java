package util;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

/**
 * 
 * @ClassName: FrameUtil 
 * @Description: TODO(��ʼ�����幤����)
 * @author yuxiang csyuxiang1988_gmail_com
 * @date 2015��11��12�� ����5:09:20
 */
public class FrameUtil {

	//��ʼ������
	public static void initFrame(JFrame frame, int width, int height){
		
		//��ȡһ����ϵͳ��ع��������
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		
		//��ȡ��Ļ�ķֱ���
		Dimension dimension = toolkit.getScreenSize();
		int x = (int)dimension.getWidth();
		int y = (int)dimension.getHeight();
		
		//���ô����ʼ����С
		frame.setBounds((x-width)/2, (y-height)/2, width, height);
		
		//��ʾ��������ɼ�
		frame.setVisible(true);
		
		//���ô���ر��¼�
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
