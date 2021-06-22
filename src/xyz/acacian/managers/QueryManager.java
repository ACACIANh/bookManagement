package xyz.acacian.managers;

public enum QueryManager {
	INSTANCE;
	
	private QueryManager() {	
	}
	
	public QueryManager getInstance() {
		return INSTANCE;
	}
	
	public static final String bookFromMemberLoanSelect 
	=  "select num, name, author, publisher, category, "
	+ "(select member_num from loanmanager where book_num = bookmanager.num) as mem_num ";
	
	public static final String bookFromMemberLoan 
	=  bookFromMemberLoanSelect + " from bookmanager order by num";

	public static final String memberFromMemberLoan = "";
	
}
