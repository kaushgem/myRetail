package com.target.myRetail.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.target.myRetail.model.Product;
import com.target.myRetail.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
	private final static Logger LOG = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/{id}")
	public Product get(@PathVariable Long id) {
		return productService.get(id);
	}
	
	@PutMapping("/{id}")
	public Product updateProductPrice(@PathVariable Long id,
	                                  @Valid @RequestBody Product product) {
		return productService.modifyProductPrice(id, product);
	}
}
