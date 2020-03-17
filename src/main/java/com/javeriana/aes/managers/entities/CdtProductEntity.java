package com.javeriana.aes.managers.entities;

import javax.persistence.*;

@Entity
@Table(name = "CDT_PRODUCT")
public class CdtProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @JoinColumn(name = "PRODUCT", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private ProductEntity product;

    @Column(name = "TERM")
    private int term;

    @Column(name = "RATE")
    private double rate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}
