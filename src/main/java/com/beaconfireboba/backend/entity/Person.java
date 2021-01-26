package com.beaconfireboba.backend.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name="person")
public class Person implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "email")
    private String email;

    @Column(name = "cell_phone")
    private String cellphone;

    @Column(name = "alternate_phone")
    private String alternatePhone;

    @Column(name = "gender")
    private String gender;

    @Column(name = "ssn")
    private String SSN;

    @Column(name = "dob")
    private String DOB;

    @Column(name = "user_id")
    private int userId;

    @OneToMany(fetch=FetchType.LAZY, mappedBy = "person")
    private List<Contact> contacts = new ArrayList<>();

    @OneToOne(fetch=FetchType.LAZY, mappedBy = "person")
    private Address address;

    @OneToOne(fetch=FetchType.LAZY, mappedBy = "person")
    private Employee employee;
}
