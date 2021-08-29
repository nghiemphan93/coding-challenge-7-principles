package com.example.codingchallenge7principles.address.dto;

import lombok.Data;

import java.util.Date;

@Data
public abstract class AddressDto {
    private String firstName;
    private String lastName;
    private Date birthday;
    private String street;
    private String houseNumber;
    private String postalCode;
    private String city;
    private String phone;
    private String mobile;
    private String email;
    private boolean newsletter;
}
