package com.target.myRetail.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
	private final static Logger LOG = LoggerFactory.getLogger(ErrorResponse.class);
	private String message;
}
