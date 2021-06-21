package xyz.acacian.swing;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class JManagementSwing extends JFrame {
	private static final long serialVersionUID = 1L;
	
	public JTabbedPane tabPane = null;

	private JPanel panel = null;
	
	public JManagementSwing() {
		setTitle("도서 관리 프로그램");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 400);
		createTab();
		//setResizable(false);
		setLocationRelativeTo(null);
		add(tabPane);
		//add(new JBookManagePane());
	}
	
	public void createTab() {
		tabPane = new JTabbedPane();
		panel = new JBookManagePane();
		
		tabPane.addTab("도서 관리", panel);
		tabPane.addTab("회원 관리", new JLabel("menu 2"));
	}
	

	
	

}
