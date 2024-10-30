package com.example.contacts.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ContactDTO {
    private String firstName;
    private String lastName;
    private String mail;
    private int phone;
    private String address;

    public ContactDTO(){}
}
