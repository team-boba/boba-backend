package com.beaconfireboba.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name="contact")
public class Contact implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @Column(name = "relationship")
    private String relationship;

    @Column(name = "title")
    private String title;

    @Column(name = "is_reference")
    private boolean isReference;

    @Column(name = "is_emergency")
    private boolean isEmergency;

    @Column(name = "is_landlord")
    private boolean isLandlord;

    @JsonIgnore
    @OneToMany(fetch=FetchType.LAZY, mappedBy = "contact")
    private List<House> houses = new ArrayList<>();
}
