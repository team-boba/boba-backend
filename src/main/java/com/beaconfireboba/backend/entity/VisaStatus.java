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
@Table(name="visa_status")
public class VisaStatus implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "visa_type")
    private String visaType;

    @Column(name = "active")
    private boolean active;

    @Column(name = "modification_date")
    private String modificationDate;

    @Column(name = "create_user")
    private String createdUser;

    @JsonIgnore
    @OneToMany(fetch=FetchType.LAZY, mappedBy = "visaStatus")
    private List<Employee> employees = new ArrayList<>();
}
