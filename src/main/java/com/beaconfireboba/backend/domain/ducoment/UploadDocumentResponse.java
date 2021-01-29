package com.beaconfireboba.backend.domain.ducoment;


import com.beaconfireboba.backend.domain.common.ServiceStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UploadDocumentResponse {
    private UploadDocumentRequest documentRequest;
    private ServiceStatus serviceStatus;
}
