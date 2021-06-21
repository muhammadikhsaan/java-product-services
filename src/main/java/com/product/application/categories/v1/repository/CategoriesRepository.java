package com.product.application.categories.v1.repository;

import java.util.Date;
import java.util.List;

import com.product.application.categories.v1.models.entities.CategoryEntity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriesRepository extends CrudRepository<CategoryEntity, Long> {

    Long countByDeletedAt(@Nullable Date deletedAt);

    Long countByIdAndDeletedAt(Long id, @Nullable Date deletedAt);

    CategoryEntity findByIdAndDeletedAt(Long id, @Nullable Date deletedAt);

    List<CategoryEntity> findByDeletedAt(@Nullable Date deletedAt);
}
