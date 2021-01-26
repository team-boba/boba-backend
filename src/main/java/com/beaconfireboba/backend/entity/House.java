package com.beaconfireboba.backend.entity;

import lombok.*;

import javax.persistence.*;
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
@Table(name="house")
public class House implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "contact_id")
    private Contact contact;

    @OneToOne
    @JoinColumn(name = "address")
    private Address address;

    @Column(name = "number_of_person")
    private int numberOfPerson;

    @OneToMany(fetch=FetchType.LAZY, mappedBy = "house")
    private List<Employee> employees = new ArrayList<>();

    @OneToOne(fetch=FetchType.LAZY, mappedBy = "house")
    private Facility facility;
}
