package xyz.acacian.managers;

import javax.swing.JTabbedPane;

import xyz.acacian.database.MemberDAO;
import xyz.acacian.database.MemberDTO;
import xyz.acacian.enums.ELevel;
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
	private JMemberManagePane memberManagePane;
	

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
		return memberManagePane;
	}

	public void setMemberMangePane(JMemberManagePane memberMangePane) {
		this.memberManagePane = memberMangePane;
	}

	public void Login(String id, String pw) {
		if(null != member) {
			//이미 로그인 되었음
			return;
		}
		var list = MemberDAO.getDAO().selectMemberList(id, EMemberAttribute.ID);
		if(list.isEmpty()) {
			// db에 데이터없음. 추후처리	
			return;
		}
		MemberDTO member = list.get(0);
		if(!member.getPw().equals(pw)) {
			// 비밀번호 틀림처리
			return;
		}
		
		this.member = member;
		viewLevelTabb();
		viewLevelBook();
		viewLevelMember();
	}
	
	public void viewLevelTabb() {
		if(null == member) {
			tabbPane.setEnabledAt(0, true);
			tabbPane.setEnabledAt(1, false);
			tabbPane.setEnabledAt(2, false);
			return;
		}
		
		switch(ELevel.getParse(member.getId_level())) {
		case ADMIN:			
		case LIBRARIAN:
			tabbPane.setEnabledAt(0, true);
			tabbPane.setEnabledAt(1, true);
			tabbPane.setEnabledAt(2, true);
			break;
		case MEMBER:
			tabbPane.setEnabledAt(0, true);
			tabbPane.setEnabledAt(1, true);
			tabbPane.setEnabledAt(2, false);
			break;
		}		
	}
	
	public void viewLevelBook() {		
		switch(ELevel.getParse(member.getId_level())) {
		case ADMIN:			
		case LIBRARIAN:
			bookManagePane.viewLevelButton(true);
			break;
		case MEMBER:
			bookManagePane.viewLevelButton(false);
			break;
		}		
	}
	
	public void viewLevelMember() {
		switch(ELevel.getParse(member.getId_level())) {
		case ADMIN:		
			memberManagePane.viewLevelButton(true);
			break;
		case LIBRARIAN:
			memberManagePane.viewLevelButton(false);
			break;
		default:
			break;
		}		
	}
		
}
