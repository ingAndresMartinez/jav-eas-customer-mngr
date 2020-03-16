package com.javeriana.aes.managers.mappers;

import com.javeriana.aes.managers.dto.CustomerDto;
import com.javeriana.aes.managers.dto.PhoneNumberDto;
import com.javeriana.aes.managers.entities.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CustomerMapper {

    public static ClientEntity customerMapperInClient(CustomerDto customerDto) {
        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setIdentificationNumber(customerDto.getIdentificationNumber());
        IdentificationTypeEntity identificationTypeEntity = new IdentificationTypeEntity();
        identificationTypeEntity.setId(customerDto.getIdentificationType());
        clientEntity.setIdentificationType(identificationTypeEntity);
        clientEntity.setFirstName(customerDto.getFirstName());
        clientEntity.setLastName(customerDto.getLastName());
        GenderTypeEntity genderTypeEntity = new GenderTypeEntity();
        genderTypeEntity.setId(customerDto.getGenderType());
        clientEntity.setGenderType(genderTypeEntity);
        clientEntity.setDateBirth(customerDto.getDateBirth());
        clientEntity.setEmail(customerDto.getEmail());
        return clientEntity;
    }

    public static Set<ClientContactEntity> customerMapperInClientContact(CustomerDto customerDto) {
        Set<ClientContactEntity> contacts = new HashSet<>();
        customerDto.getContact().forEach(phone -> {
            ClientContactEntity c = new ClientContactEntity();
            PhoneTypeEntity phoneTypeEntity = new PhoneTypeEntity();
            phoneTypeEntity.setId(phone.getType());
            c.setPhoneType(phoneTypeEntity);
            c.setPhone(phone.getNumber());
            contacts.add(c);
        });
        return contacts;
    }

    public static CustomerDto clientMapperInCustomer(ClientEntity clientEntity, Set<ClientContactEntity> contactEntity) {
        CustomerDto customerDto = new CustomerDto();
        if(clientEntity.getId() == 0)
            return customerDto;
        customerDto.setIdentificationNumber(clientEntity.getIdentificationNumber());
        customerDto.setIdentificationType(clientEntity.getIdentificationType().getId());
        customerDto.setFirstName(clientEntity.getFirstName());
        customerDto.setLastName(clientEntity.getLastName());
        customerDto.setGenderType(clientEntity.getGenderType().getId());
        customerDto.setDateBirth(clientEntity.getDateBirth());
        customerDto.setEmail(clientEntity.getEmail());
        List<PhoneNumberDto> phones = new ArrayList<>();
        contactEntity.forEach(contact -> {
            PhoneNumberDto phoneNumberDto = new PhoneNumberDto();
            phoneNumberDto.setType(contact.getId());
            phoneNumberDto.setNumber(contact.getPhone());
            phones.add(phoneNumberDto);
        });
        customerDto.setContact(phones);
        return customerDto;
    }

}
