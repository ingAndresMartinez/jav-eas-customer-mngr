package com.javeriana.aes.managers.service.impl;

import com.javeriana.aes.managers.dto.CustomerDto;
import com.javeriana.aes.managers.entities.ClientContactEntity;
import com.javeriana.aes.managers.entities.ClientEntity;
import com.javeriana.aes.managers.mappers.CustomerMapper;
import com.javeriana.aes.managers.repositories.IClientContactRepository;
import com.javeriana.aes.managers.repositories.IClientRepository;
import com.javeriana.aes.managers.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CustomerServiceImpl implements ICustomerService {

    private IClientRepository clientRepository;
    private IClientContactRepository clientContactRepository;

    @Override
    public CustomerDto getByIdentificationNumber(String identificationNumber) {
        ClientEntity clientEntity = clientRepository.findByIdentificationNumber(identificationNumber).orElse(new ClientEntity());
        Set<ClientContactEntity> clientContactEntity = clientContactRepository.findByIdentificationNumber(identificationNumber);
        return CustomerMapper.clientMapperInCustomer(clientEntity, clientContactEntity);
    }

    @Override
    public void createClient(CustomerDto client) {
        ClientEntity clientEntity = CustomerMapper.customerMapperInClient(client);
        clientRepository.save(clientEntity);
        Set<ClientContactEntity> contacts = CustomerMapper.customerMapperInClientContact(client);
        contacts.forEach(phone -> {
            phone.setClient(clientEntity);
            clientContactRepository.save(phone);
        });
    }

    @Autowired
    public void setClientRepository(IClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Autowired
    public void setClientContactRepository(IClientContactRepository clientContactRepository) {
        this.clientContactRepository = clientContactRepository;
    }
}
