package com.enums;

public enum ResponseEnum {
	DATA("data"), 
	STATUS("status"), 
	REASON("reason"),
	ERROR("error"),
	SUCCESS("success"),
	USER_NOT_FOUND("USR_NOT_FOUND"),
	USER_ROLE_NOT_FOUND("USR_ROLE_NOT_FOUND"),
	EXCEPTION("SYS_ERR_01");
	

	String value;

	private ResponseEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
