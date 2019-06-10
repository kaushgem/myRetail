package com.target.myRetail.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.target.myRetail.util.Constants.PRODUCT_NOT_FOUND_MSG_TEMPLATE;

public class NotFoundException extends ApplicationException {
	private final static Logger LOG = LoggerFactory.getLogger(NotFoundException.class);
	
	public NotFoundException(Long id) {
		super(String.format(PRODUCT_NOT_FOUND_MSG_TEMPLATE, id));
	}
}
