package springboot.hello.model;

public enum UsersGender {
	
MALE("M", 1), FEMALE("F", 2);
	
	private UsersGender(String codeName, Integer codeValue) {
		this.codeName = codeName;
		this.codeValue = codeValue;
	}
	
	private String codeName;
	
	private Integer codeValue;

	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	public Integer getCodeValue() {
		return codeValue;
	}

	public void setCodeValue(Integer codeValue) {
		this.codeValue = codeValue;
	}
}
