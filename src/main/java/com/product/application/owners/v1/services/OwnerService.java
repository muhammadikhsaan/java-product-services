package com.product.application.owners.v1.services;

import java.util.Date;

import javax.transaction.Transactional;

import com.product.application.owners.v1.models.entities.OwnerEntity;
import com.product.application.owners.v1.repository.OwnerRepository;
import com.product.application.owners.v1.services.interfaces.OwnerServiceInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class OwnerService implements OwnerServiceInterface {
    
    @Autowired
    private OwnerRepository ownerRepository;

    @Override
    @Transactional
    public Long ownerCounts() {
        return this.ownerRepository.countByDeletedAt(null);
    }

    @Override
    @Transactional
    public Long ownerCount(Long id) {
        return this.ownerRepository.countByIdAndDeletedAt(id, null);
    }

    @Override
    @Transactional
    public Iterable<OwnerEntity> getOwners() {
        return this.ownerRepository.findByDeletedAt(null);
    }

    @Override
    @Transactional
    @Cacheable(key = "#id", value = "OwnerEntity")
    public OwnerEntity getOwner(Long id) {
        return this.ownerRepository.findByIdAndDeletedAt(id, null);
    }

    @Override
    @Transactional
    public OwnerEntity saveOwner(OwnerEntity categoriesEntity) {
        return this.ownerRepository.save(categoriesEntity);
    }

    @Override
    @Transactional
    public OwnerEntity updatedOwner(OwnerEntity categoriesEntity) throws Exception {
        Long count = this.ownerCount(categoriesEntity.getId());

        if (count == 0) {
            throw new Exception("Owner can't be update");
        }

        return this.ownerRepository.save(categoriesEntity);
    }

    @Override
    @Transactional
    public Iterable<OwnerEntity> saveOwners(Iterable<OwnerEntity> categoriesEntity) {
        return this.ownerRepository.saveAll(categoriesEntity);
    }

    @Override
    @Transactional
    @CacheEvict(key = "#id", value = "OwnerEntity")
    public void deleteOwner(Long id) throws Exception {
        OwnerEntity categoriesEntity = this.getOwner(id);

        if (categoriesEntity == null) {
            throw new Exception("Owner can't be delete");
        }

        categoriesEntity.setDeletedAt(new Date());
        
        this.saveOwner(categoriesEntity);
    }
}
