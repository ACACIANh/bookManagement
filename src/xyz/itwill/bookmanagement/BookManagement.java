package xyz.itwill.bookmanagement;

import java.util.LinkedHashSet;

public class BookManagement {
	LinkedHashSet<Book> library = null; //���Ŀ� Collection���� ����
	
	public BookManagement() {
		super();
		this.library = new LinkedHashSet<Book>();
	}

	public void insertBook(Book book) {
		if(library.contains(book)){
			return;
		}
		library.add(book);
	}
	
	public Book searchBook(String name) {
		Book value = library.stream().
				filter(book -> book.getName().equals(name)).
				findFirst()
				.orElse(null);
		//((book)-> if(book.name.equals(name)) );		
		return value;
	}
	
	public void deleteBook() {
		
	}
	
	public void updateBook() {
		
	}
	
	public void showAll() {
		System.out.println("��� å ��� ���");
		System.out.println("������ȣ | å�̸� | ���� | ���ǻ� | �з���ȣ");
		library.stream().forEach(book-> System.out.println(book.toString()));
	}
	
}
