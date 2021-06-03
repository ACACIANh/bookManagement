package xyz.itwill.bookmanagement;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextField;


public class JBookManagePane extends JPanel{
	private static final long serialVersionUID = 1L;
	
	//Main
	JPanel[] mainPanel = null;
	
	//LeftSide
	JButton[] bookButton = null;	
	JPanel leftBottomPanel = null;
	
	//BottomSide
	TextField searchTextField = null;
	JButton searchButton = null; 
	JButton cancleButton = null; 
	
	//CenterSide
	JTable table = null;
	JScrollPane centerScroll = null;
	
	public JBookManagePane() {
		setLayout(new BorderLayout());
		
		mainPanel = new JPanel[EBdLayout.values().length];
		for(int i=0;i<EBdLayout.values().length; ++i) {
			mainPanel[i] = new JPanel();
			add(mainPanel[i], EBdLayout.getTransStr(i));
		}
		
		///////////////////////////
		//LeftSidePanel Constructor
		///////////////////////////
		
		mainPanel[EBdLayout.WEST.getValue()].setLayout(new BorderLayout());
		
		leftBottomPanel = new JPanel();
		mainPanel[EBdLayout.WEST.getValue()].add(leftBottomPanel, BorderLayout.SOUTH);
		leftBottomPanel.setLayout(new GridLayout(5, 0, 0, 0));
		//leftBottomPanel.setLayout(new GridLayout(ECrudButton.values().length, 0, 0, 0));
		
		bookButton = new JButton[ECrudButton.values().length];
		
		bookButton[ECrudButton.INSERT.getValue()] = new JButton("���� �߰�");
		leftBottomPanel.add(bookButton[ECrudButton.INSERT.getValue()]);
		
		bookButton[ECrudButton.UPDATE.getValue()] = new JButton("���� ����");
		leftBottomPanel.add(bookButton[ECrudButton.UPDATE.getValue()]);
		
		bookButton[ECrudButton.REMOVE.getValue()] = new JButton("���� ����");
		leftBottomPanel.add(bookButton[ECrudButton.REMOVE.getValue()]);
		
		///////////////////////////
		//BottomSidePanel Constructor
		///////////////////////////
		
		searchTextField = new TextField();
		searchTextField.setFont(new Font("���", Font.BOLD, 18));
		searchTextField.setColumns(20);
		mainPanel[EBdLayout.SOUTH.getValue()].add(searchTextField);
		
		searchButton = new JButton("�˻�");
		mainPanel[EBdLayout.SOUTH.getValue()].add(searchButton);
		
		cancleButton = new JButton("���");
		mainPanel[EBdLayout.SOUTH.getValue()].add(cancleButton);
	
		///////////////////////////
		//CenterSidePanel Constructor
		///////////////////////////
		
		String[] columnNames = Book.expressAtrribute;
		String[][] rowData = BookManagement.getInstance().getString2Dimension();
		DefaultTableModel tableModel = new DefaultTableModel(rowData, columnNames);
		table = new JTable(tableModel);		//���� �� �־��ֱ�
		centerScroll = new JScrollPane(table);
		add(centerScroll, BorderLayout.CENTER);
		
		//createTemp();
	}
	
	void createTemp() {
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
		textField_bottom.setFont(new Font("���", Font.BOLD, 18));
		textField_bottom.setColumns(20);
		panel_bottom.add(textField_bottom);
		
		JLabel newlabel_bottom = new JLabel("bottom side");
		panel_bottom.add(newlabel_bottom);
	}
	
	
	

}
