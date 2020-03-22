package com.javeriana.aes.managers.repositories;

import com.javeriana.aes.managers.entities.BlackListEntity;
import org.springframework.data.repository.CrudRepository;

public interface IBlackListRepository extends CrudRepository<BlackListEntity, Integer> {

}