package xyz.acacian.enums;

// String���� �ٲ���.
public enum EBookAttribute {
	NUM(0), 
	NAME(1), 
	AUTHOR(2), 
	PUBLISHER(3), 
	CATEGORY(4),
	LOAN(5)
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
			str = "��ȣ";
			break;
		case NAME:
			str = "����";
			break;
		case AUTHOR:
			str = "����";
			break;
		case PUBLISHER:
			str = "���ǻ�";
			break;
		case CATEGORY:
			str = "�з�";
			break;
		case LOAN:
			str = "����";
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
		case LOAN:
			str = "loan";
			break;		
		}
		return str;
	}

}
