package com.target.myRetail.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.target.myRetail.util.Constants.BAD_REQUEST_MSG_TEMPLATE;

public class BadRequestException extends ApplicationException {
	private final static Logger LOG = LoggerFactory.getLogger(BadRequestException.class);
	
	public BadRequestException(String urlValue, String payloadValue) {
		super(String.format(BAD_REQUEST_MSG_TEMPLATE, urlValue, payloadValue));
	}
}
