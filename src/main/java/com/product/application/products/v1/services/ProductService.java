package com.product.application.products.v1.services;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import com.product.application.products.v1.models.broker.OrderQtyBroker;
import com.product.application.products.v1.models.entities.ProductEntity;
import com.product.application.products.v1.repository.ProductRepository;
import com.product.application.products.v1.services.interfaces.ProductServiceInterfaces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements ProductServiceInterfaces {
    
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Long productsCount() {
        return this.productRepository.countByDeletedAt(null);
    }

    @Override
    public Long productCount(Long id) {
        return this.productRepository.countByIdAndDeletedAt(id, null);
    }

    @Override
    public List<ProductEntity> getProducts() {
        return this.productRepository.findByDeletedAt(null);
    }

    @Override
    @Cacheable(key = "#id", value = "ProductEntity")
    public ProductEntity getProduct(Long id) {
        return this.productRepository.findByIdAndDeletedAt(id, null);
    }

    @Override
    @Transactional
    public ProductEntity saveProduct(ProductEntity productEntity) {
        ProductEntity result = this.productRepository.save(productEntity);
        return result;
    }

    @Override
    @Transactional
    public ProductEntity updatedProduct(ProductEntity productEntity) throws Exception {
        Long count = this.productCount(productEntity.getId());

        if (count == 0) {
            throw new Exception("Product can't be update");
        }

        return this.productRepository.save(productEntity);
    }

    @Override
    @Transactional
    public OrderQtyBroker updatedProductQty(OrderQtyBroker orderQtyBroker) throws Exception {
        Long count = this.productCount(orderQtyBroker.getId());

        if (count == 0) {
            throw new Exception("Product can't be update");
        }

        return this.productRepository.save(orderQtyBroker);
    }

    @Override
    @Transactional
    public Iterable<ProductEntity> saveProducts(Iterable<ProductEntity> productEntity) {
        return this.productRepository.saveAll(productEntity);
    }

    @Override
    @Transactional
    @CacheEvict(key = "#id", value = "ProductEntity")
    public void deleteProduct(Long id) throws Exception {
        ProductEntity categoriesEntity = this.getProduct(id);

        if (categoriesEntity == null) {
            throw new Exception("Product can't be delete");
        }

        categoriesEntity.setDeletedAt(new Date());
        
        this.saveProduct(categoriesEntity);
    }
    
}
