package xyz.itwill.bookmanagement;

//import java.awt.BorderLayout;

public class TestClass {
	public static void main(String[] args) {

		BookManagement bm = BookManagement.getInstance();
		
		Book book0 = new Book(100, "첫번째책", "첫번째저자", "첫번째출판사");
		Book book1 = new Book(800, "Java의정석", "남궁성", "도우출판");
		Book book2 = new Book(400, "그리고 아무도 없었다", "애거사 크리스티", "해문출판사");
		Book book3 = new Book(200, "토비의 스프링", "이일민", "에이콘출판사");
		Book book4 = new Book(200, "토비의 스프링", "이일민", "에이콘출판사");
		

		bm.insertBook(book0);
		bm.insertBook(book1);
		bm.insertBook(book2);
		bm.insertBook(book3);
//		bm.insertBook(new Book(200, "토비의 스프링", "이일민", "에이콘출판사"));
//		bm.insertBook(book3); // 한번 넣은 데이터 재시도
		bm.insertBook(book4); // 속성은 같지만 고유 ID로 비교하므로 다른객체판단
		for(int i=0; i<50; ++i) {
			//bm.insertBook(new Book(400, "그리고 아무도 없었다", "애거사 크리스티", "해문출판사"));
		}
		

//		System.out.println("================");
//		
//		bm.showBook(bm.searchBook("토비의 스프링"));
//		bm.showAll();
		
		JManagementSwing ms = new JManagementSwing();
		ms.setVisible(true);
		//ms.getContentPane().add(new JInsertBookDialog(), );
		
	}

}
