package xyz.acacian.swing;

import xyz.acacian.enums.ECrudButton;
import xyz.acacian.enums.EBdLayout;
import xyz.acacian.enums.EBookAttribute;
import xyz.acacian.managers.BookManager;
import xyz.acacian.managers.MethodManager;
import xyz.acacian.managers.UtilManager;
import xyz.acacian.objects.Book;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextField;


public class JBookManagePane extends JPanel{
	private static final long serialVersionUID = 1L;
	
	//Main
	private JPanel[] mainPanel = null;
	
	//LeftSide
	private JButton[] bookButton = null;	
	private JPanel leftBottomPanel = null;
	
	private JDialog insertDialog = null;
	
	//BottomSide
	private TextField searchTextField = null;
	private JButton searchButton = null; 
	private JButton cancleButton = null; 
	
	//CenterSide
	private JScrollPane centerScroll = null;
	private JTable table = null;
	private DefaultTableModel tableModel = null;
	private int selectRowIndex = -1;
	
	public JBookManagePane() {
		setLayout(new BorderLayout());
		
		mainPanel = new JPanel[EBdLayout.size()];
		for(int i=0;i<EBdLayout.size(); ++i) {
			mainPanel[i] = new JPanel();
			add(mainPanel[i], EBdLayout.getTransStr(i));
		}
		
		///////////////////////////
		//LeftSidePanel Constructor
		///////////////////////////
		
		mainPanel[EBdLayout.WEST.getValue()].setLayout(new BorderLayout());
		
		leftBottomPanel = new JPanel();
		mainPanel[EBdLayout.WEST.getValue()].add(leftBottomPanel, BorderLayout.SOUTH);
		leftBottomPanel.setLayout(new GridLayout(5, 0, 5, 5));
		//leftBottomPanel.setLayout(new GridLayout(ECrudButton.values().length, 0, 0, 0));
		
		insertDialog = new JInsertBookDialog(this);

		bookButton = new JButton[ECrudButton.size()];
		
		bookButton[ECrudButton.INSERT.getValue()] = new JButton("도서 추가");
		leftBottomPanel.add(bookButton[ECrudButton.INSERT.getValue()]);
		bookButton[ECrudButton.INSERT.getValue()].addActionListener(
				e -> insertDialog.setVisible(true));
		
		bookButton[ECrudButton.UPDATE.getValue()] = new JButton("도서 수정");
		leftBottomPanel.add(bookButton[ECrudButton.UPDATE.getValue()]);
		
		bookButton[ECrudButton.REMOVE.getValue()] = new JButton("도서 삭제");
		leftBottomPanel.add(bookButton[ECrudButton.REMOVE.getValue()]);
		bookButton[ECrudButton.REMOVE.getValue()].addActionListener(
				e -> deleteColumn());
		
		///////////////////////////
		//BottomSidePanel Constructor
		///////////////////////////
		
		mainPanel[EBdLayout.SOUTH.getValue()].setLayout(new FlowLayout());
		searchTextField = new TextField();
		searchTextField.setFont(new Font("고딕", Font.BOLD, 15));
		searchTextField.setColumns(40);
		mainPanel[EBdLayout.SOUTH.getValue()].add(searchTextField);
		
		searchButton = new JButton("검색");
		mainPanel[EBdLayout.SOUTH.getValue()].add(searchButton);
		
		cancleButton = new JButton("취소");
		mainPanel[EBdLayout.SOUTH.getValue()].add(cancleButton);
	
		///////////////////////////
		//CenterSidePanel Constructor
		///////////////////////////
		
		String[] columnNames = Book.expressAttribute;
		String[][] rowData = BookManager.getInstance().getString2Dimension();
		tableModel = new DefaultTableModel(rowData, columnNames);
		table = new JTable(tableModel);	
		table.getSelectionModel().addListSelectionListener(
			//모든 클릭 이벤트 발생.. 개선할것
			e -> setLowIndex(table.getSelectedRow()));
		

		centerScroll = new JScrollPane(table);
		add(centerScroll, BorderLayout.CENTER);
		
		///////////////////////////
		//TopSidePanel Constructor
		///////////////////////////

		add(new JLabel("여백의 미"), BorderLayout.NORTH);
		
		///////////////////////////
		//RightSidePanel Constructor
		///////////////////////////
		
		add(new JLabel("여백의 미"), BorderLayout.EAST);
		
		//createTemp();
	}
	
	private void createTemp() {
		//temp
		JPanel panel_left = new JPanel();
		add(panel_left, BorderLayout.WEST);	
		JLabel newlabel_left = new JLabel("left side");
		panel_left.add(newlabel_left);
		
		JPanel panel_top = new JPanel();
		add(panel_top, BorderLayout.NORTH);	
		JLabel newlabel_top = new JLabel("top side");
		panel_top.add(newlabel_top);
		
		JPanel panel_right = new JPanel();
		add(panel_right, BorderLayout.EAST);
		JLabel newlabel_right = new JLabel("right side");
		panel_right.add(newlabel_right);
		
		JPanel panel_bottom = new JPanel();
		add(panel_bottom, BorderLayout.SOUTH);	
		
		TextField textField_bottom = new TextField();
		textField_bottom.setFont(new Font("고딕", Font.BOLD, 18));
		textField_bottom.setColumns(20);
		panel_bottom.add(textField_bottom);
		
		JLabel newlabel_bottom = new JLabel("bottom side");
		panel_bottom.add(newlabel_bottom);
	}
	
	public void validateTable() {
		//테이블만 바꾸게 개선해보자
		String[] columnNames = Book.expressAttribute;
		String[][] rowData = BookManager.getInstance().getString2Dimension();
		tableModel = new DefaultTableModel(rowData, columnNames);	

		int index = selectRowIndex;
		table.setModel(tableModel);
		if(tableModel.getRowCount() <= index) {
			--index;
		}
		if(UtilManager.OUT_OF_INDEX != index) {
			table.setRowSelectionInterval(index, index);	
		}
		table.validate();
	}
	
	private void deleteColumn() {
		if(UtilManager.OUT_OF_INDEX == selectRowIndex) {
			MethodManager.getInstance().somethingWrong(this);
			return;
		}
		String categoryName = (String)table.getValueAt(selectRowIndex, 0);
		BookManager.getInstance().removeBookCategoryName(categoryName);
		validateTable();
		//삭제하고 인덱스 초기화
	}
	
	public void setLowIndex(int index) {
		//두번씩 호출됨 고칠것.
		selectRowIndex = index;
		System.out.println("클릭된 인덱스 = " + index);
	}
	

}
