package com.javeriana.aes.managers.controller;

import com.itextpdf.text.*;

import com.javeriana.aes.managers.dto.DocumentDto;
import com.javeriana.aes.managers.service.IMockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;

@RestController
@RequestMapping("/mocks")
public class MockController {

    private IMockService mockService;

    @PostMapping(value = "/deceval")
    public String createClient(@RequestBody DocumentDto documentDto) throws FileNotFoundException, DocumentException {
        return mockService.generatePdf(documentDto);
    }

    @Autowired
    public void setMockService(IMockService mockService) {
        this.mockService = mockService;
    }
}
