package com.product.application.categories.v1.services.interfaces;

import com.product.application.categories.v1.models.entities.CategoryEntity;

public abstract interface CategoryServiceInterfaces {
    Long categoriesCount();
    Long categoryCount(Long id);
    Iterable<CategoryEntity> getCategories();
    CategoryEntity getCategory(Long id);
    void deleteCategory(Long id) throws Exception;
    CategoryEntity saveCategory(CategoryEntity categoriesEntity);
    CategoryEntity updatedCategory(CategoryEntity categoriesEntity) throws Exception;
    Iterable<CategoryEntity> saveCategories(Iterable<CategoryEntity> categoriesEntity);
}
