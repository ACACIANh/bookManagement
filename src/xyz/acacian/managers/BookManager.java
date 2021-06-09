package xyz.acacian.managers;

import java.util.Iterator;
import java.util.LinkedHashSet;

import xyz.acacian.objects.Book;

public enum BookManager{
	INSTANCE;
	//private static BookManager instance = null;
	private LinkedHashSet<Book> library = null; // ���Ŀ� Collection���� ����

	public static BookManager getInstance() {
//		if(instance == null) {
//			instance = new BookManager();
//		}
//		return instance;
		return INSTANCE;
	}
	
	private BookManager() {
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
	
	public Book searchBookCategoryName(String categoryName) {
		Book returnBook = library.stream().filter(
				book -> book.getCategoryName().equals(categoryName)).findAny().orElse(null);
		if (returnBook != null) {
			System.out.println("�˻� �Ǿ����ϴ�.");
		} else {
			System.out.println(categoryName + " �� ã�� �� �����ϴ�.");
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
	
	public void removeBookCategoryName(String categoryName) {
		Book removeBook = searchBookCategoryName(categoryName);
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
		String[][] returnStr = new String[library.size()][Book.expressAttribute.length];
		Iterator<Book> iter = library.iterator();
		for(int i=0; i<library.size(); ++i) {
			//returnStr[i] = library.at(i).toStringArr();
			returnStr[i]= iter.next().toStringArr();
		}	
		return returnStr;
	}

}
