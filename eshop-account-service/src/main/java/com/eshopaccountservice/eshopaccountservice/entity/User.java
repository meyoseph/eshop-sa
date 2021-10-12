package com.eshopaccountservice.eshopaccountservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@Getter
@Setter
@SecondaryTable(name = "accounts")
public class User {

    public User(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(table = "accounts", unique = true)
    private String email;

    @Column(table = "accounts")
    private String password;

    private String firstName;

    private String lastName;

    @Transient
    private String address;
}