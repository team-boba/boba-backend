package com.beaconfireboba.backend.controller.employee;

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
    private AmazonService

    @PostMapping(value="/uploadImage")
    public String uploadImage(@RequestPart(value="image") MultipartFile file) {

    }
}
