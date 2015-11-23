package AWTStudy;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;

/**
 * 
 * @author yuxiang
 *
 * FlowLayout布局管理器中，组件像流水一样向某方向流动，遇到障碍（边界）就折回，从头开始排列。
 * 在默认情况下，FlowLayout布局管理器从左向右排列所有组件，遇到边界就会折回下一行重新开始。
 * 
 * f.peak()中peak()方法是window容器提供的一个方法，该方法用于将窗口调整到最佳大小。
 */

public class FlowLayoutTest {

	public static void main(String[] args) {
		Frame f = new Frame("测试窗口");
		// 设置Frame容器使用FlowLayout布局管理器
		f.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 5));
		// 向窗口中添加10个按钮
		for(int i=0; i<10; i++){
			f.add(new Button("按钮" + i));
		}
		// 设置窗口为最佳大小、并显示
		f.pack();
		f.setVisible(true);
	}
}
