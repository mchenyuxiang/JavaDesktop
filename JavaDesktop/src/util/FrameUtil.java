package util;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

/**
 * 
 * @ClassName: FrameUtil 
 * @Description: TODO(初始化窗体工具类)
 * @author yuxiang csyuxiang1988_gmail_com
 * @date 2015年11月12日 下午5:09:20
 */
public class FrameUtil {

	//初始化窗体
	public static void initFrame(JFrame frame, int width, int height){
		
		//获取一个与系统相关工具类对象
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		
		//获取屏幕的分辨率
		Dimension dimension = toolkit.getScreenSize();
		int x = (int)dimension.getWidth();
		int y = (int)dimension.getHeight();
		
		//设置窗体初始化大小
		frame.setBounds((x-width)/2, (y-height)/2, width, height);
		
		//表示整个窗体可见
		frame.setVisible(true);
		
		//设置窗体关闭事件
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
