package com.javeriana.aes.managers.service.impl;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.javeriana.aes.managers.dto.DocumentDto;
import com.javeriana.aes.managers.entities.CdtProductEntity;
import com.javeriana.aes.managers.entities.ClientEntity;
import com.javeriana.aes.managers.entities.ProductEntity;
import com.javeriana.aes.managers.repositories.ICdtProductRepository;
import com.javeriana.aes.managers.repositories.IClientRepository;
import com.javeriana.aes.managers.repositories.IProductRepository;
import com.javeriana.aes.managers.service.IMockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class MockServiceImpl implements IMockService {

    private String path;
    private String s3MainBucket;
    private String s3Folder;

    private String accessKey;
    private String secretKey;

    private static final Logger LOGGER = LoggerFactory.getLogger(MockServiceImpl.class);

    private IClientRepository clientRepository;
    private IProductRepository productRepository;
    private ICdtProductRepository cdtProductRepository;

    @Override
    public String generatePdf(DocumentDto documentDto) throws FileNotFoundException, DocumentException {

        ClientEntity clientEntity = clientRepository.findByIdentificationNumber(documentDto.getIdentificationNumber()).orElseThrow(IllegalArgumentException::new);
        ProductEntity productEntity = productRepository.findByRequestId(documentDto.getRequestId()).orElseThrow(IllegalArgumentException::new);
        CdtProductEntity cdtProductEntity = cdtProductRepository.findByProductId(productEntity.getId()).orElseThrow(IllegalArgumentException::new);

        Document document = new Document();
        UUID uuid = UUID.randomUUID();
        String fileName = path.concat("/").concat(uuid.toString()).concat(".pdf");
        PdfWriter.getInstance(document, new FileOutputStream(fileName));
        document.open();
        Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
        document.add(new Paragraph("Deceval - Titulo Valor -", font));

        StringBuilder content = new StringBuilder("_______________________________".concat("\n"));
        content.append("Cliente: ").append(clientEntity.getFirstName()).append(clientEntity.getLastName()).append("\n");
        content.append("Numero Documento: ").append(clientEntity.getIdentificationNumber()).append("\n");
        content.append("_______________________________").append("\n");
        content.append("Numero Cuenta: ").append(productEntity.getAccountNumber()).append("\n");
        content.append("Monto: ").append(productEntity.getBalance()).append("\n");
        content.append("Plazo: ").append(cdtProductEntity.getTerm()).append("\n");
        content.append("Tasa: ").append(cdtProductEntity.getRate()).append("\n");
        document.add(new Paragraph(content.toString(), font));
        document.close();
        setDocumentInS3(clientEntity.getIdentificationNumber(), fileName);
        return fileName;
    }

    private void setDocumentInS3(String identificationNumber, String fileName) {
        File file = new File(fileName);
        long contentLength = file.length();
        long partSize = 5 * 1024 * 1024;
        try {
            AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
            AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                    .withCredentials(new AWSStaticCredentialsProvider(credentials))
                    .withRegion(Regions.US_EAST_1)
                    .build();
            List<PartETag> partETags = new ArrayList<>();
            String keyName = s3Folder.concat(String.valueOf(identificationNumber).concat("/").concat(file.getName()));
            InitiateMultipartUploadRequest initRequest = new InitiateMultipartUploadRequest(s3MainBucket, keyName);
            InitiateMultipartUploadResult initResponse = s3Client.initiateMultipartUpload(initRequest);
            long filePosition = 0;
            for (int i = 1; filePosition < contentLength; i++) {
                partSize = Math.min(partSize, (contentLength - filePosition));
                UploadPartRequest uploadRequest = new UploadPartRequest()
                        .withBucketName(s3MainBucket)
                        .withKey(keyName)
                        .withUploadId(initResponse.getUploadId())
                        .withPartNumber(i)
                        .withFileOffset(filePosition)
                        .withFile(file)
                        .withPartSize(partSize);
                UploadPartResult uploadResult = s3Client.uploadPart(uploadRequest);
                partETags.add(uploadResult.getPartETag());
                filePosition += partSize;
            }
            CompleteMultipartUploadRequest compRequest = new CompleteMultipartUploadRequest(s3MainBucket, keyName,
                    initResponse.getUploadId(), partETags);
            s3Client.completeMultipartUpload(compRequest);
        } catch (AmazonServiceException e) {
            LOGGER.error("Error.", e);
        } catch (SdkClientException e) {
            LOGGER.error("Error.", e);
        }
    }

    @Value("${documents.path}")
    public void setPath(String path) {
        this.path = path;
    }

    @Value("${s3.main.bucket}")
    public void setS3MainBucket(String s3MainBucket) {
        this.s3MainBucket = s3MainBucket;
    }

    @Value("${s3.documents.folder}")
    public void setS3Folder(String s3Folder) {
        this.s3Folder = s3Folder;
    }

    @Value("${aws.accessKeyId}")
    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    @Value("${aws.secretKey}")
    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    @Autowired
    public void setClientRepository(IClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Autowired
    public void setProductRepository(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Autowired
    public void setCdtProductRepository(ICdtProductRepository cdtProductRepository) {
        this.cdtProductRepository = cdtProductRepository;
    }
}