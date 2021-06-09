package xyz.acacian.enums;

public enum ECrudButton{
	INSERT(0),
	UPDATE(1),
	REMOVE(2);
	
	private final int value;
	private ECrudButton(int value) {this.value = value;}
	
	public static int size() {return values().length;}
	public int getValue() {return value;}


}
