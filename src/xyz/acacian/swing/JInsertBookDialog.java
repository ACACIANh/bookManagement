package xyz.acacian.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
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

public class JInsertBookDialog extends JDialog implements ActionListener{
	private static final long serialVersionUID = 1L;

	private JTextField nameField;
	private JTextField authorField;
	private JTextField publisherField;
	private JTextField categoryField;
	
	private JBookManagePane parentPanel;
	
	public JInsertBookDialog(JBookManagePane parent) {
		this.parentPanel = parent;
		
		setTitle("도서 추가");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 400, 400);
		setResizable(false);
		getContentPane().setLayout(null);
		
		JLabel nameLabel = new JLabel("제 목 :");
		nameLabel.setBounds(12, 40, 85, 30);
		nameLabel.setHorizontalAlignment(JLabel.RIGHT);
		getContentPane().add(nameLabel);
		
		nameField = new JTextField();
		nameField.setBounds(102, 40, 280, 30);
		getContentPane().add(nameField);
		nameField.setColumns(10);
		
		JLabel authorLabel = new JLabel("저 자 :");
		authorLabel.setBounds(12, 80, 85, 30);
		authorLabel.setHorizontalAlignment(JLabel.RIGHT);
		getContentPane().add(authorLabel);
		
		authorField = new JTextField();
		authorField.setColumns(10);
		authorField.setBounds(102, 80, 280, 30);
		getContentPane().add(authorField);	
		
		JLabel publisherLabel = new JLabel("출판사 :");
		publisherLabel.setBounds(12, 120, 85, 30);
		publisherLabel.setHorizontalAlignment(JLabel.RIGHT);
		getContentPane().add(publisherLabel);
		
		publisherField = new JTextField();
		publisherField.setColumns(10);
		publisherField.setBounds(102, 120, 280, 30);
		getContentPane().add(publisherField);
		
		JLabel categoryLabel = new JLabel("분 류 :");
		categoryLabel.setBounds(12, 160, 85, 30);
		categoryLabel.setHorizontalAlignment(JLabel.RIGHT);
		getContentPane().add(categoryLabel);
		
		categoryField = new JTextField();
		categoryField .setColumns(10);
		categoryField .setBounds(102, 160, 280, 30);
		getContentPane().add(categoryField);
		
		JButton insertButton = new JButton("도서 추가");
		insertButton.setBounds(12, 290, 370, 73);
		getContentPane().add(insertButton);
		insertButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(!MethodManager.getInstance().isPossibleTextField(nameField, EBookAttribute.NAME)
		||!MethodManager.getInstance().isPossibleTextField(authorField, EBookAttribute.AUTHOR)
		||!MethodManager.getInstance().isPossibleTextField(publisherField, EBookAttribute.PUBLISHER)){
			MethodManager.getInstance().somethingWrong(this);
		}
		
		ECategory category = ECategory.GENERAL_WORCKS;
//		Book book = Book.Builder().
//		name(nameField.getText()).
//		author(authorField.getText()).
//		publisher(publisherField.getText()).
//		category(category).build();
		Book book = new Book(nameField.getText(), authorField.getText(),
				publisherField.getText(), category);
		
		BookManager bm = BookManager.getInstance();
		bm.insertBook(book);
		JOptionPane.showMessageDialog(this, "추가 되었습니다."
				,"알림", JOptionPane.INFORMATION_MESSAGE);
		//this.setVisible(false);
		clearField();
		parentPanel.validateTable();
	}
	
	private void clearField() {
		nameField.setText("");
		authorField.setText("");
		publisherField.setText("");
		categoryField.setText("");
	}

}