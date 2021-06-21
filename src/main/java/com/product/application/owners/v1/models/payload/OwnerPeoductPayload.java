package com.product.application.owners.v1.models.payload;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.product.application.products.v1.models.payload.ProductPayload;

public class OwnerPeoductPayload {
    
    private Set<ProductPayload> product = new HashSet<ProductPayload>();

    private String name;

    private Long phone;

    private String email;

    private Date createdAt;

    private Date updatedAt;


    public OwnerPeoductPayload() {
    }

    public OwnerPeoductPayload(Set<ProductPayload> product, String name, Long phone, String email, Date createdAt, Date updatedAt) {
        this.product = product;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Set<ProductPayload> getProduct() {
        return this.product;
    }

    public void setProduct(Set<ProductPayload> product) {
        this.product = product;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPhone() {
        return this.phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
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
