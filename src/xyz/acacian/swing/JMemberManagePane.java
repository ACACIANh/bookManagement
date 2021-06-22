package xyz.acacian.swing;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextField;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import xyz.acacian.database.MemberDAO;
import xyz.acacian.database.MemberDTO;
import xyz.acacian.enums.EBdLayout;
import xyz.acacian.enums.ECrudButton;
import xyz.acacian.enums.ELevel;
import xyz.acacian.enums.EMemberAttribute;
import xyz.acacian.managers.LoginManager;
import xyz.acacian.managers.MethodManager;
import xyz.acacian.managers.UtilManager;

public class JMemberManagePane extends JPanel{
private static final long serialVersionUID = 1L;
	
	//Main
	private JPanel[] mainPanel = null;
	
	//LeftSide
	private JButton[] memberButton = null;	
	private JPanel leftBottomPanel = null;
	
	private JDialog insertUpdateDialog = null;
	
	//BottomSide
	private EMemberAttribute searchAttribute = EMemberAttribute.NAME;
	private JComboBox searchComboBox = null;
	private TextField searchTextField = null;
	private JButton searchAllButton = null; 
	private JButton searchButton = null; 
//	private JButton cancleButton = null; 
	
	//CenterSide
	private JScrollPane centerScroll = null;
	private JTable table = null;
	private DefaultTableModel tableModel = null;

	public JMemberManagePane() {
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
		
		insertUpdateDialog = new JInsertUpdateMemberDialog(this);

		memberButton = new JButton[ECrudButton.size()];
		
		memberButton[ECrudButton.INSERT.getValue()] = new JButton("INSERT");
		leftBottomPanel.add(memberButton[ECrudButton.INSERT.getValue()]);
		memberButton[ECrudButton.INSERT.getValue()].addActionListener(
				e -> insertButton());
		
		memberButton[ECrudButton.UPDATE.getValue()] = new JButton("UPDATE");
		leftBottomPanel.add(memberButton[ECrudButton.UPDATE.getValue()]);
		memberButton[ECrudButton.UPDATE.getValue()].addActionListener(
				e -> updateButton());
		
		memberButton[ECrudButton.REMOVE.getValue()] = new JButton("DELETE");
		leftBottomPanel.add(memberButton[ECrudButton.REMOVE.getValue()]);
		memberButton[ECrudButton.REMOVE.getValue()].addActionListener(
				e -> deleteColumn());
		
		///////////////////////////
		//BottomSidePanel Constructor
		///////////////////////////
		
		mainPanel[EBdLayout.SOUTH.getValue()].setLayout(new FlowLayout());
		
		searchAllButton = new JButton("SearchAll");
		mainPanel[EBdLayout.SOUTH.getValue()].add(searchAllButton);
		searchAllButton.addActionListener(
				e-> displayAllMember());
		
		searchComboBox = new JComboBox(EMemberAttribute.values());
		searchComboBox.setSelectedItem(searchAttribute);
		searchComboBox.addActionListener( 
				e -> { JComboBox box = (JComboBox)e.getSource();
						searchAttribute = (EMemberAttribute)box.getSelectedItem(); });		
		mainPanel[EBdLayout.SOUTH.getValue()].add(searchComboBox);
		
		searchTextField = new TextField();
		searchTextField.setFont(new Font("고딕", Font.BOLD, 15));
		searchTextField.setColumns(30);
		searchTextField.addActionListener(
				e->searchMember());
		mainPanel[EBdLayout.SOUTH.getValue()].add(searchTextField);
		

		
		searchButton = new JButton("Search");
		searchButton.addActionListener(
				e->searchMember());
		mainPanel[EBdLayout.SOUTH.getValue()].add(searchButton);
		
//		cancleButton = new JButton("Cancle");
//		mainPanel[EBdLayout.SOUTH.getValue()].add(cancleButton);
	
		///////////////////////////
		//CenterSidePanel Constructor
		///////////////////////////
		
		String[] columnNames = MemberDTO.expressAttribute;
		tableModel = new DefaultTableModel(columnNames, 0);
		table = new JTable(tableModel){
			   @Override
			    public boolean isCellEditable(int row, int column) {
			        return false;
			    }
		};	
		table.getTableHeader().setReorderingAllowed(false);

		
		displayAllMember();
		
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
		
	}

	public void insertButton() {
		insertUpdateDialog.setVisible(true); 
		JInsertUpdateMemberDialog dialog = (JInsertUpdateMemberDialog)insertUpdateDialog; 
		dialog.radioInsertSelect(true); 
		dialog.clearField();
		dialog.setLevelsetEnabled(LoginManager.getInstance().isLogin());
	}
	
	private void updateButton() {
		if(UtilManager.OUT_OF_INDEX == table.getSelectedRow()) {
			MethodManager.getInstance().notSelectColumn(this);
			return;
		}		
		insertUpdateDialog.setVisible(true); 
		((JInsertUpdateMemberDialog)insertUpdateDialog).radioInsertSelect(false);	
		((JInsertUpdateMemberDialog)insertUpdateDialog)
									.setUpdateField(getSelectMemeberToUpdate());
	}
	
	private void deleteColumn() {
		if(UtilManager.OUT_OF_INDEX == table.getSelectedRow()) {
			MethodManager.getInstance().notSelectColumn(this);
			return;
		}
		insertUpdateDialog.setVisible(false); 
		int num = Integer.parseInt(
				table.getValueAt(table.getSelectedRow(), 0).toString());		
		MemberDAO.getDAO().deleteMember(num);
		JOptionPane.showMessageDialog(this, "삭제 되었습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
		displayAllMember();
	}
	
	public void displayAllMember() {
		List<MemberDTO> memberList = MemberDAO.getDAO().selectAllMemberList();
		if(memberList.isEmpty()) {
			JOptionPane.showMessageDialog(this, "저장된 책이 없습니다.");
			return;
		}
		
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		for(int i=model.getRowCount(); i>0; --i) {
			model.removeRow(0);
		}
		for(MemberDTO member:memberList) {
			Vector<Object> rowData = new Vector<Object>();
			rowData.add(member.getNum());
			rowData.add(ELevel.getStringStatic(member.getId_level()));
			rowData.add(member.getId());
			rowData.add("");
			rowData.add(member.getName());
			rowData.add(member.getPhone());
			rowData.add(member.getBirthday());
			
			model.addRow(rowData);
		}
	}
	
	public MemberDTO getSelectMember() {
		int index = table.getSelectedRow();	
		MemberDTO returnMember = new MemberDTO();
		returnMember.setNum(
		Integer.parseInt(table.getValueAt(index, EMemberAttribute.NUM.getValue()).toString()));

		returnMember.setId_level(
				Integer.parseInt(table.getValueAt(index, EMemberAttribute.ID_LEVEL.getValue()).toString().substring(0,1)));
		returnMember.setId(table.getValueAt(index, EMemberAttribute.ID.getValue()).toString());
		returnMember.setPw(table.getValueAt(index, EMemberAttribute.PW.getValue()).toString());
		returnMember.setName(table.getValueAt(index, EMemberAttribute.NAME.getValue()).toString());
		returnMember.setPhone(table.getValueAt(index, EMemberAttribute.PHONE.getValue()).toString());
		returnMember.setBirthday(table.getValueAt(index, EMemberAttribute.BIRTHDAY.getValue()).toString());
		
		return returnMember;
	}
	
	public MemberDTO getSelectMemeberToUpdate() {	
		String num = table.getValueAt(table.getSelectedRow(), EMemberAttribute.NUM.getValue()).toString();
		MemberDTO returnMember = MemberDAO.getDAO().selectMemberList(num, EMemberAttribute.NUM).get(0);
		return returnMember;
	}
	
	private void searchMember() {
		var memberList = MemberDAO.getDAO().selectMemberList(searchTextField.getText(), searchAttribute);
		displayMembers(memberList);
	}
	
	private void displayMembers(List<MemberDTO> memberList) {
		if(memberList.isEmpty()) {
			//JOptionPane.showMessageDialog(this, "저장된 책이 없습니다.");
			return;
		}		
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		for(int i=model.getRowCount(); i>0; --i) {
			model.removeRow(0);
		}
		for(MemberDTO member:memberList) {
			Vector<Object> rowData = new Vector<Object>();
			rowData.add(member.getNum());
			rowData.add(ELevel.getStringStatic(member.getId_level()));
			rowData.add(member.getId());
			rowData.add("");
			rowData.add(member.getName());
			rowData.add(member.getPhone());
			rowData.add(member.getBirthday());
			
			model.addRow(rowData);
		}
	}

	public void viewLevelButton(boolean on) {
		for(int i=0; i<ECrudButton.size(); ++i) {
			memberButton[i].setEnabled(on);
		}
		memberButton[ECrudButton.INSERT.getValue()].setEnabled(true);
	}

	
}
