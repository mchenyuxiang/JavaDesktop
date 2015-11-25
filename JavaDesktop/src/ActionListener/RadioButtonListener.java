package ActionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JRadioButton;

//ÅÐ¶ÏÑ¡ÔñÄÄ¸ö½ÇÉ«µÇÂ¼
public class RadioButtonListener implements ActionListener {
	public String saveValueTemp = "";

	
	@Override
	public void actionPerformed(ActionEvent arg0) {

		JRadioButton temp = (JRadioButton) arg0.getSource();
		if (temp.isSelected()) {
			saveValueTemp = temp.getText();
			this.setSaveValueTemp(saveValueTemp);
		}

	}
	
	public void setSaveValueTemp(String saveValueTemp){
		this.saveValueTemp = saveValueTemp;
	}
	
	public String getSaveValueTemp(){
		return saveValueTemp;
	}

}
