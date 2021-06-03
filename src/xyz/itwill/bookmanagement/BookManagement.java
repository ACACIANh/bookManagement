package xyz.itwill.bookmanagement;

import java.util.Iterator;
import java.util.LinkedHashSet;

public class BookManagement{
	private static BookManagement instance = null;
	private LinkedHashSet<Book> library = null; // 추후에 Collection으로 변경

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
			System.out.println(book.getCategoryName() + " 은(는) 이미 존재합니다.");
			return;
		}
		library.add(book);
		System.out.println(book.getCategoryName() + " 가(이) 추가 되었습니다.");
		//showBook(book);
	}

	public Book searchBook(String name) {
		Book returnBook = library.stream().filter(book -> book.getName().equals(name)).findAny().orElse(null);
		if (returnBook != null) {
			System.out.println("검색 되었습니다.");
		} else {
			System.out.println(name + " 을 찾을 수 없습니다.");
		}
		return returnBook;
	}

	public void removeBook(String name) {
		Book removeBook = searchBook(name);
		if (removeBook != null) {
			library.remove(removeBook);
			System.out.println("삭제 완료");
		}
	}

	public void updateBook(String name, int categoryNumber) {
		Book updateBook = searchBook(name);
		if (updateBook != null) {
			updateBook.setCategory(categoryNumber);
			System.out.println("업데이트 완료");
		}
	}

	public void showAll() {
		System.out.println("모든 책 목록 출력");
		System.out.println(" | 고유번호 | 책이름 | 저자 | 출판사 | 분류번호 |");
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
