package com.product.application.products.v1.repository;

import java.util.List;

import java.sql.Date;

import com.product.application.products.v1.models.broker.OrderQtyBroker;
import com.product.application.products.v1.models.entities.ProductEntity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<ProductEntity, Long>{
    Long countByDeletedAt(@Nullable Date deletedAt);

    Long countByIdAndDeletedAt(Long id, @Nullable Date deletedAt);

    ProductEntity findByIdAndDeletedAt(Long id, @Nullable Date deletedAt);

    List<ProductEntity> findByDeletedAt(@Nullable Date deletedAt);

    OrderQtyBroker save(OrderQtyBroker orderQtyBroker);
}
