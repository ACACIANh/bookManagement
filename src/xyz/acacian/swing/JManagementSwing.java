package xyz.acacian.swing;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class JManagementSwing extends JFrame {
	private static final long serialVersionUID = 1L;
	
	public JTabbedPane tabPane = null;

	private JPanel[] panel = null;
	
	public JManagementSwing() {
		setTitle("���� ���� ���α׷�");
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
		
		panel = new JPanel[3];
		panel[1] = new JBookManagePane();
		panel[2] = new JMemberManagePane();
		
		tabPane.addTab("�α���â", new JLabel("�α���â"));
		tabPane.addTab("���� ����", panel[1]);
		tabPane.addTab("ȸ�� ����", panel[2]);
		
	}
	

	
	

}
