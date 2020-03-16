package com.javeriana.aes.managers.service;


import com.javeriana.aes.managers.dto.CustomerDto;

public interface ICustomerService {

    CustomerDto getByIdentificationNumber(String identificationNumber);

    void createClient(CustomerDto client);

}
