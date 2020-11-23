package com.tc.exception;

public class ValidationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ValidationException(String errMsg) {
		super(errMsg);
	}
}
