package com.javeriana.aes.managers.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "CLIENT")
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "IDENTIFICATION_NUMBER", nullable = false)
    private String identificationNumber;

    @JoinColumn(name = "IDENTIFICATION_TYPE", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private IdentificationTypeEntity identificationType;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @JoinColumn(name = "GENDER_TYPE", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private GenderTypeEntity genderType;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "DATE_BIRTH", nullable = false)
    private Date dateBirth;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public IdentificationTypeEntity getIdentificationType() {
        return identificationType;
    }

    public void setIdentificationType(IdentificationTypeEntity identificationType) {
        this.identificationType = identificationType;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public GenderTypeEntity getGenderType() {
        return genderType;
    }

    public void setGenderType(GenderTypeEntity genderType) {
        this.genderType = genderType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(Date dateBirth) {
        this.dateBirth = dateBirth;
    }
}
