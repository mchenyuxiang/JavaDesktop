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

	private Frame f = new Frame("�򵥵ļ��������");
	//��ȡϵͳ������
	private Clipboard clipboard = Toolkit
			.getDefaultToolkit().getSystemClipboard();
	//���ڸ�ֵ�ı����ı���
	private TextArea jtaCopyTo = new TextArea(5, 20);
	private TextArea jtaPaste = new TextArea(5, 20);
	private Button btCopy = new Button("����");
	private Button btPaste = new Button("ճ��");
	public void init(){
		Panel p = new Panel();
		p.add(btCopy);
		p.add(btPaste);
		btCopy.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//��һ�������ı�������ַ�����װ��StringSelection����
				StringSelection contents = new 
						StringSelection(jtaCopyTo.getText());
				//��StringSelection��������������
				clipboard.setContents(contents, null);
			}
		});
		btPaste.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//����������а���stringFlavor����
				if(clipboard.isDataFlavorAvailable(DataFlavor.stringFlavor)){
					//ȡ���������е�stringFlavor����
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
		//����һ��ˮƽ���е�Box����
		Box box = new Box(BoxLayout.X_AXIS);
		//�����������ı������Box������
		box.add(jtaCopyTo);
		box.add(jtaPaste);
		//����ť���ڵ�Panel��Box������ӵ�Frame������
		f.add(p,BorderLayout.SOUTH);
		f.add(box,BorderLayout.CENTER);
		f.pack();
		f.setVisible(true);
	}
	public static void main(String[] args) {
		new SimpleClipboard().init();
	}
}
