package xyz.acacian.database;

import xyz.acacian.enums.EMemberAttribute;

/*
이름       널       유형            
-------- -------- ------------- 
NUM      NOT NULL NUMBER(4)    
ID_LEVEL NOT NULL NUMBER(4)
ID		 UNIQUE	  VARCHAR2(40)
PW 		 		  VARCHAR2(40)
NAME              VARCHAR2(40)  
PHONE			  VARCHAR2(40)  
BIRTHDAY          DATE(40) 
*/

public class MemberDTO {
	public static String[] expressAttribute = {EMemberAttribute.NUM.getString(),
												EMemberAttribute.ID_LEVEL.getString(),
												EMemberAttribute.ID.getString(),
												EMemberAttribute.PW.getString(),
												EMemberAttribute.NAME.getString(),
												EMemberAttribute.PHONE.getString(),
												EMemberAttribute.BIRTHDAY.getString(),
												EMemberAttribute.LOAN.getString()};

	private int num;
	private int id_level;
	private String id;
	private String pw;
	private String name;
	private String phone;
	private String birthday;
	private String loan_book;
	
	public MemberDTO() {
		
	}

	public MemberDTO(int num, int id_level, String id, String pw, String name, String phone, String birthday) {
		super();
		this.num = num;
		this.id_level = id_level;
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.phone = phone;
		this.birthday = birthday;
	}
	
	public MemberDTO(int id_level, String id, String pw, String name, String phone, String birthday) {
		super();
		this.id_level = id_level;
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.phone = phone;
		this.birthday = birthday;
	}
	public MemberDTO(int num, int id_level, String id, String pw, String name, String phone, String birthday,
			String loan_book) {
		super();
		this.num = num;
		this.id_level = id_level;
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.phone = phone;
		this.birthday = birthday;
		this.loan_book = loan_book;
	}
	
	public String getLoan_book() {
		return loan_book;
	}

	public void setLoan_book(String loan_book) {
		this.loan_book = loan_book;
	}

	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getId_level() {
		return id_level;
	}
	public void setId_level(int id_level) {
		this.id_level = id_level;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	
	
}
