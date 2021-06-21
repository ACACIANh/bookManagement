package xyz.acacian.swing;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import xyz.acacian.managers.LoginManager;

public class JLoginManagePane extends JPanel {
	private static final long serialVersionUID = 1L;

	private static final int START_ = 130;
	private static final int INTERVAL_ = 60;

	private JTextField idField;
	private JTextField pwField;
	
	private JButton loginButton;

	public JLoginManagePane() {
		
		setBounds(100, 100, 500, 600);
		
		JLabel idLabel = new JLabel("ID :");
		idLabel.setBounds(12, START_ + INTERVAL_ * 1, 73, 30);
		idLabel.setHorizontalAlignment(JLabel.RIGHT);
		add(idLabel);

		idField = new JTextField();
		idField.setColumns(10);
		idField.setBounds(90, START_ + INTERVAL_ * 1, 280, 30);
		idField.addActionListener(e->loginButton());
		add(idField);

		JLabel pwLabel = new JLabel("PW :");
		pwLabel.setBounds(12, START_ + INTERVAL_ * 2, 73, 30);
		pwLabel.setHorizontalAlignment(JLabel.RIGHT);
		add(pwLabel);

		pwField = new JTextField();
		pwField.setColumns(10);
		pwField.setBounds(90, START_ + INTERVAL_ * 2, 280, 30);
		pwField.addActionListener(e->loginButton());
		add(pwField);

		loginButton = new JButton("�α���");
		loginButton.setBounds(12, START_ + INTERVAL_ * 3, 370, 73);
		loginButton.addActionListener(e-> loginButton());
		
//		loginButton.addActionListener(
//				e-> { System.out.println(e.getActionCommand());
//						loginButton(); });
		
		add(loginButton);
		
		JButton loginQuickButton = new JButton("������ �α���");
		loginQuickButton.setBounds(12, START_ + INTERVAL_ * 4, 370, 73);
		loginQuickButton.addActionListener(e-> {
			LoginManager.getInstance().Login("gkehdrn", "gkehdrn");
			loginButton.setText("�α׾ƿ�"); });
		add(loginQuickButton);
		
		JButton insertMemberButton = new JButton("ȸ������");
		insertMemberButton.setBounds(12, START_ + INTERVAL_ * 5, 370, 73);
		insertMemberButton.addActionListener(e-> System.out.println("ȸ������â �����"));
		add(insertMemberButton);
	}

	private void loginButton() {
		if(LoginManager.getInstance().isLogin()) {
			LoginManager.getInstance().logOut();
			loginButton.setText("�α���");
		}
		if(!canLoginButton()) {
			return;
		}
		loginButton.setText("�α׾ƿ�");
		LoginManager.getInstance().Login(idField.getText(), pwField.getText());		
		clearField();
	}
	
	public boolean canLoginButton() {
		String strTemp = idField.getText();	
		if(strTemp.equals("")) {
			JOptionPane.showMessageDialog(this, "ID�� �Է����ּ���.");
			idField.requestFocus();
			return false;
		}
		
		strTemp = pwField.getText();	
		if(strTemp.equals("")) {
			JOptionPane.showMessageDialog(this, "PW�� �Է����ּ���.");
			pwField.requestFocus();
			return false;
		}
		return true;
	}
	

	public void clearField() {
		idField.setText("");
		pwField.setText("");	
	}


}