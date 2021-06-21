package com.product.application.owners.v1.services.interfaces;

import com.product.application.owners.v1.models.entities.OwnerEntity;

public interface OwnerServiceInterface {
    Long ownerCounts();
    Long ownerCount(Long id);
    Iterable<OwnerEntity> getOwners();
    OwnerEntity getOwner(Long id);
    void deleteOwner(Long id) throws Exception;
    OwnerEntity saveOwner(OwnerEntity categoriesEntity);
    OwnerEntity updatedOwner(OwnerEntity categoriesEntity) throws Exception;
    Iterable<OwnerEntity> saveOwners(Iterable<OwnerEntity> categoriesEntity);
}
