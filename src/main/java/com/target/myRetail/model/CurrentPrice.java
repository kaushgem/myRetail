package com.target.myRetail.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurrentPrice {
	private double value;
	
	@JsonProperty("currency_code")
	private CurrencyCode currencyCode;
}
