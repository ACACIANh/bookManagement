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
