package com.javeriana.aes.managers.service.impl;

import com.javeriana.aes.managers.dto.ProductDto;
import com.javeriana.aes.managers.dto.RequestProductDto;
import com.javeriana.aes.managers.entities.CdtProductEntity;
import com.javeriana.aes.managers.entities.ClientEntity;
import com.javeriana.aes.managers.entities.ProductEntity;
import com.javeriana.aes.managers.entities.ProductRequestEntity;
import com.javeriana.aes.managers.mappers.ProductMapper;
import com.javeriana.aes.managers.repositories.ICdtProductRepository;
import com.javeriana.aes.managers.repositories.IClientRepository;
import com.javeriana.aes.managers.repositories.IProductRepository;
import com.javeriana.aes.managers.repositories.IProductRequestRepository;
import com.javeriana.aes.managers.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;


@Service
public class ProductServiceImpl implements IProductService {

    private static final int SAVING_ACCOUNT = 1;
    private static final int CDT = 2;

    private IClientRepository clientRepository;
    private IProductRequestRepository productRequestRepository;
    private IProductRepository productRepository;
    private ICdtProductRepository cdtProductRepository;

    @Override
    public ProductDto getProductByClientAndProductType(String identificationNumber, int productType) {
        try {
            ProductEntity productEntity = productRepository.findByProductTypeAndClientIdentification(identificationNumber, productType).orElseThrow(IllegalArgumentException::new);
            return ProductMapper.productMapperInProductDto(productEntity);
        } catch (Exception e) {
            return new ProductDto();
        }
    }

    @Override
    public RequestProductDto createRequestProduct(RequestProductDto requestProductDto) {
        ClientEntity clientEntity = clientRepository.findByIdentificationNumber(requestProductDto.getIdentificationNumber()).orElseThrow(IllegalArgumentException::new);
        ProductRequestEntity productRequestEntity = ProductMapper.productRequestDtoMapperInProductRequest(requestProductDto, clientEntity);
        productRequestRepository.save(productRequestEntity);
        requestProductDto.setId(productRequestEntity.getId());
        return requestProductDto;
    }

    @Override
    public void createProduct(ProductDto productDto) {
        ProductRequestEntity productRequestEntity = productRequestRepository.findById(productDto.getRequestId()).orElseThrow(IllegalArgumentException::new);
        Random r2 = new Random();
        int accountNumber = r2.nextInt(10000);
        productDto.setAccountNumber(String.format("A%06d", accountNumber));
        ProductEntity newProduct = ProductMapper.productDtoMapperInProduct(productDto, productRequestEntity);
        productRepository.save(newProduct);
        productRequestEntity.setAccepted(true);
        productRequestRepository.save(productRequestEntity);
        if (productDto.getProductType() == CDT) {
            CdtProductEntity cdtProductEntity = ProductMapper.productRequestDtoMapperInCdtProduct(productDto, newProduct);
            cdtProductRepository.save(cdtProductEntity);
            ProductEntity productEntity = productRepository.findByProductTypeAndClientIdentification(productDto.getIdentificationNumber(), SAVING_ACCOUNT).orElseThrow(IllegalArgumentException::new);
            productEntity.setBalance(productEntity.getBalance() - productDto.getBalance());
            productRepository.save(productEntity);
        }
    }

    @Autowired
    public void setClientRepository(IClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Autowired
    public void setProductRequestRepository(IProductRequestRepository productRequestRepository) {
        this.productRequestRepository = productRequestRepository;
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
