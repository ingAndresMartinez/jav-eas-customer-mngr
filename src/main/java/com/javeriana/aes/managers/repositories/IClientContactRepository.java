package com.javeriana.aes.managers.repositories;

import com.javeriana.aes.managers.entities.ClientContactEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface IClientContactRepository extends CrudRepository<ClientContactEntity, Integer> {

    @Query("SELECT cc FROM ClientContactEntity cc " +
            "INNER JOIN ClientEntity c " +
            "ON cc.client = c.id " +
            "AND c.identificationNumber = :identificationNumber")
    Set<ClientContactEntity> findByIdentificationNumber(@Param("identificationNumber") String identificationNumber);

}