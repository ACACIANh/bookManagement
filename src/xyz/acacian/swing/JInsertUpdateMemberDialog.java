package xyz.acacian.swing;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import xyz.acacian.database.BookDAO;
import xyz.acacian.database.BookDTO;
import xyz.acacian.database.MemberDAO;
import xyz.acacian.database.MemberDTO;
import xyz.acacian.enums.ECategory;
import xyz.acacian.enums.ELevel;
import xyz.acacian.enums.EMemberAttribute;

import javax.swing.JRadioButton;


public class JInsertUpdateMemberDialog extends JDialog{
	private static final long serialVersionUID = 1L;
	
	private static final int START_ = 60;
	private static final int INTERVAL_ = 40;

	private JTextField numberField;

	private JComboBox levelComboBox;
	
	private JTextField idField;
	private JTextField pwField;
	private JTextField nameField;

	private JTextField phoneField;
	private JTextField birthdayField;
	
	private JRadioButton insertRadio;
	private JRadioButton updateRadio;
	
	private JMemberManagePane parentPanel;
	
	public JInsertUpdateMemberDialog(JMemberManagePane parent) {
		this.parentPanel = parent;
		
		setTitle("회원 INSERT / UPDATE");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 600);
		setResizable(false);
		getContentPane().setLayout(null);
		
		
		JLabel numberLabel = new JLabel("번 호:");
		numberLabel.setBounds(12, START_+INTERVAL_*0, 73, 30);
		numberLabel.setHorizontalAlignment(JLabel.RIGHT);
		getContentPane().add(numberLabel);
		
		numberField = new JTextField();
		numberField.setBounds(90, START_+INTERVAL_*0, 280, 30);
		getContentPane().add(numberField);
		numberField.setColumns(10);
		numberField.setEnabled(false);
		
		JLabel levelLabel = new JLabel("등 급 :");
		levelLabel.setBounds(12, START_+INTERVAL_*1, 73, 30);
		levelLabel.setHorizontalAlignment(JLabel.RIGHT);
		getContentPane().add(levelLabel);
		
		levelComboBox = new JComboBox(ELevel.values());
		levelComboBox.setBounds(90, START_+INTERVAL_*1, 280, 30);
		getContentPane().add(levelComboBox);
		

		JLabel idLabel = new JLabel("ID :");
		idLabel.setBounds(12, START_+INTERVAL_*2, 73, 30);
		idLabel.setHorizontalAlignment(JLabel.RIGHT);
		getContentPane().add(idLabel);
		
		idField = new JTextField();
		idField.setColumns(10);
		idField.setBounds(90, START_+INTERVAL_*2, 280, 30);
		getContentPane().add(idField);	
		
		JLabel pwLabel = new JLabel("PW :");
		pwLabel.setBounds(12, START_+INTERVAL_*3, 73, 30);
		pwLabel.setHorizontalAlignment(JLabel.RIGHT);
		getContentPane().add(pwLabel);
		
		pwField = new JTextField();
		pwField.setColumns(10);
		pwField.setBounds(90, START_+INTERVAL_*3, 280, 30);
		getContentPane().add(pwField);
		
		JLabel nameLabel = new JLabel("이 름 :");
		nameLabel.setBounds(12, START_+INTERVAL_*4, 73, 30);
		nameLabel.setHorizontalAlignment(JLabel.RIGHT);
		getContentPane().add(nameLabel);
		
		nameField = new JTextField();
		nameField.setBounds(90, START_+INTERVAL_*4, 280, 30);
		getContentPane().add(nameField);
		nameField.setColumns(10);
		
		JLabel phoneLabel = new JLabel("전화번호 :");
		phoneLabel.setBounds(12, START_+INTERVAL_*5, 73, 30);
		phoneLabel.setHorizontalAlignment(JLabel.RIGHT);
		getContentPane().add(phoneLabel);
		
		phoneField = new JTextField();
		phoneField.setBounds(90, START_+INTERVAL_*5, 280, 30);
		getContentPane().add(phoneField);
		phoneField.setColumns(10);
		
		JLabel birthdayLabel = new JLabel("생년월일 :");
		birthdayLabel.setBounds(12, START_+INTERVAL_*6, 73, 30);
		birthdayLabel.setHorizontalAlignment(JLabel.RIGHT);
		getContentPane().add(birthdayLabel);
		
		birthdayField = new JTextField();
		birthdayField.setBounds(90, START_+INTERVAL_*6, 280, 30);
		getContentPane().add(birthdayField);
		birthdayField.setColumns(10);
		
		
		JButton insertUpdateButton = new JButton("INSERT / UPDATE");
		insertUpdateButton.setBounds(12, START_+INTERVAL_*7, 370, 73);
		getContentPane().add(insertUpdateButton);
		//insertUpdateButton.addActionListener(
		//		e -> insertRadio.isSelected() ? insertButton() : updateButton() );
		// 무슨차이일까..? ㅠㅅㅠ
		insertUpdateButton.addActionListener(
				e -> {	if(insertRadio.isSelected()) { insertButton(); }
						else { updateButton();}	});
		
		insertRadio = new JRadioButton("INSERT");
		insertRadio.setBounds(63, 19, 121, 23);
		//insertRadio.setSelected(true);
		insertRadio.setEnabled(false);
//		insertRadio.addActionListener(
//				e->radioInsertSelect(true));
		getContentPane().add(insertRadio);
		
		updateRadio = new JRadioButton("UPDATE");
		updateRadio.setBounds(211, 19, 121, 23);
		updateRadio.setEnabled(false);
//		updateRadio.addActionListener(
//				e->radioInsertSelect(false));
		getContentPane().add(updateRadio);
				
	}
	
	public void radioInsertSelect(boolean check) {
		insertRadio.setSelected(check);
		updateRadio.setSelected(!check);
	}
	
	public void clearField() {
		String latestNum = Integer.toString(MemberDAO.getDAO().getLatestNum()+1);
		numberField.setText(latestNum);
		//numberField.setText(Integer.toString(Book.getSeedId()+1));
		nameField.setText("");
		idField.setText("");
		pwField.setText("");
		phoneField.setText("");
		birthdayField.setText("");
		levelComboBox.setSelectedIndex(2);
	}
	
	public void setUpdateField(MemberDTO member) {
		numberField.setText(Integer.toString(member.getNum()));
		nameField.setText(member.getName());
		levelComboBox.setSelectedIndex(member.getId_level());
		idField.setText(member.getId());
		pwField.setText(member.getPw());
		phoneField.setText(member.getPhone());
		birthdayField.setText(member.getBirthday());
	}
	
	public void insertButton() {
		if(!canInsertUpdate()) {
			return;
		}
		if(!canUseId()) {
			return;
		}
		
		MemberDTO member = new MemberDTO(
				Integer.parseInt(numberField.getText()),
				((ELevel)levelComboBox.getSelectedItem()).getValue(),
				idField.getText(), 
				pwField.getText(),
				nameField.getText(), 
				phoneField.getText(), 
				birthdayField.getText()	);

		MemberDAO.getDAO().insertMember(member);
		JOptionPane.showMessageDialog(this, "추가 되었습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);

		clearField();	
		
		parentPanel.displayAllMember();
	}
	
	public void updateButton() {
		if(!canInsertUpdate()) {
			return;
		}
		if(!canUseId()) {
			return;
		}
		
		MemberDTO member = new MemberDTO(
				Integer.parseInt(numberField.getText()),
				((ELevel)levelComboBox.getSelectedItem()).getValue(),
				idField.getText(), 
				pwField.getText(),
				nameField.getText(), 
				phoneField.getText(), 
				birthdayField.getText()	);

		MemberDAO.getDAO().updateMember(member);
		JOptionPane.showMessageDialog(this, "갱신 되었습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
		clearField();	
		parentPanel.displayAllMember();
	}
	public boolean canUseId() {
		var list = MemberDAO.getDAO().selectMemberList(idField.getText(), EMemberAttribute.ID);
		if(list.isEmpty()) {
			return true;
		}
		if(list.get(0).getNum() == Integer.parseInt(numberField.getText())) {
			return true;
		}		
		JOptionPane.showMessageDialog(this, "생성할수 없습니다.");
		return false;
	}

	public boolean canInsertUpdate() {
		String strTemp = nameField.getText();	
		
		if(strTemp.equals("")) {
			JOptionPane.showMessageDialog(this, "이름을 입력해주세요.");
			nameField.requestFocus();
			return false;
		}
		strTemp = idField.getText();	
		if(strTemp.equals("")) {
			JOptionPane.showMessageDialog(this, "ID를 입력해주세요.");
			idField.requestFocus();
			return false;
		}
		strTemp = pwField.getText();	
		if(strTemp.equals("")) {
			JOptionPane.showMessageDialog(this, "비밀번호를 입력해주세요.");
			pwField.requestFocus();
			return false;
		}
		strTemp = phoneField.getText();	
		if(strTemp.equals("")) {
			JOptionPane.showMessageDialog(this, "번호를 입력해주세요.");
			nameField.requestFocus();
			return false;
		}
		strTemp = birthdayField.getText();		
		if(strTemp.equals("")) {
			JOptionPane.showMessageDialog(this, "생일을 입력해주세요.");
			nameField.requestFocus();
			return false;
		}
		return true;
	}



}