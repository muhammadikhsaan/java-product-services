package com.product.application.owners.v1.repository;

import java.util.Date;
import java.util.List;

import com.product.application.owners.v1.models.entities.OwnerEntity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends CrudRepository<OwnerEntity, Long>{
    Long countByDeletedAt(@Nullable Date deletedAt);

    Long countByIdAndDeletedAt(Long id, @Nullable Date deletedAt);

    OwnerEntity findByIdAndDeletedAt(Long id, @Nullable Date deletedAt);

    List<OwnerEntity> findByDeletedAt(@Nullable Date deletedAt);
}
