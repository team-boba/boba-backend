package com.beaconfireboba.backend.controller;

import com.beaconfireboba.backend.domain.s3bucket.S3FileNameRequest;
import com.beaconfireboba.backend.domain.s3bucket.S3Response;
import com.beaconfireboba.backend.domain.common.ServiceStatus;
import com.beaconfireboba.backend.service.AmazonS3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.bind.annotation.*;
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

    @PostMapping(value= "/download")
    public ByteArrayResource downloadFile(@RequestBody S3FileNameRequest s3FileNameRequest) {
        String fileName = s3FileNameRequest.getFileName();
        final byte[] data = amazonS3Service.downloadFile(fileName);
        final ByteArrayResource resource = new ByteArrayResource(data);
        return resource;
    }

    private void prepareS3Response(S3Response response, boolean success, String errorMessage) {
        response.setServiceStatus(new ServiceStatus(success ? "SUCCESS" : "FAILED", success, errorMessage));
    }
}
