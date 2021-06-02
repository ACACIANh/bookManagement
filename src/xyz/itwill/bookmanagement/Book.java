package xyz.itwill.bookmanagement;

import java.io.Serializable;

public class Book implements Serializable {
	private static final long serialVersionUID = 1060401278850448591L;
	private static int seedId = 0;
	
	private int id = 0;
	private int category = 0;
	private boolean loanState = false;
	private String categoryName = null;
	private String name = null;
	private String author = null;
	private String publisher = null;
	
	public Book() {
		super();
	}
	public Book(int category, String name, String author, String publisher) {
		this();
		generateId();
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
	public boolean isLoanState() {
		return loanState;
	}
	public void setLoanState(boolean loanState) {
		this.loanState = loanState;
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
		int endIndex = 2;
		String generateName = Integer.toString(generateNumber)
							+ name.substring(0, endIndex)
							+ author.substring(0, endIndex)
							+ publisher.substring(0, endIndex);
		this.categoryName = generateName;
	}
	
}
