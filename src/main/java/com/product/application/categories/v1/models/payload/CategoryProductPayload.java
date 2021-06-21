package com.product.application.categories.v1.models.payload;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.product.application.products.v1.models.payload.ProductPayload;

public class CategoryProductPayload {
    
    private String name;

    private Set<ProductPayload> product = new HashSet<ProductPayload>();

    private String description;

    private Date createdAt;

    private Date updatedAt;


    public CategoryProductPayload() {
    }

    public CategoryProductPayload(String name, Set<ProductPayload> product, String description, Date createdAt, Date updatedAt) {
        this.name = name;
        this.product = product;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ProductPayload> getProduct() {
        return this.product;
    }

    public void setProduct(Set<ProductPayload> product) {
        this.product = product;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

}
