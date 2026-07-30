package com.lms.entity;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator.class,
    property = "addressId"
)
@Entity
@Getter
@Setter
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "address_id_generator")
    @SequenceGenerator(
            name = "address_id_generator",
            initialValue = 401,
            allocationSize = 1)
    private Integer addressId;

    private String houseNumber;
    private String area;
    private String city;
    private String state;
    private String country;
    private int pincode;

    @OneToOne(mappedBy="address")
    private User user;

    @OneToOne(mappedBy="address")
    private Library library;
}