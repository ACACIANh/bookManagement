package xyz.acacian.enums;

//도서 분류 번호
//000  General works	총류
//100  Philosophy		철학	
//200  Religion			종교
//300  Social sciences	통계학
//400  Natural sciences	수학
//500  Technology		의학
//600  Arts				예술
//700  Language			언어
//800  Literature		문학
//900  History			역사

public enum ECategory {
	GENERAL_WORCKS("000"),			
	PHILOSOPHY("100"),		
	RELIGION("200"),
	SOCIAL_SCIENCES("300"),
	NATURAL_SCIENCES("400"),
	TECHNOLOGY("500"),
	ARTS("600"),
	LANGUAGE("700"),
	LITERATURE("800"),
	HISTORY("900");	
	
	private final String value;
	private ECategory(String value) {this.value = value;}
	
	public static int size() {return values().length;}
	public String getValue() {return value;}
	
	public String toStringKor() {
		String returnStr = null;
		switch (value) {
		case "000":
			returnStr = "총류";
			break;
		case "100":
			returnStr = "철학";
			break;
		case "200":
			returnStr = "종교";
			break;
		case "300":
			returnStr = "통계학";
			break;
		case "400":
			returnStr = "수학";
			break;
		case "500":
			returnStr = "의학";
			break;
		case "600":
			returnStr = "예술";
			break;
		case "700":
			returnStr = "언어";
			break;
		case "800":
			returnStr = "문학";
			break;
		case "900":
			returnStr = "역사";
			break;
		}
		return returnStr;
	}
	
	@Override 
	public String toString() {
		return value;
	}
}


