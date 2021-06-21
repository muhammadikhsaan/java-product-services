package com.product.application.products.v1.models.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.product.application.categories.v1.models.entities.CategoryEntity;
import com.product.application.owners.v1.models.entities.OwnerEntity;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Length;

@Entity
@DynamicUpdate
@Table(name = "products", schema = "products")
public class ProductEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryEntity categories;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private OwnerEntity owners;

    @NotEmpty(message = "Product Name must be filled")
    @Length(max = 64, message = "Product Name can only be 64 characters")
    @Column(name = "name", nullable = false, length = 64)
    private String name;

    @Length(max = 225, message = "Description can only be 225 characters")
    @Column(name = "description", nullable = false, length = 225)
    private String description;

    @NotNull(message = "Qty must be filled")
    @Min(value = 0, message = "Minimum value for qty is 0")
    @Column(name = "qty", nullable = false)
    private Long qty;

    @NotNull(message = "Price must be filled")
    @Min(value = 0, message = "Minimum value for price is 0")
    @Column(name = "price", nullable = false)
    private Long price;
    
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt = new Date();

    @Column(name = "updated_at", nullable = false)
    private Date updatedAt = new Date();

    @Column(name = "deleted_at")
    private Date deletedAt;


    public ProductEntity() {
    }

    public ProductEntity(Long id, CategoryEntity categories, OwnerEntity owners, String name, String description, Long qty, Long price, Date createdAt, Date updatedAt, Date deletedAt) {
        this.id = id;
        this.categories = categories;
        this.owners = owners;
        this.name = name;
        this.description = description;
        this.qty = qty;
        this.price = price;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CategoryEntity getCategories() {
        return this.categories;
    }

    public void setCategories(CategoryEntity categories) {
        this.categories = categories;
    }

    public OwnerEntity getOwners() {
        return this.owners;
    }

    public void setOwners(OwnerEntity owners) {
        this.owners = owners;
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

    public Date getDeletedAt() {
        return this.deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }

}
