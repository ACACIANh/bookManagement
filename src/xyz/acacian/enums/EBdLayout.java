package xyz.acacian.enums;

import java.awt.BorderLayout;

public enum EBdLayout {
	NORTH(0),
	SOUTH(1),
	EAST(2),
	WEST(3),
	CENTER(4);

	private final int value;

	EBdLayout(int value) {this.value = value;}
	public int getValue() {return value;}
	public int getTrans(String name) {
		return value;
	}
	
	public static String getTransStr(int index) {
		String returnStr = null;
		EBdLayout value = values()[index];
		switch(value) {
		case NORTH:
			returnStr = BorderLayout.NORTH;
			break;
		case SOUTH:
			returnStr = BorderLayout.SOUTH;
			break;
		case EAST:
			returnStr = BorderLayout.EAST;
			break;
		case WEST:
			returnStr = BorderLayout.WEST;
			break;
		case CENTER:
			returnStr = BorderLayout.CENTER;
			break;
		default:
			assert (false) : "EBdLayout type error";   
			break;
		}
		return returnStr;
	}
}
