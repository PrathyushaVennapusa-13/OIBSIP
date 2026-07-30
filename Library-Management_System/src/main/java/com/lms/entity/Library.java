package com.lms.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Library {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "library_id_generator")
    @SequenceGenerator(
            name = "library_id_generator",
            initialValue = 501,
            allocationSize = 1)
    private Integer libraryId;

    private String libraryName;

    @Column(unique = true)
    private long phoneNumber;

    @OneToMany(mappedBy="library")
    private List<User> users;

    @OneToMany(mappedBy="library")
    private List<Book> books;

    @OneToOne
    @JoinColumn(name="address_Id")
    private Address address;
}
