package xyz.itwill.bookmanagement;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;

public class JManagementSwing extends JFrame {
	private static final long serialVersionUID = 1L;
	
	public JTabbedPane tabPane = null;
	
	public JManagementSwing() {
		setTitle("���� ���� ���α׷�");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 200, 450, 500);
		createTab();
		this.add(tabPane);
	}
	
	public void createTab() {
		tabPane = new JTabbedPane();
		tabPane.addTab("���� ����", new JLabel("menu 1"));
		tabPane.addTab("ȸ�� ����", new JLabel("menu 2"));
	}
	

	
	

}
