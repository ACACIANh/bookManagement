package xyz.itwill.bookmanagement;

public enum EBdLayout {
	NORTH(0),
	SOUTH(1),
	EAST(2),
	WEST(3),
	CENTER(4);

	private final int value;

	EBdLayout(int value) {this.value = value;}
	public int getValue() {return value;}
}
