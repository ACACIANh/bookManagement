package xyz.itwill.bookmanagement;

import javax.swing.JDialog;
import javax.swing.JFrame;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;

public class JInsertBookDialog extends JDialog{
	private static final long serialVersionUID = 1L;

	private JTextField nameField;
	private JTextField authorField;
	private JTextField publisherField;
	
	public JInsertBookDialog() {
		setTitle("���� ���� ���α׷�");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 400);
		setResizable(false);
		getContentPane().setLayout(null);
		
		JLabel nameLabel = new JLabel("�� �� :");
		nameLabel.setBounds(12, 40, 85, 30);
		nameLabel.setHorizontalAlignment(JLabel.EAST);
		getContentPane().add(nameLabel);
		
		nameField = new JTextField();
		nameField.setBounds(102, 40, 280, 30);
		getContentPane().add(nameField);
		nameField.setColumns(10);
		
		JLabel authorLabel = new JLabel("�� �� :");
		authorLabel.setBounds(12, 80, 85, 30);
		authorLabel.setHorizontalAlignment(JLabel.EAST);
		getContentPane().add(authorLabel);
		
		authorField = new JTextField();
		authorField.setColumns(10);
		authorField.setBounds(102, 80, 280, 30);
		getContentPane().add(authorField);	
		
		JLabel publisherLabel = new JLabel("���ǻ� :");
		publisherLabel.setBounds(12, 120, 85, 30);
		publisherLabel.setHorizontalAlignment(JLabel.EAST);
		getContentPane().add(publisherLabel);
		
		publisherField = new JTextField();
		publisherField.setColumns(10);
		publisherField.setBounds(102, 120, 280, 30);
		getContentPane().add(publisherField);
		
		
		JButton insertButton = new JButton("���� �߰�");
		insertButton.setBounds(12, 290, 370, 73);
		getContentPane().add(insertButton);
	}

}