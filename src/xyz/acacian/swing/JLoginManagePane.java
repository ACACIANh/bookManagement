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

		loginButton = new JButton("로그인");
		loginButton.setBounds(12, START_ + INTERVAL_ * 3, 370, 73);
		loginButton.addActionListener(e-> loginButton());
		
//		loginButton.addActionListener(
//				e-> { System.out.println(e.getActionCommand());
//						loginButton(); });
		
		add(loginButton);
		
		JButton loginQuickButton = new JButton("관리자 로그인");
		loginQuickButton.setBounds(12, START_ + INTERVAL_ * 4, 370, 73);
		loginQuickButton.addActionListener(e-> {
			LoginManager.getInstance().Login("gkehdrn", "gkehdrn");
			loginButton.setText("로그아웃"); });
		add(loginQuickButton);
		
		JButton insertMemberButton = new JButton("회원가입");
		insertMemberButton.setBounds(12, START_ + INTERVAL_ * 5, 370, 73);
		insertMemberButton.addActionListener(e-> System.out.println("회원가입창 띄우자"));
		add(insertMemberButton);
	}

	private void loginButton() {
		if(LoginManager.getInstance().isLogin()) {
			LoginManager.getInstance().logOut();
			loginButton.setText("로그인");
		}
		if(!canLoginButton()) {
			return;
		}
		loginButton.setText("로그아웃");
		LoginManager.getInstance().Login(idField.getText(), pwField.getText());		
		clearField();
	}
	
	public boolean canLoginButton() {
		String strTemp = idField.getText();	
		if(strTemp.equals("")) {
			JOptionPane.showMessageDialog(this, "ID를 입력해주세요.");
			idField.requestFocus();
			return false;
		}
		
		strTemp = pwField.getText();	
		if(strTemp.equals("")) {
			JOptionPane.showMessageDialog(this, "PW를 입력해주세요.");
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