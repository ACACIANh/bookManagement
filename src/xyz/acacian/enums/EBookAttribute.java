package xyz.acacian.enums;

//private int id = 0;
//private int category = 0;
//private boolean loanState = false;
//private String categoryName = null;
//private String name = null;
//private String author = null;
//private String publisher = null;
public enum EBookAttribute {
	ID(0),
	CATEGORY(1),
	NAME(2),
	AUTHOR(3),
	PUBLISHER(4);
	

	private final int value;

	EBookAttribute(int value) {this.value = value;}
	public int getValue() {return value;}
	public int getTrans(String name) {
		return value;
	}
	
}
