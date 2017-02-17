package com.response;

import java.io.Serializable;

public class GenericApiResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private String responseCode;
	private Object responseData;
	private String errorMessage;
	private String errorMessageData;

	public GenericApiResponse() {
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public Object getResponseData() {
		return responseData;
	}

	public void setResponseData(Object responseData) {
		this.responseData = responseData;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorMessageData() {
		return errorMessageData;
	}

	public void setErrorMessageData(String errorMessageData) {
		this.errorMessageData = errorMessageData;
	}

}
