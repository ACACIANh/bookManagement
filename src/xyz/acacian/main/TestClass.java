package xyz.acacian.main;

import xyz.acacian.managers.LoginManager;
//import xyz.acacian.enums.ECategory;
//import xyz.acacian.managers.BookManager;
//import xyz.acacian.objects.Book;
import xyz.acacian.swing.JManagementSwing;

//import java.awt.BorderLayout;

public class TestClass {
	public static void main(String[] args) {

//		BookManager bm = BookManager.getInstance();
//				
//		Book book0 = new Book("첫번째책", "첫번째저자", "첫번째출판사", ECategory.ARTS);
//		Book book1 = new Book("Java의정석", "남궁성", "도우출판", ECategory.GENERAL_WORCKS);
//		Book book2 = new Book("그리고 아무도 없었다", "애거사 크리스티", "해문출판사", ECategory.HISTORY);
//		Book book3 = new Book("토비의 스프링", "이일민", "에이콘출판사", ECategory.NATURAL_SCIENCES);
//		Book book4 = new Book("토비의 스프링", "이일민", "에이콘출판사", ECategory.NATURAL_SCIENCES);
//		
//
//		bm.insertBook(book0);
//		bm.insertBook(book1);
//		bm.insertBook(book2);
//		bm.insertBook(book3);
//		bm.insertBook(book4);
//		for(int i=0; i<50; ++i) {
//			//bm.insertBook(new Book("토비의 스프링", "이일민", "에이콘출판사", ECategory.NATURAL_SCIENCES));
//		}
//		
//
////		System.out.println("================");
////		
////		bm.showBook(bm.searchBook("토비의 스프링"));
//		bm.showAll();
		
		JManagementSwing ms = new JManagementSwing();
		ms.setVisible(true);

		LoginManager.getInstance().Login("gkehdrn", "gkehdrn");
		//LoginManager.getInstance().Login("gkehdrn2", "gkehdrn2");
		//LoginManager.getInstance().Login("test", "test");
		//LoginManager.getInstance().viewLevel();
		//ms.getContentPane().add(new JInsertBookDialog(), );
		
	}

}
