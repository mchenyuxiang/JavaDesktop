package AWTStudy;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.BoxLayout;

public class SimpleClipboard {

	private Frame f = new Frame("简单的剪贴板程序");
	//获取系统剪贴板
	private Clipboard clipboard = Toolkit
			.getDefaultToolkit().getSystemClipboard();
	//用于赋值文本的文本框
	private TextArea jtaCopyTo = new TextArea(5, 20);
	private TextArea jtaPaste = new TextArea(5, 20);
	private Button btCopy = new Button("复制");
	private Button btPaste = new Button("粘贴");
	public void init(){
		Panel p = new Panel();
		p.add(btCopy);
		p.add(btPaste);
		btCopy.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//将一个多行文本域里的字符串封装成StringSelection对象
				StringSelection contents = new 
						StringSelection(jtaCopyTo.getText());
				//将StringSelection对象放入剪贴板中
				clipboard.setContents(contents, null);
			}
		});
		btPaste.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//如果剪贴板中包含stringFlavor内容
				if(clipboard.isDataFlavorAvailable(DataFlavor.stringFlavor)){
					//取出剪贴板中的stringFlavor内容
					try {
						String content = (String)clipboard
								.getData(DataFlavor.stringFlavor);
						jtaPaste.append(content);
					} catch (UnsupportedFlavorException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		//创建一个水平排列的Box容器
		Box box = new Box(BoxLayout.X_AXIS);
		//将两个多行文本域放在Box容器中
		box.add(jtaCopyTo);
		box.add(jtaPaste);
		//将按钮所在的Panel、Box容器添加到Frame窗口中
		f.add(p,BorderLayout.SOUTH);
		f.add(box,BorderLayout.CENTER);
		f.pack();
		f.setVisible(true);
	}
	public static void main(String[] args) {
		new SimpleClipboard().init();
	}
}
