package xyz.itwill.bookmanagement;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.GridLayout;


public class JBookManagePane extends JPanel{
	private static final long serialVersionUID = 1L;

	//Main
	JPanel[] mainPanel = null;
	BorderLayout mainBorderLayout = null;
	
	//LeftSide
	JButton[] bookButton = null;	
	BorderLayout leftBorderLayout = null;	
	JPanel leftBottomPanel = null;
	
	//BottomSide
	
	
	//CenterSide
	JTable table = null;
	JScrollPane centerScroll = null;
	
	public JBookManagePane() {
		mainBorderLayout = new BorderLayout();
		setLayout(mainBorderLayout);
		
		mainPanel = new JPanel[EBdLayout.values().length];
		for(int i=0;i<EBdLayout.values().length; ++i) {
			mainPanel[i] = new JPanel();
		}
		
		//for문 안에 넣고 싶어요..
		add(mainPanel[EBdLayout.NORTH.getValue()], BorderLayout.NORTH);
		add(mainPanel[EBdLayout.SOUTH.getValue()], BorderLayout.SOUTH);
		add(mainPanel[EBdLayout.EAST.getValue()], BorderLayout.EAST);
		add(mainPanel[EBdLayout.WEST.getValue()], BorderLayout.WEST);
		add(mainPanel[EBdLayout.CENTER.getValue()], BorderLayout.CENTER);

		///////////////////////////
		//LeftSidePanel Constructor
		///////////////////////////
		
		//add(mainPanel[EBdLayout.WEST.getValue()], BorderLayout.WEST);
		leftBorderLayout = new BorderLayout();
		mainPanel[EBdLayout.WEST.getValue()].setLayout(leftBorderLayout);
		
		leftBottomPanel = new JPanel();
		mainPanel[EBdLayout.WEST.getValue()].add(leftBottomPanel, BorderLayout.SOUTH);
		leftBottomPanel.setLayout(new GridLayout(5, 0, 0, 0));
		//leftBottomPanel.setLayout(new GridLayout(ECrudButton.values().length, 0, 0, 0));
		
		bookButton  = new JButton[ECrudButton.values().length];
		
		bookButton[ECrudButton.INSERT.getValue()] = new JButton("도서 추가");
		leftBottomPanel.add(bookButton[ECrudButton.INSERT.getValue()]);
		
		bookButton[ECrudButton.UPDATE.getValue()] = new JButton("도서 수정");
		leftBottomPanel.add(bookButton[ECrudButton.UPDATE.getValue()]);
		
		bookButton[ECrudButton.REMOVE.getValue()] = new JButton("도서 삭제");
		leftBottomPanel.add(bookButton[ECrudButton.REMOVE.getValue()]);
		
		///////////////////////////
		//BottomSidePanel Constructor
		///////////////////////////
	
		///////////////////////////
		//CenterSidePanel Constructor
		///////////////////////////
		
		String[] columnNames = { "도서정보", "임시" };

		String[][] rowData = {{ "test", "dd",}, { "test2", "dd2",}};
		DefaultTableModel tableModel = new DefaultTableModel(rowData, columnNames);
		table = new JTable(tableModel);		//열과 행 넣어주기
		centerScroll = new JScrollPane(table);
		add(centerScroll, BorderLayout.CENTER);
		
		/////
		JButton btnNewButton = new JButton("New button");
		mainPanel[EBdLayout.SOUTH.getValue()].add(btnNewButton);
		
		JPanel panel_2 = new JPanel();
		add(panel_2, BorderLayout.CENTER);
	
		
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
