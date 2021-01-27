package com.beaconfireboba.backend.domain.s3bucket;

import com.beaconfireboba.backend.domain.common.ServiceStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class S3Response {
    private ServiceStatus serviceStatus;

    private String fileUrl;
}
