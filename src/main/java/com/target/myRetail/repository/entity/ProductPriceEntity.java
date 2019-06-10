package com.target.myRetail.repository.entity;

import com.target.myRetail.model.CurrencyCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "productPrice")
public class ProductPriceEntity {
	@Id
	private Long id;
	private Double price;
	private CurrencyCode currencyCode;
}
