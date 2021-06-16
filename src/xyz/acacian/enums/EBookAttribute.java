package xyz.acacian.enums;

// String으로 바꾸자.
public enum EBookAttribute {
	ID("고유번호"),
	CATEGORY("분류"),
	NAME("제목"),
	AUTHOR("저자"),
	PUBLISHER("출판사")
	;

	private final String value;
	private EBookAttribute(String value) {this.value = value;}
	
	public static int size() {return values().length;}
	public String getValue() {return value;}
	
}
