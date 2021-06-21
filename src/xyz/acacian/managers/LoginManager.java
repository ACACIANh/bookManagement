package xyz.acacian.managers;

import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

import xyz.acacian.database.MemberDAO;
import xyz.acacian.database.MemberDTO;
import xyz.acacian.enums.ELevel;
import xyz.acacian.enums.EMemberAttribute;
import xyz.acacian.swing.JBookManagePane;
import xyz.acacian.swing.JLoginManagePane;
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

	private JLoginManagePane loginManagePane;
	private JBookManagePane bookManagePane;
	private JMemberManagePane memberManagePane;
	

	public JTabbedPane getTabbPane() {
		return tabbPane;
	}

	public void setTabbPane(JTabbedPane tabbPane) {
		this.tabbPane = tabbPane;
	}
	
	public JLoginManagePane getLoginManagePane() {
		return loginManagePane;
	}

	public void setLoginManagePane(JLoginManagePane loginManagePane) {
		this.loginManagePane = loginManagePane;
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
			JOptionPane.showMessageDialog(loginManagePane, "이미 로그인 되었습니다.");	
			return;
		}
		var list = MemberDAO.getDAO().selectMemberList(id, EMemberAttribute.ID);
		if(list.isEmpty()) {
			JOptionPane.showMessageDialog(loginManagePane, "ID가 없습니다.");	
			return;
		}
		MemberDTO member = list.get(0);
		if(!member.getPw().equals(pw)) {
			JOptionPane.showMessageDialog(loginManagePane, "PW가 틀립니다.");	
			return;
		}
		
		this.member = member;
		viewLevelTabb();
		viewLevelBook();
		viewLevelMember();
		JOptionPane.showMessageDialog(loginManagePane, "로그인 성공");	
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
