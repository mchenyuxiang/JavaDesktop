package AWTStudy;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Choice;
import java.awt.Frame;
import java.awt.List;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;

import javax.swing.Box;

public class CommonComponent {

	Frame f = new Frame("����");
	// ����һ����ť
	Button ok = new Button("ȷ��");
	CheckboxGroup cbg = new CheckboxGroup();
	//����һ����ѡ��
	Checkbox male = new Checkbox("��", cbg, true);
	//����һ����ѡ��
	Checkbox female = new Checkbox("Ů", cbg, false);
	//����һ����ѡ��
	Checkbox married = new Checkbox("�Ƿ��ѻ飿", false);
	//����һ��������
	Choice colorChooser = new Choice();
	//����һ���б�ѡ���
	List colorList = new List(6, true);
	//����һ��5�С�20�еĶ����ı���
	TextArea ta = new TextArea(5, 20);
	//����һ��50�еĵ����ı���
	TextField name = new TextField();
	
	public void init(){
		colorChooser.add("��ɫ");
		colorChooser.add("��ɫ");
		colorChooser.add("��ɫ");
		colorList.add("��ɫ");
		colorList.add("��ɫ");
		colorList.add("��ɫ");
		//����һ��װ�����ı��򡢰�ť��Panel
		Panel bottom = new Panel();
		bottom.add(name);
		bottom.add(ok);
		f.add(bottom, BorderLayout.SOUTH);
		//����һ��װ��������ѡ�������Checkbox��Panel
		Panel checkPanel = new Panel();
		checkPanel.add(colorChooser);
		checkPanel.add(male);
		checkPanel.add(female);
		checkPanel.add(married);
		//����һ����ֱ���������Box��ʢװ�����ı���Panel
		Box topLeft = Box.createVerticalBox();
		topLeft.add(ta);
		topLeft.add(checkPanel);
		//����һ��ˮƽ���������Box��ʢװtopLeft��colorList
		Box top = Box.createHorizontalBox();
		top.add(topLeft);
		top.add(colorList);
		//��top Box������ӵ����ڵ��м�
		f.add(top);
		f.pack();
		f.setVisible(true);
	}
	
	public static void main(String[] args) {
		new CommonComponent().init();
	}
}
