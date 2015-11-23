package AWTStudy;

import java.awt.Frame;

/**
 * 
 * @author yuxiang
 *
 * 通过Frame创建了一个窗口
 */

public class FrameTest {

	public static void main(String[] args) {
		Frame f = new Frame("测试窗口");
		// 设置窗口的大小、位置
		f.setBounds(30, 30, 250, 200);
		//将窗口显示出来
		f.setVisible(true);
	}
}
