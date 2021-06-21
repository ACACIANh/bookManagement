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
			returnStr = "0관리자";
			break;
		case 1:
			returnStr = "1사서";
			break;
		case 2:
			returnStr = "2회원";			
			break;
		}
		return returnStr;
	}
	
	public String getString() {
		String returnStr = null;
		switch (this) {
		case ADMIN:
			returnStr = "관리자";
			break;
		case LIBRARIAN:
			returnStr = "사서";
			break;
		case MEMBER:
			returnStr = "회원";			
			break;
		}
		return returnStr;
	}
}
