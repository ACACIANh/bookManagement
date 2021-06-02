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
	
}
