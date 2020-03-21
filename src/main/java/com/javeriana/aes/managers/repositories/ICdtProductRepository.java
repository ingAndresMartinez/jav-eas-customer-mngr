package com.javeriana.aes.managers.repositories;

import com.javeriana.aes.managers.entities.CdtProductEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ICdtProductRepository extends CrudRepository<CdtProductEntity, Integer> {

    @Query("SELECT c FROM CdtProductEntity c " +
            "INNER JOIN ProductEntity p " +
            "ON c.product = p.id " +
            "AND c.product.id = :productId ")
    Optional<CdtProductEntity> findByProductId(@Param("productId") int requestId);

}