package xyz.itwill.bookmanagement;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class JManagementSwing extends JFrame {
	private static final long serialVersionUID = 1L;
	
	public JTabbedPane tabPane = null;
	
	JPanel panel = new JBookManagePane();
	
	public JManagementSwing() {
		setTitle("���� ���� ���α׷�");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 200, 450, 500);
		createTab();
		this.add(tabPane);
	}
	
	public void createTab() {
		tabPane = new JTabbedPane();
		tabPane.addTab("���� ����", panel);
		tabPane.addTab("ȸ�� ����", new JLabel("menu 2"));
	}
	

	
	

}
