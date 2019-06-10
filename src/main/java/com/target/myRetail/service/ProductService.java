package com.target.myRetail.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.target.myRetail.model.Product;

public interface ProductService {
	Logger LOG = LoggerFactory.getLogger(ProductService.class);
	
	Product get(Long id);
	
	Product modifyProductPrice(Long id, Product product);
}
