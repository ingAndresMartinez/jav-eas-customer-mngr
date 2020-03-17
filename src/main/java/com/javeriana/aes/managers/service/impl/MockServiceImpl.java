package com.javeriana.aes.managers.service.impl;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.javeriana.aes.managers.dto.DocumentDto;
import com.javeriana.aes.managers.service.IMockService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.UUID;

@Service
public class MockServiceImpl implements IMockService {

    private String path;

    @Override
    public String generatePdf(DocumentDto documentDto) throws FileNotFoundException, DocumentException {
        Document document = new Document();
        UUID uuid = UUID.randomUUID();
        String fileName = path.concat("/").concat(uuid.toString()).concat(".pdf");
        PdfWriter.getInstance(document, new FileOutputStream(fileName));
        document.open();
        Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
        //Chunk title = new Chunk("Deceval - Titulo Valor -", font);
        //document.add(title);
        document.add(new Paragraph("Deceval - Titulo Valor -", font));

        StringBuilder content = new StringBuilder("_______________________________".concat("\n"));
        content.append("Cliente: ").append(documentDto.getCustomerDto().getFirstName()).append(documentDto.getCustomerDto().getLastName()).append("\n");
        content.append("Numero Documento: ").append(documentDto.getCustomerDto().getIdentificationNumber()).append("\n");
        content.append("_______________________________").append("\n");
        content.append("Numero Cuenta: ").append(documentDto.getProductDto().getAccountNumber()).append("\n");
        content.append("Monto: ").append(documentDto.getProductDto().getBalance()).append("\n");
        content.append("Plazo: ").append(documentDto.getProductDto().getTerm()).append("\n");
        content.append("Tasa: ").append(documentDto.getProductDto().getRate()).append("\n");
        document.add(new Paragraph(content.toString(), font));
        document.close();
        return fileName;
    }

    @Value("${documents.path}")
    public void setPath(String path) {
        this.path = path;
    }
}