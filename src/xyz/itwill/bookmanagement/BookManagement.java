package xyz.itwill.bookmanagement;

import java.util.LinkedHashSet;

public class BookManagement {
	LinkedHashSet<Book> library = null; //추후에 Collection으로 변경
	
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
		Book returnBook = library.stream().
				filter(book -> book.getName().equals(name)).
				findFirst()
				.orElse(null);
		//((returnBook)-> if(book.name.equals(name)) );		
		return returnBook;
	}
	
	public void removeBook(String name) {
		Book removeBook = library.stream().
				filter(book -> book.getName().equals(name)).
				findFirst()
				.orElse(null);		
		if(removeBook != null) {
			library.remove(removeBook);
		}
	}
	
	public void updateBook(String name, int categoryNumber) {
		Book updateBook = searchBook(name);	
		if(updateBook != null) {
			updateBook.setCategory(categoryNumber);	
		}
	}
	
	public void showAll() {
		System.out.println("모든 책 목록 출력");
		System.out.println("고유번호 | 책이름 | 저자 | 출판사 | 분류번호");
		library.stream().forEach(book-> System.out.println(book.toString()));
	}
	
}
