package xyz.acacian.swing;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

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
	
	private final static String titleInsert = "ȸ�� INSERT / UPDATE";
	private final static String titleSignup = "ȸ������";
	
	private final static String buttonInsert = "INSERT / UPDATE";
	private final static String buttonSignup = "���Խ�û";

	private JTextField numberField;

	private JComboBox levelComboBox;
	
	private JTextField idField;
	private JTextField pwField;
	private JTextField nameField;

	private JTextField phoneField;
	private JTextField birthdayField;
	
	private JRadioButton insertRadio;
	private JRadioButton updateRadio;
	
	private JButton insertUpdateButton;
	
	private JMemberManagePane parentPanel;
	
	public JInsertUpdateMemberDialog(JMemberManagePane parent) {
		this.parentPanel = parent;
		
		setTitle(titleInsert);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 600);
		setResizable(false);
		getContentPane().setLayout(null);
				
		JLabel numberLabel = new JLabel("�� ȣ:");
		numberLabel.setBounds(12, START_+INTERVAL_*0, 73, 30);
		numberLabel.setHorizontalAlignment(JLabel.RIGHT);
		getContentPane().add(numberLabel);
		
		numberField = new JTextField();
		numberField.setBounds(90, START_+INTERVAL_*0, 280, 30);
		getContentPane().add(numberField);
		numberField.setColumns(10);
		numberField.setEnabled(false);
		
		JLabel levelLabel = new JLabel("�� �� :");
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
		
		JLabel nameLabel = new JLabel("�� �� :");
		nameLabel.setBounds(12, START_+INTERVAL_*4, 73, 30);
		nameLabel.setHorizontalAlignment(JLabel.RIGHT);
		getContentPane().add(nameLabel);
		
		nameField = new JTextField();
		nameField.setBounds(90, START_+INTERVAL_*4, 280, 30);
		getContentPane().add(nameField);
		nameField.setColumns(10);
		
		JLabel phoneLabel = new JLabel("��ȭ��ȣ :");
		phoneLabel.setBounds(12, START_+INTERVAL_*5, 73, 30);
		phoneLabel.setHorizontalAlignment(JLabel.RIGHT);
		getContentPane().add(phoneLabel);
		
		phoneField = new JTextField();
		phoneField.setBounds(90, START_+INTERVAL_*5, 280, 30);
		getContentPane().add(phoneField);
		phoneField.setColumns(10);
		
		JLabel birthdayLabel = new JLabel("������� :");
		birthdayLabel.setBounds(12, START_+INTERVAL_*6, 73, 30);
		birthdayLabel.setHorizontalAlignment(JLabel.RIGHT);
		getContentPane().add(birthdayLabel);
		
		birthdayField = new JTextField();
		birthdayField.setBounds(90, START_+INTERVAL_*6, 280, 30);
		getContentPane().add(birthdayField);
		birthdayField.setColumns(10);
		
		
		insertUpdateButton = new JButton(buttonInsert);
		insertUpdateButton.setBounds(12, START_+INTERVAL_*7, 370, 73);
		getContentPane().add(insertUpdateButton);
		//insertUpdateButton.addActionListener(
		//		e -> insertRadio.isSelected() ? insertButton() : updateButton() );
		// ���������ϱ�..? �Ф���
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
	
	public void setLevelsetEnabled(boolean on) {
		if(on) {
			setTitle(titleInsert);
			insertUpdateButton.setText(buttonInsert);
		}
		else {
			setTitle(titleSignup);
			insertUpdateButton.setText(buttonSignup);
		}
		levelComboBox.setEnabled(on);
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
		JOptionPane.showMessageDialog(this, "�߰� �Ǿ����ϴ�.", "�˸�", JOptionPane.INFORMATION_MESSAGE);

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
		JOptionPane.showMessageDialog(this, "���� �Ǿ����ϴ�.", "�˸�", JOptionPane.INFORMATION_MESSAGE);
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
		JOptionPane.showMessageDialog(this, "�����Ҽ� �����ϴ�.");
		return false;
	}

	public boolean canInsertUpdate() {
		String strTemp = idField.getText();	
		if(strTemp.equals("")) {
			JOptionPane.showMessageDialog(this, "ID�� �Է����ּ���.");
			idField.requestFocus();
			return false;
		}
		String idReg = "^[a-zA-Z][a-zA-Z0-9]{1,20}$";
		if(!Pattern.matches(idReg, strTemp)) {
			JOptionPane.showMessageDialog(this, "ID�� ������ ���ڸ� �����Ͽ� �ּ�2�ڸ� �ִ�10�ڸ��� �����ּ���.");
			phoneField.requestFocus();
			return false;
		}
		
		strTemp = pwField.getText();	
		if(strTemp.equals("")) {
			JOptionPane.showMessageDialog(this, "��й�ȣ�� �Է����ּ���.");
			pwField.requestFocus();
			return false;
		}
		String pwReg = "^[a-zA-Z][a-zA-Z0-9]{1,20}$";
		if(!Pattern.matches(pwReg, strTemp)) {
			JOptionPane.showMessageDialog(this, "PW�� ������ ���ڸ� �����Ͽ� �ּ�2�ڸ� �ִ�10�ڸ��� �����ּ���.");
			phoneField.requestFocus();
			return false;
		}
		
		strTemp = nameField.getText();	
		if(strTemp.equals("")) {
			JOptionPane.showMessageDialog(this, "�̸��� �Է����ּ���.");
			nameField.requestFocus();
			return false;
		}
		strTemp = phoneField.getText();	
		if(strTemp.equals("")) {
			JOptionPane.showMessageDialog(this, "��ȣ�� �Է����ּ���.");
			phoneField.requestFocus();
			return false;
		}
		String phoneReg = "010-\\d{3,4}-\\d{4}";
		if(!Pattern.matches(phoneReg, strTemp)) {
			JOptionPane.showMessageDialog(this, "��ȣ�� 010-0000-0000 �������� �Է����ּ���.");
			phoneField.requestFocus();
			return false;
		}
		
		strTemp = birthdayField.getText();		
		if(strTemp.equals("")) {
			JOptionPane.showMessageDialog(this, "������ �Է����ּ���.");
			birthdayField.requestFocus();
			return false;
		}
		String birthdayReg = "(19|20)\\d{2}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])";
		if(!Pattern.matches(birthdayReg, strTemp)) {
			JOptionPane.showMessageDialog(this, "������ 1995-01-01 �������� �Է����ּ���.");
			phoneField.requestFocus();
			return false;
		}
		
		return true;
	}


}