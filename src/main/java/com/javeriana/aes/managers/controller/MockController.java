package com.javeriana.aes.managers.controller;

import com.itextpdf.text.*;

import com.javeriana.aes.managers.dto.BlackListResponseDto;
import com.javeriana.aes.managers.dto.DocumentDto;
import com.javeriana.aes.managers.service.IMockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;

@RestController
@RequestMapping("/mocks")
public class MockController {

    private IMockService mockService;

    @PostMapping(value = "/deceval")
    public String createClient(@RequestBody DocumentDto documentDto) throws FileNotFoundException, DocumentException {
        return mockService.generatePdf(documentDto);
    }

    @GetMapping("/black-list/{identificationNumber}")
    public BlackListResponseDto createClient(@PathVariable String identificationNumber) {
        return mockService.validateBackList(identificationNumber);
    }

    @Autowired
    public void setMockService(IMockService mockService) {
        this.mockService = mockService;
    }
}
