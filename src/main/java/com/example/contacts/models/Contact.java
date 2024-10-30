package com.example.contacts.models;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "contacts")
@Getter @Setter
public class Contact {
    @Id
    @Column(name ="contact_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long contactId;
    @Column(name="first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "mail")
    private String mail;
    @Column(name="phone")
    private int phone;
    @Column(name="address")
    private String address;

    public Contact(){}
    public Contact(String firstName, String lastName, String mail, int phone, String address ){
        this.firstName = firstName;
        this.lastName = lastName;
        this.mail = mail;
        this.phone = phone;
        this.address = address;
    }

}
