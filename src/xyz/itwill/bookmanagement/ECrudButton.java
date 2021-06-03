package xyz.itwill.bookmanagement;

public enum ECrudButton {
	INSERT(0),
	UPDATE(1),
	REMOVE(2);
	
	private final int value;

	ECrudButton(int value) {this.value = value;}
	public int getValue() {return value;}
	//public static int getLast() {return END.getValue() - 1;}
}
