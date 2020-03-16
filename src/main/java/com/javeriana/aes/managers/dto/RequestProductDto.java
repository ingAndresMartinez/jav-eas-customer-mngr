package com.javeriana.aes.managers.dto;

public class RequestProductDto extends identificationDto {

    private int id;
    private int productType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductType() {
        return productType;
    }

    public void setProductType(int productType) {
        this.productType = productType;
    }
}
