package xyz.acacian.database;

/*
이름       널       유형            
-------- -------- ------------- 
NUM      NOT NULL NUMBER(4)     
NAME              VARCHAR2(40)  
AUTHOR            VARCHAR2(40)  
PUBLISHER         VARCHAR2(40) 
CATEGORY          VARCHAR2(40) 
*/
public class BookDTO {
	private int num;
	private String name;
	private String author;
	private String publisher;
	private String category;
	
	public BookDTO() {
		
	}
	
	public BookDTO(int num, String name, String author, String publisher, String category) {
		super();
		this.num = num;
		this.name = name;
		this.author = author;
		this.publisher = publisher;
		this.category = category;
	}
	
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
}
