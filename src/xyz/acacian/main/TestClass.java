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
//		Book book0 = new Book("ù��°å", "ù��°����", "ù��°���ǻ�", ECategory.ARTS);
//		Book book1 = new Book("Java������", "���ü�", "��������", ECategory.GENERAL_WORCKS);
//		Book book2 = new Book("�׸��� �ƹ��� ������", "�ְŻ� ũ����Ƽ", "�ع����ǻ�", ECategory.HISTORY);
//		Book book3 = new Book("����� ������", "���Ϲ�", "���������ǻ�", ECategory.NATURAL_SCIENCES);
//		Book book4 = new Book("����� ������", "���Ϲ�", "���������ǻ�", ECategory.NATURAL_SCIENCES);
//		
//
//		bm.insertBook(book0);
//		bm.insertBook(book1);
//		bm.insertBook(book2);
//		bm.insertBook(book3);
//		bm.insertBook(book4);
//		for(int i=0; i<50; ++i) {
//			//bm.insertBook(new Book("����� ������", "���Ϲ�", "���������ǻ�", ECategory.NATURAL_SCIENCES));
//		}
//		
//
////		System.out.println("================");
////		
////		bm.showBook(bm.searchBook("����� ������"));
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
