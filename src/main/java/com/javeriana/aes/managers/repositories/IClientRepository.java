package com.javeriana.aes.managers.repositories;

import com.javeriana.aes.managers.entities.ClientEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IClientRepository extends CrudRepository<ClientEntity, Integer> {

    Optional<ClientEntity> findByIdentificationNumber(@Param("identificationNumber") String identificationNumber);

}