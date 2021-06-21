package xyz.acacian.enums;

public enum ELevel {
	ADMIN(0),			
	LIBRARIAN(1),		
	MEMBER(2)		
	;

	
	private final int value;
	private ELevel(int value) {this.value = value;}
	
	public static int size() {return values().length;}
	public int getValue() {return value;}
	
	public static String getStringStatic(int num) {
		String returnStr = null;
		switch (num) {
		case 0:
			returnStr = "0������";
			break;
		case 1:
			returnStr = "1�缭";
			break;
		case 2:
			returnStr = "2ȸ��";			
			break;
		}
		return returnStr;
	}
	
	public String getString() {
		String returnStr = null;
		switch (this) {
		case ADMIN:
			returnStr = "������";
			break;
		case LIBRARIAN:
			returnStr = "�缭";
			break;
		case MEMBER:
			returnStr = "ȸ��";			
			break;
		}
		return returnStr;
	}
}
