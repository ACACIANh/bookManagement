package xyz.acacian.enums;

// String으로 바꾸자.
public enum EBookAttribute {
	NUM(0), 
	NAME(1), 
	AUTHOR(2), 
	PUBLISHER(3), 
	CATEGORY(4)
	;

	private final int value;
	private EBookAttribute(int value) {
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
			str = "고유번호";
			break;
		case NAME:
			str = "제목";
			break;
		case AUTHOR:
			str = "저자";
			break;
		case PUBLISHER:
			str = "출판사";
			break;
		case CATEGORY:
			str = "분류";
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
		case NAME:
			str = "name";
			break;
		case AUTHOR:
			str = "author";
			break;
		case PUBLISHER:
			str = "publisher";
			break;
		case CATEGORY:
			str = "category";
			break;
		}
		return str;
	}

}
