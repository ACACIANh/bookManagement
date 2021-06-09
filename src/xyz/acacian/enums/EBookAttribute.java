package xyz.acacian.enums;

public enum EBookAttribute {
	ID(0),
	CATEGORY(1),
	NAME(2),
	AUTHOR(3),
	PUBLISHER(4);

	private final int value;
	private EBookAttribute(int value) {this.value = value;}
	
	public static int size() {return values().length;}
	public int getValue() {return value;}
	public int getTrans(String name) {
		return value;
	}
	
	
}
