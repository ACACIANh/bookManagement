package xyz.acacian.swing;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import xyz.acacian.enums.EBookAttribute;
import xyz.acacian.enums.ECategory;
import xyz.acacian.managers.BookManager;
import xyz.acacian.managers.MethodManager;
import xyz.acacian.objects.Book;
import javax.swing.JRadioButton;

public class JInsertUpdateBookDialog extends JDialog implements ActionListener{
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
		
		JButton insertButton = new JButton("INSERT / UPDATE");
		insertButton.setBounds(12, START_+INTERVAL_*5, 370, 73);
		getContentPane().add(insertButton);
		insertButton.addActionListener(this);
		
		
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
		JOptionPane.showMessageDialog(this, "�߰� �Ǿ����ϴ�."
				,"�˸�", JOptionPane.INFORMATION_MESSAGE);
		//this.setVisible(false);
		clearField();
		parentPanel.validateTable();
	}
	
	private void clearField() {
		nameField.setText("");
		authorField.setText("");
		publisherField.setText("");
		categoryComboBox.setSelectedIndex(0);;
	}
}