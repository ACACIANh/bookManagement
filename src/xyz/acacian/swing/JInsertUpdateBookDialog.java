package xyz.acacian.swing;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import xyz.acacian.enums.EBookAttribute;
import xyz.acacian.enums.ECategory;
import xyz.acacian.managers.BookManager;
import xyz.acacian.managers.MethodManager;
import xyz.acacian.objects.Book;
import javax.swing.JRadioButton;

public class JInsertUpdateBookDialog extends JDialog{
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
	
	private JBookManagePane parentPanel;
	
	public JInsertUpdateBookDialog(JBookManagePane parent) {
		this.parentPanel = parent;
		
		setTitle("도서 INSERT / UPDATE");
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
		
		JLabel nameLabel = new JLabel("제 목 :");
		nameLabel.setBounds(12, START_+INTERVAL_*1, 73, 30);
		nameLabel.setHorizontalAlignment(JLabel.RIGHT);
		getContentPane().add(nameLabel);
		
		nameField = new JTextField();
		nameField.setBounds(90, START_+INTERVAL_*1, 280, 30);
		getContentPane().add(nameField);
		nameField.setColumns(10);
		
		JLabel authorLabel = new JLabel("저 자 :");
		authorLabel.setBounds(12, START_+INTERVAL_*2, 73, 30);
		authorLabel.setHorizontalAlignment(JLabel.RIGHT);
		getContentPane().add(authorLabel);
		
		authorField = new JTextField();
		authorField.setColumns(10);
		authorField.setBounds(90, START_+INTERVAL_*2, 280, 30);
		getContentPane().add(authorField);	
		
		JLabel publisherLabel = new JLabel("출판사 :");
		publisherLabel.setBounds(12, START_+INTERVAL_*3, 73, 30);
		publisherLabel.setHorizontalAlignment(JLabel.RIGHT);
		getContentPane().add(publisherLabel);
		
		publisherField = new JTextField();
		publisherField.setColumns(10);
		publisherField.setBounds(90, START_+INTERVAL_*3, 280, 30);
		getContentPane().add(publisherField);
		
		JLabel categoryLabel = new JLabel("분 류 :");
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
		numberField.setText(Integer.toString(Book.getSeedId()+1));
		nameField.setText("");
		authorField.setText("");
		publisherField.setText("");
		categoryComboBox.setSelectedIndex(0);
	}
	
	public void setUpdateField(Book book) {
		numberField.setText(Integer.toString(book.getId()));
		nameField.setText(book.getName());
		authorField.setText(book.getAuthor());
		publisherField.setText(book.getPublisher());
		categoryComboBox.setSelectedItem(book.getCategory());
	}
	
	public void insertButton() {
		if(!MethodManager.getInstance().isPossibleTextField(nameField, EBookAttribute.NAME)
			||!MethodManager.getInstance().isPossibleTextField(authorField, EBookAttribute.AUTHOR)
			||!MethodManager.getInstance().isPossibleTextField(publisherField, EBookAttribute.PUBLISHER)){
			MethodManager.getInstance().somethingWrong(this);
			//수정사항
			}
				
		Book book = new Book(nameField.getText(), authorField.getText(),
				publisherField.getText(), (ECategory)categoryComboBox.getSelectedItem());
		
		BookManager bm = BookManager.getInstance();
		bm.insertBook(book);
		JOptionPane.showMessageDialog(this, "추가 되었습니다."
				,"알림", JOptionPane.INFORMATION_MESSAGE);
		//this.setVisible(false);
		clearField();	
		parentPanel.validateTable();
	}
	
	public void updateButton() {
		
	}




}