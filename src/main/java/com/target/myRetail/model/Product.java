package com.target.myRetail.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
	private Long id;
	private String name;
	
	@JsonProperty("current_price")
	private CurrentPrice currentPrice;
}
