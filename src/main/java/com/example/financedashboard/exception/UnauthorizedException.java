package com.example.financedashboard.exception;

public class UnauthorizedException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public UnauthorizedException(String message) {
		super(message);
	}
}