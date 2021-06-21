package xyz.acacian.objects;

import java.io.Serializable;
import java.util.Objects;

import xyz.acacian.enums.EBookAttribute;
import xyz.acacian.enums.ECategory;

public class Book implements Serializable {

	private static final long serialVersionUID = -7961318480811635722L;

	//public static String[] expressAttribute = {"고유 번호","고유 번호", "제목", "저자", "출판사"};
	
	public static String[] expressAttribute = {EBookAttribute.ID.getValue(),
											   EBookAttribute.NAME.getValue(),
											   EBookAttribute.AUTHOR.getValue(),
											   EBookAttribute.PUBLISHER.getValue(),
											   EBookAttribute.CATEGORY.getValue()};
	
	
	private static int seedId = 0;

	private int id = 0;

	private String name = null;
	private String author = null;
	private String publisher = null;
	private ECategory category = null;
	private String categoryName = null;
	
	private Book() {
		super();
		generateId();
	}
	public Book(String name, String author, String publisher, ECategory category) {
		this();
		this.name = name;
		this.author = author;
		this.publisher = publisher;
		this.category = category;
		generateCategoryName();
	}
	
	
	private Book(Builder builder) {
		this();					//빌더 패턴 고치기
		name = builder.name;
		author = builder.author;
		publisher = builder.publisher;
		category = builder.category;
		generateCategoryName();
	}
	
	public static class Builder{
		private String name = null;
		private String author = null;
		private String publisher = null;
		private ECategory category = null;
		
		public Builder(int num) {
			
		}
		public Builder name(String name) {
			this.name = name;
			return this;
		}
		public Builder author(String author) {
			this.author = author;
			return this;
		}	
		public Builder publisher(String publisher) {
			this.publisher = publisher;
			return this;
		}	
		public Builder category(ECategory category) {
			this.category = category;
			return this;
		}	
		public Book build() {
			return new Book(this);
		}
	}
	
	public int getId() {
		return id;
	}
	
	public static int getSeedId() {
		return seedId;
	}

	private void generateId() {
		//이거 잘못된거같아 ㅠ ㅇㅇ 잘못됬어 ㄹㄹ
		++seedId;
		id = seedId;
	}

	public ECategory getCategory() {
		return category;
	}

	public void setCategory(ECategory category) {
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
		String generateNumber = category.getValue().substring(0,2) + (id % 100);
		int endIndex = 1;
		String generateName = generateNumber + "-" + name.substring(0, endIndex)
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
				+ category.getValue() + blank;
	}
	
	public String[] toStringArr() {
		String[] returnStr = {Integer.toString(id), name, author, publisher, category.toString()};
		return returnStr;
	}
}
