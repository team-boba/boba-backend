package com.beaconfireboba.backend.domain.ducoment;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UploadDocumentRequest {

    private int employeeID;
    private String path;
    private String title;
    private String comment;
    private String createBy;

}
