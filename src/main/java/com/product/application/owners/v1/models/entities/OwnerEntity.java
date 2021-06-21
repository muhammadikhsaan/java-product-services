package com.product.application.owners.v1.models.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.product.application.products.v1.models.entities.ProductEntity;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Length;

@Entity
@DynamicUpdate
@Table(name = "owners", schema = "products")
public class OwnerEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = ProductEntity.class)
    @JoinColumn(name = "owner_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private Set<ProductEntity> product = new HashSet<ProductEntity>();

    @NotEmpty(message = "Owner Name must be filled")
    @Length(max = 64, message = "Owner Name can only be 64 characters")
    @Column(name = "name", nullable = false, length = 64)
    private String name;

    @NotEmpty(message = "Owner Email must be filled")
    @Length(max = 64, message = "Owner Email can only be 64 characters")
    @Email(message = "Owner Email invalid format")
    @Column(name = "email", nullable = false, length = 64, unique = true)
    private String email;

    @NotNull(message = "Phone Number must be filled")
    @Column(name = "phone_number", nullable = false, unique = true)
    private Long phone;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt = new Date();

    @Column(name = "updated_at", nullable = false)
    private Date updatedAt = new Date();

    @Column(name = "deleted_at")
    private Date deletedAt;


    public OwnerEntity() {
    }

    public OwnerEntity(Long id, Set<ProductEntity> product, String name, String email, Long phone, Date createdAt, Date updatedAt, Date deletedAt) {
        this.id = id;
        this.product = product;
        this.name = name;
        this.email = email;
        this.phone = phone;
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

    public Set<ProductEntity> getProduct() {
        return this.product;
    }

    public void setProduct(Set<ProductEntity> product) {
        this.product = product;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getPhone() {
        return this.phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
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
