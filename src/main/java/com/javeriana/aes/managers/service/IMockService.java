package com.javeriana.aes.managers.service;

import com.itextpdf.text.DocumentException;
import com.javeriana.aes.managers.dto.DocumentDto;

import java.io.FileNotFoundException;

public interface IMockService {

    String generatePdf(DocumentDto documentDto) throws FileNotFoundException, DocumentException;

    boolean validateBackList(String identificationNumber);
}
