package com.connect4.model;

import java.io.Serializable;

public class ErrorResponse extends BaseResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	private String message;

	public ErrorResponse() {
		this.message = "";
	}

	public ErrorResponse(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
