package com.javeriana.aes.managers.mappers;

import com.javeriana.aes.managers.dto.ProductDto;
import com.javeriana.aes.managers.dto.RequestProductDto;
import com.javeriana.aes.managers.entities.*;

public class ProductMapper {

    public static ProductDto productMapperInProductDto(ProductEntity productEntity) {
        ProductDto productDto = new ProductDto();
        productDto.setRequestId(productEntity.getProductRequest().getId());
        productDto.setAccountNumber(productEntity.getAccountNumber());
        productDto.setBalance(productEntity.getBalance());
        productDto.setProductType(productEntity.getProductRequest().getProductType().getId());
        productDto.setIdentificationNumber(productEntity.getProductRequest().getClient().getIdentificationNumber());
        productDto.setIdentificationType(productEntity.getProductRequest().getClient().getIdentificationType().getId());
        return productDto;
    }

    public static ProductEntity productDtoMapperInProduct(ProductDto productDto, ProductRequestEntity productRequest) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setAccountNumber(productDto.getAccountNumber());
        productEntity.setBalance(productDto.getBalance());
        productEntity.setProductRequest(productRequest);
        return productEntity;
    }

    public static ProductRequestEntity productRequestDtoMapperInProductRequest(RequestProductDto requestProductDto, ClientEntity clientEntity) {
        ProductRequestEntity productRequestEntity = new ProductRequestEntity();
        productRequestEntity.setAccepted(false);
        productRequestEntity.setClient(clientEntity);
        ProductTypeEntity productTypeEntity = new ProductTypeEntity();
        productTypeEntity.setId(requestProductDto.getProductType());
        productRequestEntity.setProductType(productTypeEntity);
        return productRequestEntity;
    }

}
