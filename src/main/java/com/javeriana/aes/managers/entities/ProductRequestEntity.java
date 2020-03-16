package com.javeriana.aes.managers.entities;

import javax.persistence.*;

@Entity
@Table(name = "PRODUCT_REQUEST")
    public class ProductRequestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @JoinColumn(name = "CLIENT", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private ClientEntity client;

    @JoinColumn(name = "PRODUCT_TYPE", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private ProductTypeEntity productType;

    @Column(name = "IS_ACCEPTED", nullable = false)
    private boolean isAccepted;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ClientEntity getClient() {
        return client;
    }

    public void setClient(ClientEntity client) {
        this.client = client;
    }

    public ProductTypeEntity getProductType() {
        return productType;
    }

    public void setProductType(ProductTypeEntity productType) {
        this.productType = productType;
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    public void setAccepted(boolean accepted) {
        isAccepted = accepted;
    }
}
