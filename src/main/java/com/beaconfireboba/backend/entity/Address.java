package com.beaconfireboba.backend.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name="address")
public class Address implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "address_line_1")
    private String addressLineOne;

    @Column(name = "address_line_2")
    private String addressLineTwo;

    @Column(name = "city")
    private String city;

    @Column(name = "zipcode")
    private String zipcode;

    @Column(name = "state_name")
    private String stateName;

    @Column(name = "state_abbr")
    private String stateAbbr;

    @OneToOne
    @JoinColumn(name = "person_id")
    private Person person;
}
