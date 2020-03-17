package com.javeriana.aes.managers.repositories;

import com.javeriana.aes.managers.entities.CdtProductEntity;
import org.springframework.data.repository.CrudRepository;

public interface ICdtProductRepository extends CrudRepository<CdtProductEntity, Integer> {
}