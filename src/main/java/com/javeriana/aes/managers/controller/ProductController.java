package com.javeriana.aes.managers.controller;

import com.javeriana.aes.managers.dto.ProductDto;
import com.javeriana.aes.managers.dto.RequestProductDto;
import com.javeriana.aes.managers.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    private static final int SAVING_ACCOUNT = 1;
    private static final int CDT = 2;

    private IProductService productService;

    @GetMapping("/saving-account/{identificationNumber}")
    public ProductDto getSavingAccount(@PathVariable String identificationNumber) {
        return productService.getProductByClientAndProductType(identificationNumber, SAVING_ACCOUNT);
    }

    @GetMapping("/cdt-account/{identificationNumber}")
    public ProductDto getCdtAccount(@PathVariable String identificationNumber) {
        return productService.getProductByClientAndProductType(identificationNumber, CDT);
    }

    @PostMapping("/account")
    public ResponseEntity<Void> createSavingAccount(@RequestBody ProductDto productDto) {
        productService.createProduct(productDto);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PostMapping("/request")
    public RequestProductDto createCdtAccount(@RequestBody RequestProductDto requestProductDto) {
        return productService.createRequestProduct(requestProductDto);
    }

    @Autowired
    public void setProductService(IProductService productService) {
        this.productService = productService;
    }
}
