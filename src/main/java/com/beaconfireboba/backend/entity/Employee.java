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
@Table(name="employee")
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @Column(name = "title")
    private String title;

    @Column(name = "start_date")
    private String startDate;

    @Column(name = "end_date")
    private String endDate;

    @Column(name = "avartar")
    private String avatar;

    @Column(name = "car")
    private String car;

    @ManyToOne
    @JoinColumn(name = "visa_status_id")
    private VisaStatus visaStatus;

    @Column(name = "visa_start_date")
    private String visaStartDate;

    @Column(name = "visa_end_date")
    private String visaEndDate;

    @Column(name = "driver_lisence")
    private String driverLicense;

    @Column(name = "driver_lisence_expiration_date")
    private String driverLicenseExpirationDate;

    @ManyToOne
    @JoinColumn(name = "house_id")
    private House house;

    @OneToMany(fetch=FetchType.LAZY, mappedBy = "employee")
    private List<PersonalDocument> personalDocuments;

    @OneToOne(mappedBy = "employee")
    private ApplicationWorkflow applicationWorkflow;

    @OneToMany(fetch=FetchType.LAZY, mappedBy = "employee")
    private List<FacilityReport> facilityReports = new ArrayList<>();

    @OneToMany(fetch=FetchType.LAZY, mappedBy = "employee")
    private List<FacilityReportDetail> facilityReportDetails = new ArrayList<>();
}
