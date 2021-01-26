package com.beaconfireboba.backend.controller;

import com.beaconfireboba.backend.domain.S3Response;
import com.beaconfireboba.backend.domain.common.ServiceStatus;
import com.beaconfireboba.backend.service.AmazonS3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value="/bucket")
public class BucketController {
    @Autowired
    private AmazonS3Service amazonS3Service;

    @PostMapping(value="/upload")
    public S3Response uploadImage(@RequestPart(value="file") MultipartFile file) {
        String uploadFileUrl = this.amazonS3Service.uploadFile(file);
        S3Response s3Response = new S3Response();
        s3Response.setFileUrl(uploadFileUrl);
        prepareS3Response(s3Response, true, "");

        return s3Response;
    }

    private void prepareS3Response(S3Response response, boolean success, String errorMessage) {
        response.setServiceStatus(new ServiceStatus(success ? "SUCCESS" : "FAILED", success, errorMessage));
    }
}
