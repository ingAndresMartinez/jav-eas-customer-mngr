package com.javeriana.aes.managers.repositories;

import com.javeriana.aes.managers.entities.ProductEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IProductRepository extends CrudRepository<ProductEntity, Integer> {

    @Query("SELECT p FROM ProductEntity p " +
            "INNER JOIN ProductRequestEntity pr " +
            "ON p.productRequest = pr.id " +
            "AND pr.productType.id = :productType " +
            "INNER JOIN ClientEntity c " +
            "ON pr.client = c.id " +
            "AND c.identificationNumber = :identificationNumber")
    Optional<ProductEntity> findByProductTypeAndClientIdentification(@Param("identificationNumber") String identificationNumber, @Param("productType") int productType);

    @Query("SELECT p FROM ProductEntity p " +
            "INNER JOIN ProductRequestEntity pr " +
            "ON p.productRequest = pr.id " +
            "AND pr.id = :requestId ")
    Optional<ProductEntity> findByRequestId(@Param("requestId") int requestId);

}