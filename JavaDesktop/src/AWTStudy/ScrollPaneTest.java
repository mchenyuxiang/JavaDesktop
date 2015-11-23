package AWTStudy;

import java.awt.Button;
import java.awt.Frame;
import java.awt.ScrollPane;
import java.awt.TextField;

/**
 * 
 * @author yuxiang
 * ScrollPane 是一个带滚动条的容器，它也不能独立存在，必须被添加到其他容器中。特点如下：
 * 	1、可作为容器来盛装其他组件，当组件占用空间过大时，ScrollPane自动产生滚动条。当然也可以通过指定特定的狗在其参数
 * 来指定默认具有滚动条。
 * 	2、不能单独存在，必须放置到其他容器中。
 * 	3、默认使用BorderLayout作为其布局管理器。ScrollPane通常用于盛装其他容器，所以通常不允许改变ScrollPane的布局
 * 管理器。
 */

public class ScrollPaneTest {

	public static void main(String[] args) {
		Frame f = new Frame("测试窗口");
		// 创建一个ScrollPane容器，指定总是具有滚动条
		ScrollPane sp = new ScrollPane(ScrollPane.SCROLLBARS_ALWAYS);
		// 向ScrollPane容器中添加两个组件
		sp.add(new TextField(20));
		sp.add(new Button("单击我"));
		// 将ScrollPane容器添加到Frame对象中
		f.add(sp);
		// 设置窗口大小、位置、可见
		f.setBounds(30, 30, 250, 200);
		f.setVisible(true);
	}
}
