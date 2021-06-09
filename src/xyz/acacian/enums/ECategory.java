package xyz.acacian.enums;

//���� �з� ��ȣ
//000  General works	�ѷ�
//100  Philosophy		ö��	
//200  Religion			����
//300  Social sciences	�����
//400  Natural sciences	����
//500  Technology		����
//600  Arts				����
//700  Language			���
//800  Literature		����
//900  History			����

public enum ECategory {
	GENERAL_WORCKS(0),			
	PHILOSOPHY(100),		
	RELIGION(200),
	SOCIAL_SCIENCES(300),
	NATURAL_SCIENCES(400),
	TECHNOLOGY(500),
	ARTS(600),
	LANGUAGE(700),
	LITERATURE(800),
	HISTORY(900);	
	
	private final int value;
	private ECategory(int value) {this.value = value;}
	
	public static int size() {return values().length;}
	public int getValue() {return value;}
	
	@Override 
	public String toString() {
		String returnStr = null;
		switch (value) {
		case 0:
			returnStr = "�ѷ�";
			break;
		case 100:
			returnStr = "ö��";
			break;
		case 200:
			returnStr = "����";
			break;
		case 300:
			returnStr = "�����";
			break;
		case 400:
			returnStr = "����";
			break;
		case 500:
			returnStr = "����";
			break;
		case 600:
			returnStr = "����";
			break;
		case 700:
			returnStr = "���";
			break;
		case 800:
			returnStr = "����";
			break;
		case 900:
			returnStr = "����";
			break;
		}
		return returnStr;
	}
}

