package xyz.acacian.enums;

public enum EMemberAttribute {
	NUM(0), 
	ID_LEVEL(1), 
	ID(2), 
	PW(3), 
	NAME(4), 
	PHONE(5), 
	BIRTHDAY(6)
	;

	private final int value;
	private EMemberAttribute(int value) {
		this.value = value;
	}

	public static int size() {
		return values().length;
	}
	public int getValue() {
		return value;
	}

	public String getString() {
		String str = "";
		switch (this) {
		case NUM:
			str = "번호";
			break;
		case ID_LEVEL:
			str = "레벨";
			break;
		case ID:
			str = "아이디";
			break;
		case PW:
			str = "비밀번호";
			break;
		case NAME:
			str = "이름";
			break;
		case PHONE:
			str = "전화번호";
			break;
		case BIRTHDAY:
			str = "생일";
			break;
		}
		return str;
	}

	public String getStringSQL() {
		String str = "";
		switch (this) {
		case NUM:
			str = "num";
			break;
		case ID_LEVEL:
			str = "id_level";
			break;
		case ID:
			str = "id";
			break;
		case PW:
			str = "pw";
			break;
		case NAME:
			str = "name";
			break;
		case PHONE:
			str = "phone";
			break;
		case BIRTHDAY:
			str = "birthday";
			break;
		}
		return str;
	}

}
