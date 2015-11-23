package AWTStudy;

import java.awt.Button;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextField;

/**
 * 
 * @author yuxiang
 * 
 * Panel是AWT中的一个典型容器，它代表独立存在、必须放在其他容器中的容器。
 * Panel容器具有特点:
 * 	1、可作为容器来盛装其他组件，为放置组件提供空间。
 *  2、不能单独存在，必须放置到其他容器中。
 *  3、默认使用FlowLayout作为其布局管理器。
 */

public class PanelTest {
	
	public static void main(String[] args) {
		Frame f = new Frame("测试窗口");
		// 创建一个Panel容器
		Panel p = new Panel();
		// 向Panel容器中添加两个组件
		p.add(new TextField(20));
		p.add(new Button("单击我"));
		// 将Panel容器添加到Frame窗口中
		f.add(p);
		// 设置窗口的大小、设置
		f.setBounds(30, 30, 250, 200);
		// 将窗口显示出来
		f.setVisible(true);
	}

}
