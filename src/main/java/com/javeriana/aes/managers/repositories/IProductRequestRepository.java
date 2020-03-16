package com.javeriana.aes.managers.repositories;

import com.javeriana.aes.managers.entities.ProductRequestEntity;
import org.springframework.data.repository.CrudRepository;

public interface IProductRequestRepository extends CrudRepository<ProductRequestEntity, Integer> {
}