package com.target.myRetail.service;

import com.target.myRetail.exceptions.BadRequestException;
import com.target.myRetail.exceptions.NotFoundException;
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

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceImplErrorCaseTest {
	@Mock
	ProductPriceRepository productPriceRepository;
	@Mock
	RedSkyService redSkyService;
	
	ProductService productService;
	
	@Before
	public void setUp() {
		productService = new ProductServiceImpl(productPriceRepository, redSkyService);
	}
	
	@Test(expected = NotFoundException.class)
	public void get_whenProductIdNotPresentInDB() {
		//set-up
		Long productId = 100L;
		
		when(productPriceRepository.findById(productId)).thenReturn(Optional.empty());
		
		//invoke
		productService.get(productId);
		
		//assert
		//Refer expected
	}
	
	@Test(expected = BadRequestException.class)
	public void modifyProductById_whenProductIdDoNotMatchInRequest() {
		//set-up
		Long productId = 100L;
		String productName = "Valid Product Name";
		Optional<ProductPriceEntity> productPriceEntity = Optional.of(
				new ProductPriceEntity(productId, 100.19, CurrencyCode.USD));
		Product expected = new Product(
				200L,
				productName,
				new CurrentPrice(productPriceEntity.get().getPrice(), productPriceEntity.get().getCurrencyCode()));
		
		//invoke
		productService.modifyProductPrice(productId, expected);
		
		//assert
		//Refer expected
	}
	
	@Test(expected = BadRequestException.class)
	public void modifyProductById_whenProductNameIsDifferentThanExpected() {
		//set-up
		Long productId = 100L;
		String productName = "Valid Product Name";
		Optional<ProductPriceEntity> productPriceEntity = Optional.of(
				new ProductPriceEntity(productId, 100.19, CurrencyCode.USD));
		Product expected = new Product(
				productId,
				productName,
				new CurrentPrice(productPriceEntity.get().getPrice(), productPriceEntity.get().getCurrencyCode()));
		
		when(redSkyService.getProductName(productId)).thenReturn("Different Product Name");
		
		//invoke
		productService.modifyProductPrice(productId, expected);
		
		//assert
		//Refer expected
	}
	
	@Test(expected = NotFoundException.class)
	public void modifyProductById_whenProductIdNotPresentInDB() {
		//set-up
		Long productId = 100L;
		String productName = "Valid Product Name";
		Optional<ProductPriceEntity> productPriceEntity = Optional.of(
				new ProductPriceEntity(productId, 100.19, CurrencyCode.USD));
		Product expected = new Product(
				productId,
				productName,
				new CurrentPrice(productPriceEntity.get().getPrice(), productPriceEntity.get().getCurrencyCode()));
		
		when(productPriceRepository.findById(productId)).thenReturn(Optional.empty());
		when(redSkyService.getProductName(productId)).thenReturn(productName);
		
		//invoke
		productService.modifyProductPrice(productId, expected);
		
		//assert
		//Refer expected
	}
}