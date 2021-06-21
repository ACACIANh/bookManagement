package xyz.acacian.managers;

import java.awt.Component;

import javax.swing.JOptionPane;

public enum MethodManager {
	INSTANCE;
	
	public static MethodManager getInstance() {
		return INSTANCE;
	}

	private MethodManager() {	
	
	}
		
	public void somethingWrong(Component component) {
		JOptionPane.showMessageDialog(component, "Check Something Wrong"
				,"에러",JOptionPane.ERROR_MESSAGE);
	}
	
	public void notSelectColumn(Component component) {
		JOptionPane.showMessageDialog(component, "Select Column First"
				,"에러",JOptionPane.ERROR_MESSAGE);
	}
}
