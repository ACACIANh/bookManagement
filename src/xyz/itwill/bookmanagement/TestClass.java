package xyz.itwill.bookmanagement;

public class TestClass {
	public static void main(String[] args) {
		BookManagement bm = new BookManagement();
		Book book0 = new Book(100, "ù��°å", "ù��°����", "ù��°���ǻ�");
		Book book1 = new Book(800, "Java������", "���ü�", "��������");
		Book book2 = new Book(400, "�׸��� �ƹ��� ������", "�ְŻ� ũ����Ƽ", "�ع����ǻ�");
		
		bm.insertBook(book0);
		bm.insertBook(book1);
		bm.insertBook(book2);
		
		bm.showAll();
	}

}
