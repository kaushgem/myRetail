package com.target.myRetail.service;

import com.target.myRetail.model.CurrencyCode;
import com.target.myRetail.model.CurrentPrice;
import com.target.myRetail.model.Product;
import com.target.myRetail.repository.ProductPriceRepository;
import com.target.myRetail.repository.entity.ProductPriceEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceImplTest {
	@Mock
	ProductPriceRepository productPriceRepository;
	@Mock
	RedSkyService redSkyService;
	
	ProductService productService;
	
	@Before
	public void setUp() {
		productService = new ProductServiceImpl(productPriceRepository, redSkyService);
	}
	
	@Test
	public void get() {
		//set-up
		Long productId = 100L;
		String productName = "Valid Product Name";
		Optional<ProductPriceEntity> productPriceEntity = Optional.of(
				new ProductPriceEntity(productId, 100.19, CurrencyCode.USD));
		
		when(productPriceRepository.findById(productId)).thenReturn(productPriceEntity);
		when(redSkyService.getProductName(productId)).thenReturn(productName);
		
		//invoke
		Product actual = productService.get(productId);
		
		//assert
		Product expected = new Product(
				productId,
				productName,
				new CurrentPrice(productPriceEntity.get().getPrice(), productPriceEntity.get().getCurrencyCode()));
		
		assertNotNull(actual);
		assertEquals(expected, actual);
	}
	
	@Test
	public void modifyProductById() {
		//set-up
		Long productId = 100L;
		String productName = "Valid Product Name";
		Optional<ProductPriceEntity> productPriceEntity = Optional.of(
				new ProductPriceEntity(productId, 100.19, CurrencyCode.USD));
		Product expected = new Product(
				productId,
				productName,
				new CurrentPrice(productPriceEntity.get().getPrice(), productPriceEntity.get().getCurrencyCode()));
		
		when(productPriceRepository.findById(productId)).thenReturn(productPriceEntity);
		when(redSkyService.getProductName(productId)).thenReturn(productName);
		when(productPriceRepository.save(productPriceEntity.get())).thenReturn(productPriceEntity.get());
		
		//invoke
		Product actual = productService.modifyProductPrice(productId, expected);
		
		//assert
		assertNotNull(actual);
		assertEquals(expected, actual);
	}
}