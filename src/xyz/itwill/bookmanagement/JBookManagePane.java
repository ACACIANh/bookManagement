package xyz.itwill.bookmanagement;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.GridLayout;



public class JBookManagePane extends JPanel{
	private static final long serialVersionUID = 1L;
	JTable table;
	JButton insertBook, updateBook, removeBook;
	 
	BorderLayout mainBorderLayout = null;
	
	JPanel leftPanel = null;
	BorderLayout leftBorderLayout = null;
	
	JPanel leftBottomPanel = null;
	
	public JBookManagePane() {
		mainBorderLayout = new BorderLayout();
		setLayout(mainBorderLayout);
		
		leftPanel = new JPanel();
		add(leftPanel, BorderLayout.WEST);
		leftBorderLayout = new BorderLayout();
		leftPanel.setLayout(leftBorderLayout);
		
		leftBottomPanel = new JPanel();
		leftPanel.add(leftBottomPanel, BorderLayout.SOUTH);
		leftBottomPanel.setLayout(new GridLayout(5, 0, 0, 0));
		
		insertBook = new JButton("도서 추가");
		leftBottomPanel.add(insertBook);
		
		updateBook = new JButton("도서 수정");
		leftBottomPanel.add(updateBook);
		
		removeBook = new JButton("도서 삭제");
		leftBottomPanel.add(removeBook);
		
//		GridLayout leftBottomGridLayout = new GridLayout();
//		leftBottomPanel.setLayout(leftBottomGridLayout);
//		//leftBottomPanel.setLayout();
//		
//		insertBook = new JButton("도서 추가");
//		leftBottomPanel.add(insertBook, leftBottomGridLayout);
//
//		updateBook = new JButton("도서 수정");
//		leftBottomPanel.add(updateBook, leftBottomGridLayout);
//		
//		removeBook = new JButton("도서 삭제");
//		leftBottomPanel.add(removeBook, leftBottomGridLayout);

		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("New button");
		panel_1.add(btnNewButton);
		
		JPanel panel_2 = new JPanel();
		add(panel_2, BorderLayout.CENTER);
		
		table = new JTable();
		panel_2.add(table);
		
		JPanel panel_3 = new JPanel();
		add(panel_3, BorderLayout.NORTH);
		
		JButton btnNewButton_3 = new JButton("New button");
		panel_3.add(btnNewButton_3);
		
		JPanel panel_4 = new JPanel();
		add(panel_4, BorderLayout.EAST);
		
		JButton btnNewButton_2 = new JButton("New button");
		panel_4.add(btnNewButton_2);		
	}
	
	
	
	

}
