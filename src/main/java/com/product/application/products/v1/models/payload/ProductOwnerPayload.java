package com.product.application.products.v1.models.payload;

import java.util.Date;

import com.product.application.owners.v1.models.payload.OwnerPayload;

public class ProductOwnerPayload {

    private Long id;

    private OwnerPayload owner;

    private String name;

    private String description;

    private Long qty;

    private Long price;
    
    private Date createdAt;

    private Date updatedAt;


    public ProductOwnerPayload() {
    }

    public ProductOwnerPayload(Long id, OwnerPayload owner, String name, String description, Long qty, Long price, Date createdAt, Date updatedAt) {
        this.id = id;
        this.owner = owner;
        this.name = name;
        this.description = description;
        this.qty = qty;
        this.price = price;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OwnerPayload getOwner() {
        return this.owner;
    }

    public void setOwner(OwnerPayload owner) {
        this.owner = owner;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getQty() {
        return this.qty;
    }

    public void setQty(Long qty) {
        this.qty = qty;
    }

    public Long getPrice() {
        return this.price;
    }

    public void setPrice(Long price) {
        this.price = price;
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
