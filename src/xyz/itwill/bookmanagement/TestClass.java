package xyz.itwill.bookmanagement;

//import java.awt.BorderLayout;

public class TestClass {
	public static void main(String[] args) {

		BookManagement bm = BookManagement.getInstance();
		
		Book book0 = new Book(100, "ù��°å", "ù��°����", "ù��°���ǻ�");
		Book book1 = new Book(800, "Java������", "���ü�", "��������");
		Book book2 = new Book(400, "�׸��� �ƹ��� ������", "�ְŻ� ũ����Ƽ", "�ع����ǻ�");
		Book book3 = new Book(200, "����� ������", "���Ϲ�", "���������ǻ�");
		Book book4 = new Book(200, "����� ������", "���Ϲ�", "���������ǻ�");
		

		bm.insertBook(book0);
		bm.insertBook(book1);
		bm.insertBook(book2);
		bm.insertBook(book3);
//		bm.insertBook(new Book(200, "����� ������", "���Ϲ�", "���������ǻ�"));
//		bm.insertBook(book3); // �ѹ� ���� ������ ��õ�
		bm.insertBook(book4); // �Ӽ��� ������ ���� ID�� ���ϹǷ� �ٸ���ü�Ǵ�
		for(int i=0; i<50; ++i) {
			//bm.insertBook(new Book(400, "�׸��� �ƹ��� ������", "�ְŻ� ũ����Ƽ", "�ع����ǻ�"));
		}
		

//		System.out.println("================");
//		
//		bm.showBook(bm.searchBook("����� ������"));
//		bm.showAll();
		
		JManagementSwing ms = new JManagementSwing();
		ms.setVisible(true);
		//ms.getContentPane().add(new JInsertBookDialog(), );
		
	}

}
