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
@Table(name="facility_report_detail")
public class FacilityReportDetail implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToOne
    @JoinColumn(name = "report_id")
    private FacilityReport facilityReport;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Column(name = "comments")
    private String comments;

    @Column(name = "created_date")
    private String createdDate;

    @Column(name = "last_modification_date")
    private String lastModificationDate;
}