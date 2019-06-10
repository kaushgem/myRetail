package com.target.myRetail.service;

import com.target.myRetail.exceptions.BadRequestException;
import com.target.myRetail.exceptions.NotFoundException;
import com.target.myRetail.model.CurrentPrice;
import com.target.myRetail.model.Product;
import com.target.myRetail.repository.ProductPriceRepository;
import com.target.myRetail.repository.entity.ProductPriceEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
	private final static Logger LOG = LoggerFactory.getLogger(ProductServiceImpl.class);
	
	private ProductPriceRepository productPriceRepository;
	private RedSkyService redSkyService;
	
	@Autowired
	public ProductServiceImpl(ProductPriceRepository productPriceRepository,
	                          RedSkyService redSkyService) {
		this.productPriceRepository = productPriceRepository;
		this.redSkyService = redSkyService;
	}
	
	@Override
	public Product get(Long id) {
		Optional<ProductPriceEntity> productPriceEntityOpt = productPriceRepository.findById(id);
		if (productPriceEntityOpt.isPresent()) {
			ProductPriceEntity productPriceEntity = productPriceEntityOpt.get();
			CurrentPrice currentPrice = new CurrentPrice(productPriceEntity.getPrice(), productPriceEntity.getCurrencyCode());
			return new Product(id, redSkyService.getProductName(id), currentPrice);
		} else {
			throw new NotFoundException(id);
		}
	}
	
	@Override
	public Product modifyProductPrice(Long id, Product product) {
		String productName;
		if (!id.equals(product.getId())) {
			throw new BadRequestException(id.toString(), product.getId().toString());
		} else if (!((productName = redSkyService.getProductName(id))).equals(product.getName())) {
			throw new BadRequestException(product.getName(), productName);
		}
		
		if (productPriceRepository.findById(id).isPresent()) {
			ProductPriceEntity productPriceEntity = new ProductPriceEntity(product.getId(),
					product.getCurrentPrice().getValue(),
					product.getCurrentPrice().getCurrencyCode());
			ProductPriceEntity savedProductPriceEntity = productPriceRepository.save(productPriceEntity);
			CurrentPrice savedCurrentPrice = new CurrentPrice(savedProductPriceEntity.getPrice(),
					savedProductPriceEntity.getCurrencyCode());
			return new Product(id, productName, savedCurrentPrice);
		} else {
			throw new NotFoundException(id);
		}
	}
	
}
