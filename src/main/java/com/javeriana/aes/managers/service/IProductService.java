package com.javeriana.aes.managers.service;

import com.javeriana.aes.managers.dto.ProductDto;
import com.javeriana.aes.managers.dto.RequestProductDto;

public interface IProductService {

    ProductDto getProductByClientAndProductType(String identificationNumber, int productType);

    RequestProductDto createRequestProduct(RequestProductDto requestProductDto);

    void createProduct(ProductDto productDto);

}
