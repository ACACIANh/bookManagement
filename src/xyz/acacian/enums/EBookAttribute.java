package xyz.acacian.enums;

// String���� �ٲ���.
public enum EBookAttribute {
	ID("������ȣ"),
	NAME("����"),
	AUTHOR("����"),
	PUBLISHER("���ǻ�"),
	CATEGORY("�з�")
	;

	private final String value;
	private EBookAttribute(String value) {this.value = value;}
	
	public static int size() {return values().length;}
	public String getValue() {return value;}
	
}
