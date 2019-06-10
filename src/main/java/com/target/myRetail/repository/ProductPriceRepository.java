package com.target.myRetail.repository;

import com.target.myRetail.repository.entity.ProductPriceEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductPriceRepository extends MongoRepository<ProductPriceEntity, Long> {
}
