package xyz.itwill.bookmanagement;

public class TestClass {
	public static void main(String[] args) {
		BookManagement bm = new BookManagement();
		Book book0 = new Book(100, "첫번째책", "첫번째저자", "첫번째출판사");
		Book book1 = new Book(800, "Java의정석", "남궁성", "도우출판");
		Book book2 = new Book(400, "그리고 아무도 없었다", "애거사 크리스티", "해문출판사");
		Book book3 = new Book(200, "토비의 스프링", "이일민", "에이콘출판사");
			
		bm.insertBook(book0);
		bm.insertBook(book1);
		bm.insertBook(book2);
		bm.insertBook(book3);
		
		bm.showAll();
	}

}
