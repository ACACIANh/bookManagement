package xyz.itwill.bookmanagement;

import java.util.Iterator;
import java.util.LinkedHashSet;

public class BookManagement{
	private static BookManagement instance = null;
	private LinkedHashSet<Book> library = null; // ���Ŀ� Collection���� ����

	public static BookManagement getInstance() {
		if(instance == null) {
			instance = new BookManagement();
		}
		return instance;
	}
	
	private BookManagement() {
		this.library = new LinkedHashSet<Book>();
	}
	
	public void insertBook(Book book) {
		if (library.contains(book)) {
			System.out.println(book.getCategoryName() + " ��(��) �̹� �����մϴ�.");
			return;
		}
		library.add(book);
		System.out.println(book.getCategoryName() + " ��(��) �߰� �Ǿ����ϴ�.");
		//showBook(book);
	}

	public Book searchBook(String name) {
		Book returnBook = library.stream().filter(book -> book.getName().equals(name)).findAny().orElse(null);
		if (returnBook != null) {
			System.out.println("�˻� �Ǿ����ϴ�.");
		} else {
			System.out.println(name + " �� ã�� �� �����ϴ�.");
		}
		return returnBook;
	}

	public void removeBook(String name) {
		Book removeBook = searchBook(name);
		if (removeBook != null) {
			library.remove(removeBook);
			System.out.println("���� �Ϸ�");
		}
	}

	public void updateBook(String name, int categoryNumber) {
		Book updateBook = searchBook(name);
		if (updateBook != null) {
			updateBook.setCategory(categoryNumber);
			System.out.println("������Ʈ �Ϸ�");
		}
	}

	public void showAll() {
		System.out.println("��� å ��� ���");
		System.out.println(" | ������ȣ | å�̸� | ���� | ���ǻ� | �з���ȣ |");
		library.stream().forEach(book -> showBook(book));
	}

	public void showBook(Book book) {
		System.out.println(book.toString());
	}
	
	public String[][] getString2Dimension(){
		String[][] returnStr = new String[library.size()][Book.expressAtrribute.length];
		//String[][] returnStr2 = library.toArray().toString();
		Iterator<Book> iter = library.iterator();
		for(int i=0; i<library.size();++i) {
			//returnStr[i] = library.at(i).toStringArr();
			returnStr[i]= iter.next().toStringArr();
		}	
		return returnStr;
	}

}
