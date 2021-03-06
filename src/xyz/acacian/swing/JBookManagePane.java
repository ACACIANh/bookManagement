package xyz.acacian.swing;

import xyz.acacian.enums.ECrudButton;
import xyz.acacian.enums.ELevel;
import xyz.acacian.database.BookDAO;
import xyz.acacian.database.BookDTO;
import xyz.acacian.database.MemberDAO;
import xyz.acacian.database.MemberDTO;
import xyz.acacian.enums.EBdLayout;
import xyz.acacian.enums.EBookAttribute;
import xyz.acacian.enums.ECategory;
import xyz.acacian.managers.BookManager;
import xyz.acacian.managers.LoginManager;
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
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextField;
import java.util.List;
import java.util.Vector;

import javax.swing.JComboBox;


public class JBookManagePane extends JPanel{
	private static final long serialVersionUID = 1L;
	
	//Main
	private JPanel[] mainPanel = null;
	
	//LeftSide
	private JButton[] bookButton = null;	
	private JPanel leftBottomPanel = null;
	
	private JDialog insertUpdateDialog = null;
	
	//BottomSide
	private EBookAttribute searchAttribute = EBookAttribute.NAME;
	private JComboBox searchComboBox = null;
	private TextField searchTextField = null;
	private JButton searchAllButton = null; 
	private JButton searchButton = null; 
//	private JButton cancleButton = null; 
	
	//CenterSide
	private JScrollPane centerScroll = null;
	private JTable table = null;
	private DefaultTableModel tableModel = null;
	//private int selectRowIndex = -1;
	
	private JLabel idLabel = null;
	
	public JBookManagePane() {
		firstStart();
		
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
		
		insertUpdateDialog = new JInsertUpdateBookDialog(this);
		
		bookButton = new JButton[ECrudButton.size()];
		
		bookButton[ECrudButton.INSERT.getValue()] = new JButton("INSERT");
		leftBottomPanel.add(bookButton[ECrudButton.INSERT.getValue()]);
		bookButton[ECrudButton.INSERT.getValue()].addActionListener(
				e -> insertButton());
		
		bookButton[ECrudButton.UPDATE.getValue()] = new JButton("UPDATE");
		leftBottomPanel.add(bookButton[ECrudButton.UPDATE.getValue()]);
		bookButton[ECrudButton.UPDATE.getValue()].addActionListener(
				e -> updateButton());
		
		bookButton[ECrudButton.REMOVE.getValue()] = new JButton("DELETE");
		leftBottomPanel.add(bookButton[ECrudButton.REMOVE.getValue()]);
		bookButton[ECrudButton.REMOVE.getValue()].addActionListener(
				e -> deleteColumn());
		
		///////////////////////////
		//BottomSidePanel Constructor
		///////////////////////////
		
		mainPanel[EBdLayout.SOUTH.getValue()].setLayout(new FlowLayout());
		
		searchAllButton = new JButton("SearchAll");
		mainPanel[EBdLayout.SOUTH.getValue()].add(searchAllButton);
		searchAllButton.addActionListener(
				e-> displayAllBook());
		
		searchComboBox = new JComboBox(EBookAttribute.values());
		searchComboBox.setSelectedItem(searchAttribute);
		searchComboBox.addActionListener( 
				e -> { JComboBox box = (JComboBox)e.getSource();
						searchAttribute = (EBookAttribute)box.getSelectedItem(); });		
		mainPanel[EBdLayout.SOUTH.getValue()].add(searchComboBox);
		
		searchTextField = new TextField();
		searchTextField.setFont(new Font("????", Font.BOLD, 15));
		searchTextField.setColumns(30);
		searchTextField.addActionListener(
				e->searchBook());
		mainPanel[EBdLayout.SOUTH.getValue()].add(searchTextField);
		

		
		searchButton = new JButton("Search");
		searchButton.addActionListener(
				e->searchBook());
		mainPanel[EBdLayout.SOUTH.getValue()].add(searchButton);
		
//		cancleButton = new JButton("Cancle");
//		mainPanel[EBdLayout.SOUTH.getValue()].add(cancleButton);
	
		///////////////////////////
		//CenterSidePanel Constructor
		///////////////////////////
		
		// ?? ????
		String[] columnNames = Book.expressAttribute;
		//String[][] rowData = BookManager.getInstance().getString2Dimension();
		tableModel = new DefaultTableModel(columnNames, 0);
		table = new JTable(tableModel){
			   @Override
			    public boolean isCellEditable(int row, int column) {
			        return false;
			    }
		};	
		table.getTableHeader().setReorderingAllowed(false);
//		table.getSelectionModel().addListSelectionListener(
//			e -> {if(!e.getValueIsAdjusting())
//						setLowIndex(table.getSelectedRow());});
		
		displayAllBook();
		
		centerScroll = new JScrollPane(table);
		add(centerScroll, BorderLayout.CENTER);
		
		///////////////////////////
		//TopSidePanel Constructor
		///////////////////////////
		idLabel = new JLabel("?????? ??");
		add(idLabel, BorderLayout.NORTH);
		
		///////////////////////////
		//RightSidePanel Constructor
		///////////////////////////
		
		//add(new JLabel("?????? ??"), BorderLayout.EAST);
		
	}

	private void insertButton() {
		insertUpdateDialog.setVisible(true); 
		((JInsertUpdateBookDialog)insertUpdateDialog).radioInsertSelect(true); 
		((JInsertUpdateBookDialog)insertUpdateDialog).clearField();
	}
	
	private void updateButton() {
		if(UtilManager.OUT_OF_INDEX == table.getSelectedRow()) {
			MethodManager.getInstance().notSelectColumn(this);
			return;
		}		
		insertUpdateDialog.setVisible(true); 
		((JInsertUpdateBookDialog)insertUpdateDialog).radioInsertSelect(false);	
//		int id = Integer.parseInt(
//				table.getValueAt(table.getSelectedRow(), 0).toString());
//		((JInsertUpdateBookDialog)insertUpdateDialog).setUpdateField(
//				BookManager.getInstance().searchBook(id));
		((JInsertUpdateBookDialog)insertUpdateDialog)
									.setUpdateField(getSelectBook());
	}
	
	private void deleteColumn() {
		if(UtilManager.OUT_OF_INDEX == table.getSelectedRow()) {
			MethodManager.getInstance().notSelectColumn(this);
			return;
		}
		insertUpdateDialog.setVisible(false); 
		int num = Integer.parseInt(
				table.getValueAt(table.getSelectedRow(), 0).toString());		
		BookDAO.getDAO().deleteBook(num);
		JOptionPane.showMessageDialog(this, "???? ??????????.", "????", JOptionPane.INFORMATION_MESSAGE);
		displayAllBook();
	}
	
	public void displayAllBook() {
		List<BookDTO> bookList = BookDAO.getDAO().selectAllBookList();
		if(bookList.isEmpty()) {
			JOptionPane.showMessageDialog(this, "?????? ???? ????????.");
			return;
		}
		
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		for(int i=model.getRowCount(); i>0; --i) {
			model.removeRow(0);
		}
		for(BookDTO book:bookList) {
			Vector<Object> rowData = new Vector<Object>();
			rowData.add(book.getNum());
			rowData.add(book.getName());
			rowData.add(book.getAuthor());
			rowData.add(book.getPublisher());
			rowData.add(book.getCategory());
			rowData.add(book.getLoan_member());
			model.addRow(rowData);
		}
	}
	
	public BookDTO getSelectBook() {
		int index = table.getSelectedRow();	
		BookDTO returnBook = new BookDTO();
		returnBook.setNum(
		Integer.parseInt(table.getValueAt(index, EBookAttribute.NUM.getValue()).toString()));
		returnBook.setName(table.getValueAt(index, EBookAttribute.NAME.getValue()).toString());
		returnBook.setAuthor(table.getValueAt(index, EBookAttribute.AUTHOR.getValue()).toString());
		returnBook.setPublisher(table.getValueAt(index, EBookAttribute.PUBLISHER.getValue()).toString());
		returnBook.setCategory(table.getValueAt(index, EBookAttribute.CATEGORY.getValue()).toString());
        return returnBook;
	}
	
	private void searchBook() {
		List<BookDTO> bookList = BookDAO.getDAO().selectBookList(searchTextField.getText(), searchAttribute);
		displayBooks(bookList);
	}
	
	private void displayBooks(List<BookDTO> bookList) {
		if(bookList.isEmpty()) {
			JOptionPane.showMessageDialog(this, "???? ???? ????????.");
			return;
		}		
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		for(int i=model.getRowCount(); i>0; --i) {
			model.removeRow(0);
		}
		for(BookDTO book:bookList) {
			Vector<Object> rowData = new Vector<Object>();
			rowData.add(book.getNum());
			rowData.add(book.getName());
			rowData.add(book.getAuthor());
			rowData.add(book.getPublisher());
			rowData.add(book.getCategory());
			model.addRow(rowData);
		}
	}
	
	public void viewLevelButton(boolean on) {
		for(int i=0; i<ECrudButton.size(); ++i) {
			bookButton[i].setEnabled(on);
		}
	}

	private void firstStart() {
		if(!BookDAO.getDAO().selectAllBookList().isEmpty()) {
			return;
		}

		BookDTO[] books = {
				new BookDTO(0,"????1","????1","??????1", ECategory.GENERAL_WORCKS.getValue()),
				new BookDTO(0,"JAVA?? ????", "??????", "????????", ECategory.LANGUAGE.getValue()),
				new BookDTO(0,"?????? ?????? ??????", "?????? ????????", "??????????", ECategory.LITERATURE.getValue()),
				new BookDTO(0,"?????? ??????", "??????", "????????????", ECategory.NATURAL_SCIENCES.getValue()),				
				new BookDTO(0,"????2","????3","??????5", ECategory.GENERAL_WORCKS.getValue()),
		};
				
		for(var book: books) {
			BookDAO.getDAO().insertBook(book);
		}
		
		makeDummyData(20);

		JOptionPane.showMessageDialog(this, "???? ???????? ?? ???????? ??????????????.", "????", JOptionPane.INFORMATION_MESSAGE);	
	}
	
	public void makeDummyData(int size) {
		for(int i=0; i< size; ++i) {
			BookDAO.getDAO().insertBook(
				new BookDTO(0,
						"???? ???? " + i,
						"???? ???? " +i*2,
						"???? ?????? "+i*3, 
						ECategory.values()[i%ECategory.values().length].getValue())
				);
			}	
	}
	
	public void validateLogin() {
		idLabel.setText(
				"???? ???????? ID : " + LoginManager.getInstance().getId() +
				"  ???? : " + ELevel.getParse(LoginManager.getInstance().getLevelInt()) );	
	}

}
