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
import xyz.acacian.database.MemberDTO;
import xyz.acacian.enums.EBookAttribute;
import xyz.acacian.enums.ECategory;
import xyz.acacian.managers.BookManager;
import xyz.acacian.managers.MethodManager;
import xyz.acacian.objects.Book;
import javax.swing.JRadioButton;


//�ϴ� ����
public class JInsertUpdateMemberDialog extends JDialog{
	private static final long serialVersionUID = 1L;
	
	private static final int START_ = 60;
	private static final int INTERVAL_ = 70;

	private JTextField numberField;
	private JTextField nameField;
	private JTextField authorField;
	private JTextField publisherField;
	private JComboBox categoryComboBox;
	
	private JRadioButton insertRadio;
	private JRadioButton updateRadio;
	
	private JMemberManagePane parentPanel;
	
	public JInsertUpdateMemberDialog(JMemberManagePane parent) {
		this.parentPanel = parent;
		
		setTitle("���� INSERT / UPDATE");
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
		
		JLabel nameLabel = new JLabel("�� �� :");
		nameLabel.setBounds(12, START_+INTERVAL_*1, 73, 30);
		nameLabel.setHorizontalAlignment(JLabel.RIGHT);
		getContentPane().add(nameLabel);
		
		nameField = new JTextField();
		nameField.setBounds(90, START_+INTERVAL_*1, 280, 30);
		getContentPane().add(nameField);
		nameField.setColumns(10);
		
		JLabel authorLabel = new JLabel("�� �� :");
		authorLabel.setBounds(12, START_+INTERVAL_*2, 73, 30);
		authorLabel.setHorizontalAlignment(JLabel.RIGHT);
		getContentPane().add(authorLabel);
		
		authorField = new JTextField();
		authorField.setColumns(10);
		authorField.setBounds(90, START_+INTERVAL_*2, 280, 30);
		getContentPane().add(authorField);	
		
		JLabel publisherLabel = new JLabel("���ǻ� :");
		publisherLabel.setBounds(12, START_+INTERVAL_*3, 73, 30);
		publisherLabel.setHorizontalAlignment(JLabel.RIGHT);
		getContentPane().add(publisherLabel);
		
		publisherField = new JTextField();
		publisherField.setColumns(10);
		publisherField.setBounds(90, START_+INTERVAL_*3, 280, 30);
		getContentPane().add(publisherField);
		
		JLabel categoryLabel = new JLabel("�� �� :");
		categoryLabel.setBounds(12, START_+INTERVAL_*4, 73, 30);
		categoryLabel.setHorizontalAlignment(JLabel.RIGHT);
		getContentPane().add(categoryLabel);
		
		categoryComboBox = new JComboBox(ECategory.values());
		categoryComboBox.setBounds(90, START_+INTERVAL_*4, 280, 30);
		getContentPane().add(categoryComboBox);
		
		JButton insertUpdateButton = new JButton("INSERT / UPDATE");
		insertUpdateButton.setBounds(12, START_+INTERVAL_*5, 370, 73);
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
		String latestNum = Integer.toString(BookDAO.getDAO().getLatestNum()+1);
		numberField.setText(latestNum);
		//numberField.setText(Integer.toString(Book.getSeedId()+1));
		nameField.setText("");
		authorField.setText("");
		publisherField.setText("");
		categoryComboBox.setSelectedIndex(0);
	}
	
	public void setUpdateField(MemberDTO member) {
		numberField.setText(Integer.toString(member.getNum()));
		nameField.setText(member.getName());
	}
	
	public void insertButton() {
		if(!canInsertUpdate()) {
			return;
		}
		
		BookDTO book = new BookDTO(
				Integer.parseInt(numberField.getText()),
				nameField.getText(), 
				authorField.getText(),
				publisherField.getText(), 
				((ECategory)categoryComboBox.getSelectedItem()).getValue());

//		Book book = new Book(nameField.getText(), authorField.getText(),
//				publisherField.getText(), (ECategory)categoryComboBox.getSelectedItem());
		
//		BookManager bm = BookManager.getInstance();
//		bm.insertBook(book);

		BookDAO.getDAO().insertBook(book);
		JOptionPane.showMessageDialog(this, "�߰� �Ǿ����ϴ�.", "�˸�", JOptionPane.INFORMATION_MESSAGE);
		//this.setVisible(false);
		clearField();	
		//parentPanel.validateTable();
		parentPanel.displayAllMember();
	}
	
	public void updateButton() {
		if(!canInsertUpdate()) {
			return;
		}
		
		BookDTO book = new BookDTO(
				Integer.parseInt(numberField.getText()),
				nameField.getText(), 
				authorField.getText(),
				publisherField.getText(), 
				((ECategory)categoryComboBox.getSelectedItem()).getValue());

		BookDAO.getDAO().updateBook(book);
		JOptionPane.showMessageDialog(this, "���� �Ǿ����ϴ�.", "�˸�", JOptionPane.INFORMATION_MESSAGE);
		clearField();	
		parentPanel.displayAllMember();
	}

	public boolean canInsertUpdate() {
		String strTemp = nameField.getText();	
		if(strTemp.equals("")) {
			JOptionPane.showMessageDialog(this, "�̸��� �Է����ּ���.");
			nameField.requestFocus();
			return false;
		}
		strTemp = authorField.getText();	
		if(strTemp.equals("")) {
			JOptionPane.showMessageDialog(this, "���ڸ� �Է����ּ���.");
			authorField.requestFocus();
			return false;
		}
		strTemp = publisherField.getText();	
		if(strTemp.equals("")) {
			JOptionPane.showMessageDialog(this, "���ǻ縦 �Է����ּ���.");
			publisherField.requestFocus();
			return false;
		}
		return true;
	}



}