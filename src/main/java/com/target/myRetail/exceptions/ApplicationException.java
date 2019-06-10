package com.target.myRetail.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApplicationException extends RuntimeException {
	private final static Logger LOG = LoggerFactory.getLogger(ApplicationException.class);
	
	public ApplicationException() {
	}
	
	public ApplicationException(String message) {
		super(message);
	}
	
	public ApplicationException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public ApplicationException(Throwable cause) {
		super(cause);
	}
	
	public ApplicationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
