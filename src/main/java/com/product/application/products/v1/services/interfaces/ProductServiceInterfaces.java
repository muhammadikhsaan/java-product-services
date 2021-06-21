package com.product.application.products.v1.services.interfaces;

import java.util.List;

import com.product.application.products.v1.models.broker.OrderQtyBroker;
import com.product.application.products.v1.models.entities.ProductEntity;

public interface ProductServiceInterfaces {
    Long productsCount();
    Long productCount(Long id);
    List<ProductEntity> getProducts();
    ProductEntity getProduct(Long id);
    void deleteProduct(Long id) throws Exception;
    ProductEntity saveProduct(ProductEntity productEntity);
    ProductEntity updatedProduct(ProductEntity productEntity) throws Exception;
    Iterable<ProductEntity> saveProducts(Iterable<ProductEntity> productEntity);
    OrderQtyBroker updatedProductQty(OrderQtyBroker orderQtyBroker) throws Exception;
}
