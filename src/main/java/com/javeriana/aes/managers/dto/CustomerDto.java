package com.javeriana.aes.managers.dto;

import java.util.Date;
import java.util.List;

public class CustomerDto extends identificationDto {

    private String firstName;
    private String lastName;
    private int genderType;
    private String email;
    private Date dateBirth;
    private List<PhoneNumberDto> contact;

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

    public int getGenderType() {
        return genderType;
    }

    public void setGenderType(int genderType) {
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

    public List<PhoneNumberDto> getContact() {
        return contact;
    }

    public void setContact(List<PhoneNumberDto> contact) {
        this.contact = contact;
    }

}
