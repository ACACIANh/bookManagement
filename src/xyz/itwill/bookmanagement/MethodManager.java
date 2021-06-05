package xyz.itwill.bookmanagement;

import java.awt.Component;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class MethodManager {
	private static MethodManager instance = null;
	
	private MethodManager() {
	}
	
	public static MethodManager getInstance() {
		if(instance == null) {
			instance = new MethodManager();			
		}
		return instance;
	}
	
	public boolean isPossibleTextField(JTextField field, EBookAttribute attriute) {
		String text = field.getText();
		if(text.equals("")) {
			return false;
		}
		
		//각 항목별 정규표현식 비교 추후에 추가할것.
		switch(attriute) {
		case ID:
			if(text.length() > 10) {
				return false;
			}
			break;
		case CATEGORY:
			if(text.length() > 10) {
				return false;
			}
			break;
		case NAME:
			if(text.length() > 10) {
				return false;
			}
			break;
		case AUTHOR:
			if(text.length() > 10) {
				return false;
			}
			break;
		case PUBLISHER:
			if(text.length() > 10) {
				return false;
			}
			break;
		}
		
		return true;
	}
	
	public void somethingWrong(Component component) {
		JOptionPane.showMessageDialog(component, "무언가 잘못되었다."
				,"에러",JOptionPane.ERROR_MESSAGE);
	}

}
