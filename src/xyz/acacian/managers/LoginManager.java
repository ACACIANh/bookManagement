package xyz.acacian.managers;

import javax.swing.JTabbedPane;

import xyz.acacian.database.MemberDAO;
import xyz.acacian.database.MemberDTO;
import xyz.acacian.enums.EMemberAttribute;
import xyz.acacian.swing.JBookManagePane;
import xyz.acacian.swing.JMemberManagePane;

public enum LoginManager {
	INSTANCE;

	public static LoginManager getInstance() {
		return INSTANCE;
	}

	private LoginManager() {	
	}
	
	private MemberDTO member = null;

	private JTabbedPane tabbPane;

	private JBookManagePane bookManagePane;
	private JMemberManagePane memberMangePane;
	

	public JTabbedPane getTabbPane() {
		return tabbPane;
	}

	public void setTabbPane(JTabbedPane tabbPane) {
		this.tabbPane = tabbPane;
	}
	
	public JBookManagePane getBookManagePane() {
		return bookManagePane;
	}

	public void setBookManagePane(JBookManagePane bookManagePane) {
		this.bookManagePane = bookManagePane;
	}

	public JMemberManagePane getMemberMangePane() {
		return memberMangePane;
	}

	public void setMemberMangePane(JMemberManagePane memberMangePane) {
		this.memberMangePane = memberMangePane;
	}

	public void Login(String id, String pw) {
		var list = MemberDAO.getDAO().selectMemberList(id, EMemberAttribute.ID);
		if(list.isEmpty()) {
			// db에 데이터없음. 추후처리	
			return;
		}
		
		MemberDTO member = list.get(0);
		if(member.getPw().equals(pw)) {
			this.member = member;
		}
		
		
		
	}
	
	
}
