package xyz.itwill.bookmanagement;

import java.io.Serializable;
import java.util.Objects;

public class Book implements Serializable {	
	public static String[] expressAttribute = {"고유 번호", "제목", "저자", "출판사"};
	
	private static final long serialVersionUID = 1060401278850448591L;
	private static int seedId = 0;

	private int id = 0;
	private int category = 0;
	
	private String categoryName = null;
	private String name = null;
	private String author = null;
	private String publisher = null;

	public Book() {
		super();
		generateId();
	}

	public Book(int category, String name, String author, String publisher) {
		this();
		this.category = category;
		this.name = name;
		this.author = author;
		this.publisher = publisher;
		generateCategoryName();
	}

	public int getId() {
		return id;
	}

	private void generateId() {
		++seedId;
		id = seedId;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
		generateCategoryName();
	}

	public String getCategoryName() {
		return categoryName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		generateCategoryName();
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
		generateCategoryName();
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
		generateCategoryName();
	}

	private void generateCategoryName() {
		this.categoryName = null;
		int generateNumber = id % 100 + this.category;
		int endIndex = 1;
		String generateName = Integer.toString(generateNumber) + "-" + name.substring(0, endIndex)
				+ author.substring(0, endIndex) + publisher.substring(0, endIndex);
		this.categoryName = generateName;
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

	
	@Override
	public boolean equals(Object obj) {	
		if(this == obj)
			return true;
		if(null == obj || getClass() != obj.getClass()) 
			return false;
		Book book = (Book)obj;
		return Objects.equals(categoryName, book.categoryName);
	}

	@Override
	public String toString() {
		String blank = " | ";
		return blank + this.categoryName + blank + this.name + blank + this.author + blank + this.publisher + blank
				+ Integer.toString(category) + blank;
	}
	
	public String[] toStringArr() {
		//{"고유 번호", "제목", "저자", "출판사"};
		String[] returnStr = {categoryName, name, author, publisher};
		return returnStr;
	}
}
