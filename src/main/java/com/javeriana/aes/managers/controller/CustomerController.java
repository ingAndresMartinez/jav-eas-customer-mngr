package com.javeriana.aes.managers.controller;

import com.javeriana.aes.managers.dto.CustomerDto;
import com.javeriana.aes.managers.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private ICustomerService customerService;

    @GetMapping("/{identificationNumber}")
    public CustomerDto getClient(@PathVariable String identificationNumber) {
        return customerService.getByIdentificationNumber(identificationNumber);
    }

    @PostMapping
    public ResponseEntity<Void> createClient(@RequestBody CustomerDto customerDto) {
        customerService.createClient(customerDto);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @Autowired
    public void setCustomerService(ICustomerService customerService) {
        this.customerService = customerService;
    }
}
