package com.javeriana.aes.managers.entities;

import javax.persistence.*;

@Entity
@Table(name = "PRODUCT")
    public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @JoinColumn(name = "PRODUCT_REQUEST", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private ProductRequestEntity productRequest;

    @Column(name = "ACCOUNT_NUMBER", nullable = false)
    private String accountNumber;

    @Column(name = "BALANCE", nullable = false)
    private double balance;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ProductRequestEntity getProductRequest() {
        return productRequest;
    }

    public void setProductRequest(ProductRequestEntity productRequest) {
        this.productRequest = productRequest;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
