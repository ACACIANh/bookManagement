package xyz.itwill.bookmanagement;

import java.io.Serializable;

public class Book implements Serializable {
	private static final long serialVersionUID = 1060401278850448591L;
	private static int seedId = 0;
	
	int id = 0;
	int category = 0;
	boolean loanState = false;
	String categoryName = null;
	String name = null;
	String author = null;
	String publisher = null;
	
	public int getId() {
		return id;
	}
	@SuppressWarnings("unused")
	private void setId(int id) {
		this.id = id;
	}
	@SuppressWarnings("unused")
	private void setId() {
		++seedId;
		id = seedId;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
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
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	
}
