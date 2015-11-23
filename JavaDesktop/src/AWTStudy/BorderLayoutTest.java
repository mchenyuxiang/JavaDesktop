package AWTStudy;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;

public class BorderLayoutTest {

	public static void main(String[] args) {
		Frame f = new Frame("测试窗口");
		// 设置Frame容器使用BorderLayout布局管理器
		f.setLayout(new BorderLayout(30, 5));
		f.add(new Button("南"), BorderLayout.NORTH);
		f.add(new Button("北"), BorderLayout.SOUTH);
		// 默认添加到中间区域中
		f.add(new Button("中"));
		f.add(new Button("东"), BorderLayout.EAST);
		f.add(new Button("西"), BorderLayout.WEST);
		
		f.pack();
		f.setVisible(true);
	}
}
