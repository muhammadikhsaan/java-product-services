package com.product.application.categories.v1.services;

import java.util.Date;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import javax.transaction.Transactional;

import com.product.application.categories.v1.models.entities.CategoryEntity;
import com.product.application.categories.v1.repository.CategoriesRepository;
import com.product.application.categories.v1.services.interfaces.CategoryServiceInterfaces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService implements CategoryServiceInterfaces {
    
    @Autowired
    private CategoriesRepository categoriesRepository;

    @Override
    public Long categoriesCount() {
        return this.categoriesRepository.countByDeletedAt(null);
    }

    @Override
    public Long categoryCount(Long id) {
        return this.categoriesRepository.countByIdAndDeletedAt(id, null);
    }

    @Override
    public Iterable<CategoryEntity> getCategories() {
        return this.categoriesRepository.findByDeletedAt(null);
    }

    @Override
    @Cacheable(key = "#id", value = "CategoryEntity")
    public CategoryEntity getCategory(Long id) {
        return this.categoriesRepository.findByIdAndDeletedAt(id, null);
    }

    @Override
    @Transactional
    public CategoryEntity saveCategory(CategoryEntity categoriesEntity) {
        CategoryEntity result = this.categoriesRepository.save(categoriesEntity);
        return result;
    }

    @Override
    @Transactional
    public CategoryEntity updatedCategory(CategoryEntity categoriesEntity) throws Exception {
        Long count = this.categoryCount(categoriesEntity.getId());

        if (count == 0) {
            throw new Exception("Catalogues can't be update");
        }

        return this.categoriesRepository.save(categoriesEntity);
    }

    @Override
    @Transactional
    public Iterable<CategoryEntity> saveCategories(Iterable<CategoryEntity> categoriesEntity) {
        return this.categoriesRepository.saveAll(categoriesEntity);
    }

    @Override
    @Transactional
    @CacheEvict(key = "#id", value = "CategoryEntity")
    public void deleteCategory(Long id) throws Exception {
        CategoryEntity categoriesEntity = this.getCategory(id);

        if (categoriesEntity == null) {
            throw new Exception("Catalogues can't be delete");
        }

        categoriesEntity.setDeletedAt(new Date());
        
        this.saveCategory(categoriesEntity);
    }
}