package xyz.acacian.swing;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import xyz.acacian.managers.LoginManager;

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
		LoginManager.getInstance().setTabbPane(new JTabbedPane());
	
		LoginManager.getInstance().setBookManagePane(new JBookManagePane());
		LoginManager.getInstance().setMemberMangePane(new JMemberManagePane());
		
		tabPane =LoginManager.getInstance().getTabbPane();
		
		panel = new JPanel[3];
		
		panel[1] = LoginManager.getInstance().getBookManagePane();
		panel[2] = LoginManager.getInstance().getMemberMangePane();
			
		
		tabPane.addTab("�α���â", new JLabel("�α���â"));
		tabPane.addTab("���� ����", panel[1]);
		tabPane.addTab("ȸ�� ����", panel[2]);
		
		tabPane.setSelectedIndex(2);	
		//tabPane.setEnabled(false);
	}
	

	
	

}
