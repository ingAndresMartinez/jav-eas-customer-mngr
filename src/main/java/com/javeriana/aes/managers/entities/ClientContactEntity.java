package com.javeriana.aes.managers.entities;

import javax.persistence.*;

@Entity
@Table(name = "CLIENT_CONTACT")
public class ClientContactEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @JoinColumn(name = "CLIENT", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private ClientEntity client;

    @JoinColumn(name = "PHONE_TYPE", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private PhoneTypeEntity phoneType;

    @Column(name = "PHONE", nullable = false)
    private String phone;

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

    public PhoneTypeEntity getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(PhoneTypeEntity phoneType) {
        this.phoneType = phoneType;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
